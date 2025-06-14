package linkedlist;

//class Node<T>{
//    // Manoj implementation of a LinkedList Node
//    T value;
//    Node<T> next;
//
//    public Node(T value) {
//        this.value = value;
//        this.next = null;
//    }
//
//    public T getValue() {
//        return value;
//    }
//
//    public void setValue(T value) {
//        this.value = value;
//    }
//
//    public Node<T> getNext() {
//        return next;
//    }
//
//    public void setNext(Node<T> next) {
//        this.next = next;
//    }
//
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "value=" + value +
//                ", next=" + next +
//                '}';
//    }
//}

import java.util.HashMap;
import java.util.HashSet;

public class LinkedListDsa {

    static class Node<T>{
        // Manoj implementation of a LinkedList Node
        T value;
        Node<T> next;
        Node<T> random; // for cloning linked list question. else we don't need this

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getRandom() {
            return random;
        }

        public void setRandom(Node<T> random) {
            this.random = random;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    /* Learn skiplist
    * search O(logn)
    * insertion O(logn)
    * */

    public static void changeNodeContent(Node x){
        // change persists outside when we change content(value or next) of Node argument
        x.value = 25;
        x.next = new Node(100);
    }

    public static void changeNodeReference(Node x){
        x = new Node(123);
    }
    public static void linkedList1(){
        Node<Integer> head = new Node<>(20);
        Node<Integer> node1 = new Node<>(30);
        Node<Integer> node2 = new Node<>(10);
        head.setNext(node1);
        node1.setNext(node2);

        System.out.println(head);
        printLinkedList(head);
        printLinkedListRecursive(head);
    }

    public static void printLinkedList(Node head){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
    }

    public static void printLinkedListRecursive(Node head){
        if(head == null) return;
        System.out.print(head.value + " ");
        printLinkedListRecursive(head.next);
    }

    public static Node insertAtBeginOfSLL(Node head, int x){
        Node begin = new Node(x);
        begin.next = head;

        return begin;
    }

    public static Node insertAtEndOfSLL(Node head, int x){
        Node end = new Node(x);
        if(head == null) return end;
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = end;
        return head;
    }

    public static Node insertAtGivenPositionOfSLLManoj(Node head, int pos, int x){
        Node tmp = new Node(x);
        if(pos == 1) {
            tmp.next = head;
            return tmp;
        }

        int cnt = 0; // to count current node
        Node curr = head;
        while(curr != null){
            cnt++; // start from 1 -> similar to 1 based index as per the question
            // we have to find previous node of position. then only we can use node.next to insert
            if(cnt == pos-1){
                tmp.next = curr.next;
                curr.next = tmp;
                break;
            }
            curr = curr.next;
        }

        return head;
    }

    public static Node insertAtGivenPositionOfSLL(Node head, int pos, int x){
        Node tmp = new Node(x);

        if(pos == 1){
            // insert at begin
            tmp.next = head;
            return tmp;
        }
        Node curr = head;
        for(int i = 1; i<=pos-2 && curr!=null; i++){
            curr = curr.next;
        }

        if(curr == null) return head;

        tmp.next = curr.next;
        curr.next = tmp;

        return head;
    }

    public static Node deleteFirstNodeOfSLL(Node head){
        if(head == null) return null;
        return head.next;
    }

    public static Node deleteLastNodeOfSLL(Node head){
        if(head == null || head.next == null) return null;

        Node curr = head;
        while(curr.next.next != null){
            curr = curr.next;
        }

        curr.next = null;

        return head;
    }

    public static int searchInSLL(Node head, int x){
        int pos = 1;
        Node curr = head;
        while(curr != null){
            if(curr.value.equals(x)) return pos;
            curr = curr.next;
            pos++;
        }

        return -1;
    }

    public static int searchInSLLRecursive(Node head, int x,int pos){
        if(head == null) return -1;
        if(head.value.equals(x)) return pos;
        return  searchInSLLRecursive(head.next,x,pos+1);
    }

    public static int searchInSLLRecursive2(Node head, int x){
        if(head == null) return -1;
        if(head.value.equals(x)) return 1;
        int res = searchInSLLRecursive2(head.next,x);
        return (res == -1)? -1 : res+1;
    }

    // CIRCULAR SINGLY LINKED LIST
    public static void circularLinkedList(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head;
        printCircularLinkedList(head);
    }

    public static void printCircularLinkedList(Node head){
        if(head == null) return;
        Node curr = head;
        do{
            System.out.print(curr.value + " ");
            curr = curr.next;
        }while(curr != head);
        System.out.println();
    }

    public static Node insertAtBeginOfCSLLNaive(Node head, int x){
        // time : O(n)
        Node tmp = new Node(x);
        if(head == null){
            tmp.next = tmp;
            return tmp;
        }

        Node curr = head;
        while(curr.next != head){
            curr = curr.next;
        }

        curr.next = tmp;
        tmp.next = head;

        return tmp;
    }

    public static Node insertAtBeginOfCSLL(Node head, int x){
        // O(1) solution: maintaining tail
        // but without maintaining tail. we insert after head and we swap head.value with head.next.value
        Node tmp = new Node(x);
        if(head == null){
            tmp.next = tmp;
            return tmp;
        }

        // insert after head
        tmp.next = head.next;
        head.next = tmp;

        // swap head with head.next
        int t = (int) head.value;
        head.value = head.next.value;
        head.next.value = t;


        return head;

    }

    public static void testInsertBeginOfCSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head;

//        head = insertAtBeginOfCSLLNaive(head,0);
        head = insertAtBeginOfCSLL(head,0);
        printCircularLinkedList(head);
    }


