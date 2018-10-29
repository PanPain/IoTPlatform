package edu.xd.ridelab.vo;


/**
 * @author PanTeng
 * @version 1.0
 * @file ProductVO.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class ProductVO {
    /**
     * 主键
     */
    private long productId;
    /**
     * 外键
     */
    private long fkUserId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品厂商
     */
    private String productManufacturer;
    /**
     * 产品型号
     */
    private String productCode;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 设备模型   通过“属性”、“服务”、“事件”来描述一类产品（Json格式）
     */
    private String deviceModel;

    public ProductVO() {
    }

    public ProductVO(long productId, long fkUserId, String productName, String productManufacturer, String productCode, String productDesc, String deviceModel) {
        this.productId = productId;
        this.fkUserId = fkUserId;
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.deviceModel = deviceModel;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
