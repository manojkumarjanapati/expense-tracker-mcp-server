package linkedlist;

import java.util.Arrays;

public class DoublyLinkedListDsa {
    // this node inner class is for doublylinkedlist
    static class Node<T>{
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            String forward =  "Node{" + "value=" + value + ", next=" + next + '}';

            return forward;
        }

        public String toStringBackward(){
            String prevNode = (prev != null) ? prev.toStringBackward() : null;
            String backWard = "Node{" + "value=" + value + ", prev=" + prevNode + '}';
            return backWard;
        }

    }

    // methods

    public static void doublyLinkedList(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;

        tmp2.prev = tmp1;
        tmp1.prev = head;

        System.out.println(head);
        System.out.println(tmp2.toStringBackward());
    }

    public static Node insertAtBeginInDLL(Node head, int x){
        Node tmp = new Node(x);
        tmp.next = head;
        if(head != null) head.prev = tmp;
        return tmp;
    }
    public static void testInsertAtBeginInDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;

        tmp2.prev = tmp1;
        tmp1.prev = head;

        head = insertAtBeginInDLL(head,25);
        System.out.println(head);
    }

    public static Node insertAtEndInDLL(Node head, int x){
        Node tmp = new Node(x);
        if(head == null) return tmp;

        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }

        curr.next = tmp;
        tmp.prev = curr;
        return head;
    }

    public static void testInsertAtEndInDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;

        tmp2.prev = tmp1;
        tmp1.prev = head;

        head = insertAtEndInDLL(head,25);
        head = insertAtEndInDLL(head,125);
        System.out.println(head);
    }

    public static Node reverseDLL(Node head){
        if(head == null) return null;
        Node curr = head;
        while(curr.next != null){
            Node tmp = curr.next;
            curr.next = curr.prev;
            curr.prev = tmp;

            curr = curr.prev;
        }

        Node tmp = curr.next;
        curr.next = curr.prev;
        curr.prev = tmp;

        return curr;
    }

    public static Node reverseDLL2(Node head){
        if(head == null) return null;

        Node curr = head;
        Node ans = null;
        while(curr != null){
            Node tmp = curr.next;
            curr.next = curr.prev;
            curr.prev = tmp;
            if(curr.prev == null) ans = curr;
            curr = curr.prev;
        }

        return ans;
    }

    public static Node reverseDLLAuthor(Node head){
        if(head == null || head.next == null) return head;

        Node prev = null;
        Node curr = head;
        while(curr != null){
            prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;

            curr = curr.prev;
        }

        return prev.prev;

    }

    public static void testReverseDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;

        tmp2.prev = tmp1;
        tmp1.prev = head;

        System.out.println(head);
        head = reverseDLLAuthor(head);
        System.out.println(head);
    }

    public static Node deleteHeadOfDLL(Node head){
        if(head == null || head.next == null) return null;

        head = head.next;
        head.prev = null;
        return head;

    }

    public static void testDeleteHeadOfDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;

        tmp2.prev = tmp1;
        tmp1.prev = head;

        System.out.println(head);
        head = deleteHeadOfDLL(head);
        System.out.println(head);
        head = deleteHeadOfDLL(head);
        System.out.println(head);
        head = deleteHeadOfDLL(head);
        System.out.println(head);
    }

    public static Node deleteLastOfDLL(Node head){
        if(head == null || head.next == null) return null;

        Node curr = head;
        while(curr.next.next != null){
            curr = curr.next;
        }

        curr.next = null;

        return head;
    }


    public static void testDeleteLastOfDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;

        tmp2.prev = tmp1;
        tmp1.prev = head;

        System.out.println(head);
        head = deleteLastOfDLL(head);
        System.out.println(head);
        head = deleteHeadOfDLL(head);
        System.out.println(head);
    }

    // Circular Doubly Linked List
    public static void circularDoublyLinkedList(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;
        tmp2.next = head;

        tmp2.prev = tmp1;
        tmp1.prev = head;
        head.prev = tmp2;
        printCDLL(head);
    }

    public static void printCDLL(Node head){
        Node curr = head;
        do{
            System.out.print(curr.value + " ");
            curr = curr.next;
        }while(curr != head);
        System.out.println();
    }
    public static Node insertAtBeginOfCDLL(Node head, int x){
        Node tmp = new Node(x);
        if(head == null){
            tmp.next = tmp;
            tmp.prev = tmp;
            return tmp;
        }

        tmp.next = head;
        tmp.prev = head.prev;

        head.prev.next = tmp;
        head.prev = tmp;

        return tmp;
    }

    public static void testInsertAtBeginOfCDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;
        tmp2.next = head;

        tmp2.prev = tmp1;
        tmp1.prev = head;
        head.prev = tmp2;

        head = insertAtBeginOfCDLL(head,2);
        printCDLL(head);
    }

    public static Node insertAtEndOfCDLL(Node head, int x){
        Node tmp = new Node(x);
        if(head == null){
            tmp.next = tmp;
            tmp.prev = tmp;
            return tmp;
        }

        tmp.next = head;
        tmp.prev = head.prev;

        head.prev.next = tmp;
        head.prev = tmp;

        return head;// head is not changed
    }

    public static void testInsertAtEndOfCDLL(){
        Node head = new Node(3);
        Node tmp1 = new Node(1);
        Node tmp2 = new Node(5);
        head.next = tmp1;
        tmp1.next = tmp2;
        tmp2.next = head;

        tmp2.prev = tmp1;
        tmp1.prev = head;
        head.prev = tmp2;

        head = insertAtEndOfCDLL(head,2);
        printCDLL(head);
    }

    public static void main(String[] args) {
        doublyLinkedList();
        testInsertAtBeginInDLL();
        testInsertAtEndInDLL();
        testReverseDLL();
        testDeleteHeadOfDLL();
        testDeleteLastOfDLL();

        // circular doubly linked list
        circularDoublyLinkedList();
        testInsertAtBeginOfCDLL();
        testInsertAtEndOfCDLL();
    }
}
