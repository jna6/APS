import java.util.Scanner;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        int n= Integer.parseInt(scanner.nextLine());

        Stack<String> print= new Stack<>();

        for (int i=0;i<n;i++){
            String line= scanner.nextLine();
            String[] part= line.split(" ");

            if (part[0].contains("TYPE")){
                print.push(part[1]);
            }else if (part[0].contains("UNDO")){
                print.pop();
            } else if (part[0].contains("SHOW")) {
                for (int j=0; j<print.size();j++){
                    System.out.printf(print.get(j));
                }
                System.out.println();

            }
        }
    }
}