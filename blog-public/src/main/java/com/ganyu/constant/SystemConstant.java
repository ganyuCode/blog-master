package com.ganyu.constant;

/**
 * DATA: 2022/8/26
 * Author: 晨夕
 */
public class SystemConstant {

    public static final int ARTICLE_STATUS_NORMAL = 0; //表示文章是正常状态
    public static final int ARTICLE_STATUS_ABNORMAL = 1; //表示文章是草稿或已删除状态

    public static final int ARTICLE_PAGE_NUMBER = 1; //展示的文章页码
    public static final int ARTICLE_NUMBER = 10; //每页显示10条数据

    public static final int CATEGORY_STATUS_NORMAL = 0; //表示文章类别是正常状态
    public static final int CATEGORY_STATUS_ABNORMAL = 1; //表示文章类别是禁用状态

    public static final int LINK_STATUS_PASS = 0; //审核通过
    public static final int LINK_STATUS_NOPASS = 1; //审核未通过
    public static final int LINK_NOAUDIT = 2; //审核未通过
}
