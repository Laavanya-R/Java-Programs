public class Parameter {

    private String parameterName;

    private Integer weightageValueInPercentage = 0;
    private int employeeScore;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Integer getWeightageValueInPercentage() {
        return weightageValueInPercentage;
    }

    public void setWeightageValueInPercentage(Integer weightageValueInPercentage) {
        this.weightageValueInPercentage = weightageValueInPercentage;
    }

    public int getEmployeeScore() {
        return employeeScore;
    }

    public void setEmployeeScore(int employeeScore) {
        this.employeeScore = employeeScore;
    }
}
