package edu.xd.ridelab.mapper.product;

import edu.xd.ridelab.model.ProductBriefInfoModel;
import edu.xd.ridelab.vo.DeviceModelVO;
import edu.xd.ridelab.vo.ProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductMapper.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public interface ProductMapper {

    /**
     * @description 根据用户id,获取该用户添加的所有产品
     * @author PanTeng
     * @date 14:58,2018/8/12
     * @param userId 用户Id
     * @return
     */
    List<ProductBriefInfoModel> getProductsByUserId(@Param("userId") long userId);

    /**
     * @description 根据产品id，获取该产品的详细信息
     * @author PanTeng
     * @date 15:02,2018/8/12
     * @param productId 产品id
     * @return
     */
    ProductVO getProductInfoByProductId(@Param("productId") long productId);

    /**
     * @description 根据产品id，删除该产品
     * @author PanTeng
     * @date 16:38,2018/8/13
     * @param productId 产品id
     * @return
     */
    int deleteProductByProductId(@Param("productId") long productId);

    /**
     * @description 添加产品,与用户id绑定
     * @author PanTeng
     * @date 16:13,2018/8/14
     * @param product 产品VO
     * @return
     */
    int addProductWithUserId(ProductVO product);

    /**
     * @description 添加产品的同时，需要给DEVICE_MODEL表中插入多条deviceModel记录
     * @author PanTeng
     * @date 16:27,2018/8/14
     * @param deviceModelVOList 设备模型列表
     * @return
     */
    @Deprecated
    int addDeviceModels(@Param("deviceModelList")List<DeviceModelVO> deviceModelVOList);
}
