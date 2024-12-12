
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private HashSet<String> enrolled;
    private transient List<Course> cart;

    public Student(String name) {
        this.name = name;
        this.enrolled = new HashSet<>();
        this.cart = new ArrayList<>();
    }
    public Student() {
    	this.name = null;
    	this.enrolled = new HashSet<>();
        this.cart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    public HashSet<String> getEnrolled(){
    	return enrolled;
    }

    public List<Course> getShoppingCart() {
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    public void addToCart(Course course) {
        if (!getShoppingCart().contains(course)) {
            getShoppingCart().add(course);
        }
    }

    public void removeFromCart(Course course) {
        getShoppingCart().remove(course);
    }
    
    public void removeFromCart(String course) {
    	try {
    		for (Course c: getShoppingCart()) {
        	
        		if (course.equals(c.getName())) {
        			getShoppingCart().remove(c);     			
        		}
        	}
    	} catch(Exception e) {
    	}
    	
    }

    public void enrollFromCart() {
        for (Course course : getShoppingCart()) {
        	String courseName = course.getName();
            enrolled.add(courseName);
            course.addStudent(name);
        }
        cart.clear();
    }
}




