import java.util.ArrayList;
import java.util.Scanner;

public class AppraisalForm {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] n)
    {
        initializeEmployees();

        System.out.println("Welcome to Employee Appraisal System");

        int maxLoginCount = 3;
        do {
            System.out.print("Login using Employee ID : ");
            String employeeID = sc.next();

            if(isValidEmployee(employeeID)) {

                //System.out.print("Enter Employee name : ");
                //String employeeName = sc.next();

                Employee employee = new Employee(employeeID);
                //employee.setEmployeeName(employeeName);

                do {

                    System.out.print("\nEnter   '1' to Add Goals for Self\n        '2' for Self Appraisal\n        '3' for Peer appraisal\n Choice : ");
                    int choiceOfAppraisal = sc.nextInt();

                    if (choiceOfAppraisal == 1) {
                        addGoalsForSelf(employee);

                    } else if (choiceOfAppraisal == 2) {
                        appraisal(employee);


                    } else if (choiceOfAppraisal == 3) {
                        peerAppraisal(employee);

                    } else
                        System.out.println("Not a valid choice.");

                    System.out.print("\nContinue to Add goal / Self Appraisal / Peer Appraisal? Y/N : ");
                } while (sc.next().equalsIgnoreCase("Y"));

                System.out.print("\nThank You for using the Employee Appraisal System");
                break;
            }
            else {
                if (maxLoginCount > 1)
                    System.out.print("Not a valid user - Try again");
                else if (maxLoginCount == 1)
                    System.out.print("\nMaximum of 3 Login attempts only. Try again later");
            }

        maxLoginCount--;
        }while(maxLoginCount>0);
    }

    static void addGoalsForSelf(Employee employee)
    {
        System.out.println("\nThere are 3 Modules - Goals, Assignments and Projects");

        //by default weightage enabled is false - set in module constructor
        System.out.print("\nEnable weightage for Goal Module? Enter 'true'/'false' : ");
        employee.getGoalModule().setWeightageEnabled(sc.nextBoolean());

        System.out.print("\nEnable weightage for Assignment Module? Enter 'true'/'false' : ");
        employee.getAssignmentModule().setWeightageEnabled(sc.nextBoolean());

        System.out.print("\nEnable weightage for project Module? Enter 'true'/'false' : ");
        employee.getProjectModule().setWeightageEnabled(sc.nextBoolean());

        do {

            System.out.print("\nEnter   '1' to add Goals \n        '2' to add Assignments\n        '3' to add Projects\n Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    Parameter goalParameter = new Parameter();
                    System.out.print("\nEnter Goal parameter name : ");
                    String goalParameterName = sc.nextLine();
                    goalParameter.setParameterName(goalParameterName);

                    if(employee.getGoalModule().getWeightageEnabled()){
                        System.out.print("\nEnter Goal Weightage Value : ");
                        int goalWeightageValueInPercentage = sc.nextInt();
                        goalParameter.setWeightageValueInPercentage(goalWeightageValueInPercentage);
                    }
                    employee.getGoalModule().getParametersInModule().add(goalParameter);
                    break;

                case 2:
                    Parameter assignmentParameter = new Parameter();
                    System.out.print("\nEnter Assignment parameter name : ");
                    String assignmentParameterName = sc.nextLine();
                    assignmentParameter.setParameterName(assignmentParameterName);

                    if(employee.getAssignmentModule().getWeightageEnabled()){
                        System.out.print("\nEnter Assignment Weightage Value : ");
                        int assignmentWeightageValueInPercentage = sc.nextInt();
                        assignmentParameter.setWeightageValueInPercentage(assignmentWeightageValueInPercentage);
                    }
                    employee.getAssignmentModule().getParametersInModule().add(assignmentParameter);
                    break;

                case 3:
                    Parameter projectParameter = new Parameter();
                    System.out.print("\nEnter Assignment parameter name : ");
                    String projectParameterName = sc.nextLine();
                    projectParameter.setParameterName(projectParameterName);

                    if(employee.getProjectModule().getWeightageEnabled()){
                        System.out.print("Enter Assignment Weightage Value : ");
                        int projectWeightageValueInPercentage = sc.nextInt();
                        projectParameter.setWeightageValueInPercentage(projectWeightageValueInPercentage);
                    }
                    employee.getProjectModule().getParametersInModule().add(projectParameter);
                    break;
                default:
                    System.out.println("\nNot a valid choice");

            }

            //employee.addToEmployeeParameters(parameter);
            employee.displayEmployeeParameters();

            System.out.print("\nAdd more Parameters? Enter Y/N : ");

        } while (sc.next().equalsIgnoreCase("y"));

        System.out.print("\nGoing back to main menu");

    }

    static void appraisal(Employee employee){
        employee.displayEmployeeParameters();

        System.out.println("Goals : ");
        for(Parameter parameter : employee.getGoalModule().getParametersInModule())
        {
            System.out.print("Enter Rating (1 to 5) for Parameter Name : " + parameter.getParameterName() + " : ");
                parameter.setEmployeeScore(sc.nextInt());
        }

        System.out.println("Assignments : ");
        for(Parameter parameter : employee.getAssignmentModule().getParametersInModule()) {
            System.out.print("Enter Rating (1 to 5) for Parameter Name : " + parameter.getParameterName() + " : ");
            parameter.setEmployeeScore(sc.nextInt());
        }

        System.out.println("Projects : ");
        for(Parameter parameter : employee.getProjectModule().getParametersInModule()) {
            System.out.print("Enter Rating (1 to 5) for Parameter Name : " + parameter.getParameterName() + " : ");
            parameter.setEmployeeScore(sc.nextInt());
        }

        employee.displayEmployeeParametersWithScore();

        System.out.print("Submit to get Score? Enter Y : ");
        String submitConfirmation = sc.next();
        if(submitConfirmation.equalsIgnoreCase("y"))
            employee.calculateRatings();

        employee.displayEmployeeParametersWithRatings();
    }
    static void peerAppraisal(Employee employee){
        System.out.println("\nChoose the employee based on Hierarchy for peer Review ");
        boolean peersPresent = false;
        for(Employee eachEmployee : EmployeeDataBase)
            if(eachEmployee.getHierarchyLevel() < employee.getHierarchyLevel()) {
                peersPresent = true;
                System.out.println("\t" + eachEmployee.getEmployeeID() + " - '" + eachEmployee.getEmployeeName() + "' with Hierarchy Level : " + eachEmployee.getHierarchyLevel());
            }
        if(!peersPresent)
        {
            System.out.println("\nThere are no peer appraisals to review"); return;
        }
        System.out.print("Choose the employee ID and enter here : ");
        String employeeIdChosenForPeerReview = sc.next();

        for(Employee eachEmployee : EmployeeDataBase)
            if(eachEmployee.getEmployeeID().equalsIgnoreCase(employeeIdChosenForPeerReview)) {
                appraisal(eachEmployee);
                break;
            }

    }

    static ArrayList<Employee> EmployeeDataBase = new ArrayList<>();
    static void initializeEmployees()
    {
        Employee employee1 = new Employee("EMP001");
        Employee employee2 = new Employee("EMP002");
        Employee employee3 = new Employee("EMP003");
        Employee employee4 = new Employee("EMP004");
        Employee employee5 = new Employee("EMP005");

        employee1.setEmployeeName("EmployeeName1");
        //employee1.setEmployeeID("EMP001");
        employee1.setHierarchyLevel(3);

        employee2.setEmployeeName("emp2");
        //employee2.setEmployeeID("EMP002");
        employee2.setHierarchyLevel(2);

        employee3.setEmployeeName("emp3");
        //employee3.setEmployeeID("EMP003");
        employee3.setHierarchyLevel(1);

        employee4.setEmployeeName("emp4");
        //employee4.setEmployeeID("EMP004");
        employee4.setHierarchyLevel(2);

        employee5.setEmployeeName("emp5");
        //employee5.setEmployeeID("EMP005");
        employee5.setHierarchyLevel(1);

        EmployeeDataBase.add(employee1);
        EmployeeDataBase.add(employee2);
        EmployeeDataBase.add(employee3);
        EmployeeDataBase.add(employee4);
        EmployeeDataBase.add(employee5);
    }

    static boolean isValidEmployee(String employeeID)
    {
        for(Employee eachEmployee : EmployeeDataBase)
            if(eachEmployee.getEmployeeID().equalsIgnoreCase(employeeID)) {
                return true;
            }
        return false;
    }


}
