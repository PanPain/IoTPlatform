package edu.xd.ridelab.service.receiver.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class HttpReceiverServiceDebugRunner {

  /**
   * After running this program,
   * exec `curl -v 'localhost:9999' -X POST -H 'Content-Type: application/json' -d '{"key":"value"}'`
   */
  public static void main(String[] args) {
    val port = 9999;
    val id = 10010L;
    val receiverService = new HttpReceiverService();
    receiverService.start(id, port, packet -> {
      logger.info("[id={}] {}", id, packet);
      // write to database
    });
    logger.info("listening at {}", port);
  }

}
