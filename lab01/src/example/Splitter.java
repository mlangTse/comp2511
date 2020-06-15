package example;
import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner a = new Scanner(System.in);

        String[] word = a.nextLine().split(" ");

        for (String i : word){
            System.out.println(i);
        }
        
        a.close();
    }
}
