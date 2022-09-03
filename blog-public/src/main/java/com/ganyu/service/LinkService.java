package com.ganyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Link;
import com.ganyu.domain.vo.LinkListVo;

/**
 * DATA: 2022/8/30
 * Author: 晨夕
 */

public interface LinkService extends IService<Link> {
    //友链
    ResponseResult<LinkListVo> getAllLink();
}
