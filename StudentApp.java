
import java.util.Scanner;

public class StudentApp {
    public static void run(StudentManager studentManager, CourseManager courseManager, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Student student = studentManager.getStudent(name);

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("----------------------");
            System.out.println("1. View All Courses");
            System.out.println("2. Add Course to Shopping Cart");
            System.out.println("3. View Shopping Cart");
            System.out.println("4. View Enrolled Courses");
            System.out.println("5. View Grades");
            System.out.println("6. Log Out");
            System.out.println("----------------------"); 
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    courseManager.getAllCourses().forEach(course -> 
                        System.out.println(course.getName() + ": " + course.getDescription()));
                    break;
                case 2:
                    System.out.print("Enter the course name to add to your cart: ");
                    //System.out.println("-------------------------------------------"); 
                    String courseName = scanner.nextLine();
                    System.out.println("-------------------------------------------");
                    Course course = courseManager.findCourse(courseName);
                    if (course != null) {
                        student.addToCart(course);
                        System.out.println(courseName + " added to your shopping cart.");
                    } else {
                        System.out.println("This course was not found. Try again.");
                    }
                    break;
                case 3:
                    handleShoppingCart(student, scanner);
                    break;
                case 4:
                    student.getEnrolledCourses().keySet().forEach(c -> 
                        System.out.println(c.getName() + ": " + c.getDescription()));
                    break;
                case 5:
                    student.syncGrades(); // Synchronize grades with courses
                    student.getEnrolledCourses().forEach((c, grade) -> 
                        System.out.println(c.getName() + ": " + grade));
                    break;
                case 6:
                    System.out.println("Logging out...");
                    studentManager.saveStudentsToFile();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleShoppingCart(Student student, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Shopping Cart ---");
            if (student.getShoppingCart().isEmpty()) {
                System.out.println("Your shopping cart is empty.");
                System.out.println("----------------------------"); 
                return;
            }

            System.out.println("\nYour cart:");
            System.out.println("----------"); 
            int i = 1;
            for (Course course : student.getShoppingCart()) {
                System.out.println(i++ + ". " + course.getName() + ": " + course.getDescription());
            }

            System.out.println("\n1. Enroll in All Courses");
            System.out.println("2. Remove a Course");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    student.enrollFromCart();
                    System.out.println("Enrolled in all courses from your shopping cart.");
                    return;
                case 2:
                    System.out.print("Enter the number of the course to remove: ");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (index >= 0 && index < student.getShoppingCart().size()) {
                        Course removedCourse = student.getShoppingCart().get(index);
                        student.removeFromCart(removedCourse);
                        System.out.println(removedCourse.getName() + " removed from your shopping cart.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}




