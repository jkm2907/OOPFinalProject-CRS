import javax.swing.border.EmptyBorder;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class CartGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Student student;
	private JTextField toDelete;
	private JButton enroll;
	private JButton delete;
	private JTextField courseText;
	private JButton back;

	public CartGUI(Student student) {
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.student = student;
		toDelete = new JTextField();
		courseText = new JTextField();
		enroll = new JButton("Enroll Cart");
		delete = new JButton("Remove Specified from Cart");
		back = new JButton("Go Back");
	}

	private void delete(String course) {
		student.removeFromCart(course);
	}
	private void displayCart() {
		List<Course> courses = student.getShoppingCart();
		String cartText ="";
		for (Course c: courses) {
			cartText += (c.getName() + ": " + c.getDescription() + "\n");
		}
		if (courses.size()==0) {
			cartText="Empty cart";
		}
		courseText.setEditable(true);
		courseText.setText(cartText);
		courseText.setEditable(false);
	}
	private void enrollCart() {
		student.enrollFromCart();
		String message = "Cart enrolled successfully";
		JOptionPane.showMessageDialog(null, message);
	}

	private void goBack() {
		this.setVisible(false);
	}

	public void run() {
		this.setLayout(new FlowLayout());
		toDelete.setPreferredSize(new Dimension(400, 40));
		this.add(toDelete);
		this.add(delete);
		this.add(enroll);
		this.add(courseText);
		this.add(back);
		displayCart();

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == delete) {
					delete(toDelete.getText());
				} if (e.getSource() == enroll) {
					enrollCart();
				} if (e.getSource() == back) {
					goBack();
				}          
			}
		};
		enroll.addActionListener(listener);
		delete.addActionListener(listener);
		back.addActionListener(listener);

		this.setVisible(true);
	}
}

