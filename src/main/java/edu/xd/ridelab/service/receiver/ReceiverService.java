package edu.xd.ridelab.service.receiver;

import io.netty.buffer.ByteBuf;
import lombok.Builder;
import lombok.Value;

import java.net.SocketAddress;
import java.time.Instant;
import java.util.function.Consumer;

public interface ReceiverService {

  @Value
  @Builder
  class DataPacket {
    SocketAddress clientAddress;
    Instant time;
    ByteBuf data;
  }

  void start(Long id, Integer port, Consumer<DataPacket> consumer);

  void stop(Long id);

  boolean isRunning(Long id);

}
