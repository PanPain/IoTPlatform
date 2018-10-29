package edu.xd.ridelab.controller.device;

/**
 * @Author zjh
 * @Date 2018/08/13,21:11
 */
public enum DeviceCode {

    GET_DEVICE_SUCCESS("03001", "查看设备成功"),
    GET_DEVICE_FAILURE("03002", "查看设备失败"),
    ADD_DEVICE_SUCCESS("03003", "添加设备成功"),
    ADD_DEVICE_FAILURE("03004", "添加设备失败"),
    UPDATE_DEVICE_SUCCESS("03005", "更新设备成功"),
    UPDATE_DEVICE_FAILURE("03006", "更新设备失败"),
    DELETE_DEVICE_SUCCESS("03007", "删除设备成功"),
    DELETE_DEVICE_FAILURE("03008", "删除设备失败"),
    GET_DEVICE_DETAIL_SUCCESS("03009", "查看设备详情成功"),
    GET_DEVICE_DETAIL_FAILURE("03010", "查看设备详情失败")
    ;

    private String code;
    private String message;

    DeviceCode(String code, String message) {
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
