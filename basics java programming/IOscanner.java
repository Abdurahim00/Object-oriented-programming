import java.util.Scanner;

public class IOscanner {
    public static Scanner input = new Scanner(System.in);

    public static String TextRead(){
        String text = input.nextLine();
        return text;
    }

    public static int IntRead(){
        int a = 0;
        int number = input.nextInt();
        a = number;
        input.nextLine();
        return a;


    }


    public static void close(){
        input.close();
    }
}
