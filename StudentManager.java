
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {
    private Map<String, Student> students;
    private final String FILE_PATH = "students.dat";

    public StudentManager() {
        this.students = new HashMap<>();
        loadStudentsFromFile();
    }

    // Retrieve or create a new student
    public Student getStudent(String name) {
        return students.computeIfAbsent(name, Student::new);
    }

    // Save all students to a file
    public void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all students from a file
    private void loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            students = (Map<String, Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            students = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

