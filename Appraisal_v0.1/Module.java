import java.util.ArrayList;

public class Module {

    private String moduleName; //Goal, Assignment, Project
    private Boolean weightageEnabled; // true/false
    private ArrayList<Parameter> parametersInModule = new ArrayList<>();
    private float moduleTotalAverage;
    private float moduleActualScore;

    Module(String moduleName){
        this.moduleName = moduleName;
        this.weightageEnabled = false;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public ArrayList<Parameter> getParametersInModule() {
        return parametersInModule;
    }

    public void setParametersInModule(ArrayList<Parameter> parametersInModule) {
        this.parametersInModule = parametersInModule;
    }

    public Boolean getWeightageEnabled() {
        return weightageEnabled;
    }

    public void setWeightageEnabled(Boolean weightageEnabled) {
        this.weightageEnabled = weightageEnabled;
    }

    public float getModuleTotalAverage() {
        return moduleTotalAverage;
    }

    public void setModuleTotalAverage(float moduleTotalAverage) {
        this.moduleTotalAverage = moduleTotalAverage;
    }

    public float getModuleActualScore() {
        return moduleActualScore;
    }

    public void setModuleActualScore(float moduleActualScore) {
        this.moduleActualScore = moduleActualScore;
    }
}
