package com.ganyu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DATA: 2022/8/30
 * Author: 晨夕
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkListVo {
    private Long id;

    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
}
