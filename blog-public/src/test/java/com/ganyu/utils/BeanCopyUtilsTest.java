package com.ganyu.utils;

import com.ganyu.domain.entity.Article;
import com.ganyu.domain.vo.HotArticleVo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DATA: 2022/8/28
 * Author: 晨夕
 */
public class BeanCopyUtilsTest {
    @Test
    public void test(){
        Article article = new Article();
        article.setId(1L);
        article.setTitle("一千零一夜");
        article.setViewCount(43L);

        HotArticleVo hotArticleVo = BeanCopyUtils.copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo.getTitle());
        System.out.println(hotArticleVo.getViewCount());

        Article article1 = new Article();
        article1.setId(1L);
        article1.setTitle("一千零一夜");
        article1.setViewCount(43L);
        Article article2 = new Article();
        article2.setId(1L);
        article2.setTitle("一千零一夜");
        article2.setViewCount(43L);
        Article article3 = new Article();
        article3.setId(1L);
        article3.setTitle("一千零一夜");
        article3.setViewCount(43L);
        Article article4 = new Article();
        article4.setId(1L);
        article4.setTitle("一千零一夜");
        article4.setViewCount(43L);

        ArrayList<Article> articles = new ArrayList<>(Arrays.asList(article, article1, article2, article3, article4));


        System.out.println(BeanCopyUtils.copyBeanList(articles, HotArticleVo.class));

    }
}
