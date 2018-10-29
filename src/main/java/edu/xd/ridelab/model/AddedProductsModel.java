package edu.xd.ridelab.model;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file AddedProductsModel.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class AddedProductsModel {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 已添加产品
     */
    private List<ProductBriefInfoModel> list;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<ProductBriefInfoModel> getList() {
        return list;
    }

    public void setList(List<ProductBriefInfoModel> list) {
        this.list = list;
    }
}
