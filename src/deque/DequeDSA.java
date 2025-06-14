package deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
* @Logic when you insert at front, update front index as front--, when you insertRear, update rearindex
 * as rear++. initialize them accordingly
* */
class DequeWithArray{
    int capacity;
    int size;
    int front,rear;

    int[] arr;

    public DequeWithArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = rear = -1;
    }

    boolean isFull(){
        return (size == capacity);
    }
    boolean isEmpty(){
        return (size == 0);
    }

    void insertFront(int x){
        if(isFull()){
            System.out.println("Overflow");
            return;
        }
        if(isEmpty()){
            front = rear = 0;
        }else{
            front = (front-1+capacity)%capacity;
        }
        arr[front] = x;
        size++;
    }

    void insertRear(int x){
        if(isFull()){
            System.out.println("Overflow");
            return;
        }
        if(isEmpty()){
            front = rear = 0;
        }else{
            rear = (rear+1)%capacity;
        }
        arr[rear] = x;
        size++;
    }

    int popFront(){
        if(isEmpty()){
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int res = arr[front];
        front = (front+1)%capacity;
        size--;
        return res;
    }

    int popRear(){
        if(isEmpty()){
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int res = arr[rear];
        rear = (rear-1+capacity)%capacity;
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

    void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(arr[(front+i)%capacity]+" ");
        }
        System.out.println();
    }
}

/**
 * @Logic
 * initialize front as 1, while inserting at front do circular decrement of frontIndex.
 * maintain size variable, rear can be calculated from front&size
 * */
class DequeWithArrayV2{
    int capacity;
    int size;
    int front;

    int[] arr;

    public DequeWithArrayV2(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 1;
    }

    boolean isFull(){
        return (size == capacity);
    }
    boolean isEmpty(){
        return (size == 0);
    }

    void insertFront(int x){
        if(isFull()){
            System.out.println("Overflow");
            return;
        }
        front = (front-1+capacity)%capacity;
        arr[front] = x;
        size++;
    }

    void insertRear(int x){
        if(isFull()){
            System.out.println("Overflow");
            return;
        }
        // basically rear = (front+size-1)%capacity. but we need next Index of rear to insert
        int rear = (front+size)%capacity;
        arr[rear] = x;
        size++;
    }

    int popFront(){
        if(isEmpty()){
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int res = arr[front];
        front = (front+1)%capacity;
        size--;
        return res;
    }

    int popRear(){
        if(isEmpty()){
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int rear = (front+size-1)%capacity;
        int res = arr[rear];
        size--;
        return res;
    }

    int getFront(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return arr[front];
    }
    int getRear(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return arr[(front+size-1)%capacity];
    }
    int getSize(){
        return size;
    }

    void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(arr[(front+i)%capacity]+" ");
        }
        System.out.println();
    }
}

class DequeWithDLL{
    class Node<T>{
        T value;

        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    int size;
    Node<Integer> head;
    Node<Integer> tail;

    public DequeWithDLL() {
        this.size = 0;
        this.head = this.tail = null;
    }

    boolean isEmpty(){
        return (size == 0);
    }

    void insertFront(int x){
        Node<Integer> tmp = new Node<>(x);
        if(isEmpty()){
            head = tail = tmp;
        }else {
            tmp.next = head;
            head.prev = tmp;
            head = tmp;
        }
        size++;
    }

    void insertRear(int x){
        Node<Integer> tmp = new Node<>(x);
        if(isEmpty()){
            head = tail = tmp;
        }
        else{
            tail.next = tmp;
            tmp.prev = tail;
            tail = tmp;
        }
        size++;
    }

    int popFront(){
        if(isEmpty()){
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int res = head.value;
        head = head.next;
        head.prev = null;
        size--;
        return res;
    }

    int popRear(){
        if(isEmpty()){
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int res = tail.value;
        tail = tail.prev;
        tail.next = null;
        size--;
        return res;
    }

    int getFront(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return head.value;
    }
    int getRear(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return tail.value;
    }
    int getSize(){
        return size;
    }

    void printDeque(){
        System.out.println(head);
    }

}


class MaxNMinO1{
    Deque<Integer> dq = new ArrayDeque<>();

    void insertMin(int x){
        if(dq.isEmpty() || dq.peekFirst() > x){
            dq.offerFirst(x);
        }
    }

    void insertMax(int x){
        if(dq.isEmpty() || dq.peekLast() < x){
            dq.offerLast(x);
        }
    }

    int getMin(){
        return dq.peekFirst();
    }

    int getMax(){
        return dq.peekLast();
    }

    int extractMin(){
        return dq.pollFirst();
    }

    int extractMax(){
        return dq.pollLast();
    }
}

public class DequeDSA {

    public static void testDequeWithArray(){
//        DequeWithArray d = new DequeWithArray(10);
        DequeWithArrayV2 d = new DequeWithArrayV2(10);
        d.insertFront(1);
        d.insertFront(2);
        d.insertFront(3);
        d.insertFront(4);
        d.insertFront(5);
        d.insertRear(6);
        d.insertRear(7);
        d.insertRear(8);
        d.insertRear(9);
        d.insertRear(10);
        d.printDeque();
        d.popFront();
        d.popRear();
        d.printDeque();
    }

    public static void testDequeWithDLL(){
        DequeWithDLL d = new DequeWithDLL();
        d.insertFront(1);
        d.insertFront(2);
        d.insertFront(3);
        d.insertFront(4);
        d.insertFront(5);
        d.insertRear(6);
        d.insertRear(7);
        d.insertRear(8);
        d.insertRear(9);
        d.insertRear(10);
        d.printDeque();
        d.popFront();
        d.popRear();
        d.printDeque();
    }

    public static void testMaxNMinO1(){
        MaxNMinO1 k = new MaxNMinO1();
        k.insertMin(5);
        k.insertMax(10);
        k.insertMin(3);
        k.insertMax(15);
        k.insertMin(2);
        System.out.println(k.getMin());
        System.out.println(k.getMax());
        k.insertMin(1);
        System.out.println(k.extractMin());
        k.insertMax(20);
        System.out.println(k.extractMax());
    }

    public static ArrayList<Integer> maxOfAllSubarraysOfSizeK(int arr[], int n, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();
        int i = 0;
        for(i = 0; i<k-1; i++){
            while(!dq.isEmpty() && arr[dq.getLast()] <= arr[i]){
                dq.removeLast();
            }
            dq.addLast(i);
        }

        for(;i<n; i++){
            while(!dq.isEmpty() && dq.getFirst() <= i-k){
                dq.removeFirst();
            }
            while(!dq.isEmpty() && arr[dq.getLast()] <= arr[i]){
                dq.removeLast();
            }
            dq.addLast(i);
            ans.add(arr[dq.getFirst()]);
        }

        return ans;

    }

    public static void testMaxOfAllSubarraysOfSizeK(){
        int n = 9;
        int k = 3;
        int[] arr = {1,2,3,1,4,5,2,3,6};
        System.out.println(maxOfAllSubarraysOfSizeK(arr,n,k));
    }

    public static void main(String[] args) {
        testDequeWithArray();
        testDequeWithDLL();
        testMaxNMinO1();
        testMaxOfAllSubarraysOfSizeK();
        
    }
}
