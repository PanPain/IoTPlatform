package edu.xd.ridelab.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductCode.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public enum ProductCode {
    GET_INFO_SUCCESS("02001","已添加的产品简略信息查询成功"),
    NO_ADDED_PRODUCT("02002","没有已添加产品"),
    GRT_DETAIL_INFO_SUCCESS("02003","获取产品详细信息成功"),
    DELETE_PRODUCT_SUCCESS("02004","删除产品成功"),
    ADD_PRODUCT_SUCCESS("02005","添加产品成功"),
    ;

    private String code;
    private String message;

    ProductCode(String code, String message) {
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
