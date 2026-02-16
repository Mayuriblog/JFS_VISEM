Step-by-Step: One-to-Many Mapping in Hibernate (Eclipse)
🔹 Step 1: Create Maven Project in Eclipse

Open Eclipse

Go to
👉 File → New → Maven Project

Select
👉 Create a simple project

Enter:

Group Id: com.example
Artifact Id: HibernateOneToMany


Click Finish

🔹 Step 2: Add Dependencies (pom.xml)

Open pom.xml and add:

<dependencies>

    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.4.4.Final</version>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>

    <!-- JPA -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.1.0</version>
    </dependency>

</dependencies>


👉 Right click project → Maven → Update Project

🔹 Step 3: Create Package Structure

Inside src/main/java create:

com.example.entity
com.example.util
com.example.main

🔹 Step 4: Create Parent Entity (Department)
package com.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy="department", cascade=CascadeType.ALL)
    private List<Employee> employees;

    // getters and setters
}

🔹 Step 5: Create Child Entity (Employee)
package com.example.entity;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name="dept_id")
    private Department department;

    // getters and setters
}

🔹 Step 6: Create Hibernate Configuration

Create file:

src/main/resources/hibernate.cfg.xml

<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
 <session-factory>

  <property name="hibernate.connection.driver_class">
      com.mysql.cj.jdbc.Driver
  </property>

  <property name="hibernate.connection.url">
      jdbc:mysql://localhost:3306/testdb
  </property>

  <property name="hibernate.connection.username">root</property>
  <property name="hibernate.connection.password">root</property>

  <property name="hibernate.dialect">
      org.hibernate.dialect.MySQLDialect
  </property>

  <property name="hibernate.hbm2ddl.auto">update</property>
  <property name="hibernate.show_sql">true</property>

  <mapping class="com.example.entity.Department"/>
  <mapping class="com.example.entity.Employee"/>

 </session-factory>
</hibernate-configuration>

🔹 Step 7: Create Hibernate Utility Class
package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory factory =
            new Configuration().configure().buildSessionFactory();

    public static SessionFactory getFactory() {
        return factory;
    }
}

🔹 Step 8: Create Main Class
package com.example.main;

import org.hibernate.Session;
import com.example.entity.*;
import com.example.util.HibernateUtil;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getFactory().openSession();

        Department dept = new Department();
        dept.setName("CSE");

        Employee e1 = new Employee();
        e1.setName("Krishna");

        Employee e2 = new Employee();
        e2.setName("Rao");

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

🔹 Step 9: Create Database

In MySQL:

create database testdb;

🔹 Step 10: Run the Project

Right Click →
👉 Run As → Java Application

Hibernate will automatically create:

department table

employee table

foreign key

🔥 Important Tips (Very Important)

✔ Many side owns relationship
✔ Foreign key always in child table
✔ Always set both sides (dept + employee)
✔ Use cascade to auto save children