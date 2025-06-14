package searching;

import java.util.Scanner;

public class Searching {

    public static int binarySearch(int[] arr, int ele){
        int start = 0;
        int end = arr.length-1;

        while(start<=end){
            int mid = (start+end)/2;

            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) start = mid+1;
            else end = mid-1;
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int ele, int start, int end){
        if(start > end) return -1;

        int mid = (start+end)/2;
        if(arr[mid] == ele) return mid;
        else if(arr[mid] < ele){
            return binarySearchRecursive(arr,ele,mid+1,end);
        }else{
            return binarySearchRecursive(arr,ele,start,mid-1);
        }
    }

    public static int indexOfFirstOccurrenceInSortedArrayRecursive(int[] arr, int ele,int start, int end){
        if(start > end) return -1;

        int mid = (start+end)/2;

        if(arr[mid] < ele){
            return indexOfFirstOccurrenceInSortedArrayRecursive(arr,ele,mid+1,end);
        }else if(ele < arr[mid]){
            return indexOfFirstOccurrenceInSortedArrayRecursive(arr,ele,start,mid-1);
        }else{
            if(mid == 0 || arr[mid-1] != arr[mid]){
                return mid;
            }else{
                return indexOfFirstOccurrenceInSortedArrayRecursive(arr,ele,start,mid-1);
            }
        }
    }

