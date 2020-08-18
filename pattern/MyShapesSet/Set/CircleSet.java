/**
 * Demo file, it may not be correct and/or complete.  
 * Please watch the corresponding lecture(s) for more explanations.
 * 
 * @author ashesh
 */

package Set;

import java.util.*;
import Shapes.Circle;

public class CircleSet {

	private ArrayList<Circle> setCircle = new ArrayList<Circle>();
	
	public void add(Circle c) {

		/**
		 * Circle overrides "equals", 
		 * so we can use "contains" to check a membership
		 */
		if(! setCircle.contains(c)) {
			setCircle.add(c);
		}
	}
	
	
	public void add(ArrayList<Circle>  s1) {
		for (Circle c : s1) {
			//this.add(c);
			if(! setCircle.contains(c)) {
				setCircle.add(c);
			}
		}
	}
		
	public int getSize() {
		return setCircle.size();
	}
	
	public static void main(String[] args) {
		
		Circle c1 = new Circle();
		Circle c2 = new Circle();
		CircleSet s = new CircleSet();
		s.add(c1);
		s.add(c2);
		System.out.println("Size is " + s.getSize());
		
	}

}
