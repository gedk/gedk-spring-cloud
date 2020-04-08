package com.gedk.jpa.dao.entity.many2one;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 16:16
 */
@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private List<Article> articles;
}