    public static Node insertAtEndOfCSLL(Node head, int x){
        Node tmp = new Node(x);
        if(head == null){
            tmp.next = tmp;
            return tmp;
        }

        // insert after head
        tmp.next = head.next;
        head.next = tmp;

        // swap head with head.next
        int t = (int) head.value;
        head.value = head.next.value;
        head.next.value = t;


        return head.next; // return tmp;

    }

    public static void testInsertEndOfCSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head;

        head = insertAtEndOfCSLL(head,0);
        printCircularLinkedList(head);
    }


    public static Node deleteHeadOfCSLL(Node head){
        if(head == null || head.next == head) return null;

        head.value = head.next.value;
        head.next = head.next.next;
        return head;
    }

    public static void testDeleteHeadOfCSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head;

        head = deleteHeadOfCSLL(head);
        printCircularLinkedList(head);
    }

    public static Node deleteKthNodeFromCSLLManoj(Node head, int k){
        // won't work to delete last node. as you copy head data to last node data and head.next to lastNode.next
        // but you return head.
        if(head == null || k == 0) return head;

        Node curr = head;
        int cnt = 1;
        while(cnt < k){
            curr = curr.next;
            cnt++;
        }

        if(curr == curr.next) return null;

        curr.value = curr.next.value;
        curr.next = curr.next.next;

        return head;

    }

    public static Node deleteKthNodeFromCSLL(Node head, int k){
        if(head == null || k == 0) return head;

        if(k == 1) return deleteHeadOfCSLL(head);

        Node curr = head;
        for(int i = 0; i<k-2; i++) curr = curr.next;

        curr.next = curr.next.next;
        return head;
    }


    public static void testDeleteKthNodeFromCSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head;

