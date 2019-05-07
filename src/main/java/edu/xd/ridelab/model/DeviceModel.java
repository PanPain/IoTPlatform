package edu.xd.ridelab.model;

import edu.xd.ridelab.vo.DeviceVO;
import edu.xd.ridelab.vo.ProductVO;

/**
 * @Author FBY
 * @Version 1.0
 * @File DeviceModel.java
 * @Date 2018/8/13
 * @Attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class DeviceModel {
    private DeviceVO device ;
    private boolean status;

    private ProductDetailInfoModel product;

    public ProductDetailInfoModel getProduct() {
        return product;
    }

    public void setProduct(ProductDetailInfoModel product) {
        this.product = product;
    }

    public DeviceVO getDevice() {
        return device;
    }

    public void setDevice(DeviceVO device) {
        this.device = device;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
