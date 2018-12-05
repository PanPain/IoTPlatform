package edu.xd.ridelab.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PanTeng
 * @describe 表示一个设备的数据信息，采用 “设备标识符” ： “设备数据”
 *             其中“设备数据”采用 Map形式，将json串中的数据部分解析出来
 * @version 1.0
 * @file DeviceDataModel.java
 * @date 2018/12/5
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class DeviceDataModel {
    private String deviceIdentifier;    //设备标识符
    private Map<String,String> deviceData = new HashMap<>(); //设备数据

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public void setDeviceIdentifier(String deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }

    public Map<String, String> getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(Map<String, String> deviceData) {
        this.deviceData = deviceData;
    }
}
