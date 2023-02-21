
public class Assignment {
    private String assignmentName;
    private Integer assignmentWeightageValue = 0;
    private String assignmentDueDate;
    private String assignmentCompletionStatus;

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Integer getAssignmentWeightageValue() {
        return assignmentWeightageValue;
    }

    public void setAssignmentWeightageValue(Integer assignmentWeightageValue) {
        this.assignmentWeightageValue = assignmentWeightageValue;
    }

    public String getAssignmentDueDate() {
        return assignmentDueDate;
    }

    public void setAssignmentDueDate(String assignmentDueDate) {
        this.assignmentDueDate = assignmentDueDate;
    }

    public String isAssignmentCompletionStatus() {
        return assignmentCompletionStatus;
    }

    public void setAssignmentCompletionStatus(String assignmentCompletionStatus) {
        this.assignmentCompletionStatus = assignmentCompletionStatus;
    }

    public String getAssignmentCompletionStatus() {
        return assignmentCompletionStatus;
    }
}
