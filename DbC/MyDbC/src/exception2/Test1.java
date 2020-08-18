package exception2;

import java.io.IOException;

public class Test1 {

	public static void main(String[] args)   {
		
		try {
			ListOfNumbers ln = new ListOfNumbers();
			ln.writeList();
		}
		catch(IOException e) {
			System.err.println(">>>> My catch!!!!");
			// e.printStackTrace();
		}

		System.out.println("####  End of the program ..... ");

	}

}
