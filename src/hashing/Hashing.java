package hashing;

import java.util.*;

class MyHash2{
    // linear probing
    int[] arr;
    int cap,size;

    public MyHash2(int cap) {
        this.cap = cap;
        arr = new int[this.cap];
        size = 0;

        for(int i = 0; i<this.cap; i++){
            arr[i] = -1; // -1 for empty slot of hash
        }
    }

    int hash(int key){
        return key%this.cap;
    }

    boolean search(int key){
        int h = hash(key);
        int i=h;
        while (arr[i] != -1){ // we stop when the current index is empty slot
            if(arr[i] == key) return true;
            // linear probing for searching next index in array for the key
            i = (i+1)%cap; // modulo arithmetic indexing for circular array traversal
            if(i == h) return false; // Or we stop when we come to initial index by traversing whole array
        }

        return false;
    }

    boolean insert(int key){
        if(size == cap) return false;

        int i = hash(key);
        while(arr[i]!=-1 && arr[i]!=-2 && arr[i]!=key){
            i = (i+1)%cap;
        }

        if(arr[i] == key) return false;
        else{
            arr[i] = key;
            size++;
            return true;
        }
    }

    boolean delete(int key){
        int h = hash(key);
        int i = h;
        while(arr[i]!=-1){
            if(arr[i] == key){
                arr[i] = -2; // -2 for marking as deleted
                size--;
                return true;
            }

            i = (i+1)%cap;
            if(i == h) return false;
        }

        return false;
    }


}
class MyHash{
    // manoj way of hashmap
    // this is based on chaining.
    // we have a chain of linked lists
    int bucket;
    ArrayList<LinkedList<Integer>> hashTable;
    ArrayList<LinkedList<Integer>> values;

    public MyHash(int bucket) {
        this.bucket = bucket;
        hashTable = new ArrayList<>();
        values = new ArrayList<>();
        for(int i=0; i<this.bucket; i++){
            hashTable.add(new LinkedList<>());
            values.add(new LinkedList<>());
        }
    }

    void insert(int key, int value){
        int i = key%this.bucket;
        hashTable.get(i).add(key);
        values.get(i).add(value);
    }

    boolean search(int key){
        int i = key%this.bucket;
        return hashTable.get(i).contains(key);
    }

    Integer get(int key){
        int i = key%this.bucket;
        int idx = hashTable.get(i).indexOf(key);
        if(idx == -1) return null;

        return values.get(i).get(idx);
    }

    void delete(int key){
        int i = key%this.bucket;
        hashTable.get(i).remove((Integer) key);
    }
}

public class Hashing {

    public static void insert(boolean[] hash, int x){
        hash[x] = true;
    }

    public static void delete(boolean[] hash, int x){
        hash[x] = false;
    }

    public static boolean search(boolean[] hash, int x){
        return hash[x];
    }


    public static int countDistinctElements(int[] arr){
        // way 1
        HashSet<Integer> hs = new HashSet<>();
        for(int x: arr) hs.add(x);

        // way 2
//        HashSet<Integer> hs = new HashSet<>(Arrays.asList(arr)); // it will work for Integer[] arr

        return hs.size();
    }

    public static void frequenciesOfArrayElements(int[] arr){
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int x: arr){
            hm.put(x,hm.getOrDefault(x,0)+1);
        }