//        head = deleteKthNodeFromCSLLManoj(head,4); // fails for last node
        head = deleteKthNodeFromCSLL(head,4);
        printCircularLinkedList(head);
    }

    // some more concepts
    public static Node sortedInsertInSLL(Node<Integer> head, int x){
        Node<Integer> tmp = new Node(x);

        if(head == null || head.value > x){
            tmp.next = head;
            return tmp;
        }

        Node<Integer> curr = head;
        while(curr.next != null && curr.next.value < x){
            curr = curr.next;
        }

        tmp.next = curr.next;
        curr.next = tmp;

        return head;
    }

    public static void testSortedInsertInSLL(){
        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(3);
        head.next.next = new Node<>(7);
        head.next.next.next = new Node<>(9);

        head = sortedInsertInSLL(head,7);
        System.out.println(head);
    }


    public static void printMiddleOfSLLNaive(Node head){
        if(head == null) return;
        int cnt = 0;
        Node curr;
        for(curr = head; curr!=null; curr = curr.next) cnt++;

        curr = head;
        for(int i = 0; i<cnt/2; i++){
            curr = curr.next;
        }

        System.out.println(curr.value);
    }

    public static void printMiddleOfSLL(Node head){
        if(head == null) return;
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println(slow.value);
    }

    public static void testMiddleOfLinkedList(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
//        head.next.next.next.next.next = new Node(6);

        printMiddleOfSLLNaive(head);
        printMiddleOfSLL(head);
    }

    public static void printNthNodeFromEndOfSLLNaive(Node head,int n){
        int len = 0;
        Node curr;
        for(curr = head; curr!=null; curr=curr.next) len++;

        if(n > len) return;

        curr = head;
        for(int i = 1; i<len-n+1; i++){
            curr = curr.next;
        }

        System.out.println(curr.value);
    }

    public static void printNthNodeFromEndOfSLL(Node head, int n){
        Node fast = head;
        Node slow = head;

//        // move fast to nth node from head;
//        while(n > 0 && fast!=null){
//            fast = fast.next;
//            n--;
//        }
//
//        if(n > 0) return; // n is greater than length of linked-list

        for(int i = 0; i<n; i++){
            if(fast == null) return;
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        System.out.println(slow.value);



    }

    public static void testPrintNthNodeFromEndOfSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
//        head.next.next.next.next.next = new Node(6);

        printNthNodeFromEndOfSLLNaive(head,4);
        printNthNodeFromEndOfSLL(head,4);
    }


    public static Node reverseSLLManoj(Node head){
        Node oldHead = head;
        Node newHead = head;

        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
            newHead = insertAtBeginOfSLL(newHead,(int) curr.value);
        }

        oldHead.next = null;

        return newHead;
    }

    public static Node reverseSLL(Node head){
        Node prev = null;
        Node curr = head;

        while (curr != null){
            Node next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node reverseSLLRecursive1(Node head){
        if(head == null) return null;
        if(head.next == null) return head;

        Node ans = reverseSLLRecursive1(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
    }

    public static Node reverseSLLRecursive2(Node curr, Node prev){
        if(curr == null) return null;
        if(curr.next == null){
            curr.next = prev;
            return curr;
        }

        Node next = curr.next;
        curr.next = prev;
        return reverseSLLRecursive2(next,curr);
    }

    public static Node reverseSLLRecursive2Author(Node curr, Node prev){
        if(curr == null) return prev;

        Node next = curr.next;
        curr.next = prev;
        return reverseSLLRecursive2Author(next,curr);
    }

    public static void testReverseSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

//        head = reverseSLLManoj(head);
//        head = reverseSLL(head);
//        head = reverseSLLRecursive1(head);
//        head = reverseSLLRecursive2(head,null);
        head = reverseSLLRecursive2Author(head,null);
        System.out.println(head);
    }

    public static void removeDuplicatesFromSortedSLL(Node head){
        if(head == null) return;
        Node curr = head;
        while(curr != null && curr.next != null){
            if(curr.next.value == curr.value){
                curr.next = curr.next.next;
            }
            else{
                curr = curr.next;
            }
        }
    }

    public static void testRemoveDuplicatesFromSortedSLL(){
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(3);

        removeDuplicatesFromSortedSLL(head);
        System.out.println(head);
    }


    public static Node reverseSLLInGroupsOfKRecursion(Node head, int k){
        if(head == null) return null;

        Node curr = head;
        Node prev = null, next = null;
        int cnt = 0;
        while(curr!=null && cnt++<k){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        head.next = reverseSLLInGroupsOfKRecursion(curr,k);
        return prev;

    }


    public static Node reverseSLLInGroupsOfKRecursionAuthor(Node head, int k){
        // this also same as my implementation.. when head is null loop won't run and prev(null) is returned
        Node curr = head;
        Node prev = null, next = null;
        int cnt = 0;
        while(curr!=null && cnt++<k){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        if(next != null){
            Node restNode = reverseSLLInGroupsOfKRecursionAuthor(next,k);
            head.next = restNode;
        }

        return prev;

    }

    public static Node reverseSLLInGroupsOfKManoj(Node head, int k){

        Node curr = head;
        Node prevFirst = null,next = null;
        int cnt = 0;
        while(curr!=null && cnt++<k){
            next = curr.next;
            curr.next = prevFirst;

            prevFirst = curr;
            curr = next;
        }

        while(curr!=null){
            Node newHead = curr;
            Node prev = null;
            cnt = 0;
            while(curr!=null && cnt++<k){
                next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }
            head.next = prev;
            head = newHead;
        }

        return prevFirst;

    }

    public static Node reverseSLLInGroupsOfKAuthor(Node head, int k){
        Node curr = head;
        Node prevFirst = null;
        boolean isFirstPass = true;
        while(curr != null){
            Node first = curr;
            Node prev = null;
            int cnt = 0;
            while(curr != null && cnt++<k){
                Node next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }

            if(isFirstPass) {
                head = prev;
                isFirstPass = false;
            }else{
                prevFirst.next = prev;
            }

            prevFirst = first;
        }

        return head;
    }


    public static void testReverseSLLInGroupsOfK(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

//        head = reverseSLLInGroupsOfKRecursion(head,3);
//        head = reverseSLLInGroupsOfKRecursionAuthor(head,3);
//        head = reverseSLLInGroupsOfKManoj(head,3);
        head = reverseSLLInGroupsOfKAuthor(head,3);
        System.out.println(head);
    }


    public static boolean detectLoopInASLLNaive(Node head){
        if(head.next == head) return true; // circular linked list is also a loop right???

        Node start = head;

        while(start != null){
            Node curr = head;
            while(curr != start){
                // checking if start.next points to any reference that are previous to start
                if(start.next == curr) return true;
                curr = curr.next;
            }

            start = start.next;
        }

        return false;
    }

    public static boolean detectLoopInSSLUsingVisitedMarkingNode(Node head){
        Node visited = new Node(0);

        // way 1
//        Node curr = head;
//        Node prev = null;
//
//        while(curr != null){
//            if(curr.next == visited) return true;
//            prev = curr;
//            curr = curr.next;
//            prev.next = visited;
//        }
//
//        return false;

        // way 2
        Node curr = head;
        while(curr != null){
            if(curr.next == visited) return true;
            Node currNext = curr.next;
            curr.next = visited;
            curr = currNext;
        }
        return false;
    }

    public static boolean detectLoopInSSLUsingHashing(Node head){
        HashSet<Node> hs = new HashSet<>();

        for(Node curr = head; curr != null; curr = curr.next){
            if(hs.contains(curr)) return true;
            hs.add(curr);
        }
        return false;
    }

    public static boolean detectLoopInSLLFloydCycle(Node head){
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }

    public static void testDetectLoopInSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next = head.next.next.next;

        System.out.println(detectLoopInASLLNaive(head));
        System.out.println(detectLoopInSSLUsingHashing(head));
        System.out.println(detectLoopInSLLFloydCycle(head));
//        detectNRemoveLoopInSLL(head);
        detectNRemoveLoopInSLLAuthor(head);
        System.out.println(head);
        System.out.println(detectLoopInSSLUsingVisitedMarkingNode(head)); // it modifies SLL so keeping it last


    }


    public static void detectNRemoveLoopInSLL(Node head){
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }

        if(slow != fast) return; // there is no loop

        // there is a loop
        slow = head;
        while(slow != fast){
            // since there is a loop slow or fast never become null
            slow = slow.next;
            fast = fast.next; // both at same speed
        }

        // while loop breaks (slow = fast). slow and fast meet at starting node of loop.
        // i will keep slow there is self for checking

        while(fast.next != slow){
            fast = fast.next;
        }

        // now fast.next = slow... so slow is starting node of loop and fast is ending node of loop
        fast.next = null; // i removed loop from SLL

    }

    public static void detectNRemoveLoopInSLLAuthor(Node head){
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }

        if(slow != fast) return; // there is no loop

        // there is a loop
        slow = head;
        while(slow.next != fast.next){
            // since there is a loop slow or fast never become null
            slow = slow.next;
            fast = fast.next; // both at same speed
        }

        // while loop breaks (slow.fast = fast.next).
        // slow.next is starting Node of loop in SLL
        // fast.next points to slow.next so fast is last node of loop in SSL

        // fast is ending node of loop. so removing loop by fast.next = null;
        fast.next = null; // i removed loop from SLL

    }

    public static void deleteANodeWithItsReference(Node delNode){
        if(delNode.next == null){
            delNode = null;
            return;
        }

        delNode.value = delNode.next.value;
        delNode.next = delNode.next.next;
    }

    public static void testDeleteANodeWithItsReference(){
        Node head = new Node(1);
        Node tmp1 = new Node(2);
        Node tmp2 = new Node(3);
        Node tmp3 = new Node(4);
        Node tmp4 = new Node(5);

        head.next = tmp1;
        tmp1.next = tmp2;
        tmp2.next = tmp3;
        tmp3.next = tmp4;

        deleteANodeWithItsReference(tmp4);
        System.out.println(head);
    }


    public static Node segregateEvenOddNodesManoj(Node head){
        // putting evens to front so that odds will stay in their place
        Node firstEven = null, lastEven = null;
        Node firstOdd = null;
        Node curr;
        int x;

        for(curr = head; curr != null; curr = curr.next){
            x = (int) curr.value;
            if(x%2 == 0){
                if(firstEven == null){
                    firstEven = lastEven = curr;
                }else{
                    lastEven = curr;
                }
            }else{
                firstOdd = curr;
                break;
            }
        }

        while(curr != null && curr.next != null){
            x = (int) curr.next.value;
            if(x%2 == 0){
                Node tmp = curr.next;
                curr.next = curr.next.next;
                if(firstEven == null){
                    firstEven = lastEven = tmp;
                    tmp.next = firstOdd;
                }else{
                    tmp.next = firstOdd;
                    lastEven.next = tmp;
                    lastEven = tmp;
                }
            }
            else{
                curr = curr.next;
            }
        }

        if(firstEven == null) return head;
        return firstEven;
    }

    public static Node segregateEvenOddNodesAuthor(Node head){
        Node evenStart = null, evenEnd = null, oddStart = null, oddEnd = null;
        Node curr = head;
        while(curr != null){
            if((int)curr.value % 2 == 0){
                if(evenStart == null){
                    evenStart = evenEnd = curr;
                }else{
                    evenEnd.next = curr;
                    evenEnd = curr;
                }
            }else{
                if(oddStart == null){
                    oddStart = oddEnd = curr;
                }else{
                    oddEnd.next = curr;
                    oddEnd = curr;
                }
            }
            curr = curr.next;
        }

        if(evenStart == null || oddStart == null) return head; // all elements are of same type

        evenEnd.next = oddStart;
        oddEnd.next = null;
        return evenStart;
    }

    public static void testSegregateEvenOddInSLL(){
        Node head = new Node(10);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        head  = segregateEvenOddNodesManoj(head);
//        head  = segregateEvenOddNodesAuthor(head);
        System.out.println(head);
    }

    public static int intersectionOfTwoSSLManoj(Node head1, Node head2){
        // but this solution disturbs the linked lists given
        Node visisted = new Node(0);

        Node curr = head1;
        while(curr != null){
            Node currNext = curr.next;
            curr.next = visisted;
            curr = currNext;
        }

        curr = head2;
        while(curr != null){
            if(curr.next == visisted) return (int) curr.value;
            Node currNext = curr.next;
            curr.next = visisted;
            curr = currNext;
        }

        return -1;
    }

    public static int intersectionOfTwoSSL(Node head1, Node head2){
        // this solution won't change linked lists given
        int len1 = 0;
        int len2 = 0;
        Node curr1;
        Node curr2;
        for(curr1 = head1; curr1!=null; curr1 = curr1.next){
            len1++;
        }

        for(curr2 = head2; curr2!=null; curr2 = curr2.next){
            len2++;
        }

        curr1 = head1;
        curr2 = head2;

        if(len1 > len2){
            int diff = len1-len2;
            for(int i=0; i<diff; i++) curr1 = curr1.next;
        }else{
            int diff = len2-len1;
            for(int i = 0; i<diff; i++) curr2 = curr2.next;
        }

        while(curr1!=null && curr2 !=null){
            if(curr1 == curr2) return (int) curr1.value;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return -1; // no intersection
    }

    public static void testIntersectionOfTwoSSL(){
        Node head1 = new Node(5);
        Node head2 = new Node(8);

        Node intersection = new Node(10);
        intersection.next = new Node(11);
        intersection.next.next = new Node(12);

        head1.next = new Node(6);
        head1.next.next = new Node(7);
        head1.next.next.next = intersection;

        head2.next = new Node(9);
        head2.next.next = intersection;

        System.out.println(intersectionOfTwoSSL(head1,head2));

    }


    public static Node pairwiseSwapNodesOfSLLNaive(Node head){
        // swaping data
        Node curr = head;
        while(curr != null && curr.next != null){
            int x = (int) curr.value;
            curr.value = curr.next.value;
            curr.next.value = x;

            curr = curr.next.next;
        }
        return head;
    }

    public static Node pairwiseSwapNodesOfSLLManoj(Node head){
        Node prev = null;
        Node curr = head;

        while(curr != null && curr.next != null){
            Node currNext = curr.next;
            curr.next = currNext.next;
            currNext.next = curr;

            if(prev == null){
                head = currNext; // this is first Node of answer
                prev = curr;
            }else{
                prev.next = currNext;
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    public static Node pairwiseSwapNodesOfSLLAuthor(Node head){
        if(head == null || head.next == null) return head;

        Node curr = head.next.next;
        Node prev = head;
        head = head.next;
        head.next = prev;

        while(curr != null && curr.next != null){
            prev.next = curr.next;
            prev = curr;

            Node next = curr.next.next;
            curr.next.next = curr;
            curr = next;
        }

        prev.next = curr;
        return head;
    }

    public static void testPairwiseSwapNodesOfSLL(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
//        head.next.next.next.next.next.next.next = new Node(8);

//        head = pairwiseSwapNodesOfSLLNaive(head);
//        head = pairwiseSwapNodesOfSLLManoj(head);
        head = pairwiseSwapNodesOfSLLAuthor(head);
        System.out.println(head);
    }



    public static Node cloneSLLWithRandomPointerUsingHashing(Node head){
        HashMap<Node,Node> hm = new HashMap<>();

        Node curr;
        for(curr = head; curr!=null; curr = curr.next){
            hm.put(curr,new Node(curr.value));
        }

        for(curr = head; curr!=null; curr = curr.next){
            Node clone = hm.get(curr);
            clone.next = hm.get(curr.next);
            clone.random = hm.get(curr.random);
        }

        return hm.get(head);

    }

    public static Node cloneSLLWithRandomPointer(Node head){
        // creat clone nodes insert them after original nodes
        Node curr = head;
        while(curr != null){
            Node currClone = new Node(curr.value);
            currClone.next = curr.next;
            curr.next = currClone;
            curr = currClone.next;
        }

        // connecting random pointers of cloned nodes
        for(curr = head; curr!=null; curr = curr.next.next){
            // skipping cloned nodes using curr = curr.next.next
            Node currClone = curr.next;
            currClone.random = curr.random.next;
        }

        Node cloneHead = head.next; // this is answer
        curr = head;
        while(curr != null && curr.next!=null){
            Node currClone = curr.next;
            curr.next = currClone.next;
            if(curr.next != null) currClone.next = curr.next.next;
            curr = curr.next;
        }

        return cloneHead;
    }


    public static Node cloneSLLWithRandomPointerAuthor(Node head){
        Node curr = head;
        while(curr != null){
            Node next = curr.next;
            curr.next = new Node(curr.value);
            curr.next.next = next;
            curr = next;
        }

        for(curr = head; curr != null; curr = curr.next.next){
            curr.next.random = (curr.random != null)? curr.random.next : null;
        }

        Node cloneHead = head.next;
        Node clone = cloneHead;
        for(curr = head; curr != null; curr = curr.next){
            curr.next = curr.next.next;
            clone.next = (clone.next != null) ? clone.next.next : null;
            clone = clone.next;
        }
        return cloneHead;
    }




    public static void testCloneSSLWithRandomPointer(){
        Node head = new Node(10);
        Node tmp1 = new Node(5);
        Node tmp2 = new Node(20);
        Node tmp3 = new Node(15);
        Node tmp4 = new Node(20);

        head.next = tmp1;
        tmp1.next = tmp2;
        tmp2.next = tmp3;
        tmp3.next = tmp4;

        head.random = tmp2;
        tmp1.random = tmp3;
        tmp2.random = head;
        tmp3.random = tmp2;
        tmp4.random = tmp3;

//        Node head2 = cloneSLLWithRandomPointerUsingHashing(head);
//        Node head2 = cloneSLLWithRandomPointer(head);
        Node head2 = cloneSLLWithRandomPointerAuthor(head);
        System.out.println(head2);
    }


    public static Node mergeTwoSortedSLLManoj(Node head1, Node head2){
        if(head2 == null) return head1;
        if(head1 == null) return head2;

        Node prev1 = null;

        Node curr1 = head1, curr2 = head2;

        Node ansHead = null; // ansHead will be min of (head1,head2)
        if((int) head1.value <= (int) head2.value){
            ansHead = head1;
        }else{
            ansHead = head2;
        }

        while(curr1 != null && curr2 != null){
            while(curr1 != null && ((int)curr1.value <= (int)curr2.value)){
                // <= makes b node after a node when a == b
                prev1 = curr1;
                curr1 = curr1.next;
            }

            if(curr1 == null){
                prev1.next = curr2;
            }else{
                Node tmp = curr2;
                curr2 = curr2.next;
                tmp.next = curr1;
                if(prev1 != null) prev1.next = tmp;
                prev1 = tmp;
            }
        }

        return ansHead;
    }

    public static Node mergeTwoSortedSLLAuthor(Node head1, Node head2){
        if(head2 == null) return head1;
        if(head1 == null) return  head2;

        Node ansHead = null, tail = null;

        if((int) head1.value <= (int) head2.value){
            ansHead = tail = head1;
            head1 = head1.next;
        }else{
            ansHead = tail = head2;
            head2 = head2.next;
        }

        while(head1 != null && head2 != null){
            if((int) head1.value <= (int) head2.value){
                // maintaining a->b order when a==b. a from head1 and b from head2
                tail.next = head1;
                tail = head1;
                head1 = head1.next;
            }else{
                tail.next = head2;
                tail = head2;
                head2 = head2.next;
            }
        }

        if(head1 == null) tail.next = head2;
        else tail.next = head1;

        return ansHead;
    }

    public static Node sortedMerge(Node head1, Node head2) {
        // This is a "method-only" submission.
        // You only need to complete this method
        Node dummy = new Node(0);
        Node tail = dummy;

        while(true){
            if(head1 == null){
                tail.next = head2;
                break;
            }

            if(head2 == null){
                tail.next = head1;
                break;
            }

            if((int)head1.value <= (int)head2.value){
                tail.next = head1;
                head1 = head1.next;
            }
            else{
                tail.next = head2;
                head2 = head2.next;
            }

            tail = tail.next;
        }

        return dummy.next;
    }

    public static void testMergeTwoSortedSLL(){
        Node head1 = new Node(5);
        head1.next = new Node(7);
        head1.next.next = new Node(8);
        head1.next.next.next = new Node(10);
        head1.next.next.next.next = new Node(13);

        Node head2 = new Node(2);
        head2.next = new Node(3);
        head2.next.next = new Node(9);
        head2.next.next.next = new Node(11);
        head2.next.next.next.next = new Node(13);
        head2.next.next.next.next.next = new Node(14);

//        Node ans = mergeTwoSortedSLLManoj(head1,head2);
//        Node ans = mergeTwoSortedSLLAuthor(head1,head2);
        Node ans = sortedMerge(head1,head2);
        System.out.println(ans);


    }


    public static boolean isPalindromeManoj(Node head){
        Node mid = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            mid = mid.next;
            fast = fast.next.next;
        }

        Node revHead = null;
        if(fast == null){
            // even length SLL
            // mid will be at second middle of two middles of even-length SLL
            // so we reverse from second mid included
            revHead = reverseSLL(mid);
        }else{
            // mid is at correct place
            // for odd-length SLL we reverse from mid.next
            revHead = reverseSLL(mid.next);
        }

        while(head != null && revHead != null){
            if(head.value.equals(revHead.value) == false){
                return false;
            }

            head = head.next;
            revHead = revHead.next;

        }

        return true;
    }

    public static boolean isPalindromeAuthor(Node head){
        Node mid = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            // the above condition makes mid at middle place for odd-length SLL
            // first middle place of Two middles in even-length SLL
            mid = mid.next;
            fast = fast.next.next;
        }

        Node revHead = reverseSLL(mid.next);


        while(revHead != null){
            if(head.value.equals(revHead.value) == false){
                return false;
            }

            head = head.next;
            revHead = revHead.next;

        }

        return true;
    }


    public static void testIsPalindrome(){
        Node head = new Node('r');
        head.next = new Node('a');
        head.next.next = new Node('d');
        head.next.next.next = new Node('d');
        head.next.next.next.next = new Node('a');
        head.next.next.next.next.next = new Node('r');

        System.out.println(isPalindromeManoj(head));
        System.out.println(isPalindromeAuthor(head));
    }

    public static void main(String[] args) {
        Node contentChanged = new Node(4);
        contentChanged.next = new Node(5);
        changeNodeContent(contentChanged); // chages content in method and change persists
        System.out.println(contentChanged);

        Node referenceChanged = new Node(4);
        referenceChanged.next = new Node(5);
        changeNodeReference(referenceChanged);
        // reference of referenceChanged is changed in method. but the change doesn't persist
        System.out.println(referenceChanged);

        linkedList1();
        System.out.println();

        Node head = new Node(34);
        head = insertAtBeginOfSLL(head,5);
        System.out.println(head);

        head = insertAtEndOfSLL(head,23);
        System.out.println(head);

        Node head1 = new Node(10);
        head1.next = new Node(30);
        head1.next.next = new Node(50);
        head1.next.next.next = new Node(70);

        head1 = insertAtGivenPositionOfSLL(head1,2,20);
        head1 = insertAtGivenPositionOfSLLManoj(head1,4,123); // working
        System.out.println(head1);

        head1 = deleteFirstNodeOfSLL(head1);
        System.out.println(head1);
        head1 = deleteLastNodeOfSLL(head1);
        System.out.println(head1);

        Node head2 = new Node(5);
        head2.next = new Node(10);
        head2.next.next = new Node(15);
        head2.next.next.next = new Node(20);
        head2.next.next.next.next = new Node(25);
        System.out.println(searchInSLL(head2,5));
        System.out.println(searchInSLLRecursive(head2,20,1));
        System.out.println(searchInSLLRecursive2(head2,20));



        // Circular Linked List
        circularLinkedList();
        testInsertBeginOfCSLL();
        testInsertEndOfCSLL();
        testDeleteHeadOfCSLL();
        testDeleteKthNodeFromCSLL();

        // practice
        testSortedInsertInSLL();
        testMiddleOfLinkedList();
        testPrintNthNodeFromEndOfSLL();
        testReverseSLL();
        testRemoveDuplicatesFromSortedSLL();
        testReverseSLLInGroupsOfK();

        testDetectLoopInSLL();
        testDeleteANodeWithItsReference();
        testSegregateEvenOddInSLL();
        testIntersectionOfTwoSSL();
        testPairwiseSwapNodesOfSLL();
        testCloneSSLWithRandomPointer();
        testMergeTwoSortedSLL();

        testIsPalindrome();
    }
}
