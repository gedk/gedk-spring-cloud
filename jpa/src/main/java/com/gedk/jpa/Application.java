package com.gedk.jpa;

import com.gedk.jpa.dao.entity.User;
import com.gedk.jpa.util.JPAUtil;

import javax.persistence.EntityManager;


/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 10:34
 */
public class Application {

    public static void main(String[] args) {
        User user = new User();
        user.setName("gedk");
        EntityManager entityManger = JPAUtil.getEntityManger();
        entityManger.getTransaction().begin();
        entityManger.persist(user);
        entityManger.getTransaction().commit();
        entityManger.close();
    }
}
