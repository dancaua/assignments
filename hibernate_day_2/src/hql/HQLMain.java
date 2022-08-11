package hql;

import mainapp.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import p1.Employee;

import java.util.List;

public class HQLMain {
    public static void main(String[] args) {

        HQLMain obj = new HQLMain();
        Session hibernate = SessionFactoryUtil.getFactory().openSession();

        obj.extractDataThroughWhereClause3(hibernate);

        hibernate.close();
    }

    public void extractDataThroughWhereClause3(Session hibernate) {
        String hql = "FROM Project WHERE Project.projectid IN " +
                "(SELECT Project.projectid FROM Project " +
                "JOIN Employee_Project " +
                "ON Employee_Project.projectid = Project.projectid " +
                "JOIN Employee " +
                "ON Employee.id = Employee_Project.id) " +
                "WHERE Employee.id =: empId)";

        Query q = hibernate.createQuery(hql);
        q.setInteger("empId", 101);
        List<String> allEmployees = q.list();
        System.out.println(allEmployees.size());
        allEmployees.forEach(System.out::println);
    }

    public void extratDataThroughWhereClause(Session hibernate) {

        String hql = "from Employee";

        Query q = hibernate.createQuery(hql);
        List<Employee> allEmployees = q.list();
        System.out.println("employess found " + allEmployees.size());

        for (Employee employee : allEmployees) {
            System.out.println(employee);
        }

    }

    public void extractDataThroughWhereClause2(Session hibernate) {

        String hql = "from Employee where salary > :filterSalary";

        Query q = hibernate.createQuery(hql);
        q.setInteger("filterSalary", 2500);
        List<Employee> allEmployees = q.list();
        System.out.println(allEmployees.size());

        for (Employee employee : allEmployees) {
            System.out.println(employee);
        }
    }
}
