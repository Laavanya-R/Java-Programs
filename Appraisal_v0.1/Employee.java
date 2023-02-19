import java.util.ArrayList;
import java.util.HashMap;

public class Employee {


    private String employeeID;
    private String employeeName;
    private int hierarchyLevel;
    private Module goalModule = new Module("Goal");
    private Module assignmentModule = new Module("Assignment");
    private Module projectModule = new Module("Project");
    private float finalScore;

    Employee(String employeeID){

        this.employeeID = employeeID;


        for(Employee employee : AppraisalForm.EmployeeDataBase)
            if(employee.getEmployeeID().equalsIgnoreCase(employeeID)){
                this.employeeName = employee.getEmployeeName();
                this.hierarchyLevel = employee.getHierarchyLevel();}

        this.goalModule.setModuleName("Goal");
        this.goalModule.setWeightageEnabled(true);

        Parameter parameter = new Parameter();
        parameter.setParameterName("Goal to achieve 100% Test Effectiveness");
        parameter.setWeightageValueInPercentage(60);

        Parameter parameter5 = new Parameter();
        parameter5.setParameterName("Goal to achieve 0% Defect Leakage");
        parameter5.setWeightageValueInPercentage(40);

        ArrayList<Parameter> goalParameters = new ArrayList<>();
        goalParameters.add(parameter);
        goalParameters.add(parameter5);
        this.goalModule.setParametersInModule(goalParameters);




        this.assignmentModule.setModuleName("Assignment");
        this.assignmentModule.setWeightageEnabled(false);

        Parameter parameter1 = new Parameter();
        parameter1.setParameterName("Assignment to Complete Java course");

        Parameter parameter3 = new Parameter();
        parameter3.setParameterName("Assignment on ROI");

        ArrayList<Parameter> assignmentParameters = new ArrayList<>();
        assignmentParameters.add(parameter1);
        assignmentParameters.add(parameter3);
        this.assignmentModule.setParametersInModule(assignmentParameters);




        this.projectModule.setModuleName("Project");
        this.projectModule.setWeightageEnabled(true);

        Parameter parameter2 = new Parameter();
        parameter2.setParameterName("Project X");
        parameter2.setWeightageValueInPercentage(80);

        Parameter parameter4 = new Parameter();
        parameter4.setParameterName("Project Y");
        parameter4.setWeightageValueInPercentage(20);

        ArrayList<Parameter> projectParameters = new ArrayList<>();
        projectParameters.add(parameter2);
        projectParameters.add(parameter4);
        this.projectModule.setParametersInModule(projectParameters);


    }


