package headfirst.decorator.io;

import java.io.*;

public class InputTest {
	public static void main(String[] args) throws IOException {
		int c;
		String filename = "src/headfirst/decorator/io/test.txt";

		try {
			InputStream f1 = new FileInputStream(filename);
			InputStream b1 = new BufferedInputStream(f1);
			InputStream lCase1 = new LowerCaseInputStream(b1);
			//InputStream rot13 = new Rot13(b1);

			while ((c = lCase1.read()) >= 0) {
				System.out.print((char) c);
			}

			f1.close();
			b1.close();
			lCase1.close();
			//rot13.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
