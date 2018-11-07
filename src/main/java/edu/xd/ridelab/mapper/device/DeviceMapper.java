package edu.xd.ridelab.mapper.device;

import edu.xd.ridelab.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zjh
 * @Date 2018/08/13,11:23
 */
public interface DeviceMapper {

    List<DeviceVO> selectAllDevice(@Param("offset") int offset, @Param("pageNum") int pageNum);

    List<DeviceVO> selectByProductId(@Param("productId") long productId);

    DeviceVO selectByPrimaryKey(@Param("deviceId") long primaryKey);

    DeviceVO selectByIdentifierAndProductId(@Param("deviceIdentifier") String deviceIdentifier, @Param("productId") long productId);

    int insert(DeviceVO deviceVO);

    int deleteByPrimaryKey(@Param("deviceId") long primaryKey);

    int updateByPrimaryKey(DeviceVO deviceVO);
}
