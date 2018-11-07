package edu.xd.ridelab.service.device.impl;

import com.alibaba.fastjson.JSON;
import edu.xd.ridelab.mapper.device.DeviceMapper;
import edu.xd.ridelab.mapper.product.ProductMapper;
import edu.xd.ridelab.service.device.DeviceService;
import edu.xd.ridelab.vo.DeviceModelVO;
import edu.xd.ridelab.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zjh
 * @Date 2018/08/13,20:08
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<DeviceVO> getAllDevice(int offset, int pageNum) {
        return deviceMapper.selectAllDevice(offset, pageNum);
    }

    @Override
    public int updateDevice(DeviceVO deviceVO) throws Exception {
        return deviceMapper.updateByPrimaryKey(deviceVO);
    }

    @Override
    public int deleteDevice(Long deviceId) throws Exception {
        return deviceMapper.deleteByPrimaryKey(deviceId);
    }

    @Override
    public int addDevice(DeviceVO deviceVO) throws Exception {

        Long productId = deviceVO.getProductId();

        String result = "{"; //最终拼接成的deviceData

        //取出设备Model，将设备属性对应的modelName设为设备data的JSON串的键，值置为空
        String deviceDataKey = productMapper.getProductInfoByProductId(productId).getDeviceModel();
        List<DeviceModelVO> list = JSON.parseArray(deviceDataKey, DeviceModelVO.class);

        //拼接JSON串
        for(DeviceModelVO deviceModelVO : list){
            if(deviceModelVO.getModelType() == 1){
                result = result + "\"" + deviceModelVO.getModelName() + "\":\"\",";
            }
        }

        String str = result.substring(0, result.length()-1);
        str += "}";
        deviceVO.setDeviceData(str);

        return deviceMapper.insert(deviceVO);
    }

    @Override
    public DeviceVO getDeviceById(Long deviceId) throws Exception {
        return deviceMapper.selectByPrimaryKey(deviceId);
    }

    @Override
    public List<DeviceVO> getDeviceByProductId(Long productId) throws Exception {
        return deviceMapper.selectByProductId(productId);
    }

    @Override
    public DeviceVO getDeviceByIndentifierAndProductId(Long productId, String identifier) throws Exception {
        return deviceMapper.selectByIdentifierAndProductId(identifier, productId);
    }
}
