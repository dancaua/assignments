package mainapp;

import org.hibernate.Session;
import p1.*;

import java.util.*;

public class MainRunner_ManyToMany {

    static int id = 101;
    static int wsId = 700;
    static int computerId = 500;
    static long phoneNumber = 484848485L;

    public static void main(String[] args) {

        Session hibernate = SessionFactoryUtil.getFactory().openSession();

        new MainRunner_ManyToMany().doSaveEntity(hibernate);

        hibernate.close();

    }

    public void doSaveEntity(Session hibernate) {
        hibernate.beginTransaction();


        Employee e1 = getEmployee();
        Employee e2 = getEmployee();
        Employee e3 = getEmployee();
        Employee e4 = getEmployee();
        Employee e5 = getEmployee();
        Employee e6 = getEmployee();



        Project project1 = new Project(1101, "Bank-1","Bank 1");
        Project project2 = new Project(1102, "Bank-2","Bank 2");
        Project project3 = new Project(1103, "Bank-3","Bank 3");


        Set<Project> projectsPerEmployee1 = new HashSet<>();
        projectsPerEmployee1.add(project1);

        e1.setProjects(projectsPerEmployee1);

        Set<Project> projectsPerEmployee2 = new HashSet<>();
        projectsPerEmployee2.add(project1);

        e1.setProjects(projectsPerEmployee2);

        List<Employee> forProject1 = new ArrayList<>();
        forProject1.add(e1);
        forProject1.add(e2);
        forProject1.add(e3);

        List<Employee> forProject2 = new ArrayList<>();
        forProject2.add(e4);
        forProject2.add(e5);

        List<Employee> forProject3 = new ArrayList<>();
        forProject3.add(e6);


        project1.setWorkingEmployees(forProject1);
        project2.setWorkingEmployees(forProject2);
        project3.setWorkingEmployees(forProject3);


        // ------------------------------------------------------------------------------------------

        hibernate.save(e1);
        hibernate.save(e2);

        hibernate.save(project1);
        hibernate.save(project2);
        hibernate.save(project3);



        hibernate.getTransaction().commit();
    }

    public Employee getEmployee()
    {
        Employee e = new Employee((id++), "a2", "a"+id+"@gmail.com", new Random().nextInt(5000));
        e.setAge(30);

        // ------------------------------------------------------------------------------------------
        Address empAddress = new Address("Abc-123", "Mumbai", "India");
        e.setPeraddress(empAddress);

        Address empAddress2 = new Address("xyz-123", "NewYork", "USA");
        e.setTemporaryAddress(empAddress2);

        EmployeeDependent dep1 = new EmployeeDependent("a", 25, "Male", "Brother");
        EmployeeDependent dep2 = new EmployeeDependent("b", 65, "Female", "Mother");
        EmployeeDependent dep3 = new EmployeeDependent("b", 25, "Female", "Wife");

        List<EmployeeDependent> dependentList = Arrays.asList(dep1, dep2, dep3);
        e.setAllDependents(dependentList);

        // ------------------------------------------------------------------------------------------
        /* One to One */

        WorkStation workStation = new WorkStation(wsId++, computerId++, phoneNumber+=200);
        e.setWorkStation(workStation);
        // ------------------------------------------------------------------------------------------

        return e;
    }

}
