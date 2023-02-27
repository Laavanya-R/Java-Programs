import java.util.ArrayList;
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
                        System.out.print("Continue as \n\tAdmin? Enter 1 : \n\tTeam Member? Enter 2 : ");
                        int userMainMenuChoice = sc.nextInt();

                        if (userMainMenuChoice == 1)
                            adminMenu(employee);
                        else if (userMainMenuChoice == 2)
                            teamMemberMenu(employee);
                        else
                            System.out.println("Not a Valid Input");

                        System.out.println("Go to Main Menu? Y/N : ");
                        continueToMainMenu = sc.next();
                        if(continueToMainMenu.equalsIgnoreCase("N")) {
                            System.out.println("Thank you for using Employee Leave Tracker!");
                            break;
                        }
                        if(!continueToMainMenu.equalsIgnoreCase("N") && continueToMainMenu.equalsIgnoreCase("Y")) {
                            System.out.println("Not a valid input, going back to main menu");
                            continue;
                        }
                    }while(continueToMainMenu.equalsIgnoreCase("Y"));
                    break;
                }
            }
            if(validUser = false)
                System.out.println("Employee does not exist in our database. Login again with valid Employee ID");
    }


static void adminMenu(Employee employee) {
    System.out.print("As Admin, continue Leave Management for \n\tSelf? Enter 1 : \n\tTeam Member? Enter 2 : ");
    int adminMenuChoice = sc.nextInt();

    if (adminMenuChoice == 1) {
        System.out.print("For Self, continue to \n\tCheck Balance? Enter 1 : \n\tApply Leave? Enter 2 : \n\tCancel Leave? Enter 3 : ");
        int adminSelfMenuChoice = sc.nextInt();

        if (adminSelfMenuChoice == 1)
            checkBalance(employee);
        else if (adminSelfMenuChoice == 2)
            applyLeave(employee);
        else if (adminSelfMenuChoice == 3)
            cancelLeave(employee);
        else
            System.out.println("Going back to Main Menu");

    } else if (adminMenuChoice == 2) {
        teamMemberLeaveManagementByAdmin(employee);

    }else {
        System.out.println("Invalid Input");
    }

}
    static void teamMemberMenu(Employee employee) {
        System.out.print("As Team Member, continue to \n\tCheck Balance? Enter 1 : \n\tApply Leave? Enter 2 : \n\tCancel Leave? Enter 3 : ");
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
        System.out.println("Available yearly CASUAL leave balance is - " + employeeDatabase.get(employeeDatabase.indexOf(employee)).getYearlyCasualLeaveBalance());
        System.out.println("Available yearly SICK leave balance is - " + employeeDatabase.get(employeeDatabase.indexOf(employee)).getYearlySickLeaveBalance());
    }
    static void applyLeave(Employee employee){
        System.out.println("Below is the calendar. Leave can be applied for current month only");
        displayCalendar();

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

                    if(isWeekEndIncluded(leaveStartDate, leaveEndDate))
                        noOfLeaveDaysCount = endDate - startDate - 2;
                    else
                        noOfLeaveDaysCount = endDate - startDate;

                    int currentSickLeaveBalance = employeeDatabase.get(employeeDatabase.indexOf(employee)).getYearlySickLeaveBalance();

                    if (noOfLeaveDaysCount >= currentSickLeaveBalance) {
                        int newLeaveBalance = currentSickLeaveBalance - noOfLeaveDaysCount;
                        employeeDatabase.get(employeeDatabase.indexOf(employee)).setYearlySickLeaveBalance(newLeaveBalance);
                    } else
                        System.out.print("Available sick leave balance is less than the days applied for");
                }
                else if(leaveType.equalsIgnoreCase("Casual")){

                        noOfLeaveDaysCount = endDate - startDate;

                    int currentCasualLeaveBalance = employeeDatabase.get(employeeDatabase.indexOf(employee)).getYearlyCasualLeaveBalance();

                    if (noOfLeaveDaysCount >= currentCasualLeaveBalance) {
                        int newLeaveBalance = currentCasualLeaveBalance - noOfLeaveDaysCount;
                        employeeDatabase.get(employeeDatabase.indexOf(employee)).setYearlyCasualLeaveBalance(newLeaveBalance);

                        //update leave record
                        Leave newLeaveEntry = new Leave();
                        int lastEntryPosition = employeeDatabase.get(employeeDatabase.indexOf(employee)).getEmpLeaveRecord().size() - 1 ;
                        employeeDatabase.get(employeeDatabase.indexOf(employee)).getEmpLeaveRecord().get(lastEntryPosition);
                        newLeaveEntry.setLeaveID("LVE001"); //Get the last entry and increment
                        newLeaveEntry.setLeaveType(leaveType);
                        newLeaveEntry.setLeaveStartDate(leaveStartDate);
                        newLeaveEntry.setLeaveEndDate(leaveEndDate);
                        newLeaveEntry.setLeaveStatus("Applied");
                        employeeDatabase.get(employeeDatabase.indexOf(employee)).getEmpLeaveRecord().add(newLeaveEntry);

                        System.out.print("Leave successfully applied");
                    } else
                        System.out.print("Available casual leave balance is less than the days applied for");
                }
            }
            else
                System.out.print("Leave can be applied only for current month");

    }
    static void cancelLeave(Employee employee){
        displayAppliedLeaveRecords();

        //choose the record by ID
        //Update the DB with status cancelled


    }
    static void teamMemberLeaveManagementByAdmin(Employee employee){
        System.out.print("For chosen Team Member, continue to \n\tCheck Balance? Enter 1 : \n\tApply Leave? Enter 2 : \n\tCancel Leave? Enter 3 : ");
        int adminTeamMemberMenuChoice = sc.nextInt();

        if (adminTeamMemberMenuChoice == 1)
            checkBalance(employee);
        else if (adminTeamMemberMenuChoice == 2)
            applyLeave(employee);
        else if (adminTeamMemberMenuChoice == 3)
            cancelLeave(employee);
        else
            System.out.println("Going back to Main Menu");
    }
    static boolean checkIfDatesInCurrentMonth(int startMonth, int endMonth){
        int currentMonth = 03;
        if(startMonth == currentMonth && endMonth == currentMonth)
            return true;
        else
            return false;
    }
    static void setCalendar(){

    }
    static void displayCalendar(){



    }

    static void displayAppliedLeaveRecords(){

    }

    static boolean isWeekEndIncluded(String leaveStartDate, String leaveEndDate){
        return true;
    }
    static boolean isValidEmployee(String empID){
        for(Employee employee: employeeDatabase)
            if(employee.getEmpID().equalsIgnoreCase(empID))
                return true;

        return false;
    }
    static void initializeEmployeeDatabase(){
        //Employee(String empID, String empName, String empRole, int yearlySickLeaveBalance, int yearlyCasualLeaveBalance)
        Employee employee1 = new Employee("EMP001", "Employee1", "Admin", 20, 25);
        Employee employee2 = new Employee("EMP002", "Employee2", "TeamMember", 10, 15);
        Employee employee3 = new Employee("EMP003", "Employee3", "TeamMember", 10, 15);
        Employee employee4 = new Employee("EMP004", "Employee4", "TeamMember", 10, 15);
        employeeDatabase.add(employee1);
        employeeDatabase.add(employee2);
        employeeDatabase.add(employee3);
        employeeDatabase.add(employee4);

    }
}
