package edu.xd.ridelab.service.product;

import edu.xd.ridelab.model.AddedProductsModel;
import edu.xd.ridelab.model.ProductDetailInfoModel;
import edu.xd.ridelab.model.ProductModel;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductService.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public interface ProductService {

    /**
     * @description 查询当前用户已添加的所有产品
     * @author PanTeng
     * @date 14:51,2018/8/12
     * @param userId 用户id
     * @return
     */
    AddedProductsModel getAddedProducts(long userId);

    /**
     * @description 获取产品详细信息，包括产品的属性、服务、事件
     * @author PanTeng
     * @date 15:54,2018/8/13
     * @param productId 产品id
     * @return
     */
    ProductDetailInfoModel getProductInfo(long productId);

    /**
     * @description 删除产品
     * @author PanTeng
     * @date 16:36,2018/8/13
     * @param productId 产品id
     * @return
     */
    void deleteProduct(long productId);

    /**
     * @description 添加产品
     * @author PanTeng
     * @date 15:48,2018/8/14
     * @param productModel 产品模型
     * @return
     */
    String addProduct(ProductModel productModel, long userId);
}
