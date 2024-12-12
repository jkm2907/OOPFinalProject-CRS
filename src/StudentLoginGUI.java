

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class StudentLoginGUI extends JFrame {

	JButton login;
	JButton back;
	JTextField name;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	CourseManager courseManager;
	StudentManager studentManager;
	
	public StudentLoginGUI(CourseManager courseManager, StudentManager studentManager) {
		this.courseManager = courseManager;
		this.studentManager = studentManager;
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setTitle("Student Login Page");
		login = new JButton("Login");
		name = new JTextField("Enter name here");
		back = new JButton("go back");
	}
	
	private void login(String name) {
		Student student = studentManager.getStudent(name);
		StudentPageGUI running = new StudentPageGUI(student, this.courseManager);
		running.run();
		this.setVisible(false);
	}
	private void goBack() {
		this.setVisible(false);
	}
			
	public void run() {
		this.setLayout(new FlowLayout());
		this.add(name);
		this.add(login);
		this.add(back);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == login) {
					login(name.getText());
				}
				if (e.getSource() == back) {
					goBack();
				}
			}
		};
		login.addActionListener(listener);
		back.addActionListener(listener);
		this.setVisible(true);		
	}
}

