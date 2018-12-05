package edu.xd.ridelab.service.devicemonitor.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.xd.ridelab.mapper.device.DeviceMapper;
import edu.xd.ridelab.mapper.devicemonitor.DeviceMonitorMapper;
import edu.xd.ridelab.mapper.product.ProductMapper;
import edu.xd.ridelab.model.DeviceDataModel;
import edu.xd.ridelab.service.devicemonitor.DeviceMonitorService;
import edu.xd.ridelab.vo.DeviceModelVO;
import edu.xd.ridelab.vo.DeviceVO;
import edu.xd.ridelab.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author FBY
 * @Version 1.0
 * @File DeviceServiceImpl.java
 * @Date 2018/8/14
 * @Attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Service
public class DeviceMonitorServiceImpl implements DeviceMonitorService {

    @Autowired
    DeviceMonitorMapper DeviceMonitorMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    ProductMapper productMapper;

    @Override
    public DeviceVO getDevicePlaceInfo(long deviceId){
       return DeviceMonitorMapper.getDevicePlaceInfo(deviceId);
    }

    @Override
    public List<DeviceVO> getAllDevice(@Param("offset") int offset, @Param("pageNum") int pageNum){
        return DeviceMonitorMapper.getAllDevice(offset,pageNum);
    }

    /**
     * @description 根据产品id，拼接所有设备的数据信息，并返回，返回形式为 “设备标识符” + [“属性名称：属性值”,....]
     * @author PanTeng
     * @date 12:28,2018/12/5
     * @param null
     * @return
     */
    @Override
    public List<DeviceDataModel> getAllDeviceData(long productId) {
        List<DeviceDataModel> deviceDataList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();              //存放 “属性标识符”：“属性名称”
        List<DeviceVO> deviceList = deviceMapper.selectByProductId(productId);   //拿到该类产品所有的设备信息

        List<DeviceModelVO> list = JSON.parseArray(productMapper.getProductInfoByProductId(productId).getDeviceModel(), DeviceModelVO.class);
        for (DeviceModelVO e : list){
            if (e.getModelType() == 1){// "设备属性"
                map.put(e.getModelIdentifier(),e.getModelName());
            }else
                continue;
        }

        for (DeviceVO deviceVO : deviceList){                     //遍历所有设备，解析每个设备的数据信息
            Map<String,String> dataMap = new HashMap<>();         //存放 “属性标识符”：“属性值”
            Map<String,String> deviceDataMap = new HashMap<>();
            DeviceDataModel deviceDataModel = new DeviceDataModel();
            deviceDataModel.setDeviceIdentifier(deviceVO.getDeviceIdentifier());
            String data = deviceVO.getDeviceData();
            data = data.replaceAll(" ","");     //去掉所有空格
            data = data.substring(1,data.length() - 1);
            String[] dataList = data.split("\\{|}|,");
            for (String str : dataList){
                dataMap.put(str.split(":")[0],str.split(":")[1]);
            }
            for (String str : map.keySet()){
                deviceDataMap.put(map.get(str),dataMap.get(str));
            }
            deviceDataModel.setDeviceData(deviceDataMap);
            deviceDataList.add(deviceDataModel);
    }
        return deviceDataList;
    }
}
