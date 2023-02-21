public class Project {
    private String projectName;
    private Integer projectWeightageValue = 0;
    private String projectStatus;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectWeightageValue() {
        return projectWeightageValue;
    }

    public void setProjectWeightageValue(Integer projectWeightageValue) {
        this.projectWeightageValue = projectWeightageValue;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
