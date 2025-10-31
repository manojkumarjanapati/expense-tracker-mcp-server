import java.util.ArrayList;
import java.util.HashSet;

class Node {
    int data;
    Node next;
    Node(int data)
    {
        this.data = data;
        next = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
public class Test{

    static Node swapkthnode(Node head, int num, int K)
    {
        // your code here
        if(K > num) return head;

        K = Math.min(K,num-K+1);

        Node dummy = new Node(0);
        dummy.next = head;
        head = dummy;

        Node prev = head;
        int i;
        for(i = 1; i<K; i++){
            prev = prev.next;
        }

        Node firstPrev = prev;

        System.out.println(firstPrev.data);

        for(i = K;i<num-K+1;i++){
            prev = prev.next;
        }

        Node secondPrev = prev;
        System.out.println(secondPrev.data);

        Node second = secondPrev.next;
        secondPrev.next = second.next;

        Node first = firstPrev.next;
        firstPrev.next = first.next;

        second.next = firstPrev.next;
        firstPrev.next = second;

        prev = firstPrev;
        for(i = K;i<num-K+1;i++){
            prev = prev.next;
        }

        secondPrev = prev;
        first.next = secondPrev.next;
        secondPrev.next = first;

        return dummy.next;
    }

    public static void testSwapKthNode(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);

        System.out.println(head);
        head = swapkthnode(head,7,2);
        System.out.println(head);
    }

    static int detMatrixUtil(int matrix[][], int r, HashSet<Integer> cols, int colIndexSum){
        // indexSum to know last remaining index in the final row
        if(matrix.length - r == 1){
            // last index = sum of all colum index [0 to matrix.length] - colIndexSum till now
            int n = matrix.length - 1;
            int lastColIndex = (n*(n+1))/2 - colIndexSum;
            return matrix[r][lastColIndex];
        }
        int det = 0;
        int sign = 1;
        for(int c = 0; c<matrix.length; c++){
            if(!cols.contains(c)){
                cols.add(c);
                det += sign * matrix[r][c] * detMatrixUtil(matrix,r+1,cols,colIndexSum+c);
                sign *= -1;
                cols.remove(c);
            }
        }
        return det;
    }

    public static void main(String[] args) {

        int[][] matrix = {{1, 0, 2, -1},
                {3, 0, 0, 5},
                {2, 1, 4, -3},
                {1, 0, 5, 0}};

        System.out.println(detMatrixUtil(matrix,0,new HashSet<>(),0));

    }
}
