package Hash.person2;

// Следните класи веќе се импортирани, не е дозволено копирање на класи овде, директно користејте ги како кога се достапни во други локални фајлови:
// The following classes are already imported, copying classes here is not allowed, use them directly as when they are available in other local files:

// CBHT, OBHT, MapEntry, SLLNode веќе се импортирани
// CBHT, OBHT, MapEntry, SLLNode are already imported

// Овде креирајте ги помошните класи за клуч и вредност
// Исполнете ги барањата од текстот за toString методите
// Дополнително осигурете се дека вашата клуч класа ќе ги имплементира потребните
// hashCode и equals методи

import java.util.Scanner;
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
class SLLNode<E> {
    protected E element;
    protected  SLLNode<E> succ;

    public SLLNode(E elem,   SLLNode<E> succ) {
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
    private   SLLNode<  MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (  SLLNode<  MapEntry<K, E>>[]) new   SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public   SLLNode<  MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
        // Return a link to that node (or null if there is none).
        int b = hash(targetKey);
          SLLNode<  MapEntry<K, E>> currNode = buckets[b];
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
          MapEntry<K, E> newEntry = new   MapEntry<>(key, val);
        int b = hash(key);
          SLLNode<  MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
              MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else currNode = currNode.succ;
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new   SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
          SLLNode<  MapEntry<K, E>> predNode = null, currNode = buckets[b];
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
              SLLNode<  MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}


// Create the helper classes for key and value here
// Fulfill the requirements from the text for the toString methods
// Additionally, make sure that your key class will implement the required
// hashCode and equals methods
class Person implements Comparable<  Person> {
    // поставете ги потребните полиња овде
    // declare the required fields here
    String name;
    int age;

    // имплементирајте соодветен конструктор
    // implement the constructor

    public Person(String name, String age) {
        this.name = name;
        this.age = Integer.parseInt(age);
    }

    @Override
    public String toString() {
        // имплементирајте го toString методот според барањето во текстот
        // implement the toString method as requested in the text
        return "<"+name+", "+age+">";
    }


    // имплементирајте ги следните два методи за да работи табелата правилно
    // implement the following two methods to make the table work properly
    @Override
    public boolean equals(Object o) {
          Person p=(  Person) o;
        return this.name.equals(p.name)&& this.age==p.age;
    }
    @Override
    public int hashCode() {
        return age*(int)name.charAt(0);
    }

    @Override
    public int compareTo(  Person o) {
        return 0;
    }
}

class Project {
    int workTime;
    int hourSalary;

    public Project(String workTime, String hourSalary) {
        this.workTime = Integer.parseInt(workTime);
        this.hourSalary = Integer.parseInt(hourSalary);
    }
    public  int getTotalSalary(){
        return workTime*hourSalary;
    }

    @Override
    public String toString() {
        return "<"+workTime+", "+hourSalary+">";
    }
}

public class Solution {
    public static void main(String[] args) {
        // Креирајте ја табелата според барањата
        // Create the table as requested
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        scanner.nextLine();

          CBHT<  Person,   Project> table=new   CBHT<>(10);

        for (int i=0;i<n;i++){
            String line= scanner.nextLine();
            String[]parts=line.split("\\s+");
            Person person= new Person(parts[0],parts[1]);
            Project project= new Project(parts[2],parts[3]);

            if (table.search(person)==null){
                table.insert(person,project);
            }else {
                Project p=table.search(person).element.value;
                if (p.workTime*p.hourSalary<project.workTime*project.hourSalary){
                    table.insert(person,project);
                }
            }
        }
        System.out.println(table);

        // Прочитајте ги податоците од влезот и пополнете ја табелата
        // Read the input data and fill the table

        // отпечатете ја вашата табела
        // print your table

    }
}


