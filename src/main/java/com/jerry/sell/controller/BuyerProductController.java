package com.jerry.sell.controller;

import com.jerry.sell.dataobject.ProductCategory;
import com.jerry.sell.dataobject.ProductInfo;
import com.jerry.sell.service.impl.ProductCategoryServiceImpl;
import com.jerry.sell.service.impl.ProductInfoServiceImpl;
import com.jerry.sell.util.ResultVOUtil;
import com.jerry.sell.vo.ProductInfoVO;
import com.jerry.sell.vo.ProductCategoryVO;
import com.jerry.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/19
 * Time: 13:57
 * Description:
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryServiceImpl categoryService;

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @GetMapping("/list")
    public ResultVO<ProductCategoryVO> list() {
        // 1、查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2、查询类目（一次性查询）
        // 查询所有查出来的商品的类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryIn(categoryTypeList);


        // 3、数据拼装
        List<ProductCategoryVO> categoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductCategoryVO categoryVO = new ProductCategoryVO();
            categoryVO.setCategoryName(productCategory.getCategoryName());
            categoryVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            categoryVO.setProductInfoVOList(productInfoVOList);
            categoryVOList.add(categoryVO);
        }

        return ResultVOUtil.success(categoryVOList);
    }

}
