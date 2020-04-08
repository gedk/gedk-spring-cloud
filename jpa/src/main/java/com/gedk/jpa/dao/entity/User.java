package com.gedk.jpa.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 10:45
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
