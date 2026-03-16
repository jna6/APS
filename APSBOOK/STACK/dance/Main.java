package APSBOOK.STACK.dance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Stack;

//Да се напише алгоритам со коj ´ке се изврши креирање на танцови парови по соодветни танц-групи во една танцова школа. Танцов пар се формира од машко и женско запишани на иста танцова група.
//Во школата за танци на располагање има групи за основни танци О, стандардни танци S и латино танци L. Има уписен рок така што заинтересираните кандидати може да се упишат.
//Со завршување на уписниот рок се врши формирање на танцови двоjки. Ваша задача е од добиениот список на сите запишани кандидати да направите соодветни парови и да кажете колку, од каков тип на кандидати
//(машко или женско) и за коjа танцова група фалат за да сите добиjат своj партнер.
//Влез:
//LM OZ OM OM LM SZ SM LZ OM LZ SZ SM SM LM
//4
//LZ SZ OZ OZ

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}
class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        SLL<String> list= new SLL<>();
        String[] input= new String[100];
        input=scanner.nextLine().split("\\s");

        for (int i=0;i<input.length;i++){
            list.insertLast(input[i]);
        }
        System.out.println(pairDancers(list));
    }

    private static String pairDancers(SLL<String> list) {


        return null;
    }
}
//public class Main {
//    public static String noPair(String s) {
//        return switch (s) {
//            case "OM" -> "OZ";
//            case "OZ" -> "OM";
//            case "SM" -> "SZ";
//            case "SZ" -> "SM";
//            case "LM" -> "LZ";
//            case "LZ" -> "LM";
//            default -> "";
//        };
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//        String[] line = input.split(" ");
//
//        Stack<String> Ostack = new Stack<String>();
//        Stack<String> Sstack = new Stack<String>();
//        Stack<String> Lstack = new Stack<String>();
//
//        for (String str : line) {
//            if (str.charAt(0) == 'O') {
//                if (!Ostack.isEmpty() && !Ostack.peek().equals(str)){
//                    Ostack.pop();
//                }else Ostack.push(str);
//
//            }
//
//            if (str.charAt(0) == 'S') {
//                if (!Sstack.isEmpty() && !Sstack.peek().equals(str)){
//                    Sstack.pop();
//                }else Sstack.push(str);
//
//            }
//
//            if (str.charAt(0) == 'L') {
//                if (!Lstack.isEmpty() && !Lstack.peek().equals(str)){
//                    Lstack.pop();
//                }else Lstack.push(str);
//            }
//        }
//
//        System.out.println(Ostack.size() + Sstack.size() + Lstack.size());
//        for (String str : Ostack) System.out.println(noPair(str)+" ");
//        for (String str : Sstack) System.out.println(noPair(str)+" ");
//        for (String str : Lstack) System.out.println(noPair(str)+" ");
//
//    }
//}

//public class Main {
//     interface Stack<E> {
//        // Elementi na stekot se objekti od proizvolen tip.
//        // Metodi za pristap:
//
//        public boolean isEmpty();
//        // Vrakja true ako i samo ako stekot e prazen.
//
//        public E peek();
//        // Go vrakja elementot na vrvot od stekot.
//
//        // Metodi za transformacija:
//        public void clear();
//        // Go prazni stekot.
//
//        public void push(E x);
//        // Go dodava x na vrvot na stekot.
//
//        public E pop();
//        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//    }
//    static class ArrayStack<E> implements Stack<E> {
//        private E[] elems; //elems[0...depth-1] se negovite elementi.
//        private int depth; //depth e dlabochinata na stekot
//
//        @SuppressWarnings("unchecked")
//        public ArrayStack(int maxDepth) {
//            // Konstrukcija na nov, prazen stek.
//            elems = (E[]) new Object[maxDepth];
//            depth = 0;
//        }
//
//        public boolean isEmpty() {
//            // Vrakja true ako i samo ako stekot e prazen.
//            return (depth == 0);
//        }
//
//        public E peek() {
//            // Go vrakja elementot na vrvot od stekot.
//            if (depth == 0)
//                throw new NoSuchElementException();
//            return elems[depth - 1];
//        }
//
//        public void clear() {
//            // Go prazni stekot.
//            for (int i = 0; i < depth; i++) elems[i] = null;
//            depth = 0;
//        }
//
//        public void push(E x) {
//            // Go dodava x na vrvot na stekot.
//            elems[depth++] = x;
//        }
//
//        public int size() {
//            // Ja vrakja dolzinata na stack-ot.
//            return depth;
//        }
//
//        public E pop() {
//            // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//            if (depth == 0)
//                throw new NoSuchElementException();
//            E topmost = elems[--depth];
//            elems[depth] = null;
//            return topmost;
//        }
//    }
//    public static String pair(List<String> list){
//         ArrayStack<String> Ostack= new ArrayStack<>(100);
//         ArrayStack<String> Sstack= new ArrayStack<>(100);
//         ArrayStack<String> Lstack= new ArrayStack<>(100);
//
//         String line= new String();
//         String output= new String();
//         int counter=0;
//
//         for (int i=0; i<list.size();i++){
//             line= list.get(i);
//
//             if (line.charAt(0)== 'O'){
//                 if (!Ostack.isEmpty()){
//                     if (line.equals(Ostack.peek())){
//                         Ostack.push(line);
//
//                     }else {
//                         Ostack.pop();
//
//                     }
//                 }else Ostack.push(line);
//             }
//             if (line.charAt(0)== 'S'){
//                 if (!Sstack.isEmpty()){
//                     if (line.equals(Sstack.peek())){
//                         Sstack.push(line);
//
//                     }else {
//                         Sstack.pop();
//
//                     }
//                 }else Sstack.push(line);
//             }
//             if (line.charAt(0)== 'L'){
//                 if (!Lstack.isEmpty()){
//                     if (line.equals(Lstack.peek())){
//                         Lstack.push(line);
//
//                     }else {
//                         Lstack.pop();
//
//                     }
//                 }else Lstack.push(line);
//             }
//
//         }
//         while (!Ostack.isEmpty()){
//             if (Ostack.pop().charAt(1)=='M'){
//                 output+="OZ ";
//                 counter++;
//             }else {
//                 output += "OM ";
//                 counter++;
//             }
//         }
//        while (!Sstack.isEmpty()){
//            if (Sstack.pop().charAt(1)=='M'){
//                output+="SZ ";
//                counter++;
//            }else {
//                output += "SM ";
//                counter++;
//            }
//
//        }
//        while (!Lstack.isEmpty()){
//            if (Lstack.pop().charAt(1)=='M'){
//                output+="LZ ";
//                counter++;
//            }else {
//                output += "LM ";
//                counter++;
//            }
//        }
//
//        System.out.println(counter);
//        return output;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//        String input[]= new String[100];
//        input=br.readLine().split(" ");
//
//        List<String> list= new LinkedList<>();
//        for (int i=0; i<input.length; i++){
//            list.add(input[i]);
//        }
//
//        System.out.println(pair(list));
//    }
//}
