import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	CourseManager courseManager;
	StudentManager studentManager;
	JButton student;
	JButton teacher;
	JButton exit;
	
	private LoginGUI(CourseManager courseManager, StudentManager studentManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.courseManager = courseManager;
        this.studentManager = studentManager;
        student = new JButton("Student");
        teacher = new JButton("Teacher");
        exit = new JButton("Exit");
        this.setTitle("Login Page");
	}
	private JButton getStudentButton() {
		return student;
	}
	private JButton getTeacherButton() {
		return teacher;
	}
	private JButton getExitButton() {
		return exit;
	}
	
	private void click_student() {
		StudentLoginGUI newStudentGUI = new StudentLoginGUI(courseManager, studentManager);
		newStudentGUI.run();   
	}
	private void click_teacher() {
		TeacherGUI newTeacherGUI = new TeacherGUI(courseManager);
		newTeacherGUI.run();
	}
	private void click_exit() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		CourseManager courseManager = new CourseManager();
		StudentManager studentManager = new StudentManager();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nSaving all data...");
            studentManager.saveStudentsToFile();
            courseManager.saveCoursesToFile();
        }));
		LoginGUI frame = new LoginGUI(courseManager, studentManager);
		frame.setLayout(new FlowLayout());
		frame.add(frame.getStudentButton());
		frame.add(frame.getTeacherButton());
		frame.add(frame.getExitButton());
	
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == frame.getStudentButton()) {
                	frame.click_student(); }
                    
                if (e.getSource() == frame.getTeacherButton()) {
                	frame.click_teacher();
                }
                if (e.getSource() == frame.getExitButton()) {
                	frame.click_exit();
                }
            }
        };
		frame.getStudentButton().addActionListener(listener);
		frame.getTeacherButton().addActionListener(listener);
		frame.getExitButton().addActionListener(listener);
		
		frame.setVisible(true);
	}
}
