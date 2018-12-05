package edu.xd.ridelab.service.device;

import edu.xd.ridelab.vo.DeviceVO;

import java.util.List;

/**
 * @Author zjh
 * @Date 2018/08/13,20:06
 */
public interface DeviceService {

    List<DeviceVO> getAllDevice(int offset, int pageNum);

    int addDevice(DeviceVO deviceVO) throws Exception;

    int updateDevice(DeviceVO deviceVO) throws Exception;

    int deleteDevice(Long deviceId) throws Exception;

    int deleteDeviceByProductId(Long productId) throws Exception;

    DeviceVO getDeviceById(Long deviceId) throws Exception;

    List<DeviceVO> getDeviceByProductId(Long productId) throws Exception;

    DeviceVO getDeviceByIndentifierAndProductId(Long productId, String identifier) throws Exception;
}
