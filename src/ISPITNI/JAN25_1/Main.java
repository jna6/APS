package ISPITNI.JAN25_1;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= Integer.parseInt(scanner.nextLine());

        Stack<String> stack= new Stack<>();
        boolean valid=true;

        for (int i=0; i<n; i++){
            String line= scanner.nextLine();
            if (line.startsWith("#")){
                if (line.matches("#Body")){
                    stack.push(line);
                } else if (line.contains("#Heading")) {
                    if (!stack.isEmpty() && stack.peek().matches("#Body")){
                        valid=false;
                        break;
                    } else if (!stack.isEmpty() &&line.substring(8).compareTo(stack.peek().substring(8))<0) {
                        valid=false;
                        break;
                    }else
                        stack.push(line);
                }

            } else if (line.endsWith("#")) {
                if (line.matches(stack.peek().substring(1)+"#")){
                    stack.pop();
                }else {
                    valid=false;
                    break;
                }
            }
        }

        if (stack.isEmpty()&&valid){
            System.out.println("TRUE");
        }else{
            System.out.println();
            System.out.println("FALSE");
            }
    }

}