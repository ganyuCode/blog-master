package com.chenxi.controller;

import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Category;
import com.ganyu.domain.vo.CategoryVo;
import com.ganyu.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATA: 2022/8/29
 * Author: 晨夕
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl gategoryServiceImpl;

    /**
     * 查询分类类别
     */
    @GetMapping("/getCategoryList")
    public ResponseResult<CategoryVo> Category(){
        return gategoryServiceImpl.getCategory();
    }


}
