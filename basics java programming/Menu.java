import java.util.Locale;
import java.util.Scanner;

public class Menu {

    //scanner to get inputs
    public static Scanner scanner = new Scanner(System.in);
    //an array to store marks
    public static int[] marks = new int[7];
    //to store maximum grade
    public static int max = 0;
    public static int option;
    public static int i;
    public static int digits = 0;
    public static void getInputs(){
        for (int i = 0; i < marks.length; i++) {

            //print a message
            if (i + 1 == 1) {
                System.out.print("Enter the score for the 1st student ");
            } else if (i + 1 == 2) {
                System.out.print("Enter the score for the 2nd student ");
            } else if (i + 1 == 3) {
                System.out.print("Enter the score for the 3rd student ");
            } else {
                System.out.print("Enter the score for the " + (i + 1) + "th student ");
            }

            //get an input
            int mark = IOscanner.IntRead();

            //input is not valid
            if (mark < 0 || mark > 100) {
                System.out.println("Error - Input out of bound. Score can only be between 0  and 100.");
                i--;//reduce i to take this record agian
            } //valid
            else {
                marks[i] = mark;
            }
        }
        for (i = 0; i < marks.length; i++){
            if (marks[i] > max)
                max = marks[i];
        }

        System.out.println("Thank you for your input. Your entered scores are:");
        System.out.print(marks[0] + ", "+ marks[1]+", "+marks[2]+", "+ marks[3]+", "+marks[4]+", "+marks[5]+ ", "+marks[6]);

    }


    public static void main(String[] args){

        getInputs();

        do {

            System.out.println();
            System.out.println("Welcome to the menu. Choose one of the options below:");
            System.out.println("1. Register new scores for students.");
            System.out.println("2. Calculate the mean of the entered scores.");
            System.out.println("3. Find the two highest and two lowest scores.");
            System.out.println("4. Find the highest score and its position.");
            System.out.println("5. Collect hashtags from a post.");
            System.out.println("6. To exit.");
            System.out.print("Type your option: ");

            option = IOscanner.IntRead();

            if (option == 1) {
                getInputs();
            } else if (option == 2) {
                printMean();
            } else if (option == 3) {
                printTwoLowestAndTwoHighestGrades();
            } else if (option == 4) {
                printHighestAndIndex();
            } else if (option == 5) {
                printHashTags();
            } else if (option == 6) {
                System.out.print("Thank you for using our grading system. Have a nice day!");
            } else {
                System.out.println("Error - Invalid value. Please type between 1 and 6");
            }
        }while (option != 6);
    }




    /*TASK 2*/
    public static void printMean() {

        //calculating sum
        int total = 0;//initialize to 0
        for (int i = 0; i < 7; i++) {
            total += marks[i]; // add every mark together
        }
        //calculating mean
        double mean = total / 7.0;

        Locale.setDefault(Locale.ENGLISH);
        System.out.printf("The mean of the numbers is %.2f", mean); //prints upto two decimal points

    }

    /*TASK 3*/
    public static void printTwoLowestAndTwoHighestGrades() {

        //create variables to store two highest grades and two lowest grades
        //initialize them
        int highest = -1;
        int second_highest = -1;
        int lowest = 101;
        int second_lowest = 101;

        //finding two highest grades and two lowest gades
        for (int i = 0; i < 7; i++) {
            if (highest < marks[i]) {
                second_highest = highest;
                highest = marks[i];
            } else if (second_highest < marks[i]) {
                second_highest = marks[i];
            }

            if (lowest > marks[i]) {
                second_lowest = lowest;
                lowest = marks[i];
            } else if (second_lowest > marks[i]) {
                second_lowest = marks[i];
            }
        }

        //print them
        System.out.println("The two lowest scores provided are " + lowest + ", and " + second_lowest);
        System.out.print("The two highest scores provided are " + highest + ", and " + second_highest);

        //set max
        max = highest;
    }

    /*Task 4*/
    public static void printHighestAndIndex() {
        //since we have already found the highest score we don't have to find it again
        //lets find the index of it
        int i;
        for (i = 0; i < 7; i++) {
            if (marks[i] == max) {//ith element is the highest
                break; //stop the loop there
            }
        }
        //print
        if (i + 1 == 1) {
            System.out.println("\nThe highest score is " + max + " and belongs to the 1st student");
        } else if (i + 1 == 2) {
            System.out.println("\nThe highest score is " + max + " and belongs to the 2nd student");
        } else if (i + 3 == 5) {
            System.out.println("\nThe highest score is " + max + " and belongs to the 3rd student");
        } else {
            System.out.println("\nThe highest score is " + max + " and belongs to the " + (i + 1) + "th student");
        }
    }

    /*Task 5*/
    public static void printHashTags() {
        System.out.print("Type your post: ");
        String script =IOscanner.TextRead();




        String[] words = script.split(" ");


        String[] hashtags = new String[words.length];
        for (String str : words) {
            if (str.startsWith("#")) {
                hashtags[digits] = str;
                digits += 1;
            }
        }

        if (digits > 0) {
            System.out.print("Hashtags found: ");
            for (int i = 0; i < digits; i++) {
                System.out.print(hashtags[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("No hashtags were typed");
        }
    }

}



