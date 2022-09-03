package com.chenxi.controller;

import com.ganyu.domain.ResponseResult;


import com.ganyu.domain.vo.ArticleDetailVo;
import com.ganyu.domain.vo.HotArticleVo;
import com.ganyu.domain.vo.PageVo;
import com.ganyu.service.impl.ArticleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;

    //查询热门文章
    @GetMapping("/hotArticleList")
    public ResponseResult<HotArticleVo> hotArticle(){
        return articleServiceImpl.getHotArticle();
    }

    //首页文章显示和分类文章显示
    @GetMapping("/articleList")
    public ResponseResult<PageVo> articleList(Integer pageNum, Integer pageSize, Long categoryId){
        return articleServiceImpl.getArticleList(pageNum, pageSize, categoryId);
    }
    //文章详情
    @GetMapping("{id}")
    public ResponseResult<ArticleDetailVo> articleDetail(@PathVariable("id") Long id){
        return articleServiceImpl.getArticleDetail(id);
    }
}
