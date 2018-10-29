package edu.xd.ridelab.service.product.impl;

import com.alibaba.fastjson.JSON;
import edu.xd.ridelab.mapper.product.ProductMapper;
import edu.xd.ridelab.model.AddedProductsModel;
import edu.xd.ridelab.model.ProductBriefInfoModel;
import edu.xd.ridelab.model.ProductDetailInfoModel;
import edu.xd.ridelab.model.ProductModel;
import edu.xd.ridelab.service.product.ProductService;
import edu.xd.ridelab.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductServiceImpl.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductMapper productMapper;

    /**
     * @description 查询用户已添加的产品
     * @author PanTeng
     * @date 10:18,2018/8/13
     * @param userId 用户id
     * @return
     */
    @Override
    public AddedProductsModel getAddedProducts(long userId) {
        AddedProductsModel addedProductsModel = new AddedProductsModel();
        List<ProductBriefInfoModel> list = productMapper.getProductsByUserId(userId);
        addedProductsModel.setUserId(userId);
        addedProductsModel.setList(list);
        return addedProductsModel;
    }

    /**
     * @description 获取产品详细信息，包括产品的属性、服务、事件
     * @author PanTeng
     * @date 15:55,2018/8/13
     * @param productId 产品id
     * @return
     */
    @Override
    public ProductDetailInfoModel getProductInfo(long productId) {
        ProductDetailInfoModel productDetailInfoModel = new ProductDetailInfoModel();
        ProductVO productVO = productMapper.getProductInfoByProductId(productId);

        productDetailInfoModel.setUserId(productVO.getFkUserId());
        productDetailInfoModel.setProductId(productVO.getProductId());
        productDetailInfoModel.setProductName(productVO.getProductName());
        productDetailInfoModel.setProductCode(productVO.getProductCode());
        productDetailInfoModel.setProductManufactuter(productVO.getProductManufacturer());
        productDetailInfoModel.setProductDesc(productVO.getProductDesc());
        productDetailInfoModel.setDeviceModel(productVO.getDeviceModel());

        return productDetailInfoModel;
    }

    /**
     * @description 删除产品
     * @author PanTeng
     * @date 16:38,2018/8/13
     * @param productId 产品id
     * @return
     */
    @Override
    @Transactional
    public void deleteProduct(long productId) {
        try {
            productMapper.deleteProductByProductId(productId);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }

    }

    /**
     * @description 添加产品
     * @author PanTeng
     * @date 15:49,2018/8/14
     * @param productModel 产品模型
     * @param userId 用户Id
     * @return
     */
    @Override
    @Transactional
    public void addProduct(ProductModel productModel, long userId) {
        ProductVO productVO = new ProductVO();

        productVO.setFkUserId(userId);
        productVO.setProductName(productModel.getProductName());
        productVO.setProductCode(productModel.getProductCode());
        productVO.setProductManufacturer(productModel.getProductManufacturer());
        productVO.setProductDesc(productModel.getProductDesc());
        productVO.setDeviceModel(JSON.toJSONString(productModel.getDeviceModel()));

        productMapper.addProductWithUserId(productVO);
/*        productMapper.addDeviceModels(productModel.getDeviceModel());*/
    }
}
