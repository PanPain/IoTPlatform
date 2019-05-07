package edu.xd.ridelab.heartbeat;

import edu.xd.ridelab.service.device.DeviceService;
import edu.xd.ridelab.service.receiver.ReceiverService;
import edu.xd.ridelab.service.receiver.impl.HttpReceiverService;
import edu.xd.ridelab.vo.DeviceVO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author zjh
 * @Date 2019/05/06,21:09
 */
@Component
public class HeartBeatReceiver {

    @Autowired
    DeviceService deviceService;

    @Async
    public void receiveHeartBeat(int port) {
        val bossGroup = new NioEventLoopGroup();
        val workerGroup = new NioEventLoopGroup();

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
                                                val packet = ReceiverService.DataPacket.builder().
                                                        clientAddress(context.channel().remoteAddress()).
                                                        time(Instant.now()).
                                                        data(request.content()).
                                                        build();
                                                //处理方法
                                                accept(packet);

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
        }
    }

    public void accept(ReceiverService.DataPacket packet) {
        val buf = packet.getData();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        //TODO: maybe compress, decrect
        String[] result = new String(bytes).split("&");

        Long productId = Long.parseLong(result[0]); //用&做分隔符，第一项为产品ID

        String deviceIdentifier = result[1];  //第二项为设备标识符
        String dateStr = result[2]; //第三项为设备数据
        Timestamp timestamp = Timestamp.valueOf(dateStr);

        DeviceVO deviceVO = null;
        try {
            deviceVO = deviceService.getDeviceByIndentifierAndProductId(productId, deviceIdentifier);
            deviceVO.setLastConnectTime(timestamp);
            deviceService.updateDevice(deviceVO);
        } catch (NullPointerException e) {
            throw new NullPointerException("no corresponding device");
        } catch (Exception e) {
            throw new NumberFormatException("wrong productId format");
        }
    }
}
