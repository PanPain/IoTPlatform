package edu.xd.ridelab.controller.receiver;

/**
 * @Author zjh
 * @Date 2018/11/09,10:51
 */
public enum ReceiverCode {
    OPEN_RECEIVER_SUCCESS("05001", "接收程序启动成功"),
    OPEN_RECEIVER_FAILURE("05002", "接收程序启动失败"),
    CLOSE_RECEIVER_SUCCESS("05003", "接收程序关闭成功"),
    CLOSE_RECEIVER_FAILURE("03002", "接收程序关闭失败");

    private String code;
    private String message;

    ReceiverCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
