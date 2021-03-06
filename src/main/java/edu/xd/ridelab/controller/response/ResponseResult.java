package edu.xd.ridelab.controller.response;

/**
 * @author PanTeng
 * @version 1.0
 * @file ResponseResult.java
 * @date 2018/8/11
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class ResponseResult {
    /**
     * 后端返回给前端的数据
     */
    private Object data;
    /**
     * 后端返回给前端的元数据，包括：成功标识符、状态码、消息
     */
    private MetaData meta;

    public ResponseResult() {
    }

    public ResponseResult(Object data, MetaData meta) {
        this.data = data;
        this.meta = meta;
    }

    public void setSuccess(Boolean success){
        if(this.meta == null){
            this.meta = new MetaData();
        }
        this.meta.setSuccess(success);
    }
    public void setCode(String code){
        if(this.meta==null){
            this.meta = new MetaData();
        }
        this.meta.setCode(code);
    }
    public void setMessage(String message){
        if(this.meta == null){
            this.meta = new MetaData();
        }
        this.meta.setMessage(message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }
}
