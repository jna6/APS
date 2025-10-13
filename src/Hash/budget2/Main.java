package Hash.budget2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class Traveler implements Comparable<Traveler> {
    String firstName;
    String lastName;
    int budget;
    String ip_address;
    String time;
    String city;
    int price;

    public Traveler(String firstName, String lastName, int budget, String ip_address, String time, String city, int price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.budget = budget;
        this.ip_address = ip_address;
        this.time = time;
        this.city = city;
        this.price = price;
    }


    @Override
    public int compareTo(Traveler o) {
        if (this.equals(0)){
            return 0;
        }
        return 1;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        CBHT<String, List<Traveler>> table=new CBHT<>(2*N);
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();

            String[] parts = line.split("\\s+");
            String firstName=parts[0];
            String lastName=parts[1];
            int budget=Integer.parseInt(parts[2]);
            String ip_address=parts[3];
            String time=parts[4];
            String city=parts[5];
            int price=Integer.parseInt(parts[6]);

            Traveler traveler= new Traveler(firstName,lastName,budget,ip_address,time,city,price);

            if (table.search(city)==null){
                table.insert(city, new ArrayList<>());

            }

            List<Traveler> list=table.search(city).element.value;
            list.add(traveler);
        }
        sc.nextLine();
        int m=Integer.parseInt(sc.nextLine());
        for (int i = 0; i < m; i++) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            String city=parts[5];
            List<Traveler> list=table.search(city).element.value;
            int count=0;
            String minTime="99:99";
            Traveler earliest=null;

            for (int j = 0; j < list.size(); j++) {
                Traveler traveler=list.get(j);
                if (traveler.time.compareTo("11:59")>0) {
                    count++;
                    if (traveler.time.compareTo(minTime)<0) {
                        minTime=traveler.time;
                        earliest=traveler;
                    }
                }
            }

            System.out.println("City: "+city+" has the following number of customers:\n" +count);
            System.out.println("The user who logged on earliest after noon from that city is:");
            System.out.println(earliest.firstName+" "+earliest.lastName+" with salary "+ earliest.budget+" from address "+earliest.ip_address+" who logged in at "+earliest.time+"\n");
        }

    }
}