package edu.xd.ridelab.service.devicemonitor;

import edu.xd.ridelab.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author FBY
 * @Version 1.0
 * @File DeviceMonitorService.java
 * @Date 2018/8/14
 * @Attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public interface DeviceMonitorService {
    /**
     * @Author FBY
     * @Description //TODO
     * @Date 9:39 2018/8/14
     * @Param
     * @Return
     **/
    DeviceVO getDevicePlaceInfo(long deviceId);


    List<DeviceVO> getAllDevice(@Param("offset") int offset, @Param("pageNum") int pageNum);
}
