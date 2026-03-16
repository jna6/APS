package APSBOOK.STACK.IspitnaSesija;

import java.util.ArrayList;
import java.util.Arrays;
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
class LinkedStack<E> implements Stack<E> {
    // top e link do prviot jazol ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;
    int size;

    public LinkedStack() {
        // Konstrukcija na nov, prazen stek.
        top = null;
        size = 0;
    }

    public String toString() {
        SLLNode<E> current = top;
        StringBuilder s = new StringBuilder();
        while (current != null) {
            s.append(current.element);
            s.append(" ");
            current = current.succ;
        }
        return s.toString();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public void clear() {
        // Go prazni stekot.
        top = null;
        size = 0;
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        top = new SLLNode<E>(x, top);
        size++;
    }

    public int size() {
        // Ja vrakja dolzinata na stekot.
        return size;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        size--;
        top = top.succ;
        return topElem;
    }

}
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}
class Book{
    String name;
    int count;

    public Book(String name) {
        this.name = name;
        this.count = 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int n= scanner.nextInt();
        int m= scanner.nextInt();
        scanner.nextLine();

        Stack<Book> books= new LinkedStack<>();
        Stack<Book> books2= new LinkedStack<>();

        String book=null;

        Book[] booksArray= new Book[n];
        String[]terms=new String[m];

        for (int i=0; i<n;i++){
            booksArray[i]= new Book(scanner.next());
        }
        for (int i=0; i<n;i++){
            books.push(booksArray[i]);
        }
        for (int i=0;i<m;i++){
            terms[i]= scanner.next();
        }
        for (int i=0;i<m;i++){
            while (!books.isEmpty() && !terms[i].equals(books.peek().name)){
                Book b= books.pop();
                books2.push(b);
                b.count++;

            }

            if (!books.isEmpty()&& books.peek().name.equals(terms[i])){
                Book target=books.pop();
                target.count++;
                while (!books2.isEmpty()){
                    books.push(books2.pop());
                }
                books.push(target);
            }

        }
        for (Book b : booksArray){
            System.out.println( b.name+ " "+b.count);
        }
    }
}
