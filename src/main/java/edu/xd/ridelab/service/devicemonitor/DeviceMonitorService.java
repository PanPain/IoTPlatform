package edu.xd.ridelab.service.devicemonitor;

import edu.xd.ridelab.model.DeviceDataModel;
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

    /**
     * @description 根据产品id，获得该类产品所有设备的数据信息
     * @author PanTeng
     * @date 9:37,2018/12/5
     * @param productId 产品id
     * @return
     */
    List<DeviceDataModel> getAllDeviceData(@Param("productId")long productId);
}
