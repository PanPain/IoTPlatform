package edu.xd.ridelab.vo;

/**
 * @author PanTeng
 * @version 1.0
 * @describe 设备模型类， 描述定义一类产品下所有设备的模型，包括属性、服务、事件
 * @file DeviceModelVO.java
 * @date 2018/8/14
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public class DeviceModelVO {
    /**
     * id
     */
    private long modelId;
    /**
     * 外键
     */
    private long fkProductId;
    /**
     * 模型类型 1：属性  2：服务  3：事件
     */
    private int modelType;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 模型标识符
     */
    private String modelIdentifier;
    /**
     * 属性数据类型  0:空（用于服务、事件） 1：整数型 2：浮点型 3：枚举型 4：布尔型 5：字符串 6：时间型
     */
    private int dataType;
    /**
     * 服务参数  (存储的为Json，对于“属性”，本字段为空）
     */
    private String serviceParam;
    /**
     * 模型描述
     */
    private String serviceDesc;

    public DeviceModelVO() {
    }

    public DeviceModelVO(long modelId, long fkProductId, int modelType, String modelName, String modelIdentifier, int dataType, String serviceParam, String serviceDesc) {
        this.modelId = modelId;
        this.fkProductId = fkProductId;
        this.modelType = modelType;
        this.modelName = modelName;
        this.modelIdentifier = modelIdentifier;
        this.dataType = dataType;
        this.serviceParam = serviceParam;
        this.serviceDesc = serviceDesc;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getFkProductId() {
        return fkProductId;
    }

    public void setFkProductId(long fkProductId) {
        this.fkProductId = fkProductId;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModoelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(String modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getServiceParam() {
        return serviceParam;
    }

    public void setServiceParam(String serviceParam) {
        this.serviceParam = serviceParam;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
