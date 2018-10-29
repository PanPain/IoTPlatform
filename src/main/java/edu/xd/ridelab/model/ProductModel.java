package edu.xd.ridelab.model;

import com.alibaba.fastjson.JSON;
import edu.xd.ridelab.vo.DeviceModelVO;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductModel.java
 * @date 2018/8/14
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class ProductModel {
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
    private List<DeviceModelVO> deviceModel;

    public ProductModel() {
    }

    public ProductModel(String productName, String productManufacturer, String productCode, String productDesc, List<DeviceModelVO> deviceModel) {
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.deviceModel = deviceModel;
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

    public List<DeviceModelVO> getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(List<DeviceModelVO> deviceModel) {
        this.deviceModel = deviceModel;
    }
}
