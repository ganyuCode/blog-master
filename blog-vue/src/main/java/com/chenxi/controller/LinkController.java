package com.chenxi.controller;


import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.vo.LinkListVo;
import com.ganyu.service.impl.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATA: 2022/8/30
 * Author: 晨夕
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;
    //友链
    @GetMapping("/getAllLink")
    public ResponseResult<LinkListVo> AllLink(){
        return linkService.getAllLink();
    }
}
