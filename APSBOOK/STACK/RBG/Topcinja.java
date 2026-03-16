package APSBOOK.STACK.RBG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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

public class Topcinja {
    public static String ponistiTopcinja(List list) {
        ArrayStack<String> crveni = new ArrayStack<>(100);
        ArrayStack<String> zeleni = new ArrayStack<>(100);
        ArrayStack<String> sini = new ArrayStack<>(100);

        String s;
        String izlez= new String();
        int n=0;
        int par=0;

        for (int i=0; i<list.size();i++){
            s= (String) list.get(i);

            if (s.charAt(0)=='R'){
                if (!crveni.isEmpty()) {
                    if (crveni.peek().equals(s)){
                        crveni.push(s);
                    }else {
                        crveni.pop();
                        par++;
                    }
                }else crveni.push(s);
            }
            if (s.charAt(0)=='G'){
                if (!zeleni.isEmpty()) {
                    if (zeleni.peek().equals(s)){
                        zeleni.push(s);
                    }else {
                        zeleni.pop();
                        par++;
                    }
                }else zeleni.push(s);
            }
            if (s.charAt(0)=='B'){
                if (!sini.isEmpty()) {
                    if (sini.peek().equals(s)){
                        sini.push(s);
                    }else {
                        sini.pop();
                        par++;
                    }
                }else sini.push(s);
            }
        }
        while (!crveni.isEmpty()){
            n++;
            if (crveni.pop().charAt(1)=='+')
                izlez+="R- ";
            else
                izlez+="R+ ";
        }
        while (!zeleni.isEmpty()){
            n++;
            if (zeleni.pop().charAt(1)=='+')
                izlez+="G- ";
            else
                izlez+="G+ ";
        }
        while (!sini.isEmpty()){
            n++;
            if (sini.pop().charAt(1)=='+')
                izlez+="B- ";
            else
                izlez+="B+ ";
        }
        System.out.println(par);
        System.out.println(n);
        return izlez;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String vlez[]= new String[100];
        vlez=br.readLine().split(" ");

        List<String> topcinja= new LinkedList<>();
        for (int i=0; i< vlez.length;i++)
            topcinja.add(vlez[i]);

        System.out.println(ponistiTopcinja(topcinja));
    }
}
