package heap;

import java.util.*;

class MinHeap{
    int[] arr; // binary heap , which is an array, but we can also perform binaryTree like operations
    int size;
    int capacity;
    public MinHeap(int capacity){
        this.capacity = capacity;
        arr = new int[this.capacity];
    }

    public MinHeap(int[] arr){
        this.arr = arr;
        this.capacity = arr.length;
        this.size = arr.length;
    }

    int left(int i){return 2*i + 1;}
    int right(int i){return 2*i + 2;}
    int parent(int i){return (i-1)/2;}

    void insert(int key){ // add()
        if(this.size < this.capacity){
            int i = this.size;
            arr[i] = key;
            this.size++;
            while(i > 0 && arr[i] < arr[parent(i)]){ // for i = 0, parent(i) will also be 0 and we don't need this
                // swapping with parent
                arr[i] = arr[parent(i)];
                arr[parent(i)] = key;
                // updating i to parent(i)
                i = parent(i);
            }
        }else{
            System.out.println("Overflow");
        }
    }

    void minHeapifyRecursive(int i){
        int lt = left(i);
        int rt = right(i);
        int smallestAtIndex = i;
        if(lt < size && arr[lt] < arr[i]){
            smallestAtIndex = lt;
        }
        if(rt < size && arr[rt] < arr[smallestAtIndex]){
            smallestAtIndex = rt;
        }
        if(smallestAtIndex != i){
            int tmp = arr[i];
            arr[i] = arr[smallestAtIndex];
            arr[smallestAtIndex] = tmp;

            minHeapifyRecursive(smallestAtIndex); // tail recursion
        }
    }

    void minHeapifyIterative(int i){
        while(true){
            int lt = left(i);
            int rt = right(i);
            int smallestAtIndex = i;
            if(lt < size && arr[lt] < arr[smallestAtIndex]){
                smallestAtIndex = lt;
            }
            if(rt < size && arr[rt] < arr[smallestAtIndex]){
                smallestAtIndex = rt;
            }
            if(smallestAtIndex != i){
                int tmp = arr[i];
                arr[i] = arr[smallestAtIndex];
                arr[smallestAtIndex] = tmp;

                i = smallestAtIndex;
            }else break;
        }
    }

    int extractMin(){ // poll()
        if(size == 0) return Integer.MAX_VALUE;
        int ans = arr[0];

        int tmp = arr[0];
        arr[0] = arr[size-1];
        arr[size-1] = tmp;

        size--; // so that next insertion will override

//        minHeapifyRecursive(0);
        minHeapifyIterative(0);

        return ans;
    }

    void decreaseKey(int i, int key){
        if(i < size){
            arr[i] = key;
            while(i > 0 && arr[i] < arr[parent(i)]){ // for i = 0, parent(i) will also be 0 and we don't need this
                // swapping with parent
                arr[i] = arr[parent(i)];
                arr[parent(i)] = key;
                // updating i to parent(i)
                i = parent(i);
            }
        }else{
            System.out.println("index out of bound");
        }
    }

    void delete(int i){
        decreaseKey(i,Integer.MIN_VALUE);
        extractMin();
    }

    void heapSort(){
        for(int i = size-1; i>=1; i--){
            extractMin();
        }
    }

    void print(){
        System.out.println(Arrays.toString(this.arr));
    }
}

public class HeapDSA {

    /*
     * BINARY HEAP DATA-STRUCTURE
     * Heap is Complete Binary Tree + Array. we get both advantages
     * left(i) = 2*i + 1
     * right(i) = 2*i + 2
     * parent(i) = Floor((i-1)/2)
     *
     * Min Heap => root smaller than descendants
     * */
    public static void testMinHeap(){
        MinHeap mnh = new MinHeap(10);
        mnh.insert(10);
        mnh.insert(20);
        mnh.insert(15);
        mnh.insert(40);
        mnh.insert(50);
        mnh.insert(100);
        mnh.insert(25);
        mnh.insert(45);
        mnh.print();
        mnh.insert(12);
        mnh.print();

        System.out.println(mnh.extractMin());
        System.out.println(mnh.extractMin());
        System.out.println(mnh.extractMin());
        System.out.println(mnh.extractMin());
        System.out.println(mnh.extractMin());
    }

    public static MinHeap buildHeap(int[] arr){
        // Surprisingly it is O(n), so building a minHeap or maxHeap is O(n)
        MinHeap mnh = new MinHeap(arr);
        /* Index of bottom most - right most internal node
        * index of last element i = size - 1
        * parent(i) = ((size-1)-1)/2 = (size-2)/2
        * now we heapify all elements from this index back to 0
        * */
        for(int i = (mnh.size-2)/2; i>=0; i--){
            mnh.minHeapifyIterative(i);
        }
        return mnh;
    }

    public static void testBuildHeap(){
        int[] arr = {10,5,20,2,4,8};
        MinHeap mnh = buildHeap(arr);
        mnh.print();
    }

    public static void testHeapSort() {
        int[] arr = {10,5,20,2,4,8};
        MinHeap mnh = buildHeap(arr);
        mnh.print();
        mnh.heapSort();
        mnh.print();
    }

