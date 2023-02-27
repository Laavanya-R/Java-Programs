import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LeaveTrackerDriver {
    public static ArrayList<Employee> employeeDatabase = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){

        initializeEmployeeDatabase();
        mainMenu();
    }

    static void mainMenu(){

        System.out.println("Welcome to Employee Leave Tracker");
        System.out.print("Login using EmployeeID : "); //EMP001, EMP002, EMP003, EMP004
        String empID = sc.next();
        boolean validUser = false;
        String continueToMainMenu;

            for (Employee employee : employeeDatabase) {
                if (employee.getEmpID().equalsIgnoreCase(empID)) {

                        validUser = true;

                        do {
                            if (employee.getEmpRole().equalsIgnoreCase("Admin"))
                                adminMenu(employee);
                            else if (employee.getEmpRole().equalsIgnoreCase("TeamMember"))
                                teamMemberMenu(employee);

                            System.out.println("Go to Main Menu? Y/N : ");
                            continueToMainMenu = sc.next();
                            if (continueToMainMenu.equalsIgnoreCase("N")) {
                                System.out.println("Thank you for using Employee Leave Tracker!");
                                break;
                            }
                        } while (continueToMainMenu.equalsIgnoreCase("Y"));
                        break;

                }
            }
            if(validUser = false)
                System.out.println("Employee does not exist in our database. Login again with valid Employee ID");
    }


    static void adminMenu(Employee employee) {
        System.out.print("As Admin, continue Leave Management for \n\tSelf? Enter 1 : \n\tTeam Members? Enter 2 : ");
        int adminMenuChoice = sc.nextInt();

        if (adminMenuChoice == 1)
            teamMemberMenu(employee);
        else if (adminMenuChoice == 2)
            teamMemberLeaveManagementByAdmin(employee);
        else
            System.out.println("Invalid Input. Going back to Previous Menu.");
    }

    static void teamMemberMenu(Employee employee) {
        System.out.print("Continue to \n\tCheck Balance? Enter 1 : \n\tApply Leave? Enter 2 : \n\tCancel Leave? Enter 3 : \n\tAdd Leave? Enter 4 : ");
        int teamMemberMenuChoice = sc.nextInt();
        if (teamMemberMenuChoice == 1)
            checkBalance(employee);
        else if (teamMemberMenuChoice == 2)
            applyLeave(employee);
        else if (teamMemberMenuChoice == 3)
            cancelLeave(employee);
        else
            System.out.println("Going back to Main Menu");

    }

    static void checkBalance(Employee employee){
        System.out.println("Available yearly CASUAL leave balance is - " + employee.getYearlyCasualLeaveBalance());
        System.out.println("Available yearly SICK leave balance is - " + employee.getYearlySickLeaveBalance());
    }
    static void applyLeave(Employee employee){
        System.out.println("Below is the calendar. Leave can be applied for current month only");

        System.out.println("Below is your Casual and Sick leave balance");
        checkBalance(employee);

        System.out.println("Enter leave type that you wish to apply for (Enter 'Sick' / 'Casual') - ");
        String leaveType = sc.next();
        if(!leaveType.equalsIgnoreCase("sick") || !leaveType.equalsIgnoreCase("casual"))
            System.out.println("Not a valid leave Type"); //do while to get input again

            System.out.print("Enter leave start date (Enter in format 'dd/mm/yyyy') : ");
            String leaveStartDate = sc.next();
            System.out.print("Enter leave end date (Enter in format 'dd/mm/yyyy') : ");
            String leaveEndDate = sc.next();


            int startDate = Integer.parseInt(leaveStartDate.substring(0,2));
            int startMonth = Integer.parseInt(leaveStartDate.substring(3,5));
            int startYear = Integer.parseInt(leaveStartDate.substring(6));


            int endDate = Integer.parseInt(leaveEndDate.substring(0,2));
            int endMonth = Integer.parseInt(leaveEndDate.substring(3,5));
            int endYear = Integer.parseInt(leaveEndDate.substring(6));

            if(checkIfDatesInCurrentMonth(startMonth, endMonth)){

                int noOfLeaveDaysCount;

                if(leaveType.equalsIgnoreCase("Sick")) {

                    if(isWeekEndIncluded(startDate, endDate))
                        noOfLeaveDaysCount = endDate - startDate - 2;
                    else
                        noOfLeaveDaysCount = endDate - startDate;

                    int currentSickLeaveBalance = employee.getYearlySickLeaveBalance();

                    if (noOfLeaveDaysCount >= currentSickLeaveBalance) {
                        int newLeaveBalance = currentSickLeaveBalance - noOfLeaveDaysCount;
                        employee.setYearlySickLeaveBalance(newLeaveBalance);
                    } else
                        System.out.print("Available sick leave balance is less than the days applied for");
                }
                else if(leaveType.equalsIgnoreCase("Casual")){

                        noOfLeaveDaysCount = endDate - startDate;

                    int currentCasualLeaveBalance = employee.getYearlyCasualLeaveBalance();

                    if (noOfLeaveDaysCount >= currentCasualLeaveBalance) {
                        int newLeaveBalance = currentCasualLeaveBalance - noOfLeaveDaysCount;
                        employee.setYearlyCasualLeaveBalance(newLeaveBalance);

                        //update leave record
                        Leave newLeaveEntry = new Leave();

                        if(employee.getEmpLeaveRecord().size() != 0) {
                            int lastEntryPosition = employee.getEmpLeaveRecord().size() - 1;
                            String lastLeaveID = employee.getEmpLeaveRecord().get(lastEntryPosition).getLeaveID();
                            int leaveIDNumber = Integer.parseInt(leaveEndDate.substring(3));
                            String newLeaveID = "LVE" + (leaveIDNumber+1);
                            newLeaveEntry.setLeaveID(newLeaveID);
                        }
                        else
                            newLeaveEntry.setLeaveID("LVE001");


                        newLeaveEntry.setLeaveType(leaveType);
                        newLeaveEntry.setLeaveStartDate(leaveStartDate);
                        newLeaveEntry.setLeaveEndDate(leaveEndDate);
                        newLeaveEntry.setLeaveStatus("Applied");
                        employee.getEmpLeaveRecord().add(newLeaveEntry);

                        System.out.print("Leave successfully applied");
                    } else
                        System.out.print("Available casual leave balance is less than the days applied for");
                }
            }
            else
                System.out.print("Leave can be applied only for current month");

    }
    static void cancelLeave(Employee employee){
        displayAppliedLeaveRecords(employee);
        System.out.println("Enter leave ID that needs cancelling : ");
        String leaveID = sc.next();
        boolean validLeaveID = false;

        for(Leave leave:employee.getEmpLeaveRecord())
            if(leave.getLeaveID().equalsIgnoreCase(leaveID)){
                validLeaveID = true;
                leave.setLeaveStatus("Cancelled");
                System.out.print("Leave cancelled successfully");
            }

        if(!validLeaveID)
            System.out.println("Not a valid ID. Going back to previous menu");
    }
    static void teamMemberLeaveManagementByAdmin(Employee employee){
        displayTeamMembers(employee);
        System.out.print("Choose the Employee ID and enter here : ");
        String empIDOfTeamMember = sc.next();
        for (Employee employeeTeamMember : employeeDatabase)
            if (employeeTeamMember.getEmpID().equalsIgnoreCase(empIDOfTeamMember)) {
                teamMemberMenu(employeeTeamMember);
                break;
            }
    }

    static boolean checkIfDatesInCurrentMonth(int startMonth, int endMonth){
        int currentMonth = 03;
        if(startMonth == currentMonth && endMonth == currentMonth)
            return true;
        else
            return false;
    }

    static void  displayTeamMembers(Employee employee) {
        for (Employee employeeTeamMembers : employeeDatabase) {
            if (employeeTeamMembers.getReportingManagerEmpID().equalsIgnoreCase(employee.getEmpID()))
                System.out.println("Emp ID : " + employee.getEmpID() + "\tEmployee Name : " + employee.getEmpName());
        }
    }

    static void displayAppliedLeaveRecords(Employee employee){
        for(Leave leave:employee.getEmpLeaveRecord())
            if(leave.getLeaveStatus().equalsIgnoreCase("Applied"))
            {
                System.out.println("Leave ID : " + leave.getLeaveID() + "\tLeave Type : " + leave.getLeaveType()
                        + "\tLeave Start Date : " + leave.getLeaveStartDate() + "\tLeave End Date : " + leave.getLeaveEndDate()
                        + "\tLeave Status : " + leave.getLeaveStatus());
            }

    }

    static boolean isWeekEndIncluded(int startDate, int endDate){
        ArrayList<Integer> weekendDates = new ArrayList<>(Arrays.asList(6, 7, 13, 14, 20, 21, 27, 28));

        for(int i = 0; i< weekendDates.size() ; )
        {
            if (startDate < weekendDates.get(i) && endDate > weekendDates.get(i + 1))
                return true;
            i = i+2;
        }
        return false;
    }

    static void initializeEmployeeDatabase(){
        //Employee(String empID, String empName, String empRole, int yearlySickLeaveBalance, int yearlyCasualLeaveBalance)
        Employee employee1 = new Employee("EMP001", "Employee1", "Admin", 20, 25, "EMP009");
        Employee employee2 = new Employee("EMP002", "Employee2", "TeamMember", 10, 15, "EMP001");
        Employee employee3 = new Employee("EMP003", "Employee3", "TeamMember", 10, 15, "EMP001");
        Employee employee4 = new Employee("EMP004", "Employee4", "TeamMember", 10, 15, "EMP001");
        employeeDatabase.add(employee1);
        employeeDatabase.add(employee2);
        employeeDatabase.add(employee3);
        employeeDatabase.add(employee4);

    }
}
