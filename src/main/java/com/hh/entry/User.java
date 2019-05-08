/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: User.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月08日, Create
 */
package com.hh.entry;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