    public static void KSorted(int[] arr, int k){
        PriorityQueue<Integer> mnh = new PriorityQueue<>(); // minHeap
        for(int i = 0; i<=k; i++){
            mnh.add(arr[i]);
        }
        int idx = 0;
        for(int i = k+1; i<arr.length; i++){
            arr[idx++] = mnh.poll();
            mnh.add(arr[i]);
        }

        while(!mnh.isEmpty()){
            arr[idx++] = mnh.poll();
        }
    }

    public static void testKSorted(){
        int[] arr = {9,8,7,19,18};
        int k = 2;
        KSorted(arr,k);
        System.out.println(Arrays.toString(arr));
    }

    public static int buyMaximumItemsWithGivenSum(int[] costs, int sum){
        PriorityQueue<Integer> mnh = new PriorityQueue<>(); // minHeap
        for(int x: costs) mnh.add(x);
        int cnt = 0;
        while(sum - mnh.peek() >= 0){
            sum -= mnh.poll();
            cnt++;
        }
        return cnt;
    }

    public static void testBuyMaximumItemsWithGivenSum(){
        int[] costs = {20,10,5,30,100};
        int sum = 35;
        System.out.println(buyMaximumItemsWithGivenSum(costs,sum));
    }

    public static void printKLargest(int[] arr, int k){
        PriorityQueue<Integer> mnh = new PriorityQueue<>(); // minHeap
        for(int i = 0; i<k; i++) mnh.add(arr[i]);

        for(int i = k; i<arr.length; i++){
            if(arr[i] > mnh.peek()){
                mnh.poll();
                mnh.add(arr[i]);
            }
        }

        System.out.println(mnh);
    }

    public static void testPrintKLargest(){
        int[] arr = {5,15,10,20,8,25,18};
        int k = 3;
        printKLargest(arr,k);
    }

    public static void printKClosest(int[] arr, int x, int k){
        PriorityQueue<Integer> mxh = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
        for(int i = 0; i<k; i++){
            int diff = arr[i] - x;
            mxh.add(diff);
        }

        for(int i = k; i<arr.length; i++){
            int diff = arr[i] - x;
            if(Math.abs(diff) < Math.abs(mxh.peek())){
                mxh.poll();
                mxh.add(diff);
            }
        }

        while(!mxh.isEmpty()){
            System.out.print(mxh.poll() + x + " "); // diff + x is actual array element
        }
        System.out.println();
    }

    public static void testPrintKClosest(){
        int[] arr = {10,15,7,3,4};
        int x = 8;
        int k = 2;
        printKClosest(arr,x,k);
    }

    static class Element{
        int value;
        int rowIndex;
        int columnIndex;

        public Element(int value, int rowIndex, int columnIndex) {
            this.value = value;
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        int getValue(){
            return this.value;
        }
    }

    public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> sortedArrays){
        PriorityQueue<Element> mnh = new PriorityQueue<>(Comparator.comparing(Element::getValue)); // minHeap
        ArrayList<Integer> res = new ArrayList<>();
        int k = sortedArrays.size();
        for(int i = 0; i<k; i++){
            mnh.add(new Element(sortedArrays.get(i).get(0),i,0));
        }

        while(!mnh.isEmpty()){
            Element currMin = mnh.poll();
            res.add(currMin.value);
            if(currMin.columnIndex + 1 < sortedArrays.get(currMin.rowIndex).size()){
                int nextVal = sortedArrays.get(currMin.rowIndex).get(currMin.columnIndex+1);
                mnh.add(new Element(nextVal,currMin.rowIndex,currMin.columnIndex+1));
            }
        }
        return res;
    }

    public static void testMergeKSortedArrays(){
        ArrayList<ArrayList<Integer>> sortedArrays = new ArrayList<>();
        sortedArrays.add(new ArrayList<>(List.of(10,20)));
        sortedArrays.add(new ArrayList<>(List.of(5,15)));
        sortedArrays.add(new ArrayList<>(List.of(4,9,11)));

        ArrayList<Integer> res = mergeKSortedArrays(sortedArrays);
        System.out.println(res);
    }

    public static void printMediansOfAStream(int[] arr){
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
        PriorityQueue<Integer> greater = new PriorityQueue<>(); // minHeap

        double[] medians = new double[arr.length];

        medians[0] = arr[0];
        smaller.add(arr[0]);
        for(int i = 1; i<arr.length; i++){
            if(arr[i] < medians[i-1]){
                // smaller than median are kept in maxHeap
                if(smaller.size() > greater.size()){
                    greater.add(smaller.poll());
                }
                smaller.add(arr[i]);
            }else{
                // greater than median are kept in minHeap
                if(greater.size() == smaller.size()){
                    smaller.add(greater.poll());
                }
                greater.add(arr[i]);
            }

            if((i+1)%2 == 0){ // i+1 is current total size of all elements processed till now
                medians[i] = (smaller.peek() + greater.peek()) / 2.0;
            }else{
                medians[i] = smaller.peek();
            }
        }

        System.out.println(Arrays.toString(medians));
    }

    public static void testPrintMediansOfAStream(){
        int[] arr = {25,7,10,15,20};
        printMediansOfAStream(arr);
        int[] arr2 = {12,15,10,5,8,7,16};
        printMediansOfAStream(arr2);
    }

    public static void main(String[] args) {
        testMinHeap();
        testBuildHeap();
        testHeapSort();
        testKSorted();
        testBuyMaximumItemsWithGivenSum();
        testPrintKLargest();
        testPrintKClosest();
        testMergeKSortedArrays();
        testPrintMediansOfAStream();
    }

}