package com.skillnext1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;
import com.skillnext1.Employee;

public class App {

    static int menu(Scanner sc) {
        System.out.println(
            "\n1. Insert\n2. Retrieve\n3. Update\n4. Delete\n5. Exit\nEnter your choice:"
        );
        return sc.nextInt();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();

        while (true) {
            int choice = menu(sc);
            sc.nextLine(); // buffer clear

            switch (choice) {

                // INSERT
                case 1:
                    Transaction tx1 = session.beginTransaction();

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter salary: ");
                    double salary1 = sc.nextDouble();

                    Employee emp1 = new Employee(name, email, salary1);
                    session.persist(emp1);

                    tx1.commit();
                    System.out.println("Employee inserted successfully!");
                    break;

                // RETRIEVE (ORDER BY NAME)
                case 2:
                    List<Employee> list =
                        session.createQuery(
                            "from Employee e order by e.name",
                            Employee.class
                        ).list();

                    for (Employee e : list)
                        System.out.println(e.getId()+" "+e.getName()+" "+e.getEmail()+" "+e.getSalary());
                    break;

                // UPDATE
                case 3:
                    Transaction tx3 = session.beginTransaction();

                    System.out.print("Enter employee id: ");
                    int id1 = sc.nextInt();

                    Employee emp2 = session.get(Employee.class, id1);

                    if (emp2 == null) {
                        System.out.println("Record not found");
                        tx3.rollback();
                    } else {
                        System.out.print("Enter new salary: ");
                        double salary2 = sc.nextDouble();
                        emp2.setSalary(salary2);
                        tx3.commit();
                        System.out.println("Employee updated successfully!");
                    }
                    break;

                // DELETE
                case 4:
                    Transaction tx4 = session.beginTransaction();

                    System.out.print("Enter employee id: ");
                    int id2 = sc.nextInt();

                    Employee emp3 = session.get(Employee.class, id2);

                    if (emp3 == null) {
                        System.out.println("Record not found");
                        tx4.rollback();
                    } else {
                        session.remove(emp3);
                        tx4.commit();
                        System.out.println("Employee deleted successfully!");
                    }
                    break;

                // EXIT
                case 5:
                    session.close();
                    System.out.println("Application closed.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
