package hashing;

import java.util.*;
import java.util.stream.Collectors;

public class Hashing {

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

        printFrequencyOfDigits(new int[]{1,2,3,2,4,5,3,1,1,6,4,2,4});
        printCharacterFrequency("manojkumarjanapatiharitha");

        int[] arr = new int[]{2,3,2,3,4};
        System.out.println("Frequency of "+Arrays.toString(arr)+" : ");
        inplaceFrequencyCount(arr,5,4);
        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(topK(new int[]{1,1,2,2,3,3,3,4},2)));
    }
}
