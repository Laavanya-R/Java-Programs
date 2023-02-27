import java.util.ArrayList;

public class Employee {

    private String empID;
    private String empName;
    private String empRole;
    private int yearlySickLeaveBalance;
    private int yearlyCasualLeaveBalance;
    private ArrayList<Leave> empLeaveRecord;
    private String reportingManagerEmpID;


    public Employee(String empID, String empName, String empRole, int yearlySickLeaveBalance, int yearlyCasualLeaveBalance, ArrayList<Leave> empLeaveRecord) {
        this.empID = empID;
        this.empName = empName;
        this.empRole = empRole;
        this.yearlySickLeaveBalance = yearlySickLeaveBalance;
        this.yearlyCasualLeaveBalance = yearlyCasualLeaveBalance;
        this.empLeaveRecord = empLeaveRecord;
    }

    Employee(String empID, String empName, String empRole, int yearlySickLeaveBalance, int yearlyCasualLeaveBalance, String reportingManagerEmpID){
        this.empID = empID;
        this.empName = empName;
        this.empRole = empRole;
        this.yearlySickLeaveBalance = yearlySickLeaveBalance;
        this.yearlyCasualLeaveBalance = yearlyCasualLeaveBalance;
        this.reportingManagerEmpID = reportingManagerEmpID;

    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public int getYearlySickLeaveBalance() {
        return yearlySickLeaveBalance;
    }

    public void setYearlySickLeaveBalance(int yearlySickLeaveBalance) {
        this.yearlySickLeaveBalance = yearlySickLeaveBalance;
    }

    public int getYearlyCasualLeaveBalance() {
        return yearlyCasualLeaveBalance;
    }

    public void setYearlyCasualLeaveBalance(int yearlyCasualLeaveBalance) {
        this.yearlyCasualLeaveBalance = yearlyCasualLeaveBalance;
    }

    public ArrayList<Leave> getEmpLeaveRecord() {
        return empLeaveRecord;
    }

    public void setEmpLeaveRecord(ArrayList<Leave> empLeaveRecord) {
        this.empLeaveRecord = empLeaveRecord;
    }

    public String getReportingManagerEmpID() {
        return reportingManagerEmpID;
    }

    public void setReportingManagerEmpID(String reportingManagerEmpID) {
        this.reportingManagerEmpID = reportingManagerEmpID;
    }
}
