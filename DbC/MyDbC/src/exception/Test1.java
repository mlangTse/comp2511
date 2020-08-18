package exception;

import java.io.IOException;

public class Test1 {

	public static void main(String[] args)  {

		ListOfNumbers ln = new ListOfNumbers();
		try {
			
			ln.writeList();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(" ### end of program ");
	}

}
