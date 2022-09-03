package com.ganyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Article;
import com.ganyu.domain.vo.ArticleDetailVo;
import com.ganyu.domain.vo.HotArticleVo;
import com.ganyu.domain.vo.PageVo;


public interface ArticleService extends IService<Article> {

    //查询热门文章
    ResponseResult<HotArticleVo> getHotArticle();

    //首页文章显示和分类文章显示
    ResponseResult<PageVo> getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    //文章详情
    ResponseResult<ArticleDetailVo> getArticleDetail(Long id);
}
