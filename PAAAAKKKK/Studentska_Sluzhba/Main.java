package PAAAAKKKK.Studentska_Sluzhba;

import java.sql.Struct;
import java.util.NoSuchElementException;
import java.util.Scanner;
interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:
    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}
class ArrayQueue<E> implements Queue<E>{
    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        if (length == elems.length)
            throw new NoSuchElementException();
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}


class Student{
    String name;
    int giveDoc;
    int takeId;
    int takeDoc;

    public Student(String name, int giveDoc, int takeId, int takeDoc) {
        this.name = name;
        this.giveDoc = giveDoc;
        this.takeId = takeId;
        this.takeDoc = takeDoc;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        int n= Integer.parseInt(scanner.nextLine());

        Queue<Student> queueGiveDoc= new ArrayQueue<>(n);
        Queue<Student> queueTakeId= new ArrayQueue<>(n);
        Queue<Student> queueTakeDoc=new ArrayQueue<>(n);



        for (int i=0; i<n; i++){
            String name= scanner.nextLine();
            int giveDoc= Integer.parseInt(scanner.nextLine());
            int takeId= Integer.parseInt(scanner.nextLine());
            int takeDoc= Integer.parseInt(scanner.nextLine());

            Student student= new Student(name,giveDoc,takeId,takeDoc);

            if (giveDoc==1){
                queueGiveDoc.enqueue(student);
            }else if (takeId==1){
                queueTakeId.enqueue(student);
            }else {
                queueTakeDoc.enqueue(student);
            }
        }
        for (int i=0; i<n;i++){

            if (!queueGiveDoc.isEmpty()){
                Student student= queueGiveDoc.peek();
                if (student.takeId==1){
                    queueTakeId.enqueue(queueGiveDoc.dequeue());
                } else if (student.takeDoc==1) {
                    queueTakeDoc.enqueue(queueGiveDoc.dequeue());
                }else
                    System.out.println(queueGiveDoc.dequeue().name);
            }
            if (!queueTakeId.isEmpty()){
                Student student=queueTakeId.peek();
                if (student.takeDoc==1){
                    queueTakeDoc.enqueue(queueTakeId.dequeue());
                }else
                    System.out.println(queueTakeId.dequeue().name);
            }
            if (!queueTakeDoc.isEmpty()){
                Student student=queueTakeDoc.peek();
                System.out.println(queueTakeDoc.dequeue().name);
            }
        }
    }
}
