package com.ganyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganyu.domain.ResponseResult;
import com.ganyu.domain.entity.Article;
import com.ganyu.domain.entity.Category;
import com.ganyu.domain.vo.CategoryVo;
import com.ganyu.mapper.ArticleMapper;
import com.ganyu.mapper.CategoryMapper;
import com.ganyu.service.ArticleService;
import com.ganyu.service.CategoryService;
import com.ganyu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import static com.ganyu.constant.SystemConstant.ARTICLE_STATUS_NORMAL;
import static com.ganyu.constant.SystemConstant.CATEGORY_STATUS_NORMAL;

/**
 * DATA: 2022/8/29
 * Author: 晨夕
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 查询文章类别
     */
    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult<CategoryVo> getCategory() {
        //1.查询文章是否为正式文章，并放入集合中
        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
        articleQuery.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(articleQuery);
        //2.获取文章的类型id
        Set<Long> categorySet = articles.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());//包含了去重，set集合是无序且不可重复
        //3.查询文章类别，并且为正常状态
        //3.1 获取文章类别
        List<Category> categories = listByIds(categorySet);
        //2. 文章类别状态 字段status: 0表示正常，1表示禁用
        categories.stream()
                .filter(category -> category.getStatus().equals(CATEGORY_STATUS_NORMAL))
                .collect(Collectors.toList());

        //2. bean拷贝
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
