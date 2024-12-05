
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nSaving all data...");
            studentManager.saveStudentsToFile();
            courseManager.saveCoursesToFile();
        }));

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("-------------------");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Exit\n");
            System.out.println("-------------------"); 
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    StudentApp.run(studentManager, courseManager, scanner);
                    break;
                case 2:
                    TeacherApp.run(courseManager, scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
