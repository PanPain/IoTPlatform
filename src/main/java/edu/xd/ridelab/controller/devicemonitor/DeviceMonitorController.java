package edu.xd.ridelab.controller.devicemonitor;


import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.model.DeviceModel;
import edu.xd.ridelab.model.ProductDetailInfoModel;
import edu.xd.ridelab.service.devicemonitor.DeviceMonitorService;
import edu.xd.ridelab.service.product.ProductService;
import edu.xd.ridelab.vo.DeviceVO;
import edu.xd.ridelab.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author FBY
 * @Version 1.0
 * @File DeviceController.java
 * @Date 2018/8/14
 * @Attention Copyright (C),04-2017,SSELab, SEI, XiDian University
 */
@RestController
public class DeviceMonitorController {

    @Autowired
    DeviceMonitorService deviceMonitorService;

    @Autowired
    ProductService productService;

    /**
     * @Author FBY
     * @Description
     * @Date 10:17 2018/8/14
     * @Param
     * @Return
     **/
    @RequestMapping(value = "/devicemonitor/getAllDeviceInfo",method = RequestMethod.GET)
    public ResponseResult getAllDeviceInfo(@RequestParam(defaultValue = "1") int curPage,@RequestParam(defaultValue = "10") int tagNum){
        ResponseResult responseResult = new ResponseResult();
        int offset =  (curPage - 1) * tagNum;
        List<DeviceVO> deviceVOLists = null;
        List<DeviceModel> deviceModelLists = new ArrayList<>();
        deviceVOLists = deviceMonitorService.getAllDevice(offset,tagNum);
        DeviceModel deviceModel = null;
        for(int i = 0; i < deviceVOLists.size(); i++){
            if(deviceVOLists.get(i).getLastConnectTime() != null){
                deviceModel = new DeviceModel();
                Timestamp d = new Timestamp(System.currentTimeMillis());
                double time = (d.getTime() - deviceVOLists.get(i).getLastConnectTime().getTime())/(1000*60);
                if(time <= 5){
                    deviceModel.setStatus(true);
                }
                else{
                    deviceModel.setStatus(false);
                }
                deviceModel.setDevice(deviceVOLists.get(i));
                ProductDetailInfoModel product = productService.getProductInfo(deviceVOLists.get(i).getProductId());
                deviceModel.setProduct(product);

                deviceModelLists.add(deviceModel);
                }
        }
        if(deviceModelLists.size() != 0){
        responseResult.setData(deviceModelLists);
        responseResult.setSuccess(true);
        responseResult.setCode(DeviceMonitorCode.GET_DEVICE_SUCCESS.getCode());
        responseResult.setMessage(DeviceMonitorCode.GET_DEVICE_SUCCESS.getMessage());
        }
        else {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceMonitorCode.NO_DEVICE_INFO.getCode());
            responseResult.setMessage(DeviceMonitorCode.NO_DEVICE_INFO.getMessage());
        }
        return responseResult;
    }

    /**
     * @Author FBY
     * @Description //TODO
     * @Date 20:33 2018/8/14
     * @Param
     * @Return
     **/
    @RequestMapping(value = "/devicemonitor/getDeviceLocationInfo",method = RequestMethod.GET)
    public ResponseResult getDeviceLocationInfo(@RequestParam(defaultValue = "1") int curPage,@RequestParam(defaultValue = "10") int tagNum){
        ResponseResult responseResult = new ResponseResult();
        int offset =  (curPage - 1) * tagNum;
        List<DeviceVO> deviceVOLists = null;
        List<DeviceModel> deviceModelLists = new ArrayList<>();
        deviceVOLists = deviceMonitorService.getAllDevice(offset,tagNum);
        DeviceModel deviceModel = null;
        for(int i = 0; i < deviceVOLists.size(); i++){
            if(deviceVOLists.get(i).getLastConnectTime() != null){
                deviceModel = new DeviceModel();
                Timestamp d = new Timestamp(System.currentTimeMillis());
                double time = (d.getTime() - deviceVOLists.get(i).getLastConnectTime().getTime())/(1000*60);
                if(time <= 5){
                    deviceModel.setDevice(deviceVOLists.get(i));
                    deviceModel.setStatus(true);
                    deviceModelLists.add(deviceModel);
                }
                else{
                    deviceModel.setDevice(deviceVOLists.get(i));
                    deviceModel.setStatus(false);
                    deviceModelLists.add(deviceModel);
                }
            }
        }
        if(deviceModelLists.size() != 0){
            responseResult.setData(deviceModelLists);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceMonitorCode.GET_DEVICE_LOCATION_SUCCESS.getCode());
            responseResult.setMessage(DeviceMonitorCode.GET_DEVICE_LOCATION_SUCCESS.getMessage());
        }
        else {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceMonitorCode.NO_DEVICE_LOCATION.getCode());
            responseResult.setMessage(DeviceMonitorCode.NO_DEVICE_LOCATION.getMessage());
        }
        return responseResult;
    }

    /**
     * @Author FBY
     * @Description //TODO
     * @Date 20:37 2018/8/14
     * @Param
     * @Return
     **/
    @RequestMapping(value = "/devicemonitor/getDeviceStatusInfo",method = RequestMethod.GET)
    public ResponseResult getDeviceStatusInfo(@RequestParam(defaultValue = "1") int curPage,@RequestParam(defaultValue = "10") int tagNum){
        ResponseResult responseResult = new ResponseResult();
        Map<String, Integer> map = new HashMap<String, Integer>();
        int numOfOnline = 0,numOfOffline = 0;
        int offset =  (curPage - 1) * tagNum;
        List<DeviceVO> deviceVOLists = null;
        List<DeviceModel> deviceModelLists = new ArrayList<>();
        deviceVOLists = deviceMonitorService.getAllDevice(offset,tagNum);
        DeviceModel deviceModel = null;
        for(int i = 0; i < deviceVOLists.size(); i++){
            if(deviceVOLists.get(i).getLastConnectTime() != null){
                deviceModel = new DeviceModel();
                Timestamp d = new Timestamp(System.currentTimeMillis());
                double time = (d.getTime() - deviceVOLists.get(i).getLastConnectTime().getTime())/(1000*60);
                if(time <= 5){
                    deviceModel.setDevice(deviceVOLists.get(i));
                    deviceModel.setStatus(true);
                    deviceModelLists.add(deviceModel);
                    numOfOnline++;
                }
                else{
                    deviceModel.setDevice(deviceVOLists.get(i));
                    deviceModel.setStatus(false);
                    deviceModelLists.add(deviceModel);
                    numOfOffline++;
                }
            }
        }
        if(numOfOffline != 0 || numOfOnline != 0){
            map.put("numOfOnline",numOfOnline);
            map.put("numOfOffline",numOfOffline);
            responseResult.setData(map);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceMonitorCode.GET_DEVICE_STATUS.getCode());
            responseResult.setMessage(DeviceMonitorCode.GET_DEVICE_STATUS.getMessage());
        }
        else {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceMonitorCode.NO_DEVICE_STATUS.getCode());
            responseResult.setMessage(DeviceMonitorCode.NO_DEVICE_STATUS.getMessage());
        }
        return responseResult;
    }

    /**
     * @description 根据产品id 获取该类产品所有设备的数据信息
     * @author PanTeng
     * @date 9:07,2018/12/5
     * @param productId 产品id
     * @return
     */
    @RequestMapping(value = "/devicemonitor/getDeviceData", method = RequestMethod.POST)
    public ResponseResult getDeviceData(@RequestParam("productId") long productId){
        ResponseResult responseResult = new ResponseResult();
        List deviceDataList = new ArrayList();      //存放该类产品所有设备的数据信息
        deviceDataList = deviceMonitorService.getAllDeviceData(productId);

        responseResult.setData(deviceDataList);
        responseResult.setSuccess(true);
        responseResult.setCode(DeviceMonitorCode.GET_DEVICE_DATA_SUCCESS.getCode());
        responseResult.setMessage(DeviceMonitorCode.GET_DEVICE_DATA_SUCCESS.getMessage());
        return responseResult;
    }
}
