package com.example.main;

import org.hibernate.Session;
import com.example.entity.*;
import com.example.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getFactory().openSession();

        Department dept = new Department();
        dept.setName("CSE");

        Employee e1 = new Employee();
        e1.setName("Anudeep KV");

        Employee e2 = new Employee();
        e2.setName("Anil Ravipudi");

        e1.setDepartment(dept);
        e2.setDepartment(dept);

       // dept.setEmployees(List.of(e1,e2));
	   List<Employee> empList = new ArrayList<Employee>();
        empList.add(e1);
        empList.add(e2);
        dept.setEmployees(empList);

        session.beginTransaction();
        session.persist(dept);
        session.getTransaction().commit();

        session.close();
    }
}