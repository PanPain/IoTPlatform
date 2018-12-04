package edu.xd.ridelab.service.receiver.impl;

import edu.xd.ridelab.service.device.DeviceService;
import edu.xd.ridelab.service.receiver.ReceiverService;
import edu.xd.ridelab.vo.DeviceVO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HttpReceiverService implements ReceiverService {

  @Autowired
  DeviceService deviceService;

  private ExecutorService pool = Executors.newFixedThreadPool(32);

  private Map<Long, Runnable> shutdowns = new HashMap<>();

  @Override
  public synchronized void start(Long id, Integer port, Consumer<DataPacket> consumer) {
    if (shutdowns.containsKey(id)) {
      throw new IllegalStateException("Instance for" + id + " is running");
    }
    shutdowns.put(id, new Runnable() {
      @Override
      public void run() {
        throw new IllegalStateException("Just a placeholder");
      }
    });
    pool.submit(new Runnable() {
      @Override
      public void run() {
        val bossGroup = new NioEventLoopGroup();
        val workerGroup = new NioEventLoopGroup();
        shutdowns.put(id, new Runnable() {
          @Override
          public void run() {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
          }
        });
        try {
          val bootstrap = new ServerBootstrap();
          bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
          bootstrap.group(bossGroup, workerGroup).
                  channel(NioServerSocketChannel.class).
                  childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel channel) {
                      channel.pipeline().
                              addLast(new HttpServerCodec()).
                              addLast(new SimpleChannelInboundHandler<Object>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext context, Object o) {
                                  if (o instanceof HttpRequest) {
                                    val request = (HttpRequest) o;
                                    if (!request.method().equals(HttpMethod.POST)) {
                                      context.write(new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
                                    }
                                    if (HttpUtil.is100ContinueExpected(request)) {
                                      context.write(new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.CONTINUE));
                                    }
                                  }
                                  if (o instanceof LastHttpContent) {
                                    val request = (LastHttpContent) o;
                                    val packet = DataPacket.builder().
                                            clientAddress(context.channel().remoteAddress()).
                                            time(Instant.now()).
                                            data(request.content()).
                                            build();
                                    consumer.accept(packet);
                                    context.writeAndFlush(new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK)).
                                            addListener(ChannelFutureListener.CLOSE);
                                  }
                                }
                              });
                    }
                  });
          bootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          HttpReceiverService.this.stop(id);
        }
      }
    });
    // TODO: return a future
  }

  @Override
  public void handle(DataPacket packet) {
    val buf = packet.getData();
    byte[] bytes = new byte[buf.readableBytes()];
    buf.readBytes(bytes);
    //TODO: maybe compress, decrect
    String[] result = new String(bytes).split("&");

    Long productId = Long.parseLong(result[0]); //用&做分隔符，第一项为产品ID

      String deviceIdentifier = result[1];  //第二项为设备标识符
      String data = result[2]; //第三项为设备数据

      DeviceVO deviceVO = null;
      try {
        deviceVO = deviceService.getDeviceByIndentifierAndProductId(productId, deviceIdentifier);
        deviceVO.setDeviceData(data);
        deviceService.updateDevice(deviceVO);
      } catch (NullPointerException e) {
        throw new NullPointerException("no corresponding device");
      } catch (Exception e) {
        throw new NumberFormatException("wrong productId format");
      }
  }

  @Override
  public synchronized void stop(Long id) {
    val shutdown = shutdowns.get(id);
    if (shutdown != null) {
      // TODO: maybe block
      shutdown.run();
      shutdowns.remove(id);
    }
  }

  @Override
  public boolean isRunning(Long id) {
    return shutdowns.containsKey(id);
  }

}
