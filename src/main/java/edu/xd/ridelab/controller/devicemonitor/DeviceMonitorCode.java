package edu.xd.ridelab.controller.devicemonitor;

/**
 * @Author FBY
 * @Version 1.0
 * @File DeviceMonitorCode.java
 * @Date 2018/8/14
 * @Attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public enum DeviceMonitorCode {
    GET_DEVICE_SUCCESS("04001", "设备详细信息查询成功"),
    NO_DEVICE_INFO("04002","无已添加设备"),
    GET_DEVICE_LOCATION_SUCCESS("04003","设备位置信息查询成功"),
    NO_DEVICE_LOCATION("04004","无设备位置信息"),
    GET_DEVICE_STATUS("04005","设备状态信息查询成功"),
    NO_DEVICE_STATUS("04006","无设备状态信息"),
    GET_DEVICE_DATA_SUCCESS("04007","获取设备数据信息成功"),
    ;

    private String code;
    private String message;

    DeviceMonitorCode(String code, String message) {
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
