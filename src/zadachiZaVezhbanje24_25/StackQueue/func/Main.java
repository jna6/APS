package zadachiZaVezhbanje24_25.StackQueue.func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//public class Main{
//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        Stack<String> stack= new Stack<>();
//
//        while (true){
//            String line= sc.nextLine();
//            if (line.equals("x")){
//                break;
//            }
//            if (!line.startsWith("end")){
//                stack.push(line);
//            } else {
//                if (stack.isEmpty()){
//                    break;
//                }else if (line.substring(3).equals(stack.peek())){
//                    stack.pop();
//                }
//            }
//        }
//        System.out.println(stack.isEmpty()? "Valid" : "Invalid");
//    }
//}
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//        Stack<String> stack = new Stack<>();
//
//        while (true){
//            String line = br.readLine().trim();
//
//            if (line.equals("x")){
//                break;
//            }
//            if (!line.startsWith("end")){
//                stack.push(line);
//            }else {
//                if (stack.isEmpty()){
//                    System.out.println("Invalid");
//                    return;
//                }
//                String last= stack.pop();
//                String expected= "end"+last;
//
//                if (!line.equals(expected)){
//                    System.out.println("Invalid");
//                    return;
//                }
//            }
//        }
//        System.out.println(stack.isEmpty() ? "Valid":"Invalid");
//    }
//}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack= new Stack<>();
        boolean isValid=true;

        while (true){
            String line= br.readLine().trim();
            if (line.equals("x")){
                break;
            }
            if (!line.startsWith("end")){
                stack.push(line);
            }else {
                if (stack.isEmpty()){
                    isValid=false;
                }else if (line.equals("end"+stack.peek())){
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()&& isValid){
            System.out.println("Valid");
        }else System.out.println("Invalid");
    }
}
