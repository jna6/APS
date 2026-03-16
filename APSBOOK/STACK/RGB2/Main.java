package APSBOOK.STACK.RGB2;

import java.util.*;

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

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String [] input= new String[100];
        input=scanner.nextLine().split(" ");

        List<String> balls= new LinkedList<>();

        for (int i=0;i<input.length;i++){
            balls.add(input[i]);
        }

        System.out.println(pairBalls(balls));
    }

    private static String pairBalls(List balls) {

        ArrayStack<String> redstack= new ArrayStack<>(100);
        ArrayStack<String> greenstack= new ArrayStack<>(100);
        ArrayStack<String> bluestack= new ArrayStack<>(100);

        String s;
        String output= new String();
        int pair=0;
        int nopair=0;

        for (int i=0; i<balls.size();i++){
            s= (String) balls.get(i);

            if (s.charAt(0)=='R'){
                if (redstack.isEmpty()||s.charAt(1)==redstack.peek().charAt(1))
                    redstack.push(s);
                else {
                    redstack.pop();
                    pair++;
                }
            }
            if (s.charAt(0)=='G'){
                if (greenstack.isEmpty()||s.charAt(1)==greenstack.peek().charAt(1))
                    greenstack.push(s);
                else {
                    greenstack.pop();
                    pair++;
                }
            }
            if (s.charAt(0)=='B'){
                if (bluestack.isEmpty()||s.charAt(1)==bluestack.peek().charAt(1))
                    bluestack.push(s);
                else {
                    bluestack.pop();
                    pair++;
                }
            }


        }
        while (!redstack.isEmpty()){
            nopair++;
            if (redstack.pop().charAt(1)=='+'){
                output+="R- ";
            }else
                output+="R+ ";
        }
        while (!greenstack.isEmpty()){
            nopair++;
            if (greenstack.pop().charAt(1)=='+'){
                output+="G- ";
            }else
                output+="G+ ";
        }
        while (!bluestack.isEmpty()){
            nopair++;
            if (bluestack.pop().charAt(1)=='+'){
                output+="B- ";
            }else
                output+="B+ ";
        }

        System.out.println(nopair);
        return output;
    }
}
