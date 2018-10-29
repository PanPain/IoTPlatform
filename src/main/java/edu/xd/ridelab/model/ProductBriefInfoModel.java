package edu.xd.ridelab.model;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductBriefInfoModel.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class ProductBriefInfoModel {

    /**
     * 产品id
     */
    private int productId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
