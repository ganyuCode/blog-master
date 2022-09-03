package com.ganyu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DATA: 2022/8/29
 * Author: 晨夕
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    //内容
    private List<ArticleListVo> rows;
    //数量
    private Long total;
}
