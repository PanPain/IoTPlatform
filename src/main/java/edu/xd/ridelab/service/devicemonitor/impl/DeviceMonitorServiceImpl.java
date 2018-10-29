package edu.xd.ridelab.service.devicemonitor.impl;

import edu.xd.ridelab.mapper.devicemonitor.DeviceMonitorMapper;
import edu.xd.ridelab.service.devicemonitor.DeviceMonitorService;
import edu.xd.ridelab.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public DeviceVO getDevicePlaceInfo(long deviceId){
       return DeviceMonitorMapper.getDevicePlaceInfo(deviceId);
    }

    @Override
    public List<DeviceVO> getAllDevice(@Param("offset") int offset, @Param("pageNum") int pageNum){
        return DeviceMonitorMapper.getAllDevice(offset,pageNum);
    }
}
