package com.gedk.jpa;

import com.gedk.jpa.dao.entity.User;
import com.gedk.jpa.util.JPAUtil;

import javax.persistence.EntityManager;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 11:19
 */
public class Test {
    @org.junit.Test
    public void add(){
        User user = new User();
        user.setName("gedk");
        EntityManager entityManger = JPAUtil.getEntityManger();
        entityManger.getTransaction().begin();
        entityManger.persist(user);
        entityManger.getTransaction().commit();
        entityManger.close();
    }

    @org.junit.Test
    public void find(){
        EntityManager entityManger = JPAUtil.getEntityManger();
        entityManger.getTransaction().begin();
        User user = entityManger.find(User.class, 1);
        user.setName("ddd");
        System.out.println(user.getName());
        entityManger.getTransaction().commit();
        entityManger.close();
    }

    @org.junit.Test
    public void update(){
        EntityManager entityManger = JPAUtil.getEntityManger();
        entityManger.getTransaction().begin();
        User user = entityManger.find(User.class, 1);
        user.setName("gedekun1");
        entityManger.persist(user);
        entityManger.getTransaction().commit();
        entityManger.close();
    }
}
