package Set;

/**
 * Demo file, it may not be correct and/or complete.  
 * Please watch the corresponding lecture(s) for more explanations.
 * 
 * @author ashesh
 */

import java.util.*;
import Shapes.Circle;

public class SetCountErr extends CircleSet {
	public int countAdd = 0;
	
	public void add(Circle c) {
		super.add(c);
		countAdd++;
	}

	public void add(ArrayList<Circle> list) {
		super.add(list);
		countAdd = countAdd + list.size();
	}
	
	public static void main(String[] args) {		
		Circle c1 = new Circle(2);
		Circle c2 = new Circle(5);
		ArrayList<Circle> l1 = new ArrayList<Circle>();
		l1.add(c1); 
		l1.add(c2);

		SetCountErr s1 = new SetCountErr();
		s1.add(l1);
		
		// The following prints wrong answer! Why?
		System.out.println("count is " + s1.countAdd);
		
	}

}
