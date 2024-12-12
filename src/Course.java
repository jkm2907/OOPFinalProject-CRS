
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Map<String, String> students;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.students = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getStudents() {
        return students;
    }

    public void addStudent(String studentName) {
        students.putIfAbsent(studentName, "N/A");
    }

    public int assignGrade(String studentName, String grade) {
        if (students.containsKey(studentName)) {
            students.put(studentName, grade);
            return 0;
        } return 1;
    }

    public String getGrade(String studentName) {
    	String grade = students.get(studentName);
        return grade;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}


