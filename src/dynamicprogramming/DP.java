package dynamicprogramming;

import java.util.Arrays;

public class DP {

    public static int countWaysRecursion(int x, int sum){
        if(sum == 0) return 1; // sum is possible to so counting +1
        if(sum < 0 || x == 0) return 0; // returning 0 as either sum didn't reduce to 0 but sum has become negative or current number reached 0 which can't contribute sum
        return countWaysRecursion(x,sum-x) + countWaysRecursion(x-1,sum); // Unbounded Knapsack
    }

    public static void testCountWaysForSumOfN(){
        System.out.println("testCountWaysForSumOfN");
        System.out.println(countWaysRecursion(4,5));
    }

    public static int minimizeMaximumPagesAllocatedRecursion1(int[] pages, int start, int end, int students){
        if(students == 1){
            int pageCnt = 0;
            for(int x = start; x <=end; x++) pageCnt += pages[x];
            return pageCnt;
        }
        students--; // -1 becoz doing partition for current student so recursion call goes for remaining students
        int res = Integer.MAX_VALUE;
        for(int i = start; i+students<=end; i++){
            res = Math.min(res,Math.max(minimizeMaximumPagesAllocatedRecursion1(pages,start,i,1),
                    minimizeMaximumPagesAllocatedRecursion1(pages,i+1,end,students)));
        }
        return res;
    }

    public static int minimizeMaximumPagesAllocatedRecursion2(int[] pages, int n, int students){
        if(students == 1){
            int pageCnt = 0;
            for(int x = 0; x < n; x++) pageCnt += pages[x];
            return pageCnt;
        }

        students--; // -1 becoz doing partition for current student so recursion call goes for remaining students
        int res = Integer.MAX_VALUE;
        int currStudentPageCnt = 0;
        for(int i = n-1; i-students>=0; i--){ // My solution is better it also reduces unnecessary recursion calls -> makes sure students <= n while doing recursion call.
            currStudentPageCnt += pages[i];
            res = Math.min(res,Math.max(currStudentPageCnt,
                    minimizeMaximumPagesAllocatedRecursion2(pages,i,students)));
        }
        return res;
    }

    public static int minimizeMaximumPagesAllocatedRecursion3(int[] pages, int n, int students){
        if(students == 1){
            int pageCnt = 0;
            for(int x = 0; x < n; x++) pageCnt += pages[x];
            return pageCnt;
        }

        if(n == 1) return pages[0];


        int res = Integer.MAX_VALUE;
        int currStudentPageCnt = 0;
        for(int i = n-1; i > 0; i--){
            currStudentPageCnt += pages[i];
            res = Math.min(res,Math.max(currStudentPageCnt,
                    minimizeMaximumPagesAllocatedRecursion3(pages,i,students-1))); // -1 becoz doing partition for current student so recursion call goes for remaining students
        }
        return res;
    }

    public static int minimizeMaximumPagesAllocatedDP(int[] pages, int n, int students){
        int[][] dp = new int[students+1][n+1];

        // dp[i][j] = minimum of max pages allocated in i students in j books

        // for students = 1
        int sum = 0;
        for(int j = 1; j<=n; j++){
            sum += pages[j-1];
            dp[1][j] = sum;
        }

        // for n == 1
        for(int i = 1; i<=students; i++) dp[i][1] = pages[0];

        for(int i = 2; i<=students; i++){
            for(int j = 2; j<=n; j++){
                dp[i][j] = Integer.MAX_VALUE;
                int currStudentPageCnt = 0;
                for(int k = j-1; k>0; k--){
                    currStudentPageCnt += pages[k];
                    dp[i][j] = Math.min(dp[i][j],Math.max(currStudentPageCnt,dp[i-1][k]));
                }
            }
        }

        return dp[students][n];
    }

    public static void testMinimizeMaximumPagesAllocated(){
        System.out.println("testMinimizeMaximumPagesAllocated");
        int[] pages = {10,5,30,1,2,5,10,10};
        int students = 3;
        System.out.println(minimizeMaximumPagesAllocatedRecursion1(pages,0,pages.length-1,students));
        System.out.println(minimizeMaximumPagesAllocatedRecursion2(pages,pages.length,students));
        System.out.println(minimizeMaximumPagesAllocatedRecursion3(pages,pages.length,students));
        System.out.println(minimizeMaximumPagesAllocatedDP(pages,pages.length,students));
    }

