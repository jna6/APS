package zadachiZaVezhbanje24_25.StackQueue.studentska_sluzhba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

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

class Student {
    String name;
    int give;
    int takeId;
    int takeDoc;

    public Student(String name, int give, int takeId, int takeDoc) {
        this.name = name;
        this.give = give;
        this.takeId = takeId;
        this.takeDoc = takeDoc;
    }

    public String getName() {
        return name;
    }

    public int getGive() {
        return give;
    }

    public int getTakeId() {
        return takeId;
    }

    public int getTakeDoc() {
        return takeDoc;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Student> give_queue = new ArrayQueue<>(n);
        Queue<Student> takeId_queue = new ArrayQueue<>(n);
        Queue<Student> takeDoc_queue = new ArrayQueue<>(n);

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            int give = Integer.parseInt(br.readLine());
            int takeId = Integer.parseInt(br.readLine());
            int takeDoc = Integer.parseInt(br.readLine());
            Student student = new Student(name, give, takeId, takeDoc);

            if (give == 1) {
                give_queue.enqueue(student);
            } else if (takeId == 1) {
                takeId_queue.enqueue(student);
            } else if (takeDoc == 1) {
                takeDoc_queue.enqueue(student);
            }
        }
        while (!give_queue.isEmpty() || !takeId_queue.isEmpty() || !takeDoc_queue.isEmpty()) {
            for (int i = 0; i < 1 && !give_queue.isEmpty(); i++) {
                Student next = give_queue.peek();
                if (next.getTakeId() == 1) {
                    takeId_queue.enqueue(give_queue.dequeue());
                } else if (next.getTakeDoc() == 1) {
                    takeDoc_queue.enqueue(give_queue.dequeue());
                } else
                    System.out.println(give_queue.dequeue().getName());
            }
            for (int i = 0; i < 1 && !takeId_queue.isEmpty(); i++) {
                Student next = takeId_queue.peek();
                if (next.takeDoc == 1) {
                    takeDoc_queue.enqueue(takeId_queue.dequeue());
                } else
                    System.out.println(takeId_queue.dequeue().getName());
            }
            for (int i = 0; i < 1 && !takeDoc_queue.isEmpty(); i++) {
                Student next = takeDoc_queue.peek();
                System.out.println(takeDoc_queue.dequeue().getName());
            }
        }
    }
}
