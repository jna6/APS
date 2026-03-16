package PAAAAKKKK.func;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        Stack<String> stack= new Stack<>();

        boolean valid=true;
        String line= new String();
        while (true){

            line= scanner.nextLine();

            if (line.equals("x")){
                break;
            }

            if (line.startsWith("end")){
                if (!stack.isEmpty() && line.matches("end"+stack.peek())){
                    stack.pop();
                }else {
                    valid=false;
                    break;
                }
            }else {
                stack.push(line);
            }

        }
        if (stack.isEmpty() && valid){
            System.out.println("Valid");
        }else
            System.out.println("Invalid");
    }
}
