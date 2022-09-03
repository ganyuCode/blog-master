package com.ganyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Link;
import com.ganyu.domain.vo.LinkListVo;
import com.ganyu.mapper.LinkMapper;
import com.ganyu.service.LinkService;
import com.ganyu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ganyu.constant.SystemConstant.LINK_STATUS_PASS;

/**
 * DATA: 2022/8/30
 * Author: 晨夕
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Autowired
    private LinkService linkService;
    /**
     * 友链
     */
    @Override
    public ResponseResult<LinkListVo> getAllLink() {
        //1. 在页面查询出所有审核通过的友链
        LambdaQueryWrapper<Link> linkQuery = new LambdaQueryWrapper<>();
        linkQuery.eq(Link::getStatus,LINK_STATUS_PASS);
        List<Link> linkList = linkService.list(linkQuery);
        //2. 返回需要返回的类型（bean拷贝）
        List<LinkListVo> linkListVos = BeanCopyUtils.copyBeanList(linkList, LinkListVo.class);
        return ResponseResult.okResult(linkListVos);
    }
}