    public static int indexOfFirstOccurrenceInSortedArray(int[] arr, int ele){
        int start = 0, end = arr.length-1;

        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]<ele) start = mid+1;
            else if(ele < arr[mid]) end = mid-1;
            else{ // this means arr[mid] == ele. we just need to go left for first occurrence
                if(mid == 0 || arr[mid-1] != arr[mid]) return mid;
                else{
                    end = mid-1;
                }

                // this work better because it skips again coming from mid,start,end calculations
//                while(mid > 0 && arr[mid-1] == arr[mid]) mid--;
//                return mid;
            }
        }

        return -1;
    }

    public static int indexOfLastOccurrenceInSortedArrayRecursive(int[] arr, int ele,int start, int end){
        if(start > end) return -1;

        int mid = (start+end)/2;

        if(arr[mid] < ele){
            return indexOfLastOccurrenceInSortedArrayRecursive(arr,ele,mid+1,end);
        }else if(ele < arr[mid]){
            return indexOfLastOccurrenceInSortedArrayRecursive(arr,ele,start,mid-1);
        }else{
            if(mid == arr.length-1 || arr[mid] != arr[mid+1]){
                return mid;
            }else{
                return indexOfLastOccurrenceInSortedArrayRecursive(arr,ele,mid+1,end);
            }
        }
    }

    public static int indexOfLastOccurrenceInSortedArray(int[] arr, int ele){
        int start = 0, end = arr.length-1;

        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]<ele) start = mid+1;
            else if(ele < arr[mid]) end = mid-1;
            else{ // this means arr[mid] == ele. we just need to go left for first occurrence
                if(mid == arr.length-1 || arr[mid] != arr[mid+1]) return mid;
                else{
                    start = mid+1;
                }
            }
        }

        return -1;
    }

    public static int countOfOccurrencesInSortedArray1(int[] arr,int ele){
        // BUT THIS IS NOT best because in both functions T = O(log n) + K (occurrences)
        // since using two functions we are doing finding element twice
        int first = indexOfFirstOccurrenceInSortedArray(arr,ele);
        if(first == -1) return 0;

        int last = indexOfLastOccurrenceInSortedArray(arr,ele);

        return last-first+1;
    }

    public static int countOfOccurrencesInSortedArray2(int[] arr,int ele){
        // in this searching done only once
        int idx = binarySearch(arr,ele);
        if(idx == -1) return 0;

        int cnt = 1;
        int j = idx-1;
        while(j >= 0){
            if(arr[j--] == ele)  cnt++;
        }

        j = idx+1;

        while(j < arr.length){
            if(arr[j++] == ele) cnt++;
        }

        return cnt;

    }

    public static int count1sInBinarySortedArray(int[] arr){
        int first = indexOfFirstOccurrenceInSortedArray(arr,1);
        if(first == -1) return 0;
        return arr.length-first;
    }

    public static int sqrtFloor(int n){
        int start = 1;
        int end = n; // end = n/2; // works as sqrt(n) <= n/2 for n >= 2; but for n == 1, n/2 = 0 so this fails.

        int ans = -1;

        while(start <= end){
            int mid = (start+end)/2;

            int sq = mid*mid;

            if(sq ==  n) return mid;
            else if(sq < n) {
                start = mid+1;
                ans = mid;
            }else{// means sq > n
                end = mid-1;
            }
        }

        return ans;
    }


    public static int searchInInfiniteSizedSortedArrayNaive(int[] arr,int ele){
        int i = 0;
        while(arr[i] <= ele){
            if(arr[i] == ele) return i; // rather than loop mode i can check this outside by keeping while condition just arr[i]<ele
            i++;
        }

        return -1;
    }

    public static int searchInInfiniteSizedSortedArray(int[] arr, int ele){

        // THIS ALGORITHM IS COMMMONLY KNOWN AS "UNBOUNDED BINARY SEARCH"

        if(arr[0] ==  ele) return 0;

        int end = 1;
        while(arr[end] < ele){ // if i use arr[end] <= ele here, i have to check this loop mode ---> if(arr[end] == ele) return end;
//            if(arr[end] == ele) return end;
            end = end*2;
        }

        // so i am checking it outside just once
        if(arr[end] == ele) return end;

        // now arr[high] > ele and arr[high/2] < ele

        // actually arr[end/2] < ele so starting from end/2 + 1; but end/2 also works any ways it's just searching from start to end.
        int start = end/2 + 1;

        while(start<=end){
            int mid = (start+end)/2;

            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) start = mid+1;
            else end = mid-1;
        }

        return -1;

    }

    public static int searchInSortedRotatedArray(int[] arr, int ele){
        // manoj logic ---> 3 logn while loops
        // sorted&Rotated array can be seen as two sorted arrays. first sorted and then second sorted array
        // max = maximum element in array
        // first sorted array 0 to maxPosition
        // second sorted array maxPosition+1 to arr.length-1;
        int start = 0;
        int end = arr.length-1;

        while(start<=end){
            int mid = (start+end)/2;
//            System.out.println(start+" "+mid+" "+end);

            if(arr[0] <= arr[mid]) start = mid+1;
            else end = mid-1;
        }

        int maxPos = Math.min(start,end); // start & end cross each other. min of them will be at maximum value of first sorted

        start = 0;
        end = maxPos;

        while(start<=end){
            int mid = (start+end)/2;

            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) start = mid+1;
            else end = mid-1;
        }

        // if not found in first sorted array we see in second sorted
        start = maxPos+1;
        end = arr.length-1;
        while(start <= end){
            int mid = (start+end)/2;

            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) start = mid+1;
            else end = mid-1;
        }

        // still not found so returning -1;
        return -1;
    }

    public static int searchInSortedRotatedArrayAuthor(int[] arr, int ele){
        // we always search in sorted part of the array as binary search works in sorted part only
        int start = 0;
        int end = arr.length-1;

        while(start<=end){
            int mid = (start + end)/2;
            if(arr[mid] == ele) return mid;

            if(arr[start] <= arr[mid]){
                // start to mid is sorted

                if(arr[start] <= ele && ele <= arr[mid]){
                    // element present in sorted part
                    end = mid-1;
                }
                else{
                    // element not present in sorted part
                    start = mid+1;
                }

            }else{
                // mid to end is sorted
                if(arr[mid] <= ele && ele <= arr[end]){
                    // element present in sorted part
                    start = mid+1;
                }
                else{
                    // element not present in sorted part
                    end = mid-1;
                }
            }
        }

        return -1;
    }


    public static int getPeakElementAuthor(int[] arr){
        // it returns one of the peak elements
        int start = 0;
        int end = arr.length-1;

        while(start<=end){
            int mid = (start+end)/2;

            if((mid == 0 || arr[mid-1] <= arr[mid]) && (mid == arr.length-1 || arr[mid] >= arr[mid+1])){
                return arr[mid];
            }else{
                if(mid > 0 && arr[mid-1] >= arr[mid]) end = mid-1;
                else start = mid+1;
            }
        }
        return -1;
    }

    public static int getPeakElementAuthorModified(int[] arr){
        // it returns one of the peak elements
        if(arr.length == 1) return arr[0];
        if(arr[0]>= arr[1]) return arr[0];
        if(arr[arr.length-1] >= arr[arr.length-2]) return arr[arr.length-1];

        // now binary search approch for all middle elements
        int start = 1;
        int end = arr.length-2;

        while(start<=end){
            int mid = (start+end)/2;

            if(arr[mid-1] <= arr[mid] && arr[mid] >= arr[mid+1]){
                return arr[mid];
            }else{
                if(arr[mid-1] >= arr[mid]) end = mid-1;
                else start = mid+1;
            }
        }
        return -1;
    }

    public static void findPairSumTwoPointer(int[] arr, int sum){
        int start = 0;
        int end = arr.length-1;

        while(start<end){
            if(arr[start] + arr[end] == sum){
                System.out.println(arr[start] + " " + arr[end]);
                return;
            }
            else if(arr[start] + arr[end] < sum){
                // smaller than sum so we go to next higher value by start++
                start++;
            }else{
                // greater than sum so we go to next smaller value by end--
                end--;
            }
        }

        System.out.println("No Such Pair");
    }

    public static void findTripletWithSumAuthor(int[] arr, int sum){
        // O(n^2)
        int n = arr.length;

        for(int i = 0; i<n-2; i++){
            int x = sum-arr[i]; // assuming arr[i] is one of the triplet element

            // two pointer approach to find a pair of sum x;
            int start = i+1;
            int end = n-1;

            while(start<end){
                if(arr[start] + arr[end] == x){
                    System.out.println("Triplet : " + arr[i] + " " + arr[start] + " " + arr[end]);
                    return;
                }
                else if(arr[start] + arr[end] < x){
                    // smaller than sum so we go to next higher value by start++
                    start++;
                }else{
                    // greater than sum so we go to next smaller value by end--
                    end--;
                }
            }
        }
        System.out.println("No Such Triplet");

    }

    public static void findTripletWithSumManoj(int[] arr, int sum){
        // O(nlogn)
        // not working for below input
        //int[] arr6 = {23, 42, 113, 130, 217, 230, 272, 338, 358, 358, 362, 424, 527, 557, 560, 566, 586, 618, 627, 697, 718, 870, 897, 933};

        int start = 0;
        int end = arr.length-1;

        while(start<end){
            if(arr[start] + arr[end] > sum){
                end--;
                continue;
            }

            int x = sum - (arr[start] + arr[end]);

            // now binary search for x between start and end
            int i = start + 1;
            int j = end-1;
            while(i<=j){
                int mid = (i+j)/2;

                if(arr[mid] == x){
                    System.out.println("Triplet : "+ arr[start] + " " + arr[mid] + " " + arr[end] );
                    return;
                }
                else if(arr[mid] < x) i = mid+1;
                else j = mid-1;
            }

            // so no such x if you come here
            if(arr[start]+arr[start+1]+arr[end] < sum) start++;
            else end--;

        }

        System.out.println("No Such Triplet");

    }

    public static double medianOfTwoSortedArrays(int[] arr1, int[] arr2){
        int n = arr1.length;
        int m = arr2.length;
        // assuming n<=m, we do binary search algo in smaller length array
        int start = 0;
        int end = n;

        while(start<=end){
            int mid1 = (start+end)/2;
            int mid2 = (n+m+1)/2 - mid1;

            int min1 = (mid1 == n) ? Integer.MAX_VALUE: arr1[mid1];
            int max1 = (mid1 == 0) ? Integer.MIN_VALUE: arr1[mid1-1];

            int min2 = (mid2 == m) ? Integer.MAX_VALUE: arr2[mid2];
            int max2 = (mid2 == 0) ? Integer.MIN_VALUE: arr2[mid2-1];

            System.out.println(min1+" "+max1+" "+min2+" "+max2);

            if(max1 <= min2 && max2 <= min1){
                if((n+m)%2 == 0) return (double) (Math.max(max1,max2) + Math.min(min1,min2))/2;
                else return (double) Math.max(max1,max2);
            }
            else if(max1 > min2) end = mid1-1;
            else start = mid1 + 1;
        }
        return -1;
    }


    public static int repeatingElementWithSpace(int[] arr){
        boolean[] flag = new boolean[arr.length];
        for(int x: arr){
            if(flag[x]) return x;
            flag[x] = true;
        }
        return -1;
    }

    public static int repeatingElement(int[] arr){
        int slow = arr[0] + 1;
        int fast = arr[0] + 1;

        do{
            slow = arr[slow] + 1;
            fast = arr[arr[fast] + 1] + 1;
        }while(slow != fast);

        slow = arr[0] + 1;
        while(slow != fast){
            fast = arr[fast] + 1;
            slow = arr[slow] + 1;
        }

        return slow-1;
    }

    public static int allocateMinimumPages(int[] arr, int k){
        int arrMax = arr[0];
        int arrSum = arr[0];
        for(int i = 1; i<arr.length; i++){
            arrSum += arr[i];
            if(arr[i] > arrMax) arrMax = arr[i];
        }

        int start = arrMax;
        int end = arrSum;
        int res = 0;

        while (start <= end){
            int mid = (start + end)/2;
            int currSum = 0;
            int cnt = 1; //atleast 1 in possible
            for(int x: arr){
                currSum += x;

                if(currSum > mid){
                    cnt++;
                    currSum = x;
                }
            }
            if(cnt <= k){
                // feasible solution
                res = mid;

                end = mid-1;
            }else{
                start = mid + 1;
            }
        }

        return res;
    }

    public static int ternarySearch(int[] arr, int ele){
        int start = 0;
        int end = arr.length-1;

        while(start<=end){
            int mid1 = start + (end-start)/3;
            int mid2 = end - (end-start)/3;


            if(arr[mid1] == ele) return mid1; // at mid1
            else if(arr[mid2] == ele) return mid2; // at mid2
            else if(ele < arr[mid1]) end = mid1 - 1; // left part < mid1
            else if(ele > arr[mid2]) start = mid2 + 1; // right part > mid2
            else{ //  middle part mid1 < middlepart < mid2
                // in middle part of current three parts
                start = mid1 + 1;
                end = mid2 -1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        int[] arr1 = {10,20,30,40,50,60,70};
        System.out.println(binarySearch(arr1,30));
        System.out.println(binarySearchRecursive(arr1,30,0,arr1.length-1));

        int[] arr2 = {10,10,10,20,20,20,30,30,40,50};
        System.out.println(indexOfFirstOccurrenceInSortedArrayRecursive(arr2,20,0,arr2.length-1));
        System.out.println(indexOfFirstOccurrenceInSortedArray(arr2,20));

        System.out.println(indexOfLastOccurrenceInSortedArrayRecursive(arr2,20,0,arr2.length-1));
        System.out.println(indexOfLastOccurrenceInSortedArray(arr2,20));

        System.out.println(countOfOccurrencesInSortedArray1(arr2,20));
        System.out.println(countOfOccurrencesInSortedArray2(arr2,20));

        int[] binaryArr = {0,0,0,1,1,1,1};
        System.out.println(count1sInBinarySortedArray(binaryArr));

        System.out.println(sqrtFloor(26));

        int[] bigArray = new int[100000];
        for(int i = 0; i<bigArray.length; i++){
            bigArray[i] = i*2;
        }
        System.out.println(searchInInfiniteSizedSortedArrayNaive(bigArray,25));
        System.out.println(searchInInfiniteSizedSortedArray(bigArray,20));

        int[] sortedRotated = {1,0,1,1,1};
        System.out.println(searchInSortedRotatedArray(sortedRotated,20));
        System.out.println(searchInSortedRotatedArrayAuthor(sortedRotated,20));

        int[] arr3 = {1,6,3,5,9};
        System.out.println(getPeakElementAuthor(arr3));
        System.out.println(getPeakElementAuthorModified(arr3));

        int[] arr4 = {2,4,8,9,11,12,20,30};
        findPairSumTwoPointer(arr4,23);

        int[] arr5 = {2,3,4,7,8,9,20,40};
        findTripletWithSumAuthor(arr5,36);
        findTripletWithSumManoj(arr5,36); // working for this but not working for all test cases

        int[] arr6 = {23, 42, 113, 130, 217, 230, 272, 338, 358, 358, 362, 424, 527, 557, 560, 566, 586, 618, 627, 697, 718, 870, 897, 933};
        findTripletWithSumManoj(arr6,986); // not working
        findTripletWithSumAuthor(arr6,986);

        int[] a = {10,20,30};
        int[] b = {5,15,25,35,45};
        System.out.println(medianOfTwoSortedArrays(a,b));

        int[] arr7 = {0,2,1,3,5,4,6,2};
        System.out.println(repeatingElementWithSpace(arr7));
        System.out.println(repeatingElement(arr7));

        int[] arr8 = {10,20,10,30};
        System.out.println(allocateMinimumPages(arr8,2));

        int[] arr9 = {2,3,5,7,9,11,34,67,99};
        System.out.println(ternarySearch(arr9,11));
    }
}
