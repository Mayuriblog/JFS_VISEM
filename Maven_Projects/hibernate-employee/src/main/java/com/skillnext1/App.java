package com.skillnext1;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.skillnext1.Employee;
import com.skillnext1.HibernateUtil;

public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee emp = new Employee(
                "Mayuri",
                "mayuri@gmail.com",
                45000
        );

        session.persist(emp);

        tx.commit();
        session.close();

        System.out.println("Employee inserted successfully!");
    }
}

