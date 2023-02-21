import java.util.HashMap;

public class Employee {

    private String employeeID;
    private String employeeName;
    private int hierarchyLevel;
    private HashMap<Goal, Integer> listOfGoals = new HashMap<>(); //Integer is Rating
    private Boolean weightageEnabledForGoals = false; // true/false
    private HashMap<Assignment, Integer> listOfAssignments = new HashMap<>();
    private Boolean weightageEnabledForAssignments = false; // true/false
    private HashMap<Project, Integer> listOfProjects = new HashMap<>();
    private Boolean weightageEnabledForProjects = false; // true/false
    private String reportingManagerID;
    Employee(){

    }
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(int hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getReportingManagerID() {
        return reportingManagerID;
    }

    public void setReportingManagerID(String reportingManagerID) {
        this.reportingManagerID = reportingManagerID;
    }

    public HashMap<Goal, Integer> getListOfGoals() {
        return listOfGoals;
    }

    public Boolean getWeightageEnabledForGoals() {
        return weightageEnabledForGoals;
    }

    public void setWeightageEnabledForGoals(Boolean weightageEnabledForGoals) {
        this.weightageEnabledForGoals = weightageEnabledForGoals;
    }

    public HashMap<Assignment, Integer> getListOfAssignments() {
        return listOfAssignments;
    }


    public Boolean getWeightageEnabledForAssignments() {
        return weightageEnabledForAssignments;
    }

    public void setWeightageEnabledForAssignments(Boolean weightageEnabledForAssignments) {
        this.weightageEnabledForAssignments = weightageEnabledForAssignments;
    }

    public HashMap<Project, Integer> getListOfProjects() {
        return listOfProjects;
    }

    public Boolean getWeightageEnabledForProjects() {
        return weightageEnabledForProjects;
    }

    public void setWeightageEnabledForProjects(Boolean weightageEnabledForProjects) {
        this.weightageEnabledForProjects = weightageEnabledForProjects;
    }

}
