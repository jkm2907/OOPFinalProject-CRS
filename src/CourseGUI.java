

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;
import java.awt.*;

public class CourseGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CourseManager courseManager;
	private JButton edit;
	private JTextField newDescription;
	private JButton deleteCourse;
	private JButton viewStudents;
	private JButton assignGrade;
	private JTextField student;
	private JTextField grade;
	private Course currCourse;
	private JButton back;


	public CourseGUI(CourseManager courseManager, Course course) {
		this.courseManager = courseManager;
		this.currCourse = course;
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		edit = new JButton("Edit course description");
		newDescription = new JTextField("NEW description");
		deleteCourse = new JButton("Delete course");
		viewStudents = new JButton("View roster");
		assignGrade = new JButton("Assign Grade");
		student = new JTextField("ENTER student name");
		grade = new JTextField("ENTER grade");
		back = new JButton("Back");
	}

	private void editCourse(String description) {
		currCourse.setDescription(description);
		JOptionPane.showMessageDialog(null, "Course update successful");
	}

	private void deleteCourse() {
		courseManager.deleteCourse(currCourse.getName());
		String message = "Course deleted!";
		JOptionPane.showMessageDialog(null, message);
		this.setVisible(false);
	}
	private void displayStudents() {
		Map<String, String> classList = currCourse.getStudents();
		String roster = "";
		for (Map.Entry<String, String> entry: classList.entrySet()) {
			roster += (entry.getKey() + ": " + entry.getValue() + "\n");
		}
		JOptionPane.showMessageDialog(null, roster);
	}

	private void assignGradeToStudent(String name, String grade) {
		int result = currCourse.assignGrade(name, grade);
		String message;
		if (result ==1) {
			message = "Error could not find student";
		} else {
			message = "Grade update successful";
		}
		JOptionPane.showMessageDialog(null, message);
	}
	
	private void goBack() {
		this.setVisible(false);
	}
	
	public void run() {
		this.setLayout(new FlowLayout());
		this.add(edit);
		this.add(newDescription);
		this.add(deleteCourse);
		this.add(viewStudents);
		this.add(assignGrade);
		this.add(student);
		this.add(grade);
		this.add(back);
		String name = currCourse.getName();
		this.setTitle("Viewing: " + name);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == edit) {
					editCourse(newDescription.getText());
				} if (e.getSource() == deleteCourse) {
					deleteCourse();
				} if (e.getSource() == viewStudents) {
					displayStudents();
				} if (e.getSource() == assignGrade) {
					assignGradeToStudent(student.getText(), grade.getText());
				} if (e.getSource() == back) {
					goBack();
				}
				
			}
		};
		edit.addActionListener(listener);
		deleteCourse.addActionListener(listener);
		viewStudents.addActionListener(listener);
		assignGrade.addActionListener(listener);
		back.addActionListener(listener);
		this.setVisible(true);
		String message = "Now viewing: " + name;
		JOptionPane.showMessageDialog(null, message);
	}
}