    public static int palindromePartitioningWithMinimumCutsRecursion(String str,int start, int end){
        if(isPalindrome(str,start,end)) return 0;
        int res = Integer.MAX_VALUE;
        for(int i = start; i<end; i++){
            res = Math.min(res,palindromePartitioningWithMinimumCutsRecursion(str,start,i)
                    + palindromePartitioningWithMinimumCutsRecursion(str,i+1,end) + 1); // +1 for current cut
        }
        return res;
    }

    public static int palindromePartitioningWithMinimumCutsDP(String str, int start, int end){
        int[][] dp = new int[str.length()][str.length()];
        /*
         * 00 01 02 03 04
         * xx 11 12 13 14
         * xx xx 22 23 24
         * xx xx xx 33 34
         * xx xx xx xx 44
         *
         * we need to traverse diagonals start=row and end=col
         * for diagonal 1 -> 00 11 22 33 44 (end-start=0), answer will be 0 as single characters are alwasy palindromes;
         * then we traverse remaining diagonals with end-start >= 1
         * actually we don't need fill 0 for end-start == 0 as int[] default value is 0
         * */
        for(int gap = 1; gap <= end; gap++){
            for(int s = start, e=s+gap; e <= end; s++,e=s+gap){
                if(isPalindrome(str,s,e)) dp[s][e] = 0;
                else{
                    dp[s][e] = Integer.MAX_VALUE;
                    for(int i = s; i<e; i++){
                        dp[s][e] = Math.min(dp[s][e],dp[s][i] + dp[i+1][e] + 1);
                    }
                }
            }
        }
        return dp[start][end];
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while(start < end){
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public static void testPalindromePartitioningWithMinimumCuts(){
        System.out.println("testPalindromePartitioningWithMinimumCuts");
        String s = "geeks";
        System.out.println(palindromePartitioningWithMinimumCutsRecursion(s,0,s.length()-1));
        System.out.println(palindromePartitioningWithMinimumCutsDP(s,0,s.length()-1));
    }

    public static int minimumMatrixChainMultiplicationsRecursion(int[] arr, int start, int end){
        if(end - start <= 1) return 0; // less than 3 elements so it's not matrix chain.
        int res = Integer.MAX_VALUE;
        for(int i = start+1; i<end; i++){
            res = Math.min(res,minimumMatrixChainMultiplicationsRecursion(arr,start,i)
                    + minimumMatrixChainMultiplicationsRecursion(arr,i,end)
                    + arr[start]*arr[i]*arr[end]); // for size 3 recursion calls return 0 here and arr[start] * arr[i] * arr[end] will be the value.
        }
        return res;
    }

    public static int minimumMatrixChainMultiplicationsDP(int[] arr,int start, int end){
        int[][] dp = new int[arr.length][arr.length];
        /*
         * 00 01 02 03 04
         * xx 11 12 13 14
         * xx xx 22 23 24
         * xx xx xx 33 34
         * xx xx xx xx 44
         *
         * we need to traverse diagonals start=row and end=col
         * for diagonal 1 -> 00 11 22 33 44 (end-start=0) and  diagonal 2 -> 01 12 23 34 (end - start = 1), answer will be 0 as matrix multiplication needs end-start >= 2;
         * then we traverse remaining diagonals with end-start >= 2
         * actually we don't need fill 0 for end-start == 0 & 1 as int[] default value is 0
         * */

        for(int gap = 2; gap <= end; gap++){
            for(int s = start, e=s+gap; e <= end; s++,e=s+gap){
                dp[s][e] = Integer.MAX_VALUE;
                for(int i = s+1; i<e; i++){
                    dp[s][e] = Math.min(dp[s][e],dp[s][i] + dp[i][e] + arr[s]*arr[i]*arr[e]);
                }
            }
        }
        return dp[start][end];
    }

    public static void testMinimumMatrixChainMultiplications(){
        System.out.println("testMinimumMatrixChainMultiplications");
        int[] arr = {2,1,3,4,5};
        System.out.println(minimumMatrixChainMultiplicationsRecursion(arr,0,arr.length-1));
        System.out.println(minimumMatrixChainMultiplicationsDP(arr,0,arr.length-1));
    }

    public static int noOfSubsetsWithSumRecursion(int[] arr, int sum, int n){
        if(sum == 0) return 1; // sum == 0 should be checked first as it is possible for num = 0 to have sum = 0 with empty subset. // this is short circuit -> immediately return 1 as we reach sum = 0. But if we have negative elements in arr then it's we should check sum == 0 once we reach n == 0 (as by that time we will have all subsets). ex subsets [1,2,3] & [-2,2,1,2,3] both result in sum 6. so implemented this in recursion2 method
        if(sum < 0 || n == 0) return 0;
        return noOfSubsetsWithSumRecursion(arr,sum-arr[n-1],n-1) + noOfSubsetsWithSumRecursion(arr,sum,n-1);
    }

    public static int noOfSubsetsWithSumRecursion2(int[] arr, int sum, int n){
        if(n == 0) return (sum == 0)? 1 : 0;
        return noOfSubsetsWithSumRecursion2(arr,sum-arr[n-1],n-1) + noOfSubsetsWithSumRecursion2(arr,sum,n-1);
    }

    public static int noOfSubsetsWithSumDP(int[] arr, int sum, int n){
        int[][] dp = new int[n+1][sum+1];

        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=sum; j++){
                // dp[i][j] => count of subsets of arr with size i and sum j
                if(i == 0) dp[i][j] = (j == 0) ? 1 : 0;
                else if(j - arr[i-1] < 0) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j-arr[i-1]] + dp[i-1][j];
            }
        }

