package ISPITNI.JAN25;

import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Stack<String> stack= new Stack<>();
        boolean valid=true;

//        Rule: subsubsection must be inside subsection
//        Rule: text must be inside any other block
        for (int i=0; i<n;i++){
            String line= scanner.nextLine();

            if (line.startsWith("/")){
                if (line.contains("subsection")&& !stack.contains("/begin{section}")){
                    valid=false;
                    break;
                } else if (line.contains("subsubsection") && !stack.contains("/begin{subsection}")) {
                    valid=false;
                    break;
                } else if (line.contains("/end")) {
                    String part= "/end"+stack.peek().substring(6);
                    if (!stack.isEmpty() && line.equals(part)){
                        stack.pop();
                    }else {
                        valid=false;
                        break;
                    }
                } else if (line.contains("text") && stack.isEmpty()) {
                        valid=false;
                        break;
                } else {
                    stack.push(line);
                }
            }else {
                if (!stack.peek().contains("text")){
                    valid=false;
                    break;
                }
            }
        }
        if (stack.isEmpty() && valid){
            System.out.println("TRUE");
        }else
            System.out.println("FALSE");
    }
}