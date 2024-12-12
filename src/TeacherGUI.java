
import java.util.List;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class TeacherGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CourseManager courseManager;
	private JButton viewAllCourses;
	private JButton addNew;
	private JButton viewCourse;
	private JTextField courseEntry;
	private JTextField newCourse;
	private JTextField newDescription;
	private JButton back;

	public TeacherGUI(CourseManager courseManager) {
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.courseManager = courseManager;
	    viewAllCourses = new JButton("View All Courses");
	    addNew = new JButton("Add New Course");
	    viewCourse = new JButton("View a course");
	    courseEntry = new JTextField("ENTER course name");
	    newCourse = new JTextField("ENTER new course name");
	    newDescription = new JTextField("ENTER new course description");
	    this.setTitle("Teacher Page");
	    back = new JButton("Go Back");
	}
	
	private void displayCourses() {
		List<Course> courses = courseManager.getAllCourses();
		String coursesText ="";
		for (Course c: courses) {
			coursesText += (c.getName() + ": " + c.getDescription() + "\n");
		}
		JOptionPane.showMessageDialog(null, coursesText);
	}
	
	private void addNewCourse(String name, String description) {
		if (name.equals("Enter new course name") || description.equals("Enter new course description")) {
			JOptionPane.showMessageDialog(null, "Please fill in necessary info before adding a new class");
		} else {
			courseManager.addCourse(name, description);
			JOptionPane.showMessageDialog(null, "Course added!");
		}
		
		
	}
	private void viewCourse(String c) {
		Course selectedCourse = courseManager.findCourse(c);
		if (selectedCourse != null) {
			CourseGUI newCourseGUI = new CourseGUI(this.courseManager, selectedCourse);
			newCourseGUI.run();
		} else {
			JOptionPane.showMessageDialog(null, "Course not found please try again");
		}
		
	}
	private void goBack() {
		this.setVisible(false);
	}
	
	public void run() {

		this.setLayout(new FlowLayout());

		this.add(viewAllCourses);
		this.add(addNew);
		this.add(newCourse);
		this.add(newDescription);
		this.add(viewCourse);
		this.add(courseEntry);
		this.add(back);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewAllCourses) {
					displayCourses();
				}
				if (e.getSource() == addNew) {
					addNewCourse(newCourse.getText(), newDescription.getText());
				}
				if (e.getSource() == viewCourse) {
					viewCourse(courseEntry.getText());
				}
				if (e.getSource() == back) {
					goBack();
				}
			}
		};
		viewAllCourses.addActionListener(listener);
		addNew.addActionListener(listener);
		viewCourse.addActionListener(listener);
		back.addActionListener(listener);
		this.setVisible(true);
	}
}

