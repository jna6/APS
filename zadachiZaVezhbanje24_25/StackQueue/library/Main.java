package zadachiZaVezhbanje24_25.StackQueue.library;

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

class PersonID{
    String name;
    int science;
    int scifi;
    int history;

    public PersonID(String name, int science, int scifi, int history) {
        this.name = name;
        this.science = science;
        this.scifi = scifi;
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public int getScience() {
        return science;
    }

    public int getScifi() {
        return scifi;
    }

    public int getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        Queue<PersonID> scienceQueue = new ArrayQueue<>(N);
        Queue<PersonID> scifiQueue= new ArrayQueue<>(N);
        Queue<PersonID> historyQueue=new ArrayQueue<>(N);

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            int science = Integer.parseInt(sc.nextLine());
            int scifi = Integer.parseInt(sc.nextLine());
            int history = Integer.parseInt(sc.nextLine());
            PersonID person = new PersonID(line, science, scifi, history);

            if (science==1){
                scienceQueue.enqueue(person);
            } else if (scifi==1) {
                scifiQueue.enqueue(person);
            }else if (history==1) {
                historyQueue.enqueue(person);
            }
        }

        while(!scienceQueue.isEmpty()){
            PersonID next=scienceQueue.peek();
            if (next.getScifi()==1){
                scifiQueue.enqueue(scienceQueue.dequeue());
            }else if (next.getHistory()==1){
                historyQueue.enqueue(scienceQueue.dequeue());
            }else{
                System.out.println(scienceQueue.dequeue());
            }
        }

        while(!scifiQueue.isEmpty()){
            PersonID next=scifiQueue.peek();
            if( next.getHistory()==1){
                historyQueue.enqueue(scifiQueue.dequeue());
            }else {
                System.out.println(scifiQueue.dequeue());
            }
        }
        while(!historyQueue.isEmpty()){
            System.out.println(historyQueue.dequeue());
        }


    }
}
