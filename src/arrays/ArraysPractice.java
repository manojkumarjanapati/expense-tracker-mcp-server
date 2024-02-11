package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArraysPractice {

    public static int getLargest(int[] arr){
        int res = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[res]) res = i;
        }

        return arr[res];
    }

    public static int secondLargest(int[] arr){
        int secondMaxIdx = -1;
        int maxIdx = 0;
        for(int i = 1; i<arr.length; i++){
            if(arr[i] > arr[maxIdx]){
                secondMaxIdx = maxIdx;
                maxIdx = i;
            }
            else if(arr[i] != arr[maxIdx]){
                if(secondMaxIdx == -1 || arr[i] > arr[secondMaxIdx]){
                    secondMaxIdx = i;
                }
            }
        }


        return (secondMaxIdx == -1)? -1 : arr[secondMaxIdx];
    }

    public static boolean isSorted(int[] arr){
        for(int i = 1; i<arr.length; i++){
            if(arr[i] < arr[i-1]) return false;
        }
        return true;
    }

    public static void reverseArrayInPlace(int[] arr){
        int n = arr.length;

        // Way 1
//        int low = 0;
//        int high = n-1;
//        while(low < high){
//            arr[low] = arr[low] ^ arr[high];
//            arr[high] = arr[low] ^ arr[high];
//            arr[low] = arr[low] ^ arr[high];
//            low++;
//            high--;
//        }

        // Way 2
        for(int i = 0; i<n/2; i++){
            // swapping arr[i] and arr[n-i-1]
            arr[i] = arr[i]^arr[n-i-1];
            arr[n-i-1] = arr[i]^arr[n-i-1];
            arr[i] = arr[i]^arr[n-i-1];
        }
    }

    public static int[] removeDuplicates(int[] arr, int n){
        int i = 0;
        for(int j = 1; j<n; j++){
            if(arr[j] > arr[i]){
                i++;
                arr[i] = arr[j];
            }
        }

        int[] ans = new int[i+1];
        for(;i>=0;i--){
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void moveAllZerosToEnd(int[] arr){
        int i = 0;
        for(int j = 0; j<arr.length; j++){
            if(arr[j] != 0){
                // this is wrong. swapping using xor when i == j will make both arr[i] and arr[j] as 0;
//                arr[i] = arr[i]^arr[j];
//                arr[j] = arr[i]^arr[j];
//                arr[i] = arr[i]^arr[j];

                // simply doing
                if(i != j){
                    // swapping only when i!=j
                    arr[i] = arr[j];
                    arr[j] = 0;
                }

                i++;
            }
        }
    }

    public static void leftRotateBy1(int[] arr){
        int tmp = arr[0];
        for(int i = 1; i<arr.length; i++){
            arr[i-1] = arr[i];
        }
        arr[arr.length-1] = tmp;
    }

    public static void reverseSubArray(int[] arr, int start, int end){
        while(start < end){
            arr[start] = arr[start] ^ arr[end];
            arr[end] = arr[start] ^ arr[end];
            arr[start] = arr[start] ^ arr[end];
            start++;
            end--;
        }
    }

    public static void leftRotateArrayByK(int[] arr, int k){

        k = k%arr.length; // to deal with k > arr.length

        reverseSubArray(arr,0,k-1); // reverse left portion of k
        reverseSubArray(arr,k,arr.length-1); // reverse right portion of k
        reverseSubArray(arr,0,arr.length-1); // reverse whole array
    }

    public static void leftRotateArrayByKusingIndex(int[] arr, int k){
        /*
        * i am starting with index = 0. now i am finding where it has to go after k left rotations.
        * i am using tmp to store current arr element to be placed in target.
        * i am swapping tmp with arr[target]. and now i am making index = target.
        * now i find new target to swap with tmp
        * */
        int n = arr.length;
        k = k%n; // to deal with k > arr.length

        int num = n;
        int idx=0;
        int tmp = arr[idx];
        int target;
        while(num-- > 0){
            target = (idx-k >= 0)? idx-k : idx-k+n;
//            System.out.println(idx + " " + target);
            tmp = tmp^arr[target];
            arr[target] = tmp^arr[target];
            tmp = tmp^arr[target];
            idx = target;
//            System.out.println(Arrays.toString(arr));
        }
    }

    public static void leftRotateArrayByKusingIndex2(int[] arr, int k){
        // Not Working - if possible modify later manoj
        System.out.println("not working manoj");
        int n = arr.length;
        k = k%n; // to deal with k > arr.length

        for(int i = 0;i<n;i++){
            int target = (i-k >= 0)? i-k : i-k+n;
            arr[i] = arr[i]^arr[target];
            arr[target] = arr[i]^arr[target];
            arr[i] = arr[i]^arr[target];
//            System.out.println(Arrays.toString(arr));
        }
    }


    public static ArrayList<Integer> leadersInArrays(int[] arr){
        int n = arr.length;

        ArrayList<Integer> leaders = new ArrayList<>();
        int currMax = arr[n-1];
        leaders.add(currMax);
        for(int i = n-2; i>=0; i--){
            if(arr[i] > currMax){
                currMax = arr[i];
                leaders.add(currMax);
            }
        }

        Collections.reverse(leaders);

        return leaders;
    }


    public static int maxDiff(int[] arr){
        /* PROBLEM STATEMENT
        * find maximum value of arr[j]-arr[i] such that j > i
        * */
        int n = arr.length;

        int minSoFar = arr[0];
        int maxDiff = arr[1]-minSoFar;

        for(int i = 1; i<n; i++){// should start from 1 because arr[1] may be minimum than arr[0]
            if(arr[i]-minSoFar > maxDiff) maxDiff = arr[i]-minSoFar; // checking currentElement-minSofar
            if(arr[i] < minSoFar) minSoFar = arr[i]; // updating minSoFar
        }

        return maxDiff;

    }

    public static void frequenciesInSortedArray(int[] arr){
        int n = arr.length;
        int curr = arr[0];
        int cnt = 1;
        for(int i = 1; i<n; i++){
            if(arr[i] == curr) cnt++;
            else{
                System.out.println(curr + " : " + cnt);
                curr = arr[i];
                cnt = 1;
            }
        }
        System.out.println(curr + " : " + cnt);

    }

    public static void frequenciesInSortedArray2(int[] arr){
        // gfg author solution
        int n = arr.length;

        int i = 1;
        int freq = 1;

        while(i < n){
            while(i < n && arr[i] == arr[i-1]){
                freq++;
                i++;
            }
            System.out.println(arr[i-1]+" : "+freq);
            i++;
            freq = 1;
        }

        if(n == 1 || arr[n-1] != arr[n-2]){
            System.out.println(arr[n-1]+" : "+1);
        }
    }

    public static int stockBuyAndSell(int[] arr){
        /*
        * profit = buy at low value and then sell at high value
        * */

        int profit = 0;

        for(int i=0;i<arr.length-1;i++){
            // i am just buying at arr[i] when arr[i+1] > arr[i] and selling at arr[i+1]
            // so throughout array i sum all profits to get maximum profit possible
            // assume {a,b,c} and a<c<b, so buying at a then selling b and then again buying at b and selling c is equal to c-a.
            // so i buy stock only if next day i am getting profit.
            // i am just ignoring arr[i] >= arr[i+1]

            // just finding increasing rate in array as they result in profits
            // adding all increasing rates
            if(arr[i] < arr[i+1]) profit += arr[i+1] - arr[i];
        }

        return profit;
    }

    public static int trappingRainWater(int[] walls){
        System.out.println("this will fail for walls{4,1,2,3}");
        int currMaxWall = walls[0]; // taking currMaxWall as walls[0]
        int currWater = 0; // currWater to calculate water stored between currMaxWall and next currMaxWall
        int waterSum = 0; // to sum all currWater
        int n = walls.length;
        for(int i = 1;i<n;i++){
            if(walls[i] < currMaxWall){
                // while walls[i] < currMaxWall , i am just adding units of water possible to store if there exists a next currMaxWall
                // i have currMaxWall on left side, but i am considering this currWater only when i find a next currMaxWall.
                currWater += currMaxWall - walls[i];
            }
            else{ // else part for new currMaxWall
                waterSum = currWater; // considering currWater into waterSum
                currWater = 0; // restarting currWater
                currMaxWall = walls[i]; // updating currMaxWall to next&new currMaxWall
            }
        }

        return waterSum;
    }


    public static int trappingRainWater2(int[] walls){
        /*  LOGIC
        * for every index i, it contributes to water storage when it has a bigger wall on it's both left N right side
        * so, by precomputing leftmax and rightmax for an index i, we can calc the contribution by formula
        * for ith index, contribution: min(leftmax,rightmax)-walls[i]
        * if wall[i] itself is either leftmax or rightmax then contibution formula  return 0.
        * */

        int n = walls.length;

        // leftMax and rightMax to precompute leftMax and rightMax for current i
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        int max = walls[0];

        for(int i = 1; i<n; i++){
            if(walls[i] > max) max = walls[i]; // update when new max encountered

            leftMax[i] = max;
        }

        max = walls[n-1];
        for(int i = n-2; i>=0; i--){
            if(walls[i] > max) max = walls[i]; // update when new max encountered

            rightMax[i] = max;
        }

        int res = 0;
        for(int i = 1; i<n-1; i++){ // excluding leftmost and rightmost indexes. as they won't store water by having left wall and right walls to  them.
            res += Math.min(leftMax[i],rightMax[i]) - walls[i];
        }

        return res;
    }


    public static int maximumConsecutive1s(int[] arr){

        int max = 0; // i didn't use Integer.MIN_VALUE because in this case 0 is the lowest possible answer. for {0,0,0} all 0s array

        int cnt = 0; // counter

        for(int x: arr){
            if(x == 0){ // 0 encountered
                if(cnt > max) max = cnt; // updating max if cnt of previous consecutive 1s before this zero is greater than max

                cnt = 0; // resetting cnt to 0
            }
            else{
                cnt++; // counting 1s
            }
        }

        if(cnt > max) max = cnt; // dealing with fencepost bug

        return max;

    }

    public static int maximumConsecutive1sV2(int[] arr){
        // this author solution won't have fencepost bug.
        int max = 0;
        int cnt = 0;
        for(int x: arr){
            if( x == 0){
                cnt = 0;
            }
            else{
                cnt++;
                max = Math.max(max,cnt); // updating max continuously for all 1s encounters.
                // it will check for last element of array so thus eliminates fencepost bug
            }
        }

        return max;
    }

    public static int maximumSumOfSubarray(int[] arr){
        int[] maxSubArraySums = new int[arr.length];
        maxSubArraySums[0] = arr[0];
        int ans = arr[0];
        for(int i = 1; i<arr.length;  i++){
//            if(maxSubArraySums[i-1] > 0){
//                maxSubArraySums[i] = maxSubArraySums[i-1] + arr[i];
//            }else{
//                maxSubArraySums[i] = arr[i];
//            }

            // for every ith element we are deciding whether to add to previous subarraysum or start new subarraysum.
            // if previous subarraysum > 0, then obviously adding it to current element makes subarraysum bigger.
            // if previous subarraysum < 0, then rather than considering negative sum subarray, we can starting new subarray sum with current element
            maxSubArraySums[i] = Math.max(maxSubArraySums[i-1]+arr[i], arr[i]);


            if(ans < maxSubArraySums[i]) ans = maxSubArraySums[i]; // max of all those subarraysums is the answer
        }

        return ans;
    }

    public static int maximumSumOfSubarrayAuthor(int[] arr){
        // What is kadane's algorithm

        int ans = arr[0];
        int prevMax = arr[0];

        for(int i=1; i<arr.length; i++){
            prevMax = Math.max(prevMax+arr[i],arr[i]);

            ans = Math.max(ans,prevMax);
        }

        return ans;
    }

    public static int maximumSumOfSubarrayAuthor2(int[] arr){
        // What is kadane's algorithm

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for(int i=0; i<arr.length; i++){
            maxEndingHere += arr[i];
            if(maxSoFar < maxEndingHere) maxSoFar = maxEndingHere;

            if(maxEndingHere < 0) maxEndingHere = 0;
        }

        return maxSoFar;
    }

    public static int maxLengthEvenOddSubarray(int[] arr){
        int ans = 0;
        int cnt = 0;
        for(int i = 0; i<arr.length-1; i++){
            // even ^ odd = odd
            if(((arr[i]^arr[i+1])&1) == 1){ // if ith and (i+1)th are even-odd pair then then their xor will have 1st bit set.
                cnt++;
                ans = Math.max(ans,cnt); // no fence post bug now.
            }
            else{
//                ans = Math.max(ans,cnt); // if we write in else block, and if last element traversal goes to if block then there is fencepost bug.
                cnt = 0;
            }
        }

//        ans = Math.max(ans,cnt); // in case of fencepost bug.

        return ans + 1; // length is +1 to all those consecutive even-odd pairs.
    }

    public static int maximumCircularSumOfSubarrayAuthor(int[] arr){
        /* LOGIC
        *
        * FIND MAXIMUM SUBARRAY SUM IN ARR
        *
        * FIND MINIMUM SUBARRAY SUM IN ARR
        * SUBTRACT MINIMUM SUBARRAY SUM FROM TOTAL ARRAY SUM = CIRCULAR SUBARRAY SUM
        *
        * FINAL ANSWER IS MAX(MAXIMUM SUBARRAY SUM, CIRCULAR SUBARRAY SUM)
        *
        * */

        int maxSubArrSum = maximumSumOfSubarrayAuthor(arr);

        if(maxSubArrSum < 0) return maxSubArrSum; // if maxSumArrSum < 0, which means array has all negatives then this is the answer

        int arrSum = 0;
        for(int i = 0; i<arr.length; i++){
            arrSum += arr[i];
            arr[i] = -arr[i]; // inverting sign so that instead of finding minimum subarray sum, i will find maximum subarray sum of inverted array and put -sign to answer
        }

        int minSumArrSum = -1 * maximumSumOfSubarrayAuthor(arr);

        int circularSubArrSum = arrSum - minSumArrSum;

        return Math.max(maxSubArrSum,circularSubArrSum);
    }

    public static int majorityElementOfArray(int[] arr){
        /* MAJORITY ELEMENT  = WHICH OCCURS MORE THAN N/2 TIMES WHERE N IS LENGTH OF ARR
        * MOORE'S VOTING ALGORITHM
        * */

        //  finding any index of majority element
        int majorityIdx = 0;
        int cnt = 1;
        for(int i = 1; i<arr.length; i++){
            if(arr[i] == arr[majorityIdx]){
                cnt++;
            }
            else{
                cnt--;
            }

            if(cnt == 0){ // so by now all votes cancelled each other
                majorityIdx = i;
                cnt = 1;
            }
        }

        // by now if there exists a majority element then definitely majorityIdx will hold any one of it's indexes.
        // if there doesn't exist a mojority element then majorityIdx will hold next idx of all votes cancelled.
        // but it will be known that it's not majority in this below checking of counter

        // checking  if majorityIdx element occured more than n/2 times
        int counter = 0;
        for(int i = 0; i<arr.length; i++){
            if(arr[i] == arr[majorityIdx]) counter++;
        }

        if(counter > arr.length/2) return arr[majorityIdx];

        return -1;
    }

    public static void minimumFlipsToMakeArrEqual(int[] arr){
        int[] fromTo = new int[2];
        int idx = 0;
        for(int i = 1;i<arr.length; i++){
            if(arr[i] != arr[i-1]){
                fromTo[idx] = i - idx; // my trick :) -> if idx = 0, from is correct, if idx = 1 , then for 'to' position i-idx gives i-1, as idx = 1;
                idx = idx^1;

                if(idx == 0){
                    System.out.println("from : "+fromTo[0]+" -> to : "+fromTo[1]);
                }
            }
        }

        // fence post bug
        if(idx == 1){
            System.out.println("from : "+fromTo[0]+" -> to : "+(arr.length-1));
        }
    }

    public static void minimumFlipsToMakeArrEqualAuthor(int[] arr){
        for(int i = 1; i<arr.length; i++){
            if(arr[i] != arr[i-1]){
                if(arr[i] != arr[0]){// arr[0] is definitely first group
                    System.out.print("from "+i+" to ");
                }else{
                    System.out.print(i-1);
                    System.out.println();

                }
            }
        }

        if(arr[arr.length-1] != arr[0]){
            System.out.print(arr.length-1);
        }

    }

    public static int maximumSumOfKlengthSubArray(int[] arr, int k){

        // window sliding technique

        int windowSum = 0;
        int i = 0;
        for(i = 0; i<k; i++){
            windowSum += arr[i]; // calculating 1st window sum
        }

        int ans = windowSum;

        for(;i<arr.length;i++){
            windowSum += (arr[i] - arr[i-k]);
            ans = Math.max(windowSum,ans);
        }

        return ans;


    }

    public static boolean findSubArrayWithGivenSum(int[] arr, int sum){
        int start = 0, end = 0, currSum = 0;

        while(end < arr.length){
            if(currSum < sum){
                currSum += arr[end];
                end++;
            }
            else if(currSum > sum){
                currSum -= arr[start];
                start++;
            }
            else{
                System.out.println("Sub Array start: "+start+" end: "+ (end-1));
                return true;
            }
        }

        return false;
    }


    public static boolean findSubArrayWithGivenSumAuthor(int[] arr, int sum){
        int start = 0;
        int  currSum=0;
        for(int end = 0; end<arr.length; end++ ){
            currSum += arr[end];
            while(currSum > sum){
                currSum -= arr[start];
                start++;
            }

            if(currSum == sum){
                System.out.println("Sub Array start: "+start+" end: "+ end);
                return true;
            }
        }

        return false;
    }

    public static void getSum(int[] arr, int[][] queries){
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for(int i = 1; i<arr.length; i++){
            prefixSum[i] = prefixSum[i-1]+arr[i];
        }

        int l,r,ans;

        for(int i = 0; i<queries.length; i++){
            l = queries[i][0];
            r = queries[i][1];

            ans = (l > 0) ? prefixSum[r] - prefixSum[l-1] : prefixSum[r];

            System.out.println("sum from " + l +" to "+ r + " is : "+ans);
        }
    }

    public static void getWeightedSum(int[] arr, int[][] queries){
        int[] prefixSum = new int[arr.length];
        int[] prefixWeightedSum = new int[arr.length];

        prefixSum[0] = arr[0];
        prefixWeightedSum[0] = 0*arr[0];
        for(int i = 1; i<arr.length; i++){
            prefixSum[i] = prefixSum[i-1]+arr[i];

            prefixWeightedSum[i] = prefixWeightedSum[i-1] + (i * arr[i]);
        }

        int l,r,ans;

        for(int i = 0; i<queries.length; i++){
            l = queries[i][0];
            r = queries[i][1];

            if(l>0){
                ans = (prefixWeightedSum[r] - prefixWeightedSum[l-1]) - (l-1) * (prefixSum[r] - prefixSum[l-1]);
            }else{
                ans = prefixWeightedSum[r] - (l-1) * (prefixSum[r]);
            }

            System.out.println("sum from " + l +" to "+ r + " is : "+ans);
        }
    }

    public static int findEquilibriumPoint(int[] arr){
        int arrSum = 0;

        for(int x: arr){
            arrSum += x;
        }

        int currSum = 0;

        int arrSumWithoutIthElement;

        for(int i = 0; i<arr.length; i++){
            arrSumWithoutIthElement = arrSum - arr[i];

            if(currSum == arrSumWithoutIthElement - currSum) return i; // i is equilibrium point

            currSum += arr[i]; // i is not equilibrium point so add arr[i] to currSum
        }

        return -1;
    }

    public static int findEquilibriumPointAuthor(int[] arr){
        int rightSum = 0;

        for(int x: arr){
            rightSum += x;
        }

        int leftSum = 0;

        for(int i = 0; i<arr.length; i++){
            rightSum -= arr[i];
            if(leftSum == rightSum) return i;
            leftSum += arr[i];
        }

        return -1;
    }

    public static boolean possibilityOfThreeEqualPartitions(int[] arr){
        int sum = 0;
        for(int x: arr){
            sum += x;
        }
        if(sum % 3 != 0) return  false;

        int partitionSum = sum/3;

        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];

        int cnt = 1;

        for(int i = 1; i<arr.length; i++){
            if(prefixSum[i-1] == partitionSum*cnt) cnt++ ;
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }

        return (partitionSum*cnt == sum);
    }

    public static boolean possibilityOfThreeEqualPartitionsWay2(int[] arr){
        int sum = 0;
        for(int x: arr){
            sum += x;
        }

        if(sum % 3 != 0) return  false;

        int partitionSum = sum/3;

        int currSum = 0;

        int cnt = 1;

        for(int i = 0; i<arr.length; i++){
            if(currSum == partitionSum*cnt) cnt++;
            currSum += arr[i];
        }

        return (partitionSum*cnt == sum);
    }

    public static int maximumAppearingFirstElementInGivenRanges(int[] left, int[] right){
        int n = left.length;

        int[] leftRightMarkings = new int[100];
        for(int i = 0; i<n; i++){
            leftRightMarkings[left[i]]++;
            leftRightMarkings[right[i]+1]--;
        }

        int[] prefixSumForFrequency = new int[leftRightMarkings.length];
        prefixSumForFrequency[0] = leftRightMarkings[0];
        int maxIdx = 0;
        for(int i = 1; i<leftRightMarkings.length; i++){
            prefixSumForFrequency[i] =prefixSumForFrequency[i-1] + leftRightMarkings[i];
            if(prefixSumForFrequency[i] > prefixSumForFrequency[maxIdx]) maxIdx = i;
        }

        // without prefixSum array
//        int currSum = leftRightMarkings[0];
//        int maxidx = 0;
//        int maxSum = leftRightMarkings[maxidx];
//        for(int i = 1; i<leftRightMarkings.length; i++){
//            currSum += leftRightMarkings[i];
//            if(currSum > maxSum){
//                maxidx = i;
//                maxSum = currSum;
//            }
//        }

        return maxIdx;
    }

    public static int maximumAppearingFirstElementInGivenRangesAuthor(int[] left, int[] right){
        int n = left.length;

        int[] freq = new int[101]; // author is using freq for both left&right markings and also for frequency prefixsum;

        for(int i = 0; i<n; i++){
            freq[left[i]]++;
            freq[right[i]+1]--;
        }

        int maxIdx = 0;
        for(int i = 1; i<freq.length; i++){
            freq[i] = freq[i-1]+freq[i];
            if(freq[i] > freq[maxIdx]) maxIdx = i;
        }

        return maxIdx;
    }

    public static ArrayList<Integer> maximumAppearingAllElementsInGivenRanges(int[] left, int[] right){
        int n = left.length;

        int[] leftRightMarkings = new int[100];
        for(int i = 0; i<n; i++){
            leftRightMarkings[left[i]]++;
            leftRightMarkings[right[i]+1]--;
        }

        int[] prefixSumForFrequency = new int[leftRightMarkings.length];
        prefixSumForFrequency[0] = leftRightMarkings[0];
        int maxFreq = prefixSumForFrequency[0];
        for(int i = 1; i<leftRightMarkings.length; i++){
            prefixSumForFrequency[i] =prefixSumForFrequency[i-1] + leftRightMarkings[i];

            if(prefixSumForFrequency[i] > maxFreq) maxFreq = prefixSumForFrequency[i];// finding maxFreq

        }

        ArrayList<Integer> maximumAppearingElements = new ArrayList<>();

        for(int i = 0; i<prefixSumForFrequency.length; i++){
            if(prefixSumForFrequency[i] == maxFreq) maximumAppearingElements.add(i);
        }

        return maximumAppearingElements;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2){
        int n = arr1.length;
        int m = arr2.length;
        int[] mergedArray = new int[n+m];

        int i = 0,j = 0,k = 0;
        while(i < n && j<m){
            if(arr1[i]<arr2[j]){
                mergedArray[k++] = arr1[i++];
            }else{
                mergedArray[k++] = arr2[j++];
            }
        }

        while(i<n) mergedArray[k++] = arr1[i++];

        while(j<m) mergedArray[k++] = arr2[j++];

        return mergedArray;
    }

    public static void countFrequencyModifyArrayInPlace(int[] arr, int N, int P){
        int i = 0;
        while(i < N){
            System.out.println(Arrays.toString(arr));
            if(arr[i] > 0){
                // element present to calc freq
                int idx = arr[i] - 1;
                if(arr[idx] >= 0){
                    arr[i] = arr[idx];
                    arr[idx] = -1;
                }else{
                    arr[i] = 0;
                    arr[idx]--;
                    i++;
                }
            }else{
                i++;
            }
        }

        for(i = 0; i<N; i++){
            arr[i] *= -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(getLargest(new int[]{5,3,6,32,6,2,33,2,43}));
        System.out.println(secondLargest(new int[]{5,3,6,32,6,2,33,2,43}));

        System.out.println(isSorted(new int[]{1,2,3,4,5,4}));

        int[] arr = {1,2,3,4,5,6};
        reverseArrayInPlace(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {1,1,1,2,2,3,3,3,4};
        System.out.println(Arrays.toString(removeDuplicates(arr2,arr2.length)));

        int[] arr3 = {1,2,0,3,0,0,4,5,0,6};
        moveAllZerosToEnd(arr3);
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = {1,2,3,4,5};
        leftRotateBy1(arr4);
        System.out.println(Arrays.toString(arr4));

        int[] arr5 = {1,2,3,4,5,6,7,8,9};
        leftRotateArrayByK(arr5,5);
        System.out.println(Arrays.toString(arr5));

        int[] arr6 = {1,2,3,4,5,6,7,8,9};
        leftRotateArrayByKusingIndex(arr6,5);
        System.out.println(Arrays.toString(arr6));

        int[] arr7 = {1,2,3,4,5,6,7,8,9};
        leftRotateArrayByKusingIndex2(arr7,5);
        System.out.println(Arrays.toString(arr7)+" wrong not working try later");

        int[] arr8 = {7,10,11,4,10,6,5,2};
        System.out.println(leadersInArrays(arr8));

        int[] arr9 = {2,3,10,6,4,8,1};
        System.out.println(maxDiff(arr9));

        int[] arr10 = {10,10,10,10};
        frequenciesInSortedArray(arr10);
        frequenciesInSortedArray2(arr10);

        int[] stocks = {1,5,3,1,2,8};
        System.out.println(stockBuyAndSell(stocks));

        int[] walls = {4,1,2,3};
        System.out.println(trappingRainWater(walls));
        System.out.println(trappingRainWater2(walls));

        int[] binaryArr = {0,0,1,1,0,1,0};
        System.out.println(maximumConsecutive1s(binaryArr));
        System.out.println(maximumConsecutive1sV2(binaryArr));

        int[] arr11 = {3,-4,5,6,-8,7};
        System.out.println(maximumSumOfSubarray(arr11));
        System.out.println(maximumSumOfSubarrayAuthor(arr11));
        System.out.println(maximumSumOfSubarrayAuthor2(arr11));

        int[] evenOdd = {10,12,14,7,8};
        System.out.println(maxLengthEvenOddSubarray(evenOdd));

        int[] arr12 = {3,-4,5,6,-8,7};
        System.out.println(maximumCircularSumOfSubarrayAuthor(arr12));

        int[] findMajority = {8,8,6,6,6,4};
        System.out.println(majorityElementOfArray(findMajority));

        int[] binaryArr2 = {1,1,0,0,0,1,1,1,0,0,1};
        int[] binaryArr3 = {0,0,1,1,1,0,0,0,0,1,1};
        int[] binaryArr4 = {1,1,1};
        minimumFlipsToMakeArrEqual(binaryArr2);
        minimumFlipsToMakeArrEqualAuthor(binaryArr2);

        minimumFlipsToMakeArrEqual(binaryArr3);
        minimumFlipsToMakeArrEqual(binaryArr4);

        int[] arr13 = {1,8,30,-5,20,7};
        System.out.println(maximumSumOfKlengthSubArray(arr13,4));

        int[] arr14 = {1,4,20,3,10,5};
        System.out.println(findSubArrayWithGivenSum(arr14,33));
        System.out.println(findSubArrayWithGivenSumAuthor(arr14,33));

        int[] arr15 = {2,8,3,9,6,5,4};
        int[][] queries = {{1,3},{0,4},{3,6}};
        getSum(arr15,queries);
        getWeightedSum(arr15,queries);


        int[] arr16 = {4,2,2};
        System.out.println(findEquilibriumPoint(arr16));
        System.out.println(findEquilibriumPointAuthor(arr16));

        int[] arr17 = {5,2,6,1,1,1,1,4};
        System.out.println(possibilityOfThreeEqualPartitions(arr17));
        System.out.println(possibilityOfThreeEqualPartitionsWay2(arr17));

        int[] left = {1,2};
        int[] right = {5,4};
        System.out.println("first element: " + maximumAppearingFirstElementInGivenRanges(left,right));
        System.out.println("first element Author Solution: " + maximumAppearingFirstElementInGivenRangesAuthor(left,right));
        System.out.println("All elements: " + maximumAppearingAllElementsInGivenRanges(left,right));

        int[] a = {1,3,4,6};
        int[] b = {2,5,7,8};

        System.out.println(Arrays.toString(mergeSortedArrays(a,b)));

        int[] arr18 = {2,3,2,3,5};
        int N = 5;
        int P = 5;
        countFrequencyModifyArrayInPlace(arr18,N,P);
        System.out.println(Arrays.toString(arr18));


    }
}
