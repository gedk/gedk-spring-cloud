package com.gedk.jpa.dao.entity.one2many;

import lombok.Data;

import javax.persistence.*;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 13:10
 */
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