    void displayEmployeeParameters(){
        System.out.println("\nParameters set for " + employeeName.toUpperCase() + " : ");
        System.out.println("------------------------------------------------------------------------------");

        if(goalModule.getParametersInModule().size()>0) {
            System.out.println("GOALS");
            for (Parameter parameter : goalModule.getParametersInModule()) {
                System.out.print("Parameter : " + parameter.getParameterName());
                if(goalModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.println();
            }
        }

        if(assignmentModule.getParametersInModule().size()>0) {
            System.out.println("ASSIGNMENTS");
            for (Parameter parameter : assignmentModule.getParametersInModule()) {
                System.out.print("Parameter : " + parameter.getParameterName());
                if (assignmentModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.println();
            }
        }

        if(projectModule.getParametersInModule().size()>0) {
            System.out.println("PROJECTS");
            for (Parameter parameter : projectModule.getParametersInModule()){
                System.out.print("Parameter : " + parameter.getParameterName());
            if (projectModule.getWeightageEnabled())
                System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
            System.out.println();
            }
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    void displayEmployeeParametersWithScore(){
        System.out.println("\nParameters set for " + employeeName.toUpperCase() + " after inputting the score : ");
        System.out.println("------------------------------------------------------------------------------");

        if(goalModule.getParametersInModule().size()>0) {
            System.out.println("GOALS");
            for (Parameter parameter : goalModule.getParametersInModule()) {
                System.out.print("Parameter : " + parameter.getParameterName());
                if(goalModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.print("\tScore : " + parameter.getEmployeeScore());
                System.out.println();
            }
        }

        if(assignmentModule.getParametersInModule().size()>0) {
            System.out.println("ASSIGNMENTS");
            for (Parameter parameter : assignmentModule.getParametersInModule()) {
                System.out.print("Parameter : " + parameter.getParameterName());
                if (assignmentModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.print("\tScore : " + parameter.getEmployeeScore());
                System.out.println();
            }
        }

        if(projectModule.getParametersInModule().size()>0) {
            System.out.println("PROJECTS");
            for (Parameter parameter : projectModule.getParametersInModule()){
                System.out.print("Parameter : " + parameter.getParameterName());
                if (projectModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.print("\tScore : " + parameter.getEmployeeScore());
                System.out.println();
            }
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    void displayEmployeeParametersWithRatings(){
        System.out.println("\nRatings for " + employeeName.toUpperCase() + " : ");
        System.out.println("------------------------------------------------------------------------------");

        if(goalModule.getParametersInModule().size()>0) {
            System.out.println("GOALS");
            for (Parameter parameter : goalModule.getParametersInModule()) {
                System.out.print("Parameter : " + parameter.getParameterName());
                if(goalModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.print("\tScore : " + parameter.getEmployeeScore());
                System.out.println();
            }
            System.out.println("\nGoals Module Total Average : " + goalModule.getModuleTotalAverage() + "\tActual Score of Goals Module : " + goalModule.getModuleActualScore());

        }
        System.out.println("------------------------------------------------------------------------------");
        if(assignmentModule.getParametersInModule().size()>0) {
            System.out.println("ASSIGNMENTS");
            for (Parameter parameter : assignmentModule.getParametersInModule()) {
                System.out.print("Parameter : " + parameter.getParameterName());
                if (assignmentModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.print("\tScore : " + parameter.getEmployeeScore());
                System.out.println();
            }
            System.out.println("\nAssignments Module Total Average : " + assignmentModule.getModuleTotalAverage() + "\tActual Assignments of Goals Module : " + assignmentModule.getModuleActualScore());
        }
        System.out.println("------------------------------------------------------------------------------");
        if(projectModule.getParametersInModule().size()>0) {
            System.out.println("PROJECTS");
            for (Parameter parameter : projectModule.getParametersInModule()){
                System.out.print("Parameter : " + parameter.getParameterName());
                if (projectModule.getWeightageEnabled())
                    System.out.print("\tWeightage : " + parameter.getWeightageValueInPercentage());
                System.out.print("\tScore : " + parameter.getEmployeeScore());
                System.out.println();
            }
            System.out.println("\nProjects Module Total Average : " + projectModule.getModuleTotalAverage() + "\tActual Score of Projects Module : " + projectModule.getModuleActualScore());
        }
        System.out.println("------------------------------------------------------------------------------");
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
    }

    void calculateRatings(){

        int numberOfModulesSet = 0;

        if(goalModule.getParametersInModule().size()>0) {
            numberOfModulesSet++;
            if (goalModule.getWeightageEnabled()) {
                float sumOfScoresWithWeightage = 0;
                float moduleTotalAverage;
                float sumOfGoalsWeightage = 0;
                for (Parameter parameter : goalModule.getParametersInModule()) {
                    sumOfScoresWithWeightage = sumOfScoresWithWeightage + parameter.getEmployeeScore() * parameter.getWeightageValueInPercentage();
                    sumOfGoalsWeightage = sumOfGoalsWeightage + parameter.getWeightageValueInPercentage();
                }
                moduleTotalAverage = sumOfScoresWithWeightage / sumOfGoalsWeightage;

                goalModule.setModuleTotalAverage(moduleTotalAverage);

            } else {
                float sumOfScores = 0;
                float moduleTotalAverage;
                float totalNumberOfGoals = goalModule.getParametersInModule().size();
                for (Parameter parameter : goalModule.getParametersInModule()) {
                    sumOfScores = sumOfScores + parameter.getEmployeeScore();
                }
                moduleTotalAverage = sumOfScores / totalNumberOfGoals;

                goalModule.setModuleTotalAverage(moduleTotalAverage);

            }
        }

        if(assignmentModule.getParametersInModule().size()>0){
            numberOfModulesSet++;

            if(assignmentModule.getWeightageEnabled()) {
                float sumOfScoresWithWeightage = 0;
                float moduleTotalAverage;
                float sumOfAssignmentWeightage = 0;
                for(Parameter parameter: assignmentModule.getParametersInModule()) {
                    sumOfScoresWithWeightage = sumOfScoresWithWeightage + parameter.getEmployeeScore() * parameter.getWeightageValueInPercentage();
                    sumOfAssignmentWeightage = sumOfAssignmentWeightage + parameter.getWeightageValueInPercentage();
                }
                moduleTotalAverage = sumOfScoresWithWeightage/sumOfAssignmentWeightage;

                assignmentModule.setModuleTotalAverage(moduleTotalAverage);
            }else {
                float sumOfScores = 0;
                float moduleTotalAverage;
                float totalNumberOfAssignments = assignmentModule.getParametersInModule().size();
                for (Parameter parameter : assignmentModule.getParametersInModule()) {
                    sumOfScores = sumOfScores + parameter.getEmployeeScore();
                }
                moduleTotalAverage = sumOfScores / totalNumberOfAssignments;

                assignmentModule.setModuleTotalAverage(moduleTotalAverage);

            }
        }
        if(projectModule.getParametersInModule().size()>0){
            numberOfModulesSet++;
            if(projectModule.getWeightageEnabled()) {
                float sumOfScoresWithWeightage = 0;
                float moduleTotalAverage;
                float sumOfProjectsWeightage = 0;
                for(Parameter parameter: projectModule.getParametersInModule()) {
                    sumOfScoresWithWeightage = sumOfScoresWithWeightage + parameter.getEmployeeScore() * parameter.getWeightageValueInPercentage();
                    sumOfProjectsWeightage = sumOfProjectsWeightage + parameter.getWeightageValueInPercentage();
                }
                moduleTotalAverage = sumOfScoresWithWeightage/sumOfProjectsWeightage;

                projectModule.setModuleTotalAverage(moduleTotalAverage);
            }else {
                float sumOfScores = 0;
                float moduleTotalAverage;
                float totalNumberOfProjects = projectModule.getParametersInModule().size();
                for (Parameter parameter : projectModule.getParametersInModule()) {
                    sumOfScores = sumOfScores + parameter.getEmployeeScore();
                }
                moduleTotalAverage = sumOfScores / totalNumberOfProjects;

                projectModule.setModuleTotalAverage(moduleTotalAverage);

            }
         }

        goalModule.setModuleActualScore(goalModule.getModuleTotalAverage()/numberOfModulesSet);
        assignmentModule.setModuleActualScore(assignmentModule.getModuleTotalAverage()/numberOfModulesSet);
        projectModule.setModuleActualScore(projectModule.getModuleTotalAverage()/numberOfModulesSet);

        finalScore = goalModule.getModuleActualScore()+assignmentModule.getModuleActualScore()+goalModule.getModuleActualScore();

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

    public Module getGoalModule() {
        return goalModule;
    }

    public void setGoalModule(Module goalModule) {
        this.goalModule = goalModule;
    }

    public Module getAssignmentModule() {
        return assignmentModule;
    }

    public void setAssignmentModule(Module assignmentModule) {
        this.assignmentModule = assignmentModule;
    }

    public Module getProjectModule() {
        return projectModule;
    }

    public void setProjectModule(Module projectModule) {
        this.projectModule = projectModule;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }


    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

}
