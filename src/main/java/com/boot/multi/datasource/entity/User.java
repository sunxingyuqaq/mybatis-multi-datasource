package com.boot.multi.datasource.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Xingyu Sun
 * @date 2018/6/4 14:29
 */
@Data
public class User {

    private Integer id;

    private String name;

    private Date created;
}
