package edu.xd.ridelab.model;

import com.alibaba.fastjson.JSON;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductDetailInfoModel.java
 * @date 2018/8/13
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class ProductDetailInfoModel {
    /**
     * 主键
     */
    private long productId;
    /**
     * 外键
     */
    private long userId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品厂商
     */
    private String productManufactuter;
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

    private String productSdk;

    public ProductDetailInfoModel() {
    }

    public ProductDetailInfoModel(long productId, long userId, String productName, String productManufactuter, String productCode, String productDesc, String deviceModel) {
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
        this.productManufactuter = productManufactuter;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufactuter() {
        return productManufactuter;
    }

    public void setProductManufactuter(String productManufactuter) {
        this.productManufactuter = productManufactuter;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getProductSdk() {
        return productSdk;
    }

    public void setProductSdk(String productSdk) {
        this.productSdk = productSdk;
    }
}
