package zadachiZaVezhbanje24_25.StackQueue.libraryPAK;

import java.time.Period;
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


class Pesrson{
    String name;
    int science;
    int scifi;
    int history;

    public Pesrson(String name, int science, int scifi, int history) {
        this.name = name;
        this.science = science;
        this.scifi = scifi;
        this.history = history;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int n= scanner.nextInt();
        scanner.nextLine();

        Queue<Pesrson> scienceQueue= new ArrayQueue<>(n);
        Queue<Pesrson> scifiQueue= new ArrayQueue<>(n);
        Queue<Pesrson> historyeQueue= new ArrayQueue<>(n);

        for (int i=0; i<n; i++){
            String line= scanner.nextLine();

            int science= Integer.parseInt(scanner.nextLine());
            int scifi= Integer.parseInt(scanner.nextLine());
            int history= Integer.parseInt(scanner.nextLine());

            Pesrson person= new Pesrson(line,science,scifi,history);

            if (science==1){
                scienceQueue.enqueue(person);
            } else if (scifi==1) {
                scifiQueue.enqueue(person);
            }else {
                historyeQueue.enqueue(person);
            }
        }
        while (!scienceQueue.isEmpty()){
            Pesrson next= scienceQueue.peek();
            if (next.scifi==1){
                scifiQueue.enqueue(scienceQueue.dequeue());
            } else if (next.history==1) {
                historyeQueue.enqueue(scienceQueue.dequeue());
            }else
                System.out.println(scienceQueue.dequeue().name);
        }
        while (!scifiQueue.isEmpty()){
            Pesrson next= scifiQueue.peek();
            if (next.history==1){
                historyeQueue.enqueue(scifiQueue.dequeue());
            }else
                System.out.println(scifiQueue.dequeue().name);
        }
        while (!historyeQueue.isEmpty()){
            System.out.println(historyeQueue.dequeue().name);
        }
    }
}
