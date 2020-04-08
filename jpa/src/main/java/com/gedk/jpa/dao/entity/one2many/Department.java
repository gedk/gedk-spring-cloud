package com.gedk.jpa.dao.entity.one2many;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 11:57
 */
@Data
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
