import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Practice {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack= new Stack<>();

        while (true){
            String line= br.readLine().trim();
            if (line.equals("x")){
                break;
            }
            if (!line.startsWith("end")){
                stack.push(line);
            }else if (line.equals("end"+stack.peek())){
                stack.pop();
            }
        }
        if (stack.isEmpty()){
            System.out.println("Valid");
        }else System.out.println("Invalid");
    }
}
