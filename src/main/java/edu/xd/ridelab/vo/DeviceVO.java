package edu.xd.ridelab.vo;

import java.sql.Timestamp;

/**
 * @Author zjh
 * @Date 2018/08/13,09:23
 */
public class DeviceVO {
    private long deviceId;
    private String deviceIdentifier;
    private String deviceLongitude;
    private String deviceLatitude;
    private long productId;
    private String deviceData;
    private Timestamp lastConnectTime;

    public String getDeviceLongitude() {
        return deviceLongitude;
    }

    public void setDeviceLongitude(String deviceLongitude) {
        this.deviceLongitude = deviceLongitude;
    }

    public String getDeviceLatitude() {
        return deviceLatitude;
    }

    public void setDeviceLatitude(String deviceLatitude) {
        this.deviceLatitude = deviceLatitude;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public void setDeviceIdentifier(String deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(String deviceData) {
        this.deviceData = deviceData;
    }

    public Timestamp getLastConnectTime() {
        return lastConnectTime;
    }

    public void setLastConnectTime(Timestamp lastConnectTime) {
        this.lastConnectTime = lastConnectTime;
    }
}
