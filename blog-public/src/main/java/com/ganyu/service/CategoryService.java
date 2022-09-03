package com.ganyu.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Category;
import com.ganyu.domain.vo.CategoryVo;

/**
 * DATA: 2022/8/29
 * Author: 晨夕
 */
public interface CategoryService extends IService<Category> {

    //查询文章类别
    ResponseResult<CategoryVo> getCategory();
}
