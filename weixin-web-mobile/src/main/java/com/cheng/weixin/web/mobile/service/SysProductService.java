package com.cheng.weixin.web.mobile.service;

import com.cheng.weixin.common.utils.StringFormat;
import com.cheng.weixin.rpc.item.entity.Picture;
import com.cheng.weixin.rpc.item.entity.Product;
import com.cheng.weixin.rpc.item.service.RpcProductService;
import com.cheng.weixin.web.mobile.result.product.ProductDetail;
import com.cheng.weixin.web.mobile.result.product.ProductPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: hp
 * Date: 2016/10/8
 */
@Service
public class SysProductService {
    @Autowired
    private RpcProductService productService;

    /**
     * 详情页
     * @param productId
     * @return
     */
    public ProductDetail getDetail(String productId) {
        Product product = productService.getById(productId);
        List<Picture> pictures = productService.getAllPicture(productId);

        ProductDetail detail = new ProductDetail();
        detail.setProductId(product.getId());
        detail.setName(product.getName());
        detail.setDesc(product.getUnitDesc());
        detail.setSellNum(99);
        detail.setGood(98);
        detail.setMarketPrice(StringFormat.format(product.getMarketPrice()));
        detail.setMarketPrice(StringFormat.format(product.getMarketPrice()));

        List<ProductPic> pics = new ArrayList<>();
        for (Picture picture : pictures) {
            pics.add(new ProductPic(picture.getPictureUrl(), picture.getWidth(), picture.getHeight()));
        }
        detail.setPicList(pics);

        return detail;
    }

}