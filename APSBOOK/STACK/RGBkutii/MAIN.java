package APSBOOK.STACK.RGBkutii;

import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;
import java.util.Stack;

public class MAIN {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Stack<String> input= new Stack<>();

        for (int i=0; i<n; i++){
            input.push(scanner.nextLine());
        }

        Stack<String> temp=new Stack<>();
        Stack<String> reds= new Stack<>();
        Stack<String> greens= new Stack<>();
        Stack<String> blues= new Stack<>();
        int counter=0;
        for (int i=0; i<n;i++){
            if (input.peek().equals("R")){
                reds.push(input.pop());
                counter++;
                if (counter>=3){
                    while (counter!=0){
                        reds.pop();
                        counter--;
                    }
                }
//                while (counter>=3 && input.peek().equals("R")){
//                   reds.push(input.pop());
//                }
            }else if (input.peek().equals("G")){
                counter=0;
                greens.push(input.pop());
                if (counter>=3){
                    while (counter!=0){
                        reds.pop();
                        counter--;
                    }
                }
            }else {
                counter=0;
                blues.push(input.pop());
                if (counter>=3){
                    while (counter!=0){
                        reds.pop();
                        counter--;
                    }
                }
            }
        }
        while (!reds.isEmpty()){
            temp.push(reds.pop());
        }
        while (!greens.isEmpty()){
            temp.push(greens.pop());
        }
        while (!blues.isEmpty()){
            temp.push(blues.pop());
        }

        for (int i=0;i<temp.size();i++){
            System.out.print(temp.get(i)+ " ");
        }
        //System.out.println(temp);

    }
}
