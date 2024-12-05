
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Map<Course, String> enrolledCourses;
    private transient List<Course> shoppingCart;

    public Student(String name) {
        this.name = name;
        this.enrolledCourses = new HashMap<>();
        this.shoppingCart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Map<Course, String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public List<Course> getShoppingCart() {
        if (shoppingCart == null) {
            shoppingCart = new ArrayList<>();
        }
        return shoppingCart;
    }

    public void addToCart(Course course) {
        if (!getShoppingCart().contains(course)) {
            getShoppingCart().add(course);
        }
    }

    public void removeFromCart(Course course) {
        getShoppingCart().remove(course);
    }

    public void enrollFromCart() {
        for (Course course : getShoppingCart()) {
            enrolledCourses.putIfAbsent(course, "N/A");
            course.addStudent(name);
        }
        shoppingCart.clear();
    }

    public void syncGrades() {
        for (Course course : enrolledCourses.keySet()) {
            String updatedGrade = course.getGradeForStudent(name);
            enrolledCourses.put(course, updatedGrade);
        }
    }
}




