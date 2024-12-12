import java.util.*;
import java.util.List;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class StudentPageGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CourseManager courseManager;
	private Student student;

	JButton viewAllCourses;
	JButton addToCart;
	JTextField toCart;
	JButton viewCart;
	JButton viewEnrolled;
	JButton viewGrades;
	JButton logout;
	
	public StudentPageGUI(Student student, CourseManager courseManager) {
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.courseManager = courseManager;
	    this.student = student;
	    viewAllCourses = new JButton("View All Courses");
	    addToCart = new JButton("Add Course to Shopping Cart");
	    toCart = new JTextField("ENTER class name here");
	    viewCart = new JButton("View Cart");
	    viewEnrolled = new JButton("View Enrolled Courses");
	    viewGrades = new JButton("View Grades");
	    logout = new JButton("Log Out");
	    this.setTitle("Student Page");
	}
	
	private void displayAllCourses() {
		List<Course> courses = courseManager.getAllCourses();
		String coursesText ="";
		for (Course c: courses) {
			coursesText += (c.getName() + ": " + c.getDescription() + "\n");
		}
		JOptionPane.showMessageDialog(null, coursesText);
	}
	private void addCourseToCart(String courseName) {
		Course course = courseManager.findCourse(courseName);
		String message = "Course not found please try again";
        if (course != null) {
            student.addToCart(course);
            message = courseName + " added to your shopping cart.";
            JOptionPane.showMessageDialog(null, message);
        } else {
        	JOptionPane.showMessageDialog(null, message);
        }
	}
	private void displayCart() {
		CartGUI newCart = new CartGUI(student);
		newCart.run();
	}
	
	private void displayEnrolled() {
		String classes = "";
		HashSet<String> enrolled = student.getEnrolled();
		for (String c: enrolled) {
			classes += (c + "\n");
		}
		JOptionPane.showMessageDialog(null, classes);				
	}
	private void displayGrades() {
		String grades = courseManager.getGrades(student);		
		JOptionPane.showMessageDialog(null, grades);
		
	}
	private void exitGUI() {
		this.setVisible(false);
	}
	
	public void run() {

		this.setLayout(new FlowLayout());
		this.add(viewAllCourses);
		this.add(addToCart);
		this.add(toCart);
		this.add(viewCart);
		this.add(viewEnrolled);
		this.add(viewGrades);
		this.add(logout);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewAllCourses) {
					displayAllCourses();
				}
				if (e.getSource() == addToCart) {
					addCourseToCart(toCart.getText());
				}
				if (e.getSource() == viewCart) {
					displayCart();
				}
				if (e.getSource() == viewEnrolled) {
					displayEnrolled();
				}
				if (e.getSource() == viewGrades) {
					displayGrades();
				}
				if (e.getSource() == logout) {
					exitGUI();
				}
			}
		};
		viewAllCourses.addActionListener(listener);
		addToCart.addActionListener(listener);
		viewCart.addActionListener(listener);
		viewEnrolled.addActionListener(listener);
		viewGrades.addActionListener(listener);
		logout.addActionListener(listener);
		this.setVisible(true);
	}
}

