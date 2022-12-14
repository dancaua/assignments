package p1;

import java.util.List;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    private int projectid;
    private String projectName;
    private String clientName;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> workingEmployees;

    public Project() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Project(int projectid, String projectName, String clientName) {
        super();
        this.projectid = projectid;
        this.projectName = projectName;
        this.clientName = clientName;
    }

    public List<Employee> getWorkingEmployees() {
        return workingEmployees;
    }

    public void setWorkingEmployees(List<Employee> workingEmployees) {
        this.workingEmployees = workingEmployees;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


}
