package com.gedk.jpa;
import com.gedk.jpa.dao.entity.one2many.Department;
import com.gedk.jpa.dao.entity.one2many.Employee;
import com.gedk.jpa.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/6 15:55
 */
public class ManyToOneTest {
    @Test
    public void save(){
        Department department = new Department();
        department.setName("dev");

        Employee e1 = new Employee();
        e1.setName("zhangsan");
        e1.setDepartment(department);

        Employee e2 = new Employee();
        e2.setName("lisi");
        e2.setDepartment(department);

        EntityManager entityManger = JPAUtil.getEntityManger();
        entityManger.getTransaction().begin();
        entityManger.persist(department);
        entityManger.persist(e1);
        entityManger.persist(e2);
        entityManger.getTransaction().commit();
        entityManger.close();
    }

    @Test
    public void query(){
        EntityManager entityManger = JPAUtil.getEntityManger();
        Employee employee = entityManger.find(Employee.class, 1L);
        System.out.println(employee.getDepartment());
    }
}