        System.out.println(hm);
    }

    public static void intersectionOfTwoUnsortedArrays(int[] arr1, int[] arr2){
        HashSet<Integer> hs = new HashSet<>();
        for(int x: arr2) hs.add(x);

        for(int x: arr1){
            if(hs.contains(x)) System.out.print(x+" ");
        }
    }

    public static int unionSizeOfTwoUnsortedArrays(int[] arr1, int[] arr2){
        HashSet<Integer> hs = new HashSet<>();
        for(int x: arr1) hs.add(x);
        for(int x: arr2) hs.add(x);
        return hs.size();

    }

    public static boolean pairWithGivenSumInUnsortedArray(int[] arr, int sum){
        HashSet<Integer> hs = new HashSet<>();

        for(int x: arr){
            if(hs.contains(sum-x)) {
                System.out.println("Pair :" + x + "," + (sum-x));
                return true;
            }
            else{
                hs.add(x);
            }
        }

        return false;
    }

    public static boolean subArrayWithZeroSum(int[] arr){
        int prefixSum = 0;
        HashSet<Integer> hs = new HashSet<>();
        for(int x: arr){
            prefixSum += x;
            if(hs.contains(prefixSum)) return true;

            if(prefixSum == 0) return true; // we can eliminate this by adding 0 into hs initially

            hs.add(prefixSum);
        }

        return false;
    }

    public static boolean subArrayWithGivenSum(int[] arr, int sum){
        int prefixSum = 0;
        HashSet<Integer> hs = new HashSet<>();
//        hs.add(0); // to eliminate checking for subarray that starts from index 0;

        for(int x: arr){
            prefixSum += x;
            if(hs.contains(prefixSum-sum)) return true;

            if(prefixSum == sum) return true; // we can eliminate this by adding 0 into hs initially

            hs.add(prefixSum);
        }

        return false;
    }

    public static int longestSubArrayWithGivenSum(int[] arr, int sum){
        HashMap<Integer,Integer> hm = new HashMap<>();
        int prefixSum = 0;
        hm.put(0,-1); // prefixSum = 0 at index = -1;
        int res = 0;
        for(int i = 0; i<arr.length; i++){
            prefixSum += arr[i];
            if(hm.containsKey(prefixSum-sum)){
                // there is a sub array
                res = Math.max(res,i-hm.get(prefixSum-sum));
            }

            if(!hm.containsKey(prefixSum)) hm.put(prefixSum,i);
        }
        System.out.println(hm);
        return res;
    }

    public static int longestSubArrayWithEqualNoOfZerosNOnesNaive(int[] arr){
        int res = 0;
        for(int i = 0; i<arr.length; i++){
            int cnt0 = 0;
            int cnt1 = 0;
            for(int j = i; j<arr.length; j++){ // traversing all possible subArrays
                if(arr[j] == 0) cnt0++;
                else cnt1++;

                if(cnt0 == cnt1){
                    res = Math.max(res,cnt0+cnt1);
                }
            }
        }

        return res;
    }


    public static int longestSubArrayWithEqualNoOfZerosNOnes(int[] arr){

        for(int i = 0; i<arr.length; i++){
            if(arr[i] == 0) arr[i] = -1;
        }

        return longestSubArrayWithGivenSum(arr,0);
    }

    public static int longestCommonSpanWithSameSumInBinaryArrays(int[] arr1, int[] arr2){
        for(int i=0; i<arr1.length; i++){
            arr1[i] = arr1[i] - arr2[i];
        }

        return longestSubArrayWithGivenSum(arr1,0);
    }

    public static int longestConsecutiveSubsequences(int[] arr){
        HashSet<Integer> hs = new HashSet<>();
        for(int x: arr){
            hs.add(x);
        }

        int res = 1; // atleast single element is also consecutive subsequence
        for(int x: hs){
            if(hs.contains(x-1) == false){
                // this is the first element in consecutive elements
                int cnt = 1;
                while(hs.contains(x+1)){
                    cnt++;
                    x++; // we will next check for consecutive element
                }
                res = Math.max(res,cnt);
            }
        }

        return res;
    }


    public static void countDistinctElementsInKSizedWindow(int[] arr, int k){
        int n = arr.length;
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i = 0; i<k; i++){
            hm.put(arr[i],i);
        }
        System.out.print(hm.size() + " ");
        for(int i = 0; i<n-k; i++){
            if(hm.get(arr[i]) == i) hm.remove(arr[i]);
            hm.put(arr[i+k],i+k);
            System.out.print(hm.size() + " ");
        }

    }

    public static void countDistinctElementsInKSizedWindowAuthor(int[] arr, int k){
        int n = arr.length;
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i = 0; i<k; i++){
            hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
        }
        System.out.print(hm.size() + " ");
        for(int i = k; i<n; i++){
            hm.put(arr[i-k],hm.get(arr[i-k])-1);

            if(hm.get(arr[i-k]) == 0) hm.remove(arr[i-k]);

            hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
            System.out.print(hm.size() + " ");
        }

    }


    public static void moreThanNDividedByKOccurrence(int[] arr, int k){
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = arr.length;

        for(int x: arr){
            hm.put(x,hm.getOrDefault(x,0)+1);
        }

        for(int key: hm.keySet()){
            if(hm.get(key) > ((double)n/k)){
                System.out.print(key+" ");
            }
        }
    }

    public static void moreThanNDividedByKOccurrenceMooresVoting(int[] arr,int k){
        // time : O(nk)
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = arr.length;

        for(int i = 0; i<n; i++){
            if(hm.containsKey(arr[i])) hm.put(arr[i],hm.get(arr[i])+1);
            else if(hm.size() < k) hm.put(arr[i],1);
            else {
                ArrayList<Integer> rejected = new ArrayList<>();
                for(int key: hm.keySet()){
                    hm.put(key,hm.get(key)-1);
                    if(hm.get(key) == 0) rejected.add(key);
                }
                for(int key: rejected){
                    hm.remove(key);
                }
            }
        }

        for(int key: hm.keySet()){
            int cnt = 0;
            for(int x: arr){
                if(key == x) cnt++;
            }

            if(cnt > ((double)n/k)) {
                System.out.print(key + " ");
            }
        }
    }


    public static void printFrequencyOfDigits(int[] arr){
        int[] hash = new int[10];

        for(int x: arr){
            hash[x]++;
        }

        System.out.println("Digit Frequency :");
        for(int i = 0; i<10; i++){
            System.out.println(i + " : "+hash[i]);
        }
    }


    public static void printCharacterFrequency(String s){// assume lower case
        int[] hash = new int[26];
        // int[] hash = new int[256]; // for all characters

        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            hash[ch-'a']++; // hash[ch] // for all characters
        }

        System.out.println("Character Frequency");
        for(char k = 'a'; k<='z'; k++){ // traverse accordingly for all characters
            System.out.println(k+" : "+hash[k-'a']); // hash[k] for all characters
        }
    }

    public static void inplaceFrequencyCount(int arr[], int N, int P)
    { // assume arr[i] <= P
        // https://practice.geeksforgeeks.org/problems/frequency-of-array-elements-1587115620/0
        // code here
        int i = 0;
        while(i<N){
            if(arr[i] <= 0){
                i++;
                continue;
            }
            if(arr[i] > N || arr[i] > P){
                arr[i] = 0;
                i++;
                continue;
            }

            int x = arr[i];

            if(arr[x-1]  > 0){
                arr[i] = arr[x-1];
                arr[x-1] = -1;
            }

            else{
                arr[x-1]--;
                arr[i] = 0;
                i++;
            }

        }

        for(int j=0;j<N;j++) {
            if(arr[j] < 0) arr[j] *= -1;
        }

    }

    public static int[] topK(int[] nums, int k) {
        // Code here
        HashMap<Integer,Integer> hash = new HashMap<>();

        for(int x: nums){
            hash.put(x,hash.getOrDefault(x,0)+1);
        }

        int[] ans = new int[k];

        ArrayList<Integer> keys = new ArrayList<>();
        for(int x: hash.keySet()){
            keys.add(x);
        }

        Collections.sort(keys,Collections.reverseOrder());

        Collections.sort(keys, Comparator.comparing(hash::get).reversed());

//        System.out.println(keys);
        for(int i = 0; i<k; i++){
            ans[i] = keys.get(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        // direct Address table
        boolean[] hash = new boolean[1000];
        insert(hash,20);
        insert(hash,30);
        insert(hash,10);
        System.out.println(search(hash,20));
        delete(hash,30);
        System.out.println(search(hash,30));

        /* Hashing
        * The idea is to use keys as indexes.
        * */
        MyHash myHash = new MyHash(7);
        int[] keys = {70,71,56,9,72};
        for(int key: keys){
            myHash.insert(key,key*10);
        }

        System.out.println(myHash.hashTable);
        System.out.println(myHash.values);

        myHash.delete(56);

        System.out.println(myHash.hashTable);

        System.out.println(myHash.get(9));


        // problems
        int[] arr1 = {10,20,10,20,30};
        System.out.println(countDistinctElements(arr1));
        frequenciesOfArrayElements(arr1);

        int[] arr2 = {10,20,30};
        int[] arr3 = {30,10};
        intersectionOfTwoUnsortedArrays(arr2,arr3);
        System.out.println();
        System.out.println(unionSizeOfTwoUnsortedArrays(arr2,arr3));

        int[] arr4 = {8,3,9,4};
        System.out.println(pairWithGivenSumInUnsortedArray(arr4,13));

        int[] arr5 = {-3,4,-3,-1,1};
        System.out.println(subArrayWithZeroSum(arr5));

        int[] arr6 = {5,8,6,13,3,-1};
        System.out.println(subArrayWithGivenSum(arr6,22));

        int[] arr7 = {8,3,1,5,-6,6,2,2};
        System.out.println(longestSubArrayWithGivenSum(arr7,4));

        int[] arr8 = {1,0,1,1,1,0,0};
        System.out.println(longestSubArrayWithEqualNoOfZerosNOnesNaive(arr8));
        System.out.println(longestSubArrayWithEqualNoOfZerosNOnes(arr8));

        int[] arr9 = {0,1,0,0,0,0};
        int[] arr10 = {1,0,1,0,0,1};
        System.out.println(longestCommonSpanWithSameSumInBinaryArrays(arr9,arr10));

        int[] arr11 = {1,3,4,3,3,2,9,10};
        System.out.println(longestConsecutiveSubsequences(arr11));

        int[] arr12 = {10,20,20,10,30,40,10};
        countDistinctElementsInKSizedWindow(arr12,4);
        System.out.println();
        countDistinctElementsInKSizedWindowAuthor(arr12,4);
        System.out.println();

        int[] arr13 = {10,10,20,30,20,10,10};
        moreThanNDividedByKOccurrence(arr13,2);
        System.out.println();
        moreThanNDividedByKOccurrenceMooresVoting(arr13,2);


        printFrequencyOfDigits(new int[]{1,2,3,2,4,5,3,1,1,6,4,2,4});
        printCharacterFrequency("manojkumarjanapati");

        int[] arr = new int[]{2,3,2,3,4};
        System.out.println("Frequency of "+Arrays.toString(arr)+" : ");
        inplaceFrequencyCount(arr,5,4);
        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(topK(new int[]{1,1,2,2,3,3,3,4},2)));

    }
}
