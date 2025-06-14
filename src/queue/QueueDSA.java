package queue;

import java.util.*;

class QueueWithArray{
    int[] arr;
    int size,cap;

    int front,rear;
    public QueueWithArray(int cap) {
        this.cap = cap;
        this.size = 0;
        this.arr = new int[cap];
        front = -1;
        rear = -1;
    }

    boolean isFull(){
        return (size == cap);
    }

    boolean isEmpty(){
        return (size == 0);
    }

    void enque(int x){
        if(isFull()){
            System.out.println("OverFlow");
            return;
        }
        if(front == -1) front = 0;
        rear = (rear+1)%cap;
        arr[rear] = x;
        size++;
    }

    int deque(){
        if(isEmpty()){
            System.out.println("UnderFlow");
            return Integer.MIN_VALUE;
        }
        int res = arr[front];
        if(front == rear){
            front = -1;
            rear = -1;
        }else{
            front = (front+1)%cap;
        }
        size--;
        return res;
    }

    int getFront(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return arr[front];
    }
    int getRear(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return arr[rear];
    }
    int getSize(){
        return size;
    }
}


class QueueWithSLL{
    class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    Node front;
    Node rear;
    int size;

    public QueueWithSLL() {
        this.size = 0;
        front = rear = null;
    }

    boolean isEmpty(){
        return (front == null);
    }

    void enque(int x){
        Node tmp = new Node(x);

        if(isEmpty()){
            front = rear = tmp;
        }else{
            rear.next = tmp;
            rear = tmp;
        }
        size++;

    }

    int deque(){
        if(isEmpty()){
            System.out.println("underflow");
            return Integer.MIN_VALUE;
        }

        int res = front.data;
        front = front.next;
        if(front == null) rear = null;
        // means that was the last node and front&rear both was pointing to same node
        size--;
        return res;
    }

    int getFront(){
        return front.data;
    }

    int getRear(){
        return rear.data;
    }

    int getSize(){
        return size;
    }


}


public class QueueDSA {

    public static void testQueueWithArray(){
        QueueWithArray q = new QueueWithArray(10);
        q.enque(5);
        q.enque(9);
        q.enque(3);
        q.enque(4);
        q.enque(1);
        System.out.println(q.getFront());
        System.out.println(q.deque());
        System.out.println(q.deque());
        System.out.println(q.getFront());
        System.out.println(q.getRear());

    }

    public static void testQueueWithSLL(){
        QueueWithSLL q = new QueueWithSLL();
        q.enque(5);
        q.enque(9);
        q.enque(3);
        q.enque(4);
        q.enque(1);
        System.out.println(q.getFront());
        System.out.println(q.deque());
        System.out.println(q.deque());
        System.out.println(q.getFront());
        System.out.println(q.getRear());
        System.out.println(q.getSize());

    }


    public static void reverseQueue(Queue<Integer> q){
        Stack<Integer> s = new Stack<>();
        while(q.isEmpty() == false){
            s.push(q.poll());
        }

        while(s.isEmpty() == false){
            q.offer(s.pop());

        }
    }

    public static void reverseQueueRecursion(Queue<Integer> q){
        if(q.isEmpty() == false){
            int putLast = q.poll();
            reverseQueueRecursion(q);
            q.offer(putLast);
        }
    }

    public static void testReverseQueue(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);
//        reverseQueue(q);
        reverseQueueRecursion(q);
        System.out.println(q);
    }

    public static ArrayList<String> generateNNumbersWithGivenDigits(int n, int[] digits){
        Queue<String> q = new ArrayDeque<>();

        Arrays.sort(digits);
        HashSet<Integer> uniqueDigits = new HashSet<>();

        for(int d: digits){
            if(uniqueDigits.contains(d) == false){ // removing duplicate digits logic
                q.offer(""+d);
                uniqueDigits.add(d);
            }
        }
        ArrayList<String> generatedNumbers = new ArrayList<>();
        for(int i = 0; i<n; i++){
            String num = q.poll();
            generatedNumbers.add(num);
            for(int d: digits){
                q.offer(num+d);
            }
        }
        return generatedNumbers;
    }

    public static void testGenerateNNumbersWithGivenDigits(){
        int n = 20;
        int[] digits = {6,6,5};
        System.out.println(generateNNumbersWithGivenDigits(n,digits));
    }

    public static void main(String[] args) {

        testQueueWithArray();
        testQueueWithSLL();
        testReverseQueue();
        testGenerateNNumbersWithGivenDigits();


    }
}
