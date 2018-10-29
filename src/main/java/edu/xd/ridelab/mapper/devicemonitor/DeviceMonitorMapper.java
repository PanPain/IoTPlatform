package edu.xd.ridelab.mapper.devicemonitor;

import edu.xd.ridelab.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author FBY
 * @Version 1.0
 * @File DeviceMonitorMapper.java
 * @Date 2018/8/13
 * @Attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public interface DeviceMonitorMapper {
    /**
     * @Author FBY
     * @Description
     * @Date 15:39 2018/8/13
     * @Param []
     * @Return edu.xd.ridelab.vo.DeviceVO
     **/
    DeviceVO getDevicePlaceInfo(long deviceId);


    List<DeviceVO> getAllDevice(@Param("offset") int offset, @Param("pageNum") int pageNum);
}
