package zadachiZaVezhbanje24_25.StackQueue.library212;

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
    int science;
    int scifi;
    int history;

    public Student(String name, int science, int scifi, int history) {
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
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Student> science_queue = new ArrayQueue<>(n);
        Queue<Student> scifi_queue = new ArrayQueue<>(n);
        Queue<Student> history_queue = new ArrayQueue<>(n);

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            int science = Integer.parseInt(br.readLine());
            int scifi = Integer.parseInt(br.readLine());
            int history = Integer.parseInt(br.readLine());
            Student student = new Student(name, science, scifi, history);

            if (science == 1) {
                science_queue.enqueue(student);
            } else if (scifi == 1) {
                scifi_queue.enqueue(student);
            } else if (history == 1) {
                history_queue.enqueue(student);
            }
        }
        //2 1 2
        while (!science_queue.isEmpty() || !scifi_queue.isEmpty() || !history_queue.isEmpty()) {
            for (int i = 0; i < 2 && !science_queue.isEmpty(); i++) {
                Student next = science_queue.peek();
                if (next.getScifi() == 1) {
                    scifi_queue.enqueue(science_queue.dequeue());
                } else if (next.getHistory() == 1) {
                    history_queue.enqueue(science_queue.dequeue());
                } else
                    System.out.println(science_queue.dequeue().getName());
            }
            for (int i = 0; i < 1 && !scifi_queue.isEmpty(); i++) {
                Student next = scifi_queue.peek();
                if (next.getHistory() == 1) {
                    history_queue.enqueue(scifi_queue.dequeue());
                } else
                    System.out.println(scifi_queue.dequeue().getName());
            }
            for (int i = 0; i < 2 && !history_queue.isEmpty(); i++) {
                Student next = history_queue.peek();
                System.out.println(history_queue.dequeue().getName());
            }
        }
    }
}
