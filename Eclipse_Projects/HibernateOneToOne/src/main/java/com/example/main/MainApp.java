package com.example.main;

import org.hibernate.Session;
import com.example.entity.*;
import com.example.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getFactory().openSession();

        Person p = new Person();
        p.setName("Balaji");

        Passport pass = new Passport();
        pass.setNumber("IND2025XYZ");

        p.setPassport(pass);

        session.beginTransaction();
        session.persist(p);
        session.getTransaction().commit();

        session.close();
    }
}