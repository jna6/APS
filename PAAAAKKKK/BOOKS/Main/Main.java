package PAAAAKKKK.BOOKS.Main;

import java.util.Scanner;
import java.util.Stack;

class Book{
    String name;
    int count=0;

    public Book(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int n= scanner.nextInt();
        int m= scanner.nextInt();

        Stack<String> stack= new Stack<>();
        String[] count= new String[n];

        for (int i=0;i<n;i++){
            String input= scanner.nextLine();
            String [] books=input.split(" ");
            String book= books[i];
            stack.push(book);
        }
        for (int i=0;i<m;i++){
            String input= scanner.nextLine();
            String[] terms= input.split(" ");
        }

        for (int i=0;i<m;i++){
        }

    }
}
