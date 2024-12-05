
import java.util.Scanner;

public class TeacherApp {
    public static void run(CourseManager courseManager, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Teacher Menu ---");
            System.out.println("----------------------");
            System.out.println("1. View All Courses");
            System.out.println("2. Add a Course");
            System.out.println("3. Edit a Course");
            System.out.println("4. Delete a Course");
            System.out.println("5. View Students in a Course");
            System.out.println("6. Assign Grade");
            System.out.println("7. Back to Main Menu");
            System.out.println("-----------------------");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    courseManager.getAllCourses().forEach(course -> 
                        System.out.println(course.getName() + ": " + course.getDescription()));
                    break;
                case 2:
                    System.out.print("Enter course name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    String description = scanner.nextLine();
                    courseManager.addCourse(name, description);
                    System.out.println("Course added.");
                    break;
                case 3:
                    System.out.print("Enter the course name to edit: ");
                    String courseToEdit = scanner.nextLine();
                    Course course = courseManager.findCourse(courseToEdit);
                    if (course != null) {
                        System.out.print("Enter new description: ");
                        String newDescription = scanner.nextLine();
                        course.setDescription(newDescription);
                        courseManager.saveCoursesToFile();
                        System.out.println("Course updated.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the course name to delete: ");
                    String courseToDelete = scanner.nextLine();
                    courseManager.deleteCourse(courseToDelete);
                    System.out.println("Course deleted.");
                    break;
                case 5:
                    System.out.print("Enter the course name: ");
                    String courseToView = scanner.nextLine();
                    Course courseView = courseManager.findCourse(courseToView);
                    if (courseView != null) {
                        courseView.getStudents().forEach((student, grade) -> 
                            System.out.println(student + ": " + grade));
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter the course name: ");
                    String courseName = scanner.nextLine();
                    Course courseAssign = courseManager.findCourse(courseName);
                    if (courseAssign != null) {
                        System.out.print("Enter the student name: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Enter the grade: ");
                        String grade = scanner.nextLine();
                        courseAssign.assignGrade(studentName, grade);
                        System.out.println("Grade assigned.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


