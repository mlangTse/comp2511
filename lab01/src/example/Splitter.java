package example;
import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner a = new Scanner(System.in);

        String[] word = a.nextLine().split(" ");

        for (int i = 0; i < word.length; i++){
            if (i != 0){
                System.out.println();
            }
            System.out.print(word[i]);
        }
    }
}
