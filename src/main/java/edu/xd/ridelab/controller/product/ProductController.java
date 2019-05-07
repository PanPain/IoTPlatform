package edu.xd.ridelab.controller.product;

import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.model.AddedProductsModel;
import edu.xd.ridelab.model.ProductDetailInfoModel;
import edu.xd.ridelab.model.ProductModel;
import edu.xd.ridelab.service.product.ProductService;
import edu.xd.ridelab.vo.DeviceModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductController.java
 * @date 2018/8/12
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    /**
     * @methodname getAddedProducts
     * @description 查看已添加的产品
     * @author PanTeng
     * @date 13:30,2018/8/12
     * @param httpSession
     * @return edu.xd.ridelab.controller.response.ResponseResult
     */
    @RequestMapping(value = "product/getAddedProducts", method = RequestMethod.GET)
    public ResponseResult getAddedProducts(HttpSession httpSession){

        ResponseResult responseResult = new ResponseResult();
        AddedProductsModel addedProductsModel = null;

        Object userId = httpSession.getAttribute("userId");
        addedProductsModel = productService.getAddedProducts((long) userId);

        if (! (addedProductsModel.getList().size() == 0)){
            responseResult.setData(addedProductsModel);
            responseResult.setSuccess(true);
            responseResult.setCode(ProductCode.GET_INFO_SUCCESS.getCode());
            responseResult.setMessage(ProductCode.GET_INFO_SUCCESS.getMessage());
        }else {
            responseResult.setData(addedProductsModel);
            responseResult.setSuccess(false);
            responseResult.setCode(ProductCode.NO_ADDED_PRODUCT.getCode());
            responseResult.setMessage(ProductCode.NO_ADDED_PRODUCT.getMessage());
        }

        return responseResult;
    }

    /**
     * @description 查看产品详细信息
     * @author PanTeng
     * @date 15:45,2018/8/13
     * @param productId 产品id
     * @return
     */
    @RequestMapping(value = "product/getProductInfo", method = RequestMethod.POST)
    public ResponseResult getProductInfo(@RequestParam("productId") long productId){

        ResponseResult responseResult = new ResponseResult();
        ProductDetailInfoModel productDetailInfoModel = null;

        productDetailInfoModel = productService.getProductInfo(productId);

        responseResult.setData(productDetailInfoModel);
        responseResult.setSuccess(true);
        responseResult.setCode(ProductCode.GRT_DETAIL_INFO_SUCCESS.getCode());
        responseResult.setMessage(ProductCode.GRT_DETAIL_INFO_SUCCESS.getMessage());

        return responseResult;
    }

    /**
     * @description 添加产品
     * @author PanTeng
     * @date 14:51,2018/8/14
     * @param productModel 产品模型
     * @return
     */
    @RequestMapping(value = "product/addProduct", method = RequestMethod.POST)
    public ResponseResult addProduct(@RequestBody ProductModel productModel,
                                     HttpSession httpSession){
        ResponseResult responseResult = new ResponseResult();

        Object userId = httpSession.getAttribute("userId");
        String sdk = productService.addProduct(productModel,(long)userId);

        responseResult.setData(sdk);
        responseResult.setSuccess(true);
        responseResult.setCode(ProductCode.ADD_PRODUCT_SUCCESS.getCode());
        responseResult.setMessage(ProductCode.ADD_PRODUCT_SUCCESS.getMessage());
        return responseResult;
    }
    /**
     * @description 删除产品
     * @author PanTeng
     * @date 16:32,2018/8/13
     * @param productId 产品id
     * @return
     */
    @RequestMapping(value = "product/deleteProduct", method = RequestMethod.POST)
    public ResponseResult deleteProduct(@RequestParam("productId") long productId){
        ResponseResult responseResult = new ResponseResult();
        productService.deleteProduct(productId);
        responseResult.setSuccess(true);
        responseResult.setCode(ProductCode.DELETE_PRODUCT_SUCCESS.getCode());
        responseResult.setMessage(ProductCode.DELETE_PRODUCT_SUCCESS.getMessage());

        return responseResult;
    }

}
