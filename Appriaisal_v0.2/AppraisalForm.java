import java.util.ArrayList;
import java.util.Scanner;

public class AppraisalForm {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] n)
    {
        initializeEmployees();

        System.out.println("Welcome to Employee Appraisal System");

        int maxLoginCount = 3;
        login:
        do {
            System.out.print("Login using Employee ID : ");
            String employeeID = sc.next();

            for(Employee employee : AppraisalForm.EmployeeDataBase) {
                if (employee.getEmployeeID().equalsIgnoreCase(employeeID)) {
                    do {
                        System.out.print("\nEnter   '1' to Add appraisal parameters for Self\n        '2' for Self Appraisal\n        '3' for Peer appraisal\n Choice : ");
                        int choiceOfAppraisal = sc.nextInt();

                        if (choiceOfAppraisal == 1) {
                            addGoalsForSelf(employee);

                        } else if (choiceOfAppraisal == 2) {
                            appraisal(employee);

                        } else if (choiceOfAppraisal == 3) {
                            peerAppraisal(employee);

                        } else
                            System.out.println("Not a valid choice.");

                        System.out.print("\nContinue to Add appraisal parameters / Self Appraisal / Peer Appraisal? Y/N : ");
                    } while (sc.next().equalsIgnoreCase("Y"));

                    System.out.print("\nThank You for using the Employee Appraisal System");
                    break login;
                }
            }
            if (maxLoginCount > 1)
                System.out.println("Not a valid user - Try again");
            else if (maxLoginCount == 1)
                System.out.println("\nMaximum of 3 Login attempts only. Try again later");

            maxLoginCount--;
        }while(maxLoginCount>0);
    }

    static void addGoalsForSelf(Employee employee)
    {
        System.out.println("\nThere are 3 Modules - Goals, Assignments and Projects");

        System.out.print("\nEnable weightage for Goal Module? Enter 'true'/'false' : ");
        employee.setWeightageEnabledForGoals(sc.nextBoolean());

        System.out.print("\nEnable weightage for Assignment Module? Enter 'true'/'false' : ");
        employee.setWeightageEnabledForAssignments(sc.nextBoolean());

        System.out.print("\nEnable weightage for Project Module? Enter 'true'/'false' : ");
        employee.setWeightageEnabledForProjects(sc.nextBoolean());

        do {
            System.out.print("\nEnter   '1' to add Goals \n        '2' to add Assignments\n        '3' to add Projects\n Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    Goal goal = new Goal();
                    System.out.print("\nEnter Goal name : ");
                    String goalName = sc.nextLine();
                    goal.setGoalName(goalName);
                    System.out.print("\nGoal Achieved? Yes/No : ");
                    String goalAchieved = sc.nextLine();
                    goal.setGoalAchieved(goalAchieved);

                    if(employee.getWeightageEnabledForGoals()){
                        System.out.print("\nEnter Goal Weightage Value : ");
                        int goalWeightageValue = sc.nextInt();
                        goal.setGoalWeightageValue(goalWeightageValue);
                    }
                    employee.getListOfGoals().put(goal, 0);
                    break;

                case 2:
                    Assignment assignment = new Assignment();
                    System.out.print("\nEnter Assignment name : ");
                    String assignmentName = sc.nextLine();
                    assignment.setAssignmentName(assignmentName);
                    System.out.print("\nEnter Assignment Due date : ");
                    String assignmentDueDate = sc.nextLine();
                    assignment.setAssignmentDueDate(assignmentDueDate);
                    System.out.print("\nEnter Assignment Completion Status (Complete/Not Complete) : ");
                    String assignmentCompletionStatus = sc.nextLine();
                    assignment.setAssignmentCompletionStatus(assignmentCompletionStatus);

                    if(employee.getWeightageEnabledForAssignments()){
                        System.out.print("\nEnter Assignment Weightage Value : ");
                        int assignmentWeightageValue = sc.nextInt();
                        assignment.setAssignmentWeightageValue(assignmentWeightageValue);
                    }
                    employee.getListOfAssignments().put(assignment, 0);
                    break;

                case 3:
                    Project project = new Project();
                    System.out.print("\nEnter Project name : ");
                    String projectName = sc.nextLine();
                    project.setProjectName(projectName);
                    System.out.print("\nEnter Project Status : ");
                    String projectStatus = sc.nextLine();
                    project.setProjectStatus(projectStatus);

                    if(employee.getWeightageEnabledForProjects()){
                        System.out.print("Enter Project Weightage Value : ");
                        int projectWeightageValue = sc.nextInt();
                        project.setProjectWeightageValue(projectWeightageValue);
                    }
                    employee.getListOfProjects().put(project, 0);
                    break;
                default:
                    System.out.println("\nNot a valid choice");
            }
            displayEmployeeParameters(employee);

            System.out.print("\nAdd more Parameters? Enter Y/N : ");

        } while (sc.next().equalsIgnoreCase("y"));

        System.out.print("\nGoing back to main menu");

    }

    static void appraisal(Employee employee){
        displayEmployeeParameters(employee);

        System.out.println("\nGoals : ");
        for(Goal goal : employee.getListOfGoals().keySet())
        {
            boolean validRating = false;
            do {
                System.out.print(goal.getGoalName() + "\tGoal Achieved : " + goal.getGoalAchieved() + "\tEnter Rating (1 to 5) : ");
                int goalRating = sc.nextInt();
                if(goalRating == 1 || goalRating == 2 || goalRating == 3 || goalRating == 4 || goalRating == 5) {
                    employee.getListOfGoals().put(goal, goalRating);
                    validRating = true;
                }
                else System.out.println("Rating can be 1, 2, 3, 4 or 5 only. Enter again.");
           }while(!validRating);
        }

        System.out.println("\nAssignments : ");
        for(Assignment assignment : employee.getListOfAssignments().keySet()) {
            boolean validRating = false;
            do {
                System.out.print(assignment.getAssignmentName() + "\tDue Date : "
                        + assignment.getAssignmentDueDate() + "\tStatus : " + assignment.getAssignmentCompletionStatus() + "\tEnter Rating (1 to 5) : ");
                int assignmentsRating = sc.nextInt();
                if(assignmentsRating == 1 || assignmentsRating == 2 || assignmentsRating == 3 || assignmentsRating == 4 || assignmentsRating == 5) {
                    employee.getListOfAssignments().put(assignment, assignmentsRating);
                    validRating = true;
                }
                else System.out.println("Rating can be 1, 2, 3, 4 or 5 only. Enter again.");
            }while(!validRating);
        }

        System.out.println("\nProjects : ");
        for(Project project : employee.getListOfProjects().keySet()) {
            boolean validRating = false;
            do {
                System.out.print(project.getProjectName() + "\tStatus : " + project.getProjectStatus() + "\tEnter Rating (1 to 5) : ");
                int projectsRating = sc.nextInt();
                if(projectsRating == 1 || projectsRating == 2 || projectsRating == 3 || projectsRating == 4 || projectsRating == 5) {
                    employee.getListOfProjects().put(project, projectsRating);
                    validRating = true;
                }
                else System.out.println("Rating can be 1, 2, 3, 4 or 5 only. Enter again.");
            }while(!validRating);
        }

        System.out.print("Submit to get Score? Enter Y : ");
        String submitConfirmation = sc.next();
        if(submitConfirmation.equalsIgnoreCase("y"))
            calculateRatings(employee);
    }
    static void peerAppraisal(Employee employee){
        System.out.println("\nChoose the employee for peer Review ");
        boolean peersPresent = false;
        for(Employee eachEmployee : EmployeeDataBase)
            if(eachEmployee.getReportingManagerID().equalsIgnoreCase(employee.getEmployeeID())) {
                peersPresent = true;
                System.out.println("\t" + eachEmployee.getEmployeeID() + " - '" + eachEmployee.getEmployeeName() + "' with Hierarchy Level : " + eachEmployee.getHierarchyLevel());

                for(Employee eachReportingEmployee : EmployeeDataBase)
                    if(eachReportingEmployee.getReportingManagerID().equalsIgnoreCase(eachEmployee.getEmployeeID()))
                        System.out.println("\t" + eachReportingEmployee.getEmployeeID() + " - '" + eachReportingEmployee.getEmployeeName()
                                + "' with Hierarchy Level : " + eachReportingEmployee.getHierarchyLevel()
                                + " reporting to : " + eachReportingEmployee.getReportingManagerID()  );

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
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();
        Employee employee4 = new Employee();
        Employee employee5 = new Employee();
        Employee employee6 = new Employee();
        Employee employee7 = new Employee();

        employee1.setEmployeeName("Employee1");
        employee1.setEmployeeID("EMP001");
        employee1.setReportingManagerID("EMP00999");
        employee1.setHierarchyLevel(3);
        employee1.setWeightageEnabledForGoals(true);
        employee1.setWeightageEnabledForAssignments(false);
        employee1.setWeightageEnabledForProjects(true);

        Goal goal1 = new Goal();
        goal1.setGoalName("Goal to achieve 100% Test Effectiveness");
        goal1.setGoalWeightageValue(40);
        goal1.setGoalAchieved("Yes");

        employee1.getListOfGoals().put(goal1, 0);

        Goal goal2 = new Goal();
        goal2.setGoalName("Goal to achieve 0% Defect Leakage");
        goal2.setGoalWeightageValue(60);
        goal2.setGoalAchieved("No");

        employee1.getListOfGoals().put(goal2, 0);


        Assignment assignment1 = new Assignment();
        assignment1.setAssignmentName("POC on Automation");
        assignment1.setAssignmentCompletionStatus("Completed");
        assignment1.setAssignmentDueDate("12/6/2023");
        //assignment1.setAssignmentWeightageValue(40);

        Assignment assignment2 = new Assignment();
        assignment2.setAssignmentName("Assignment on Return Over Investment");
        assignment2.setAssignmentCompletionStatus("In-progress");
        assignment2.setAssignmentDueDate("1/5/2023");
        //assignment2.setAssignmentWeightageValue(60);

        employee1.getListOfAssignments().put(assignment1, 0);
        employee1.getListOfAssignments().put(assignment2, 0);

        Project project1 = new Project();
        project1.setProjectName("Project X");
        project1.setProjectWeightageValue(40);
        project1.setProjectStatus("Complete");

        Project project2 = new Project();
        project2.setProjectName("Project X");
        project2.setProjectWeightageValue(60);
        project2.setProjectStatus("Complete");

        employee1.getListOfProjects().put(project1, 0);
        employee1.getListOfProjects().put(project2, 0);

        employee2.setEmployeeName("Employee2");
        employee2.setEmployeeID("EMP002");
        employee2.setReportingManagerID("EMP001");
        employee2.setHierarchyLevel(2);
        employee2.getListOfGoals().put(goal1, 0);
        employee2.getListOfGoals().put(goal2, 0);
        employee2.getListOfAssignments().put(assignment1, 0);
        employee2.getListOfAssignments().put(assignment2, 0);
        employee2.getListOfProjects().put(project1, 0);
        employee2.getListOfProjects().put(project2, 0);

        employee3.setEmployeeName("Employee3");
        employee3.setEmployeeID("EMP003");
        employee3.setReportingManagerID("EMP002");
        employee3.setHierarchyLevel(1);
        employee3.getListOfGoals().put(goal1, 0);
        employee3.getListOfGoals().put(goal2, 0);
        employee3.getListOfAssignments().put(assignment1, 0);
        employee3.getListOfAssignments().put(assignment2, 0);
        //employee3.getListOfProjects().put(project1, 0);
        //employee3.getListOfProjects().put(project2, 0);

        employee4.setEmployeeName("Employee4");
        employee4.setEmployeeID("EMP004");
        employee4.setReportingManagerID("EMP001");
        employee4.setHierarchyLevel(2);
        //employee4.getListOfGoals().put(goal1, 0);
        //employee4.getListOfGoals().put(goal2, 0);
        employee4.getListOfAssignments().put(assignment1, 0);
        employee4.getListOfAssignments().put(assignment2, 0);
        employee4.getListOfProjects().put(project1, 0);
        employee4.getListOfProjects().put(project2, 0);

        employee5.setEmployeeName("Employee5");
        employee5.setEmployeeID("EMP005");
        employee5.setReportingManagerID("EMP004");
        employee5.setHierarchyLevel(1);
        employee5.getListOfGoals().put(goal1, 0);
        employee5.getListOfGoals().put(goal2, 0);
        //employee5.getListOfAssignments().put(assignment1, 0);
        employee5.getListOfAssignments().put(assignment2, 0);
        //employee5.getListOfProjects().put(project1, 0);
        employee5.getListOfProjects().put(project2, 0);

        employee6.setEmployeeName("Employee6");
        employee6.setEmployeeID("EMP006");
        employee6.setReportingManagerID("EMP009");
        employee6.setHierarchyLevel(2);

        employee7.setEmployeeName("Employee5");
        employee7.setEmployeeID("EMP005");
        employee7.setReportingManagerID("EMP006");
        employee7.setHierarchyLevel(1);


        EmployeeDataBase.add(employee1);
        EmployeeDataBase.add(employee2);
        EmployeeDataBase.add(employee3);
        EmployeeDataBase.add(employee4);
        EmployeeDataBase.add(employee5);
    }


    static void displayEmployeeParameters(Employee employee){
        System.out.println("\nParameters set for " + employee.getEmployeeName().toUpperCase() + " : ");
        System.out.println("------------------------------------------------------------------------------");

        displayGoals(employee);
        System.out.println();
        displayAssignments(employee);
        System.out.println();
        displayProjects(employee);
        System.out.println();

        System.out.println("------------------------------------------------------------------------------");
    }

    static void displayGoals(Employee employee){
        if(employee.getListOfGoals().size()>0) {
            System.out.println("GOALS");
            for(Goal goal : employee.getListOfGoals().keySet()) {
                System.out.print("Goal : " + goal.getGoalName());
                System.out.print("\tGoal Achieved : " + goal.getGoalAchieved());
                if(employee.getWeightageEnabledForGoals())
                    System.out.print("\tWeightage : " + goal.getGoalWeightageValue());
                if(employee.getListOfGoals().get(goal) != 0)
                    System.out.print("\tRating : " + employee.getListOfGoals().get(goal));
                System.out.println();
            }
        }
    }

    static void displayAssignments(Employee employee){
        if(employee.getListOfAssignments().size()>0) {
            System.out.println("ASSIGNMENTS");
            for(Assignment assignment : employee.getListOfAssignments().keySet()) {
                System.out.print("Assignment : " + assignment.getAssignmentName());
                System.out.print("\tDue Date : " + assignment.getAssignmentDueDate());
                System.out.print("\tStatus : " + assignment.getAssignmentCompletionStatus());
                if (employee.getWeightageEnabledForAssignments())
                    System.out.print("\tWeightage : " + assignment.getAssignmentWeightageValue());
                if(employee.getListOfAssignments().get(assignment) != 0)
                    System.out.print("\tRating : " + employee.getListOfAssignments().get(assignment));
                System.out.println();
            }
        }
    }
    static void displayProjects(Employee employee){
        if(employee.getListOfProjects().size()>0) {
            System.out.println("PROJECTS");
            for(Project project : employee.getListOfProjects().keySet()){
                System.out.print("Project : " + project.getProjectName());
                System.out.print("\tStatus : " + project.getProjectStatus());
                if (employee.getWeightageEnabledForProjects())
                    System.out.print("\tWeightage : " + project.getProjectWeightageValue());
                if(employee.getListOfProjects().get(project) != 0)
                    System.out.print("\tRating : " + employee.getListOfProjects().get(project));
                System.out.println();
            }
        }
    }

    static void calculateRatings(Employee employee){

        int numberOfModulesSet = 0;
        if(employee.getListOfGoals().size()>0)
            numberOfModulesSet++;
        if(employee.getListOfAssignments().size()>0)
            numberOfModulesSet++;
        if(employee.getListOfProjects().size()>0)
            numberOfModulesSet++;

        float actualScoreOfGoalsModule = 0;
        float actualScoreOfAssignmentsModule = 0;
        float actualScoreOfProjectsModule = 0;

        if(employee.getListOfGoals().size()>0) {
            float moduleTotalAverage;
            if (employee.getWeightageEnabledForGoals()) {
                float sumOfScoresWithWeightage = 0;
                float sumOfGoalsWeightage = 0;
                for(Goal goal : employee.getListOfGoals().keySet()) {
                    sumOfScoresWithWeightage = sumOfScoresWithWeightage + employee.getListOfGoals().get(goal) * goal.getGoalWeightageValue();
                    sumOfGoalsWeightage = sumOfGoalsWeightage + goal.getGoalWeightageValue();
                }
                moduleTotalAverage = sumOfScoresWithWeightage / sumOfGoalsWeightage;

            } else {
                float sumOfScores = 0;
                float totalNumberOfGoals = employee.getListOfGoals().size();
                for (Goal goal : employee.getListOfGoals().keySet()) {
                    sumOfScores = sumOfScores + employee.getListOfGoals().get(goal);
                }
                moduleTotalAverage = sumOfScores / totalNumberOfGoals;

            }
            actualScoreOfGoalsModule = moduleTotalAverage/numberOfModulesSet;
            System.out.println("------------------------------------------------------------------------------");
            displayGoals(employee);
            System.out.println("\nGoals Module Total Average : " + moduleTotalAverage + "\tActual Score of Goals Module : " + actualScoreOfGoalsModule);
            System.out.println("------------------------------------------------------------------------------");
        }

        if(employee.getListOfAssignments().size()>0) {
            float moduleTotalAverage;
            if (employee.getWeightageEnabledForAssignments()) {
                float sumOfScoresWithWeightage = 0;
                float sumOfAssignmentsWeightage = 0;
                for(Assignment assignment : employee.getListOfAssignments().keySet()) {
                    sumOfScoresWithWeightage = sumOfScoresWithWeightage + employee.getListOfAssignments().get(assignment) * assignment.getAssignmentWeightageValue();
                    sumOfAssignmentsWeightage = sumOfAssignmentsWeightage + assignment.getAssignmentWeightageValue();
                }
                moduleTotalAverage = sumOfScoresWithWeightage / sumOfAssignmentsWeightage;

            } else {
                float sumOfScores = 0;
                float totalNumberOfAssignments = employee.getListOfAssignments().size();
                for (Assignment assignment : employee.getListOfAssignments().keySet()) {
                    sumOfScores = sumOfScores + employee.getListOfAssignments().get(assignment);
                }
                moduleTotalAverage = sumOfScores / totalNumberOfAssignments;

            }
            actualScoreOfAssignmentsModule = moduleTotalAverage/numberOfModulesSet;
            displayAssignments(employee);
            System.out.println("\nAssignments Module Total Average : " + moduleTotalAverage + "\tActual Score of Assignments Module : " + actualScoreOfAssignmentsModule);
            System.out.println("------------------------------------------------------------------------------");
        }

        if(employee.getListOfProjects().size()>0) {
            float moduleTotalAverage;
            if (employee.getWeightageEnabledForProjects()) {
                float sumOfScoresWithWeightage = 0;
                float sumOfProjectsWeightage = 0;
                for(Project project : employee.getListOfProjects().keySet()) {
                    sumOfScoresWithWeightage = sumOfScoresWithWeightage + employee.getListOfProjects().get(project) * project.getProjectWeightageValue();
                    sumOfProjectsWeightage = sumOfProjectsWeightage + project.getProjectWeightageValue();
                }
                moduleTotalAverage = sumOfScoresWithWeightage / sumOfProjectsWeightage;

            } else {
                float sumOfScores = 0;
                float totalNumberOfProjects = employee.getListOfProjects().size();
                for (Project project : employee.getListOfProjects().keySet()) {
                    sumOfScores = sumOfScores + employee.getListOfProjects().get(project);
                }
                moduleTotalAverage = sumOfScores / totalNumberOfProjects;

            }
            actualScoreOfProjectsModule = moduleTotalAverage/numberOfModulesSet;
            displayProjects(employee);
            System.out.println("\nProjects Module Total Average : " + moduleTotalAverage + "\tActual Score of Projects Module : " + actualScoreOfProjectsModule);
            System.out.println("------------------------------------------------------------------------------");
        }
        float finalScore = actualScoreOfGoalsModule + actualScoreOfAssignmentsModule + actualScoreOfProjectsModule;
        System.out.println();
        if(finalScore>=4.1 && finalScore<=5)
            System.out.println("Your Final Score is : " + finalScore + " - Outstanding");
        else if(finalScore>=3.1 && finalScore<=4)
            System.out.println("Your Final Score is : " + finalScore + " - Excellent");
        else if(finalScore>=2.1 && finalScore<=3)
            System.out.println("Your Final Score is : " + finalScore + " - Satisfactory");
        else if(finalScore>=1.1 && finalScore<=2)
            System.out.println("Your Final Score is : " + finalScore + " - Needs Improvement");
        else if(finalScore>=0 && finalScore<=1)
            System.out.println("Your Final Score is : " + finalScore + " - Unsatisfactory");
        System.out.println("------------------------------------------------------------------------------");
    }

}
