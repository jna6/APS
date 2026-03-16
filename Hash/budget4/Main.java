package Hash.budget4;

import jdk.jshell.PersistentSnippet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class MapEntry<K extends Comparable<K>, E> {
    // Each MapEntry object is a pair consisting of a key (a Comparable object)
    // and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}
class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
        // Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey)) return currNode;
            else currNode = currNode.succ;
        }
        return null;
    }

    public void insert(K key, E val) {
        // Insert the entry <key, val> into this CBHT.
        // If entry with same key exists, overwrite it.
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else currNode = currNode.succ;
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) buckets[b] = currNode.succ;
                else predNode.succ = currNode.succ;
                return;
            } else {
                predNode = currNode;
                currNode = currNode.succ;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}

class Person{
    String firstName;
    String LastName;
    int budget;
    String ip_address;
    String time;
    String city;
    int price;

    public Person(String firstName, String lastName, int budget, String ip_address, String time, String city, int price) {
        this.firstName = firstName;
        LastName = lastName;
        this.budget = budget;
        this.ip_address = ip_address;
        this.time = time;
        this.city = city;
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n= Integer.parseInt(scanner.nextLine());


        CBHT<String, List<Person>> table= new CBHT<>(n*2);

        for (int i=0;i<n; i++){
            String input= scanner.nextLine();
            String[ ] parts=input.split("\\s+");

            String firstName= parts[0];
            String lastName=parts[1];
            int budget= Integer.parseInt(parts[2]);
            String ip_address=parts[3];
            String network= ip_address.substring(0,ip_address.lastIndexOf("."));
            String time=parts[4];
            String city=parts[5];
            int price= Integer.parseInt(parts[6]);

            Person person= new Person(firstName,lastName,budget,ip_address,time,city,price);

            if (table.search(network)==null){
                table.insert(network,new ArrayList<>());
            }

            List<Person> list= table.search(network).element.value;
            list.add(person);
        }

        scanner.nextLine();
        int m= Integer.parseInt(scanner.nextLine());


        for (int i=0; i<m;i++){
            String input= scanner.nextLine();
            String [] parts=input.split("\\s+");
            String network= parts[3].substring(0,parts[3].lastIndexOf("."));

            List<Person> list=table.search(network).element.value;
            int max=0;
            Person maxPerson=null;
            int count=0;

            for (int j=0; j<list.size();j++ ){
                Person p=list.get(j);

                if (p.budget>= p.price){
                    count++;
                    if (p.price>max){
                        max = p.price;
                        maxPerson=p;
                    }
                }
            }
            System.out.println("IP network: "+ network+ " has the following number of users:"+"\n" +count);
            System.out.println("The user who spent the most from that network is: ");
            System.out.println(maxPerson.firstName+" "+maxPerson.LastName+ " with salary "+ maxPerson.budget+" from address "+ maxPerson.ip_address+" who spent "+ max);
            System.out.println();

        }
    }
}
