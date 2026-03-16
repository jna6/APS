package StackQueue23_24.func;

import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String line= new String();

        Stack<String> stack = new Stack<>();

        boolean valid= true;
        while (true){
            line = scanner.nextLine();
            if (line.equals("x")){
                break;
            }
            if (!line.startsWith("end")){
                stack.push(line);
            } else {
                if (stack.isEmpty()){
                    valid=false;
                } else if (line.equals("end"+stack.peek())) {
                    stack.pop();
                }
            }
        }
        if (!stack.isEmpty()){
            System.out.println("Invalid");
        } else if (stack.isEmpty() && valid) {
            System.out.println("Valid");
        }
    }
}