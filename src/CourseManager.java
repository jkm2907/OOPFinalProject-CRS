
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<Course> courses;
    private final String FILE_PATH = "courses.dat";

    public CourseManager() {
        this.courses = new ArrayList<>();
        loadCoursesFromFile();
    }

    public void addCourse(String name, String description) {
        courses.add(new Course(name, description));
    }

    public void deleteCourse(String name) {
        courses.removeIf(course -> course.getName().equals(name));
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course findCourse(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }
    
    public String getGrades(Student student) {
    	String grades= "";
    	for (String sCourse: student.getEnrolled()) {
    		Course curr = findCourse(sCourse);
    		String name = student.getName();
    		grades+= (sCourse + " " + curr.getGrade(name));
    	}
    	return grades;
    }

    @SuppressWarnings("unchecked")
	private void loadCoursesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            courses = (List<Course>) ois.readObject();
        } catch (FileNotFoundException e) {
            courses = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveCoursesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

