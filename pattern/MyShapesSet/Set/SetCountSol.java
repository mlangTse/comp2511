package Set;

/**
 * Demo file, it may not be correct and/or complete.  
 * Please watch the corresponding lecture(s) for more explanations.
 * 
 * @author ashesh
 */

import java.util.ArrayList;
import Shapes.Circle;

public class SetCountSol {

	private CircleSet cSet = new CircleSet();
	public int count = 0;
	
	public void add(Circle c) {
		cSet.add(c);
		count++;
	}
	
	public void add(ArrayList<Circle>  list) {
		cSet.add(list);
		count = count + list.size();
	}
	
	
	
	public static void main(String[] args) {
		Circle c1 = new Circle(2);
		Circle c2 = new Circle(5);
		ArrayList<Circle> l1 = new ArrayList<Circle>();
		l1.add(c1); 
		l1.add(c2);

		SetCountSol s1 = new SetCountSol();
		s1.add(l1);
		
		// The following prints wrong answer! Why?
		System.out.println("count is " + s1.count);

	}

}
