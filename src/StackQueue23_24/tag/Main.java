package StackQueue23_24.tag;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in);
        int n= sc.nextInt();
        String[] input= new String[n];
        for (int i=0; i<n;i++){
            input[i]= sc.next();
        }
        if(checkTag(input))
            System.out.println(1);
        else
            System.out.println(0);


    }
    public static boolean checkTag(String[] input){
         Stack<String> stack= new Stack<>();
         for (String line:input){
             if (line.startsWith("[") && line.endsWith("]")){
                 if (line.charAt(1)!='/'){
                     stack.push(line);
                 }else {
                     if (line.substring(2).compareTo(stack.pop().substring(1))!=0){
                         return false;
                     }
                 }
             }
         }
        if(stack.isEmpty()){
            return true;
        }else
            return false;
    }
}
