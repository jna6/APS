package StackQueue23_24.MVR;

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
class ArrayQueue<E> implements Queue<E> {
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
class Person{
    String name;
    int id;
    int passport;
    int drivers;

    public Person(String name, int id, int passport, int drivers) {
        this.name = name;
        this.id = id;
        this.passport = passport;
        this.drivers = drivers;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPassport() {
        return passport;
    }

    public int getDrivers() {
        return drivers;
    }
    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n= sc.nextInt();
        sc.nextLine();
        Queue<Person> id_queue= new ArrayQueue<>(n);
        Queue<Person> passport_queue= new ArrayQueue<>(n);
        Queue<Person> drivers_queue= new ArrayQueue<>(n);

        for (int i=0;i<n;i++){
            String name= sc.nextLine();
            int id=Integer.parseInt(sc.nextLine());
            int passport=Integer.parseInt(sc.nextLine());
            int drivers= Integer.parseInt(sc.nextLine());
            Person person= new Person(name, id,passport,drivers);

            if (id==1){
                id_queue.enqueue(person);
            } else if (passport==1) {
                passport_queue.enqueue(person);
            } else if (drivers==1) {
                drivers_queue.enqueue(person);
            }
        }
        while (!id_queue.isEmpty()){
            Person next= id_queue.peek();
            if (next.getPassport()==1){
                passport_queue.enqueue(id_queue.dequeue());
            } else if (next.getDrivers()==1) {
                drivers_queue.enqueue(id_queue.dequeue());
            }else
                System.out.println(id_queue.dequeue());
        }
        while (!passport_queue.isEmpty()){
            Person next= passport_queue.peek();
            if (next.getDrivers()==1){
                drivers_queue.enqueue(passport_queue.dequeue());
            }else
                System.out.println(passport_queue.dequeue().getName());
        }
        while (!drivers_queue.isEmpty()){
            System.out.println(drivers_queue.dequeue().getName());
        }
    }
}
