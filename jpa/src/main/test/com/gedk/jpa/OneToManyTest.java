package com.gedk.jpa;

import com.gedk.jpa.dao.entity.many2one.Article;
import com.gedk.jpa.dao.entity.many2one.Author;
import com.gedk.jpa.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 16:15
 */
public class OneToManyTest {
    @Test
    public void save(){
        Author author = new Author();
        author.setName("gedk");

        Article a1 = new Article();
        a1.setTitle("title1");

        Article a2 = new Article();
        a2.setTitle("title2");

        List<Article> articles = new ArrayList<Article>();
        articles.add(a1);
        articles.add(a2);

        author.setArticles(articles);

        EntityManager entityManger = JPAUtil.getEntityManger();
        entityManger.getTransaction().begin();
        entityManger.persist(a1);
        entityManger.persist(a2);
        entityManger.persist(author);
        entityManger.getTransaction().commit();
        entityManger.close();
    }

    @Test
    public void query(){

    }
}