        return dp[n][sum];
    }

    public static void testNoOfSubsetsWithSum(){
        System.out.println("testNoOfSubsetsWithSum");
        int[] arr = {10,5,2,3,6};
        int sum = 8;
        System.out.println(noOfSubsetsWithSumRecursion(arr,sum,arr.length));
        System.out.println(noOfSubsetsWithSumRecursion2(arr,sum,arr.length));
        System.out.println(noOfSubsetsWithSumDP(arr,sum,arr.length));
    }

    public static int maximumSumWithNoConsecutiveRecursion(int[] arr, int n){
        if(n == 0) return 0;
        if(n == 1) return arr[0];
        if(n == 2) return Math.max(arr[0],arr[1]);
        return Math.max(arr[n-1] + maximumSumWithNoConsecutiveRecursion(arr,n-2),maximumSumWithNoConsecutiveRecursion(arr,n-1));
    }

    public static int maximumSumWithNoConsecutiveDP(int[] arr, int n){
        int[] dp = new int[n+1];
        for(int i = 0; i<=n; i++){
            if(i == 0) dp[i] = 0;
            else if(i == 1) dp[i] = arr[0];
            else if(i == 2) dp[i] = Math.max(arr[0],arr[1]);
            else{
                dp[i] = Math.max(arr[i-1] + dp[i-2],dp[i-1]);
            }
        }
        return dp[n];
    }

    public static int maximumSumWithNoConsecutiveDP2(int[] arr, int n){
        if(n == 0) return 0;
        if(n == 1) return arr[0];
        int prev_prev = arr[0];
        int prev = Math.max(arr[0],arr[1]);
        int res = prev;
        for(int i = 3; i<=n; i++){
            res = Math.max(arr[i-1] + prev_prev,prev);
            prev_prev = prev;
            prev = res;
        }
        return res;
    }

    public static void testMaximumSumNoConsecutive(){
        System.out.println("test maximum sum with no consecutive elements");
        int[] arr = {-366,50,677,-13,-33,-923,495,-851};
        System.out.println(maximumSumWithNoConsecutiveRecursion(arr,arr.length));
        System.out.println(maximumSumWithNoConsecutiveDP(arr,arr.length));
        System.out.println(maximumSumWithNoConsecutiveDP2(arr,arr.length));
    }

    public static int countBSTsWithNKeysRecursion(int n){
        if(n == 0 || n == 1) return 1;
        int cnt = 0;
        for(int i = 1; i<=n; i++){
            // ith node as root, cnt = countBST(i-1 keys) multiplied by countBST(n-i keys)
            cnt += countBSTsWithNKeysRecursion(i-1) * countBSTsWithNKeysRecursion(n-i);
        }
        return cnt;
    }

    public static int countBSTsWithNKeysDP(int n){
        int[] dp = new int[n+1];
        for(int i = 0; i<=n; i++){
            if(i == 0 || i == 1) dp[i] = 1;
            else{
                for(int j = 1; j<=i; j++){
                    // out of total i nodes, jth node as root, so left side j-1 elements right side i-j elements
                    dp[i] += dp[j-1] * dp[i-j];
                }
            }
        }
        return dp[n];
    }

    public static void testCountBSTsWithNKeys(){
        System.out.println("countBSTsWithNKeys");
        System.out.println(countBSTsWithNKeysRecursion(5));
        System.out.println(countBSTsWithNKeysDP(5));
    }

    public static int eggDroppingPuzzleRecursion(int e, int f){
        if(e <= 0) return Integer.MAX_VALUE;
        if(e == 1 || f == 1 || f == 0) return f;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i<=f; i++){
            min = Math.min(min,Math.max(eggDroppingPuzzleRecursion(e-1,i-1),eggDroppingPuzzleRecursion(e,f-i)));
        }
        return min+1;
    }

    public static int eggDroppingPuzzleDP(int e, int f){
        int[][] dp = new int[e+1][f+1];

        for(int i = 0; i<=e; i++){
            for(int j = 0; j<=f; j++){
                if(i <= 0) dp[i][j] = Integer.MAX_VALUE;
                else if(i == 1 || j == 1 || j == 0) dp[i][j] = j;
                else{
                    int min = Integer.MAX_VALUE;
                    for(int k = 1; k<=j; k++){
                        min = Math.min(min,Math.max(dp[i-1][k-1],dp[i][j-k]));
                    }
                    dp[i][j] = min+1;
                }
            }
        }

        return dp[e][f];
    }

    public static void testEggDroppingPuzzle(){
        System.out.println("testEggDroppingPuzzle");
        System.out.println(eggDroppingPuzzleRecursion(2,10));
        System.out.println(eggDroppingPuzzleDP(2,10));
    }

    public static int optimalStrategyGameRecursive1(int[] arr, int start, int end, int sum){
        // Objective - To return max value that 1st player gets in any case of arr size even or odd.
        // logic - Both players play best
        // so your max = MAX(total Sum - opponent's max in the array when it's his turn)
        if(start == end) return arr[start];
        if(end-start == 1) return Math.max(arr[start],arr[end]);
        return Math.max(sum - optimalStrategyGameRecursive1(arr,start+1,end,sum-arr[start]),
                sum - optimalStrategyGameRecursive1(arr,start,end-1,sum-arr[end]));
    }

    public static int optimalStrategyGameRecursive2(int[] arr, int start, int end){
        // Objective - To return max value that 1st player gets in any case of arr size even or odd.
        // logic - after you pick your element, opponent always picks in way that he gets maximum and you get MINIMUM.
        // so MAX(your pick + MIN(in your next possible array to pick))
        if(start == end) return arr[start]; // This case is for given odd size arr.
        if(end-start == 1) return Math.max(arr[start],arr[end]);
        int pickStart = arr[start] + Math.min(optimalStrategyGameRecursive2(arr,start+2,end),optimalStrategyGameRecursive2(arr,start+1,end-1));
        int pickEnd = arr[end] + Math.min(optimalStrategyGameRecursive2(arr,start,end-2),optimalStrategyGameRecursive2(arr,start+1,end-1));
        return Math.max(pickStart,pickEnd);
    }

    public static int optimalStrategyGameDP(int[] arr, int start, int end){
        int[][] dp = new int[arr.length][arr.length];
        /* arr = {20,5,4,6}
         * X     0   1   2   3
         * 0     20  20  24  25
         * 1         5   5   10
         * 2             4   6
         * 3                 6
         *
         * */
        // we need to reverse the traversal as of recursion.
        // so left goes end to start
        // right goes left to end
        for(int l = end; l>=start; l--){
            for(int r = l; r<=end; r++){
                if(l == r) dp[l][r] = arr[l];
                else if(r-l == 1) dp[l][r] = Math.max(arr[l],arr[r]);
                else {
                    int pickI = arr[l] + Math.min(dp[l+2][r],dp[l+1][r-1]);
                    int pickJ = arr[r] + Math.min(dp[l][r-2],dp[l+1][r-1]);
                    dp[l][r] = Math.max(pickI,pickJ);
                }
            }
        }
        Arrays.stream(dp).forEach(row -> System.out.println(Arrays.toString(row)));
        return dp[start][end];
    }

    public static void testOptimalStrategyGame(){
        System.out.println("testOptimalStrategyGame");
        int[] arr = {20,5,4,6,8,3};
        int sum = Arrays.stream(arr).sum();
        System.out.println(optimalStrategyGameRecursive1(arr,0,arr.length-1,sum));
        System.out.println(optimalStrategyGameRecursive2(arr,0,arr.length-1));
        System.out.println(optimalStrategyGameDP(arr,0,arr.length-1));
    }

    public static int knapsack01Recursive(int[] values, int[] weights, int n, int capacity){
        if(capacity < 0) return Integer.MIN_VALUE;
        if(capacity == 0 || n == 0) return 0;
        int pick = knapsack01Recursive(values,weights,n-1,capacity-weights[n-1]);
        int notPick = knapsack01Recursive(values,weights,n-1,capacity);
        return Math.max((pick != Integer.MIN_VALUE) ? values[n-1] + pick : Integer.MIN_VALUE, notPick);
    }

    public static int knapsack01Recursive2(int[] values, int[] weights, int n, int capacity){
        if(capacity == 0 || n == 0) return 0;

        if(weights[n-1] <= capacity){
            return Math.max(values[n-1] + knapsack01Recursive2(values,weights,n-1,capacity-weights[n-1]),
                    knapsack01Recursive2(values,weights,n-1,capacity));
        }else{
            return knapsack01Recursive2(values,weights,n-1,capacity);
        }
    }

    public static int knapsack01DP(int[] values, int[] weights, int n, int capacity){
        if(capacity == 0 || n == 0) return 0;
        int[][] dp = new int[n+1][capacity+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<= capacity; j++){
                if(i == 0 || j == 0) dp[i][j] = 0;
                else{
                    dp[i][j] = Integer.MIN_VALUE;
                    if(j-weights[i-1] >= 0) dp[i][j] = Math.max(dp[i][j],values[i-1] + dp[i-1][j-weights[i-1]]);
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j]);
                }
            }
        }
        return dp[n][capacity];
    }

    public static int knapsack01DP2(int[] values, int[] weights, int n, int capacity){
        if(capacity == 0 || n == 0) return 0;
        int[][] dp = new int[n+1][capacity+1];
        // dp[i][j] denotes Maximum value possible with first i items and knapsack with capacity j.
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<= capacity; j++){
                if(i == 0 || j == 0) dp[i][j] = 0;
                else if(weights[i-1] <= j) dp[i][j] = Math.max(values[i-1] + dp[i-1][j-weights[i-1]],dp[i-1][j]);
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][capacity];
    }

    public static void testKnapsack01(){
        System.out.println("testKnapsack01");
        int[] values = {60,100,120};
        int[] weights = {10,20,30};
        int capacity = 50;
        System.out.println(knapsack01Recursive(values,weights,weights.length,capacity));
        System.out.println(knapsack01Recursive2(values,weights,weights.length,capacity));
        System.out.println(knapsack01DP(values,weights,weights.length,capacity));
        System.out.println(knapsack01DP2(values,weights,weights.length,capacity));
    }

    public static int minimumJumpsRecursion(int[] arr, int pos){
        if(pos >= arr.length-1) return 0;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= arr[pos]; i++){
            min = Math.min(min,minimumJumpsRecursion(arr,pos+i));
        }
        return (min != Integer.MAX_VALUE) ? 1+min : Integer.MAX_VALUE;
    }

    public static int minimumJumpsRecursion2(int[] arr, int n){
        if(n <= 1) return 0;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i<n-1; i++){
            if(i+arr[i] >= n-1) res = Math.min(res,minimumJumpsRecursion2(arr,i+1));
        }
        return (res != Integer.MAX_VALUE) ? 1+res : Integer.MAX_VALUE;
    }

    public static int minimumJumpsDP(int[] arr, int pos){
        int n = arr.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for(int i = n-2; i>=0; i--){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 1; j <= arr[i]; j++){
                dp[i] = Math.min(dp[i],(i+j >= arr.length-1) ? 0 : dp[i+j]);
            }
            dp[i] += 1;
        }
        return dp[0];
    }

    public static int minimumJumpsDP2(int[] arr, int n){
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i = 1; i<n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j<i; j++){
                if(j + arr[j] >= i) dp[i] = Math.min(dp[i],dp[j]);
            }
            if(dp[i] != Integer.MAX_VALUE) dp[i]++;
        }
        return dp[n-1];
    }

    public static void testMinimumJumps(){
        System.out.println("testMinimumJumps");
        int[] arr = {3,4,2,1,2,1};
        System.out.println(minimumJumpsRecursion(arr,0));
        System.out.println(minimumJumpsRecursion2(arr,arr.length));
        System.out.println(minimumJumpsDP(arr,0));
        System.out.println(minimumJumpsDP2(arr,arr.length));
    }

    public static int minimumCoinsRecursion(int[] coins, int n, int sum){
        if(sum == 0) return 0;
        if(sum < 0 || n == 0) return Integer.MAX_VALUE;
        int pick = minimumCoinsRecursion(coins,n,sum-coins[n-1]);
        int notPick = minimumCoinsRecursion(coins,n-1,sum);
        if(pick != Integer.MAX_VALUE) pick++;
        return Math.min(pick,notPick);
    }

    public static int minimumCoinsRecursion2(int[] coins, int n, int sum){
        if(sum == 0) return 0;
        if(sum < 0) return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            int usingIthCoin = minimumCoinsRecursion2(coins,n,sum-coins[i]);
            if(usingIthCoin != Integer.MAX_VALUE) usingIthCoin += 1;
            res = Math.min(res,usingIthCoin);
        }
        return res;
    }

    public static int minimumCoinsDP(int[] coins, int n, int sum){
        int[][] dp = new int[sum+1][n+1];
        for(int i = 0; i<=sum; i++){
            for(int j = 0; j<=n; j++){
                if(i == 0) dp[i][j] = 0;
                else if(j == 0) dp[i][j] = Integer.MAX_VALUE;
                else{
                    int pick = (i-coins[j-1] >= 0) ? dp[i-coins[j-1]][j] : Integer.MAX_VALUE;
                    int notPick = dp[i][j-1];
                    if(pick != Integer.MAX_VALUE) pick++;
                    dp[i][j] = Math.min(pick,notPick);
                }
            }
        }
        return dp[sum][n];
    }

    public static int minimumCoinsDP2(int[] coins, int n, int sum){
        int[] dp = new int[sum+1];
        dp[0] = 0; // sum == 0 then minimumCoins is also 0
        for(int i = 1; i<=sum; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j<n; j++){
                if(i - coins[j] >= 0 && dp[i-coins[j]] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i],1 + dp[i - coins[j]]);
            }
        }
        return dp[sum];
    }

    public static void testMinimumCoinsRecursion(){
        System.out.println("testMinimumCoinsRecursion");
        int[] coins = {9,6,5,1};
        int sum = 11;
        System.out.println(minimumCoinsRecursion(coins,coins.length,sum));
        System.out.println(minimumCoinsRecursion2(coins,coins.length,sum));
        System.out.println(minimumCoinsDP(coins,coins.length,sum));
        System.out.println(minimumCoinsDP2(coins,coins.length,sum));
    }

    public static int maximumCutsRecursion(int rodLength, int a, int b, int c){
        if(rodLength < 0) return -1;
        if(rodLength == 0) return 0;

        int res = Math.max(maximumCutsRecursion(rodLength-a,a,b,c),
                Math.max(maximumCutsRecursion(rodLength-b,a,b,c), maximumCutsRecursion(rodLength-c,a,b,c)));
        return (res == -1) ? -1 : 1 + res; // +1, because current cut is counted now either a or b or c.
    }

    public static int maximumCutsDP(int rodLength, int a, int b, int c){
        if(rodLength < 0) return -1;
        int[] dp = new int[rodLength+1];
        dp[0] = 0;

        for(int i = 1; i<=rodLength; i++){
            dp[i] = -1;
            if(i-a >= 0) dp[i] = Math.max(dp[i],dp[i-a]);
            if(i-b >= 0) dp[i] = Math.max(dp[i],dp[i-b]);
            if(i-c >= 0) dp[i] = Math.max(dp[i],dp[i-c]);
            if(dp[i] != -1) dp[i]++;
        }

        return dp[rodLength];
    }

    public static void testMaximumCuts(){
        System.out.println("testMaximumCuts");
        System.out.println(maximumCutsRecursion(5,1,2,3));
        System.out.println(maximumCutsDP(5,1,2,3));
    }

    public static int longestIncreasingSubsequenceRecursion(int[] arr,int n, int prev){
        if(n == 0) return 0;
        if(arr[n-1]<prev) return Math.max(1 + longestIncreasingSubsequenceRecursion(arr,n-1,arr[n-1]),longestIncreasingSubsequenceRecursion(arr,n-1,prev));
        return longestIncreasingSubsequenceRecursion(arr,n-1,prev);
    }

    public static int longestIncreasingSubsequenceDP(int[] arr,int n){
        // DP solution with nxn time complexity
        if(n == 0) return 0;
        int[] lis = new int[n];
        lis[0] = 1; // longest increasing subsequence ending with element at 0th index is always of length 1.
        int maxLis = 1;
        for(int i = 1; i<n; i++){
            lis[i] = 1;
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i]) lis[i] = Math.max(lis[i],lis[j] + 1);
            }
            maxLis = Math.max(maxLis,lis[i]);
        }
        return maxLis;
    }

    public static int longestIncreasingSubsequenceDP2(int[] arr,int n){
        // DP solution with nlogn time complexity
        if(n == 0) return 0;
        int[] tail = new int[n]; // This is to store minimum possible tail values for LIS
        tail[0] = arr[0];
        int len = 1;
        for(int i = 1; i<n; i++){
            if(arr[i] > tail[len-1]){
                tail[len] = arr[i];
                len++;
            }else{
                int idx = ceilingIndex(tail,0,len-1,arr[i]);
                tail[idx] = arr[i];
            }
        }
        return len;
    }

    public static int ceilingIndex(int[] arr, int l, int r, int x){
        while(r > l){
            int mid = l + (r-l)/2;
            if(arr[mid] >= x) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    public static void testLongestIncreasingSubsequence(){
        System.out.println("testLongestIncreasingSubsequence");
        int[] arr = {3,4,2,8,10,5,1};
        System.out.println(longestIncreasingSubsequenceRecursion(arr,arr.length,Integer.MAX_VALUE));
        System.out.println(longestIncreasingSubsequenceDP(arr,arr.length));
        System.out.println(longestIncreasingSubsequenceDP2(arr,arr.length));
    }

    public static int editDistanceRecursion(String a, String b, int n, int m){
        if(n == 0 || m == 0) return n+m;
        if(a.charAt(n-1) == b.charAt(m-1)) return editDistanceRecursion(a,b,n-1,m-1);
        else return 1 + Math.min(editDistanceRecursion(a,b,n-1,m-1),
                Math.min(editDistanceRecursion(a,b,n-1,m),editDistanceRecursion(a,b,n,m-1)));
    }

    public static int editDistanceDP(String a, String b, int n, int m){
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                if(i == 0 || j == 0) dp[i][j] = i+j;
                else if(a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
            }
        }
        return dp[n][m];
    }

    public static void testEditDistance(){
        System.out.println("testEditDistance");
        String a = "saturday";
        String b = "sunday";
        System.out.println(editDistanceRecursion(a,b,a.length(),b.length()));
        System.out.println(editDistanceDP(a,b,a.length(),b.length()));
    }

    public static int coinChangeSumRec(int[] coins, int sum, int n){
        if(sum < 0 || n == 0) return 0;
        if(sum == 0) return 1;
        return coinChangeSumRec(coins,sum-coins[n-1],n) + coinChangeSumRec(coins,sum,n-1);
    }

    public static int coinChangeSumDP(int[] coins, int sum, int n){
        int[][] dp = new int[sum+1][n+1];
        for(int i = 0; i<=sum; i++){
            for(int j = 0; j<=n; j++){
                if(i == 0) dp[i][j] = 1;
                else if (j == 0) dp[i][j] = 0;
                else dp[i][j] = dp[i][j-1] + (i-coins[j-1] >= 0 ? dp[i-coins[j-1]][j] : 0);
            }
        }
        return dp[sum][n];
    }

    public static void testCoinChangeSum(){
        System.out.println("testCoinChangeSum");
        int[] coins = {2,5,3,6};
        int sum = 10;
        System.out.println(coinChangeSumRec(coins,sum,coins.length));
        System.out.println(coinChangeSumDP(coins,sum,coins.length));
    }

    public static int longestCommonSubsequenceRecursive(String a, String b, String common, int i, int j){
        int max = 0;
        if(i >= a.length() || j >= b.length()){
            System.out.println(common);
            return common.length();
        }
        if(a.charAt(i) == b.charAt(j)) max = Math.max(max,longestCommonSubsequenceRecursive(a,b,common+a.charAt(i),i+1,j+1));
        max = Math.max(max,longestCommonSubsequenceRecursive(a,b,common,i,j+1));
        max = Math.max(max,longestCommonSubsequenceRecursive(a,b,common,i+1,j));
        return max;
    }

    public static int longestCommonSubsequenceRecursive2(String a, String b, int n, int m){
        if(n == 0 || m == 0){
            return 0; // common subsequence of 2 strings with size n & m will be 0 if either of their size is 0. ex: a = "abc", b = ""(empty) -> common is also "" (empty) so returning 0 -> this is the termination case. we break the main problem into subproblems till we reach here.
        }
        if(a.charAt(n-1) == b.charAt(m-1)) return 1 + longestCommonSubsequenceRecursive2(a,b,n-1,m-1);
        else {
            return Math.max(longestCommonSubsequenceRecursive2(a,b,n,m-1),longestCommonSubsequenceRecursive2(a,b,n-1,m));
        }
    }

    public static int longestCommonSubsequenceDP(String a, String b, int n, int m){
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                if(i == 0 || j == 0) dp[i][j] = 0;
                else if(a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        return dp[n][m];
    }

    public static void testLongestCommonSubSequence(){
        System.out.println("testLongestCommonSubSequence");
        String a = "ABCDGH";
        String b = "AEDFHR";
        System.out.println(longestCommonSubsequenceRecursive(a,b, "", 0,0));
        System.out.println(longestCommonSubsequenceRecursive2(a,b,a.length(),b.length()));
        System.out.println(longestCommonSubsequenceDP(a,b,a.length(),b.length()));
    }

    public static void main(String[] args) {
        testLongestCommonSubSequence();
        testCoinChangeSum();
        testEditDistance();
        testLongestIncreasingSubsequence();
        testMaximumCuts();
        testMinimumCoinsRecursion();
        testMinimumJumps();
        testKnapsack01();
        testOptimalStrategyGame();
        testEggDroppingPuzzle();
        testCountBSTsWithNKeys();
        testMaximumSumNoConsecutive();
        testNoOfSubsetsWithSum();
        testMinimumMatrixChainMultiplications();
        testPalindromePartitioningWithMinimumCuts();
        testMinimizeMaximumPagesAllocated();
        testCountWaysForSumOfN();
    }
}