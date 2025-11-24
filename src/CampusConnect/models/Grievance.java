package campusconnect.models;

public class Grievance {
    private String id;
    private String studentName;
    private String category;
    private String description;
    private String status; // Pending, In Progress, Resolved

    public Grievance(String id, String studentName, String category, String description, String status) {
        this.id = id;
        this.studentName = studentName;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public String getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + "," + studentName + "," + category + "," + description + "," + status;
    }
}