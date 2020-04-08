package com.gedk.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 10:55
 */
public class JPAUtil {
    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("myPersistence");
    }
    public static EntityManager getEntityManger(){
        return emf.createEntityManager();
    }
}
