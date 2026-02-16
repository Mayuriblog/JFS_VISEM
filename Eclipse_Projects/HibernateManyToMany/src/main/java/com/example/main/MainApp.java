package com.example.main;

import org.hibernate.Session;
import java.util.Arrays;
import com.example.entity.*;
import com.example.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getFactory().openSession();

        Student s1 = new Student();
        s1.setName("Vanishka");

        Student s2 = new Student();
        s2.setName("Trinayani");

        Course c1 = new Course();
        c1.setTitle("Java");

        Course c2 = new Course();
        c2.setTitle("DBMS");

        s1.setCourses(Arrays.asList(c1,c2));
        s2.setCourses(Arrays.asList(c1));

        session.beginTransaction();
        session.persist(s1);
        session.persist(s2);
        session.getTransaction().commit();

        session.close();
    }
}