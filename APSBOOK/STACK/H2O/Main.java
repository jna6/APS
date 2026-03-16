package APSBOOK.STACK.H2O;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {
    // Elementi na stekot se objekti od proizvolen tip.
    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:
    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}
class ArrayStack<E> implements Stack<E> {
    private E[] elems; //elems[0...depth-1] se negovite elementi.
    private int depth; //depth e dlabochinata na stekot

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }

    public int size() {
        // Ja vrakja dolzinata na stack-ot.
        return depth;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}
public class Main {
    public static String Pair(List list){

        ArrayStack<String> h=new ArrayStack<>(100);
        ArrayStack<String> o=new ArrayStack<>(100);
        int molecule=0;
        String line=null;
        int hcount=0;
        String output= new String();

        for (int i=0;i<list.size();i++){
            line= (String) list.get(i);

            if (line.charAt(0)=='H'){
                h.push(line);
                hcount++;
                if (hcount>=2 && !o.isEmpty()){
                    h.pop();
                    h.pop();
                    molecule++;
                    hcount-=2;
                    o.pop();
                }
            }else if (line.charAt(0)=='O'){
                if( hcount>=2){
                    h.pop();
                    h.pop();
                    molecule++;
                    hcount-=2;
                }else
                    o.push(line);
            }
        }
        while (hcount!=0){
            output+="H \n";
            hcount--;
        }
        while (!o.isEmpty()){
          //  System.out.println("O");
            output+="O \n";
            o.pop();
        }
        System.out.println(molecule);
        return output;
    }
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        List<String> list= new ArrayList<>();
        for (int i=0;i<input.length;i++){
            list.add(input[i]);
        }
        System.out.println(Pair(list));
    }
}
