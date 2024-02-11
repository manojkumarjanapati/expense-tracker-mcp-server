package sorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


class Student{
    public String name;
    public int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Interval implements Comparable<Interval>{
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{"+start+","+end+"}";
    }

    @Override
    public int compareTo(Interval o) {
        return this.start - o.start;
    }
}

class EvenOddComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer a, Integer b) {
        return a%2 - b%2;
    }
}
public class Sorting {
    /*
    * STABLE SORTINGS: BUBBLE,INSERTION,MERGE
    * UNSTABLE SORTINGS: SELECTION,HEAP,QUICK
    * */


    public static void bubbleSort(int[] arr){
        /* O(n^2)
        * Bubble sort is a stable sorting algorithm
        * it won't take any extra space. it modifies array in-place
        * */
        int n = arr.length;

        for(int i = 0; i<n-1;i++){
            boolean sorted = true;
            for(int j = 0; j<n-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    sorted = false;
                }
            }
            if(sorted) break;
        }
    }


    public static void selectionSort(int[] arr){
        /*O(n^2)
        * does less memory writes compared to quicksort,mergesort,insertionsort etc.
        * But cycleSort is optimal in terms of memory writes
        * selectionSort is the basic idea for heapSort
        * selectionSort is not stable
        * modifies array In-place
        * */

        int n = arr.length;
        for(int i = 0; i<n-1; i++){
            int minIdx = i;

            for(int j = i+1; j<n; j++){
                if(arr[j] < arr[minIdx]) minIdx = j;
            }

            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
    }

    public static void insertionSort1(int[] arr){
        // my logic involves swapping;
        /* O(n^2)
        * In-place and stable
        * Used in practice for small arrays (Timsort and introsort)
        * */

        int n = arr.length;
        for(int i = 1; i<n; i++){
            for(int j = i; j>0;j--){
                if(arr[j-1] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }else{
                    break; // inserted to it's place so break
                }
            }
        }

    }

    public static void insertionSort(int[] arr){
        int n = arr.length;

        for(int i=1; i<n; i++){
            int ele = arr[i];
            int insertPos = i-1;
            // will insert ele at insertPos but will move all elements after insertPos 1 step right

            while(insertPos >= 0 && arr[insertPos] > ele){
                // you are doing linear search backwards for insertPos...try binarySearch Manoj
                arr[insertPos+1] = arr[insertPos];
                insertPos--;
            }
            arr[insertPos+1] = ele;
        }
    }


    public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2){
        // time : Theta(n+m)
        // space: Theta(n+m)

        int n = arr1.length;
        int m = arr2.length;
        int[] ans = new int[n+m];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < n && j < m){
            if(arr1[i] <= arr2[j]){
                ans[k++] = arr1[i++];
            }else{
                ans[k++] = arr2[j++];
            }
        }

        while(i<n) ans[k++] = arr1[i++];
        while(j<m) ans[k++] = arr2[j++];

        return ans;
    }



    public static void mergeFunctionOfMergeSort(int[] arr,int start, int mid, int end){
        // given array is made of two sorted arrays. start<= i <=mid is sorted, mid < i <= end is sorted.
        int[] leftSorted = new int[mid-start+1];
        int[] rightSorted = new int[end-mid];

        int idx = 0;
        for(int i = start; i<=mid; i++){
            leftSorted[idx++] = arr[i];
        }
        idx = 0;
        for(int i = mid+1; i<=end; i++){
            rightSorted[idx++] = arr[i];
        }

        int n = leftSorted.length;
        int m = rightSorted.length;

        int i = 0, j = 0, k = start;

        while(i<n && j<m){
            if(leftSorted[i] <= rightSorted[j]){
                arr[k++] = leftSorted[i++];
            }else{
                arr[k++] = rightSorted[j++];
            }
        }

        while(i<n) arr[k++] = leftSorted[i++];
        while(j<m) arr[k++] = rightSorted[j++];

    }


    public static void mergeSort(int[] arr,int start,int end){
        // atleast two elements needed to enter this if. if only one element present means end == start. then it won't enter as it is sorted already
        if(start<end){
            // this avoid overflow issue. if start and end values are high then (start + end)/2 will enter overflow
            // so we use start + (end-start)/2. which is equal to (start+end)/2
            int mid = start + (end-start)/2;

            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            mergeFunctionOfMergeSort(arr,start,mid,end);
            // mergeSort won't be fully sorted until it executes its last recursive call of merging two sorted arrays
            // even though it has two sorted arrays start->mid & mid+1->end. they are not fully sorted.
            // after merging both leftSorted array may get some more elements from rightSorted vice versa
            // but quick sort will have sorted parts around pivot. left and right side parts of pivot won't disturb each other
        }
    }

    public static ArrayList<Integer> interSectionOfTwoSortedArrays(int[] arr, int[] brr){
        int n = arr.length;
        int m = brr.length;

        ArrayList<Integer> commonElements = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i<n && j<m){
            if(i<n-1 && arr[i] == arr[i+1]){
                i++;
                continue;
            }
            if(j<m-1 && brr[j] == brr[j+1]){
                j++;
                continue;
            }

            if(arr[i] < brr[j]) i++;
            else if(brr[j] < arr[i]) j++;
            else{
                commonElements.add(arr[i++]);
            }
        }

        return commonElements;
    }

    public static ArrayList<Integer> interSectionOfTwoSortedArraysAuthor(int[] arr, int[] brr){
        // authors solution almost same
        int n = arr.length;
        int m = brr.length;

        ArrayList<Integer> commonElements = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i<n && j<m){
            //author way of dealing with duplicates
            if(i>0 && arr[i] == arr[i-1]){
                i++;
                continue;
            }
            if(j>0 && brr[j] == brr[j-1]){
                j++;
                continue;
            }

            if(arr[i] < brr[j]) i++;
            else if(brr[j] < arr[i]) j++;
            else{
                // author incremented both i and j because they are common and added to answer.
                commonElements.add(arr[i]);
                i++;
                j++;
            }
        }

        return commonElements;
    }

    public static ArrayList<Integer> unionOfTwoSortedArrays(int[] arr1, int[] arr2){
        int n = arr1.length;
        int m = arr2.length;

        // Using Merge function of Merge Sort
        // similar to merge function
        ArrayList<Integer> unionElements = new ArrayList<>();

        int i = 0;
        int j = 0;

        int cnt = -1;

        while(i<n && j<m){
            if(arr1[i]<=arr2[j]){
                if(cnt == -1 || unionElements.get(cnt) != arr1[i]){
                    unionElements.add(arr1[i]);
                    cnt++;
                }
                i++;
            }
            else{
                if(cnt == -1 || unionElements.get(cnt) != arr2[j]){
                    unionElements.add(arr2[j]);
                    cnt++;
                }
                j++;
            }
        }

        while(i<n){
            if(cnt == -1 || unionElements.get(cnt) != arr1[i]){
                unionElements.add(arr1[i]);
                cnt++;
            }
            i++;
        }

        while(j<m){
            if(cnt == -1 || unionElements.get(cnt) != arr2[j]){
                unionElements.add(arr2[j]);
                cnt++;
            }
            j++;
        }


        return unionElements;
    }

    public static ArrayList<Integer> unionOfTwoSortedArraysAuthor(int[] arr1, int[] arr2){

        int n = arr1.length;
        int m = arr2.length;

        // Using Merge function of Merge Sort
        // similar to merge function
        ArrayList<Integer> unionElements = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i<n && j<m){

            while( i+1<n && arr1[i] == arr1[i+1]){ // dealing with duplicates in arr1
                i++;
            }

            while( j+1<m && arr2[j] == arr2[j+1]){ // dealing with duplicates in arr2
                j++;
            }

            if(arr1[i] < arr2[j]){
                unionElements.add(arr1[i++]);
            }
            else if(arr2[j] < arr1[i]){
                unionElements.add(arr2[j++]);
            }
            else{
                unionElements.add(arr1[i]);
                i++;
                j++;
            }
        }

        while(i<n) {
            while( i+1<n && arr1[i] == arr1[i+1] ){// dealing with duplicates
                i++;
            }

            unionElements.add(arr1[i++]);
        }

        while(j<m){
            while( j+1<m && arr2[j] == arr2[j+1]){ // dealing with duplicates in arr2
                j++;
            }

            unionElements.add(arr2[j++]);
        }


        return unionElements;
    }

    public static int countAndMerge(int[] arr,int start, int mid, int end){
        // given array is made of two sorted arrays. start<= i <=mid is sorted, mid < i <= end is sorted.
        int cntInv = 0;

        int[] leftSorted = new int[mid-start+1];
        int[] rightSorted = new int[end-mid];

        int idx = 0;
        for(int i = start; i<=mid; i++){
            leftSorted[idx++] = arr[i];
        }
        idx = 0;
        for(int i = mid+1; i<=end; i++){
            rightSorted[idx++] = arr[i];
        }

        int n = leftSorted.length;
        int m = rightSorted.length;

        int i = 0, j = 0, k = start;

        while(i<n && j<m){
            if(leftSorted[i] <= rightSorted[j]){
                arr[k++] = leftSorted[i++];
            }else{
                // counting how many elements left on leftSorted using (n-i).
                // because all those n-i elements after leftSorted[i] are greater than rightSorted[j]
                // elements : leftSorted[i] to leftSorted[n-1] are greater (>) than rightSorted[j]
                cntInv += (n-i);
                arr[k++] = rightSorted[j++];
            }
        }

        while(i<n) arr[k++] = leftSorted[i++];
        while(j<m) arr[k++] = rightSorted[j++];

        return cntInv;

    }

    public static int countInversions(int[] arr,int start,int end){
        int cntInv = 0;

        if(start<end){
            int mid = start + (end-start)/2;
            cntInv += countInversions(arr,start,mid);
            cntInv += countInversions(arr,mid+1,end);
            cntInv += countAndMerge(arr,start,mid,end);
        }

        return cntInv;
    }


    public static void naivePartitionManoj(int[] arr){
        int n = arr.length;
        int pivotElement = arr[n-1];

        // counting elements lesser than ele so that i can know sorted position of ele - pos
        int pos = 0;
        for(int i = 0; i<n-1; i++){
            if(arr[i] < pivotElement) pos++;
        }

        // just swapping whatever element present at position 'pos' with ele
        arr[n-1] = arr[pos];
        arr[pos] = pivotElement;

        int start = 0;
        int end = n-1;

        // from start to pos will check for elements greater than equal to ele and swap them to right side of pos by using end pointer
        // every time new greater element swapped i decrement end so that next greater element can be swapped without over riding anything
        while(start < pos){
            if(arr[start] < pivotElement) start++;
            else{
                // element greaterthanorequal to ele.
                // so swapping to right side of it
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
                end--;
            }
        }
    }


    public static void naivePartition(int[] arr,int start,int end, int pivot){
        int[] tmp = new int[arr.length];
        int k = 0;
        for(int i = start; i<=end; i++){
            if(arr[i] <= arr[pivot]) tmp[k++] = arr[i];
        }

        for(int i = start; i<=end; i++){
            if(arr[i] > arr[pivot]) tmp[k++] = arr[i];
        }

        for(int i = start; i<=end; i++){
            arr[i] = tmp[i];
        }
    }

    public static void lomutoPartitionManoj(int[] arr){
        // LomutoPartition : find smaller elements than pivot and swap them to left using [counter and index] pos.
        // in lomutoPartition usually it is assumed to be pivot as last element
        // in case if we need to take pivot other than last element. we just swap the pivot with last element and do exact same if it is last

        int n = arr.length;

        int start = 0;
        int end = n-1;
        int pivot = n-1;

        int pos = start;

        for(int i = start; i<end; i++){
            if(arr[i] < arr[pivot]) {
                int tmp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = tmp;

                pos++; // it is index for smaller elements also counter for smaller elements
            }
        }

        // swap pivot to it's sorted place which is now pos[counter and index]
        int tmp = arr[pos];
        arr[pos] = arr[pivot];
        arr[pivot] = tmp;

    }

    public static void lomutoPartition(int[] arr){
        // LomutoPartition : find smaller elements than pivot and swap them to left using [counter and index] pos.
        // in lomutoPartition usually it is assumed to be pivot as last element
        // in case if we need to take pivot other than last element. we just swap the pivot with last element and do exact same if it is last

        int n = arr.length;

        int start = 0;
        int end = n-1;
        int pivot = n-1;

        int pos = start-1;

        for(int i = start; i<end; i++){
            if(arr[i] < arr[pivot]) {
                pos++; // it is index for smaller elements also counter for smaller elements

                int tmp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = tmp;

            }
        }

        // swap pivot to it's sorted place which is now pos[counter and index]
        int tmp = arr[pos+1];
        arr[pos+1] = arr[pivot];
        arr[pivot] = tmp;

    }


    public static void hoarePartitionManoj(int[] arr){
        int n = arr.length;

        int start = 0;
        int end = n-1;

        int pivot = arr[start];

        int i = start, j = end;
        // know is it okay to write this instead of
        //int i = start - 1 , j = end+1;


        while(true){
            while(arr[i] < pivot) i++;
            while(arr[j] > pivot) j--;

            if(i >= j) break;

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;

            // current i and j are filled with smaller[at i] and greater[at j] elements than pivot respectively
            // so updating both
            i++;j--;
        }
    }

    public static void hoarePartition(int[] arr){
        int n = arr.length;

        int start = 0;
        int end = n-1;

        int pivot = arr[start];

        int i = start-1, j = end+1;

        while(true){
            do{
                i++;
            }while(arr[i] < pivot);

            do{
                j--;
            }while(arr[j] > pivot);

            if(i >= j) break;

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }


    public static int lomutoPartitionforQuickSort(int[] arr,int start, int end){

        int pivot = end;

        int pos = start;

        for(int i = start; i<end; i++){
            if(arr[i] < arr[pivot]) {
                int tmp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = tmp;

                pos++; // it is index for smaller elements also counter for smaller elements
            }
        }

        // swap pivot to it's sorted place which is now pos[counter and index]
        int tmp = arr[pos];
        arr[pos] = arr[pivot];
        arr[pivot] = tmp;

        return pos;

    }

    public static void quickSortLomuto(int[] arr,int start, int end){
        if(start < end){
            int pivotPos = lomutoPartitionforQuickSort(arr,start,end);
            quickSortLomuto(arr,start,pivotPos-1);
            quickSortLomuto(arr,pivotPos+1,end);
        }
    }

    public static int hoarePartitionForQuickSort(int[] arr, int start, int end){

        int pivot = arr[start];

        int i = start-1, j = end+1;

        while(true){
            do{
                i++;
            }while(arr[i] < pivot);

            do{
                j--;
            }while(arr[j] > pivot);

            // Hoare partition splits array into [smallerthanpivotPart | greaterthanequalpivotPart].
            // after i and j crossing each other. j will be higher index in smallerthanpivotPart. so returning it
            if(i >= j) return j;


            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static void quickSortHoare(int[] arr, int start, int end){
        if(start<end){
            int pivotPoint = hoarePartitionForQuickSort(arr,start,end);
            quickSortHoare(arr,start,pivotPoint);
            quickSortHoare(arr,pivotPoint+1,end);
        }
    }


    public static int kthSmallestElementManoj(int[] arr, int k, int start, int end){
        // we use lomuto partitioning because it fixes a pivot in it's sorted place
        if(start < end){
            int pivotPos = lomutoPartitionforQuickSort(arr,start,end);

            // this is the answer no further sorting is needed. we got the pivot whose sorting place is kth smallest
            if(pivotPos == k-1) return arr[pivotPos];
            else if(pivotPos > k-1){
                return kthSmallestElementManoj(arr,k,start,pivotPos-1);
            }else{
                return kthSmallestElementManoj(arr,k,pivotPos+1,end);
            }

        }

        return -1;
    }

    public static int kthSmallestElementAuthor(int[] arr, int k){
        // this is called quick select algorithm
        int start = 0;
        int end = arr.length-1;

        while(start<=end){
            int pivotPos = lomutoPartitionforQuickSort(arr,start,end);
            if(pivotPos == k-1) return arr[pivotPos];
            else if(pivotPos > k-1) end = pivotPos-1;
            else start = pivotPos+1;
        }

        return -1;
    }

    public static int minimumDifferenceInArray(int[] arr){
        int n = arr.length;
        Arrays.sort(arr); // first sort
        int minDiff = Integer.MAX_VALUE;
        for(int i = 0; i<n-1;i++){
            minDiff = Math.min(Math.abs(arr[i+1] - arr[i]),minDiff);
            // minimum difference with a number will be possible with it's closest number in sorted order
        }
        return minDiff;
    }


    public static int chocolateDistributionWithminimumDifference(int[] arr, int m){
        // among m children we have to distribute chocolates with minimum difference between any two children chocolates
        int n = arr.length;
        if(m > n) return -1;
        Arrays.sort(arr);
        int minDiff = arr[m-1] - arr[0];
        for(int i=0; i<n-m+1; i++){
            minDiff = Math.min(Math.abs(arr[i+m-1] - arr[i]),minDiff);
        }
        return minDiff;

    }

    public static void sortArrayWithNegativesNPositives(int[] arr){
        int left = -1;
        int right = arr.length;

        int pivot = 0;
        // i am going with hoare's partition algo

        while(true){
            do{
                left++;
            }while(arr[left] < pivot);

            do{
                right--;
            }while(arr[right] >= pivot);

            if(left >= right) {
                // return right; // we can return right. right will hold last element index in left partition
                //break;
                return; // don't use break as it's return type is void. use return; using this we can break all loops and return from function call
            }

            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
    }

    public static void sortArrayWithEvenOdd(int[] arr){
        int left = -1;
        int right = arr.length;

        // i am going with hoare's partition algo

        while(true){
            do{
                left++;
            }while(arr[left]%2 == 0);

            do{
                right--;
            }while(arr[right]%2 == 1);

            if(left >= right) {
                // return right; // we can return right. right will hold last element index in left partition
                //break;
                return; // don't use break as it's return type is void. use return; using this we can break all loops and return from function call
            }

            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
    }


    public static void sortArrayWith0s1s2s(int[] arr){
        int start = -1;
        int end = arr.length;

        int pivot = 2;

        while(true){

            do{
                start++;
            }while (arr[start] < pivot);

            do{
                end--;
            }while (arr[end] >= pivot);

            if(start >= end){
                break;
            }

            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;

        }

        start = -1;
        end = end+1;

        pivot = 1;

        while(true){

            do{
                start++;
            }while (arr[start] < pivot);

            do{
                end--;
            }while (arr[end] >= pivot);

            if(start >= end){
                return;
            }

            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;

        }

    }

    public static void sortArrayWith0s1s2sAuthor(int[] arr){
        // Dutch National Flag  Algorithm

        int start = 0, mid = 0, end = arr.length-1;

        while(mid <= end){
            if(arr[mid] == 0){
                int tmp = arr[start];
                arr[start] = arr[mid];
                arr[mid] = tmp;

                start++;
                mid++;
            }
            else if(arr[mid] == 1) mid++;
            else{
                int tmp = arr[mid];
                arr[mid] = arr[end];
                arr[end] = tmp;

                end--;
            }
        }
    }

    public static void partitionArroundARange(int[] arr, int pivot1, int pivot2){
        // Dutch National Flag  Algorithm

        int start = 0, mid = 0, end = arr.length-1;

        while(mid <= end){
            if(arr[mid] < pivot1){
                int tmp = arr[start];
                arr[start] = arr[mid];
                arr[mid] = tmp;

                start++;
                mid++;
            }
            else if(arr[mid] > pivot2){
                int tmp = arr[mid];
                arr[mid] = arr[end];
                arr[end] = tmp;

                end--;
            }else{
                // it in range
                mid++;
            }
        }
    }

    public static ArrayList<Interval> mergeOverlappingIntervals(ArrayList<Interval> intervals){
        Collections.sort(intervals);
        ArrayList<Interval> mergedIntervals = new ArrayList<>();

        mergedIntervals.add(intervals.get(0));
        int k = 0;
        for(int i = 1; i<intervals.size(); i++){
            int x = Math.max(intervals.get(i).start, mergedIntervals.get(k).start);
            int y = Math.min(intervals.get(i).end, mergedIntervals.get(k).end);

            if(x <= y){
                // overlapping so update
                mergedIntervals.get(k).start = Math.min(intervals.get(i).start, mergedIntervals.get(k).start);
                mergedIntervals.get(k).end = Math.max(intervals.get(i).end, mergedIntervals.get(k).end);
            }else{
                mergedIntervals.add(intervals.get(i));
                k++;
            }
        }

        return mergedIntervals;
    }

    public static int meetingTheMaximumGuests(int[] arrivals, int[] departures){
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int n = arrivals.length;
        int m = departures.length;

        int i = 0, j = 0;

        int guestsCnt = 0;
        int maxGuestsAtAtime = 0;
        while(i < n && j<m){
            if(arrivals[i] <= departures[j]){
                guestsCnt++; // one guest arrived
                i++;
            }else{
                guestsCnt--; // one guest departured
                j++;
            }
            maxGuestsAtAtime = Math.max(guestsCnt,maxGuestsAtAtime);
        }

        return maxGuestsAtAtime;
    }


    public static void cycleSortDistinctManoj(int[] arr){
        // optimal for memory writes
        // in-place
        // not stable
        // Q pattern -> minimum swaps to sort an array
        int i = 0;
        int ele = arr[i];
        int n = arr.length;
        while(i < n-1){

            int pos = 0;

            for(int j = 0; j<n; j++){
                if(arr[j] < ele) pos++;
            }

            if(ele == arr[pos]){
                i++;
                ele = arr[i];
            }
            else{
                ele = ele ^ arr[pos];
                arr[pos] = ele ^ arr[pos];
                ele = ele ^ arr[pos];
            }

        }
    }

    public static void cycleSortDistinctAuthor(int[] arr){
        int n = arr.length;
        for(int i = 0; i<n-1; i++){
            int item = arr[i];
            int pos = i;
            for(int j = i+1; j<n; j++){
                if(arr[j] < item) pos++;
            }
            item = item^arr[pos];
            arr[pos] = item^arr[pos];
            item = item^arr[pos];
            while(pos != i){
                pos = i;
                for(int j = i+1; j<n; j++){
                    if(arr[j] < item) pos++;
                }
                item = item^arr[pos];
                arr[pos] = item^arr[pos];
                item = item^arr[pos];
            }
        }
    }


    public static void cycleSortManoj(int[] arr){
        // not working just tried
        int n = arr.length;
        System.out.println(Arrays.toString(arr));

        for(int i = 0; i<n-1; i++){
            int item = arr[i];
            int pos = i;
            for(int j = i+1; j<n; j++){
                if(arr[j] < item) pos++;
            }
            item = item^arr[pos];
            arr[pos] = item^arr[pos];
            item = item^arr[pos];
//            System.out.println(Arrays.toString(arr));


            while(pos != i && arr[pos] != arr[i]){
                pos = i;
                for(int j = i+1; j<n; j++){
                    if(arr[j] < item) pos++;
                }
                item = item^arr[pos];
                arr[pos] = item^arr[pos];
                item = item^arr[pos];

            }

            pos = 0;
            for(int j = 0; j<i; j++){
                if(arr[j] <= item) pos++;
            }
            item = item^arr[pos];
            arr[pos] = item^arr[pos];
            item = item^arr[pos];

        }
    }


    public static int cycleSort(int[] arr){
        int writesCnt = 0;
        int n = arr.length;

        for(int cs = 0; cs<n-1; cs++){
            int ele = arr[cs];

            int pos = cs;

            for(int j = cs+1; j<n; j++){
                if(arr[j] < ele) pos++;
            }

            if(pos == cs) continue;


            while(ele == arr[pos]){
                pos++;
            }

            if(pos != cs){
                int tmp = ele;
                ele = arr[pos];
                arr[pos] = tmp;
                writesCnt++;
            }

            while(pos != cs){
                pos = cs;

                for(int j = cs+1; j<n; j++){
                    if(arr[j] < ele) pos++;
                }

//                if(pos == cs) continue;


                while(ele == arr[pos]){
                    pos++;
                }

                if(ele != arr[pos]){
                    int tmp = ele;
                    ele = arr[pos];
                    arr[pos] = tmp;
                    writesCnt++;
                }
            }

        }

        return writesCnt;
    }


    public static void countingSortNaive(int[] arr, int k){
        int[] freq = new int[k];

        for(int x: arr){
            freq[x]++;
        }

        int idx=0;
        for(int i=0; i<k; i++){
//            // manoj way -> but memory writes are there so not optimal
//            while(freq[i] > 0){
//                arr[idx] = i;
//                idx++;
//                freq[i]--; // memory write
//            }

            // author way -> optimal for memory writes
            for(int j = 0; j<freq[i]; j++){
                arr[idx] = i;
                idx++;
            }
        }

    }


    public static void countingSortObjectsbyMember(Student[] students, int maxAge){
        // sorting students by their age using counting sort
        int[] freq = new int[maxAge];

        for(Student s: students){
            freq[s.age]++;
        }

        for(int i = 1; i<freq.length; i++){
            freq[i] = freq[i-1]+freq[i];
        }

        Student[] sortedStudents = new Student[students.length];

        for(int i = sortedStudents.length-1; i >=0; i--){
            Student s = students[i];
            int idx = freq[s.age] - 1;
            sortedStudents[idx] = students[i];
            freq[s.age]--;
        }

        for(int i = 0; i<students.length; i++){
            students[i] = sortedStudents[i];
        }

    }


    public static void countingSortForRadixSortManoj(int[] arr, int nthDigit){
        int divider = (int) Math.pow(10,nthDigit);
        int maxDigit = 0;

        for(int x: arr){
            int digit = (x%divider) / (divider/10);
            maxDigit = Math.max(maxDigit,digit);
        }

        int[] freq = new int[maxDigit+1];

        for(int x: arr){
            int digit = (x%divider) / (divider/10);
            freq[digit]++;
        }

        for(int i = 1; i<freq.length; i++){
            freq[i] = freq[i-1] + freq[i];
        }

//        System.out.println(Arrays.toString(freq));

        int[] sorted = new int[arr.length];
        for(int i = arr.length-1; i>=0; i--){
            int x = arr[i];
            int digit = (x%divider) / (divider/10);
            int idx = freq[digit] - 1;
            sorted[idx] =  x;
            freq[digit]--;
        }

        for(int i = 0; i<arr.length; i++){
            arr[i] = sorted[i];
        }

    }

    public static void radixSort(int[] arr){
        int max = arr[0];
        for(int i = 1; i<arr.length; i++){
            max = Math.max(max,arr[i]);
        }

        int  noOfDigts = (int) (Math.log10(max) + 1);

        for(int i = 1; i<=noOfDigts; i++){
            countingSortForRadixSortManoj(arr,i);
        }
    }


    public static void countingSortForRadixSortAuthor(int[] arr, int exp){
        int n = arr.length;
        int[] freq = new int[10]; // because freq is hashing of digits 0-9
        int[] sorted = new int[n];

        for(int x: arr){
            int digit = (x/exp)%10;
            freq[digit]++;
        }

        for(int i = 1; i<freq.length; i++){
            freq[i] = freq[i-1] + freq[i]; // prefix sum -> calcuating count of elements less than equal  to ith ele
        }

        for(int i = n-1; i>=0; i--){
            int x = arr[i];
            int digit = (x/exp)%10;
            int idx = freq[digit]-1;
            sorted[idx] = x;
            freq[digit]--;
        }

        for(int i = 0; i<n; i++){
            arr[i] = sorted[i];
        }
    }

    public static void radixSortAuthor(int[] arr){
        int n = arr.length;
        int max = arr[0];
        for(int i = 1; i<n; i++){
            if(arr[i] > max) max = arr[i];
        }

        for(int exp = 1; max/exp > 0; exp *= 10){
            countingSortForRadixSortAuthor(arr,exp);
        }
    }

    public static void bucketSort(int[] arr){
        // linear time
        // used when data is uniformly distributed

    }



    public static void main(String[] args) {
        Integer[] arr1 = {5,20,10,3,12};
        Arrays.sort(arr1,new EvenOddComparator());
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {10,8,20,5,30,6,40,2,4,3,2};
        bubbleSort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10,8,20,5,30,6,40,2,4,3,2};
        selectionSort(arr3);
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = {10,8,20,5,30,6,40,2,4,3,2};
        insertionSort(arr4);
        System.out.println(Arrays.toString(arr4));

        int[] a = {10,15,20};
        int[] b = {5,6,6,15,17};
        int[] c = mergeTwoSortedArrays(a,b);
        System.out.println(Arrays.toString(c));

        int[] twosortedsubarrays = {10,15,20,40,8,15,33};
        mergeFunctionOfMergeSort(twosortedsubarrays,0,3,twosortedsubarrays.length-1);
        System.out.println(Arrays.toString(twosortedsubarrays));

        int[] arr5 = {10,8,20,5,30,6,40,2,4,3,2};
        mergeSort(arr5,0,arr5.length-1);
        System.out.println(Arrays.toString(arr5));

        int[] arr6 = {3,5,10,10,10,15,15,20};
        int[] arr7 = {5,10,10,15,30};
        System.out.println(interSectionOfTwoSortedArrays(arr6,arr7));
        System.out.println(interSectionOfTwoSortedArraysAuthor(arr6,arr7));
        System.out.println(unionOfTwoSortedArrays(arr6,arr7));

        int[] arr8 = {1,2,3,3,4,5,5,5};
        int[] arr9 = {6,7,8,9,10,10,10};
        System.out.println(unionOfTwoSortedArrays(arr8,arr9));
        System.out.println(unionOfTwoSortedArraysAuthor(arr8,arr9));

        int[] arr10 = {4,5,8,11,3,6,9,13};
        System.out.println(countAndMerge(arr10,0,3,7));// start = 0, mid = [up to where leftSorted] 3, end = 7;
        System.out.println(Arrays.toString(arr10));


        int[] arr11 = {2,4,1,3,5};
        System.out.println(countInversions(arr11,0,arr11.length-1));
        System.out.println(Arrays.toString(arr11));


        int[] arr12 = {3,8,6,12,10,7};
        naivePartitionManoj(arr12);
        System.out.println(Arrays.toString(arr12));

        int[] arr13 = {3,8,6,12,10,7};
        naivePartition(arr13,0,arr13.length-1,arr13.length-1);
        System.out.println(Arrays.toString(arr13));

        int[] arr14 = {10,80,30,90,40,50,70};
        lomutoPartitionManoj(arr14);
        System.out.println(Arrays.toString(arr14));

        int[] arr15 = {10,80,30,90,40,50,70};
        lomutoPartition(arr15);
        System.out.println(Arrays.toString(arr15));

        int[] arr16 = {5,3,8,4,2,7,1,10};
        hoarePartitionManoj(arr16);
        System.out.println(Arrays.toString(arr16));

        int[] arr17 = {5,3,8,4,2,7,1,10};
        hoarePartition(arr17);
        System.out.println(Arrays.toString(arr17));


        int[] arr18 = {8,4,7,9,3,10,5};
        quickSortLomuto(arr18,0,arr18.length-1);
        System.out.println(Arrays.toString(arr18));

        int[] arr19 = {8,4,7,9,3,10,5};
        quickSortHoare(arr19,0,arr19.length-1);
        System.out.println(Arrays.toString(arr19));


        int[] arr20 = {10,4,5,8,11,6,26};
        System.out.println(kthSmallestElementManoj(arr20,5,0,arr20.length-1));

        int[] arr21 = {10,4,5,8,11,6,26};
        System.out.println(kthSmallestElementAuthor(arr21,5));

        int[] arr22 = {10,8,1,4};
        System.out.println(minimumDifferenceInArray(arr22));

        int[] arr23 = {7,3,1,8,9,12,56};
        System.out.println(chocolateDistributionWithminimumDifference(arr23,3));

        int[] arr24 = {13,-12,18,-10};
        sortArrayWithNegativesNPositives(arr24);
        System.out.println(Arrays.toString(arr24));


        int[] arr25 = {15,14,13,12,22,16,15};
        sortArrayWithEvenOdd(arr25);
        System.out.println(Arrays.toString(arr25));

        int[] binaryArr = {0,1,1,0,0,1,0,1}; // this also should be sorted in even odd manner
        // not by counting 0s and 1s. they can be keys to objects in real time example. so if we just count we may lose the relation between objects
        sortArrayWithEvenOdd(binaryArr);
        System.out.println(Arrays.toString(binaryArr));

        int[] arr26 = {0,2,1,2,0,0,2,2,1,0,0,2,1};
        sortArrayWith0s1s2s(arr26);
        System.out.println(Arrays.toString(arr26));

        int[] arr27 = {0,2,1,2,0,0,2,2,1,0,0,2,1};
        sortArrayWith0s1s2sAuthor(arr27);
        System.out.println(Arrays.toString(arr27));


        int[] arr28 = {10,5,6,3,20,9,40};
        partitionArroundARange(arr28,5,10);
        System.out.println(Arrays.toString(arr28));


        ArrayList<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(7,9));
        intervals.add(new Interval(6,10));
        intervals.add(new Interval(4,5));
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(2,4));

        System.out.println(intervals);
        System.out.println(mergeOverlappingIntervals(intervals));


        int[] arrivals = {900,600,700};
        int[] departures = {1000,800,730};

        System.out.println(meetingTheMaximumGuests(arrivals,departures));


        int[] arr29 = {20,40,50,10,30};
        cycleSortDistinctManoj(arr29);
        System.out.println(Arrays.toString(arr29));

        int[] arr30 = {20,40,50,10,30};
        cycleSortDistinctAuthor(arr30);
        System.out.println(Arrays.toString(arr30));

//        int[] arr31 = {4,3,2,2,1,5,1};

        int[] arr31 = {1,3,4,2,2,3,1,4,3};
        cycleSortManoj(arr31);
        System.out.println(Arrays.toString(arr31));

        int[] arr32 = {1,3,4,2,2,3,1,4,3};
        System.out.println(Arrays.toString(arr32));
        cycleSort(arr32);
        System.out.println(Arrays.toString(arr32));

        int[] arr33 = {1,4,4,1,0,1};
        countingSortNaive(arr33,5);
        System.out.println(Arrays.toString(arr33));

        Student[] students = new Student[6];
        int maxAge = 30;
        students[0] = new Student("manoj",22);
        students[1] = new Student("haritha",22);
        students[2] = new Student("rakesh", 23);
        students[3] = new Student("harsha", 21);
        students[4] = new Student("sandhya", 23);
        students[5] = new Student("shankar", 25);

        countingSortObjectsbyMember(students,maxAge);

        for(Student s: students){
            System.out.println(s);
        }

        int[] arr34 = {319,212,6,8,100,50};
//        countingSortForRadixSortManoj(arr34,1);
        radixSort(arr34);
        System.out.println(Arrays.toString(arr34));

        int[] arr35 = {319,212,6,8,100,50};
        radixSortAuthor(arr35);
        System.out.println(Arrays.toString(arr35));

   }
}
