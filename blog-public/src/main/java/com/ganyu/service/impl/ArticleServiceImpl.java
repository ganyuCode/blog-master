package com.ganyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganyu.constant.SystemConstant;
import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Article;
import com.ganyu.domain.entity.Category;
import com.ganyu.domain.vo.ArticleDetailVo;
import com.ganyu.domain.vo.ArticleListVo;
import com.ganyu.domain.vo.HotArticleVo;
import com.ganyu.domain.vo.PageVo;
import com.ganyu.mapper.ArticleMapper;
import com.ganyu.service.ArticleService;
import com.ganyu.service.CategoryService;
import com.ganyu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ganyu.constant.SystemConstant.ARTICLE_STATUS_NORMAL;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    /**
     * 查询热门文章
     */
    @Override
    public ResponseResult<HotArticleVo> getHotArticle() {
        //1. 查询正式的文章，正式文章：字段 status = 0
        //1.1 使用MybatisPlus进行查询并判断(查询出status = 0的文章)
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        //1.2 根据浏览量降序排序，浏览量：字段 view_count
        queryWrapper.orderByDesc(Article::getViewCount);
        //1.3展示前10篇文章
        Page<Article> page = new Page<>(SystemConstant.ARTICLE_PAGE_NUMBER,SystemConstant.ARTICLE_NUMBER);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        //1.4返回需要返回的字段(bean拷贝)
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    /**
     * 首页文章显示和分类文章显示
     */
    @Override
    public ResponseResult<PageVo> getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //1. 查询文章，分首页（没有categoryId）和文章列表（有categoryId）
        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
        //1.1 有文章列表的情况（categoryId不为空或大于0）
        articleQuery.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        //2. 查询正式的文章
        articleQuery.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        //3. 置顶文章显示在最前面
        articleQuery.orderByDesc(Article::getIsTop);
        //4. 进行分页操作
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,articleQuery);
        List<Article> articles = page.getRecords();
        //stream流
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //5. 返回需要返回的类型（bean拷贝）
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 文章详情
     */
    @Override
    public ResponseResult<ArticleDetailVo> getArticleDetail(Long id) {
        //1. 根据id去查询文章
        Article article = articleService.getById(id);
        //1.1 根据id查询Article表中的catetoryId，然后在Catetory表中获取catetoryName
        Long categoryId = article.getCategoryId();
        //1.2 防止报空指针异常，有文章分类就显示，没有就不显示
        if(categoryId != null){
            Category category = categoryService.getById(categoryId);
            article.setCategoryName(category.getName());
        }
        //2. 返回需要返回的类型（bean拷贝）
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        return ResponseResult.okResult(articleDetailVo);
    }
}
