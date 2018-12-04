package edu.xd.ridelab.controller.receiver;

import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.service.receiver.ReceiverService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/receiver")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiverController {

  private final ReceiverService receiverService;

  private static List openedProducts = new ArrayList();  // 记录开启接收的产品

  @ApiParam(name = "id", value = "Product ID")
  @PostMapping("/{id}/action/start")
  public ResponseResult start(@PathVariable Long id) {

    // TODO: get port from configurations
    val port = (int)(50000 + id);

    ResponseResult responseResult = new ResponseResult();

    openedProducts.add(id);
    Consumer<ReceiverService.DataPacket> handler = new Consumer<ReceiverService.DataPacket>() {
      @Override
      public void accept(ReceiverService.DataPacket packet) {

        try {
          receiverService.handle(packet);

          responseResult.setSuccess(true);
          responseResult.setCode(ReceiverCode.OPEN_RECEIVER_SUCCESS.getCode());
          responseResult.setMessage(ReceiverCode.OPEN_RECEIVER_SUCCESS.getMessage());

        } catch (Exception e) {

          responseResult.setSuccess(false);
          responseResult.setCode(ReceiverCode.OPEN_RECEIVER_FAILURE.getCode());
          responseResult.setMessage(ReceiverCode.OPEN_RECEIVER_FAILURE.getMessage());

          e.printStackTrace();
        }
        // TODO: write to several databases
        // if (config.shouldSaveNewestToMysql) deviceService.save(...);
        // if (config.shouldSaveHistoryToMysql) deviceHistoryService.save(...);
        // if (config.shouldSaveHistoryToHBase) deviceHistoryService.save(...);
        logger.debug("Received data for product {}: {}", id, packet);
      }
    };

    receiverService.start(id, port, handler);

    return responseResult;
  }

  @ApiParam(name = "id", value = "Product ID")
  @PostMapping("/{id}/action/stop")
  public ResponseResult stop(@PathVariable Long id) {
    openedProducts.remove(id);

    receiverService.stop(id);

    ResponseResult responseResult = new ResponseResult();

    responseResult.setSuccess(true);
    responseResult.setCode(ReceiverCode.CLOSE_RECEIVER_SUCCESS.getCode());
    responseResult.setMessage(ReceiverCode.CLOSE_RECEIVER_SUCCESS.getMessage());

    return responseResult;
  }

  @ApiParam(name = "id", value = "Product ID")
  @GetMapping("/{id}/running")
  public Boolean running(@PathVariable Long id) {
    return receiverService.isRunning(id);
  }

}
