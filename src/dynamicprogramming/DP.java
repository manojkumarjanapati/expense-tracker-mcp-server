package dynamicprogramming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DP {

    public static int nthFibUsingMemo(int n,int[] dp){
        // nth fibonacci number using Memoization
        // this top-down approach [from n to 0]
        if(n == 1 || n == 0) return n;
        if(dp[n] != -1) return dp[n];
        return dp[n] = nthFibUsingMemo(n-1,dp) + nthFibUsingMemo(n-2,dp);
    }

    public static int nthFibUsingTabulation(int n){
        // nth fibonacci number using Tabulation
        // this bottom-up approach [from 0 to n]
        int[] fib = new int[n+1];
        fib[0] = 0;
        fib[1] = 1;
        for(int i = 2; i<=n; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[n];
    }

    public static int nthFibUsingSpaceOpt(int n){
        int prev2 = 0;
        int prev1 = 1;
        for(int i = 2; i<=n; i++){
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void testNthFib(){
        int n = 5;
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        System.out.println(nthFibUsingMemo(n,memo));
        System.out.println(nthFibUsingTabulation(n));
        System.out.println(nthFibUsingSpaceOpt(n));
    }

    public static void longestCommonSubSeq(String a, String b, int i, int j, String subSeq, HashSet<String> commonSubSeqs){
        if(i == a.length() || j == b.length()){
            commonSubSeqs.add(subSeq);
            return;
        }
        if(a.charAt(i) == b.charAt(j)){
            longestCommonSubSeq(a,b,i+1,j+1,subSeq+a.charAt(i),commonSubSeqs);
        }else{
            longestCommonSubSeq(a,b,i+1,j,subSeq,commonSubSeqs);
            longestCommonSubSeq(a,b,i,j+1,subSeq,commonSubSeqs);
        }
    }

    public static int longestCommonSubSeq(String a, String b, int m, int n){
        if(m == 0 || n == 0) return 0;
        if(a.charAt(m-1) == b.charAt(n-1)){
            return 1 + longestCommonSubSeq(a,b,m-1,n-1);
        }else{
            return Math.max(longestCommonSubSeq(a,b,m-1,n),longestCommonSubSeq(a,b,m,n-1));
        }
    }

    public static int longestCommonSubSeqUsingMemo(String a, String b, int m, int n, int[][] dp){
        if(dp[m][n] == -1){
            // for inputs m and n memoization is not yet calculated
            // we filled memo array[dp] with -1, because -1 can never be length of longestCommonSubSeq for any input
            if(m == 0 || n == 0) dp[m][n] = 0;
            else if(a.charAt(m-1) == b.charAt(n-1)){
                dp[m][n] = 1 + longestCommonSubSeqUsingMemo(a,b,m-1,n-1,dp);
            }else{
                dp[m][n] = Math.max(longestCommonSubSeqUsingMemo(a,b,m-1,n,dp),longestCommonSubSeqUsingMemo(a,b,m,n-1,dp));
            }
        }
        return dp[m][n];
    }

    public static int longestCommonSubSeqUsingTabulation(String a, String b){
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];
        String lcs = "";
        for(int i = 0; i<=m; i++){
            dp[i][0] = 0; // for any length of a[0:i], with b[0:0] which is emptyString, the ans is 0
        }
        for(int j = 0; j<=n; j++){
            dp[0][j] = 0; // for any length of b[0:j], with a[0:0] which is emptyString, the ans is 0
        }

        // building up solution [bottom-up] approach
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    // a[i-1] and b[j-1] are same
                    // so, longestCommonSubSeqLength for length i,j is (1 + longestCommonSubSeqLength for lengths i-1,j-1)
                    // For, strings : a[0:i] and b[0:j], last chars a[i-1] = b[j-1] are equal
                    // so longestCommonSubSeqLength for a[0:i] and b[0:j] = 1 + longestCommonSubSeqLength for a[0:i-1] and b[0:j-1]
                    dp[i][j] = 1 + dp[i-1][j-1];
                    lcs += a.charAt(i-1);
                }else{
                    // a[i-1] != b[j-1] are different
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        System.out.println(lcs);
        return dp[m][n];
    }

    public static int longestCommonSubSeqSpaceOpt(String a, String b){
        int m = a.length();
        int n = b.length();
        String lcs = "";
        int[][] dp = new int[2][n+1];
        int currRow = 0;
        for(int i = 0; i<=m; i++){
            for(int j = 0; j<=n; j++){
                if(i == 0 || j == 0) dp[currRow][j] = 0;
                else if(a.charAt(i-1) == b.charAt(j-1)){
                    // we have only two rows 0 & 1
                    // 1-currRow gives as other row otherthan currRow
                    // if currRow = 0 then 1-currRow = 1 and viceVersa
                    dp[currRow][j] = 1 + dp[1-currRow][j-1];
                    lcs += a.charAt(i-1);
                }else{
                    dp[currRow][j] = Math.max(dp[currRow][j-1], dp[1-currRow][j]);
                }
            }
            currRow = 1-currRow; // switching rows
        }
        System.out.println(lcs);
        return dp[1-currRow][n];
    }
    public static void testLongestCommonSubSeq(){
        String a = "ABCDGH";
        String b = "AEDFHR";
        HashSet<String> commonSubSeqs = new HashSet<>();
        longestCommonSubSeq(a,b,0,0,"",commonSubSeqs);
        System.out.println(commonSubSeqs);
        System.out.println("Recursion :" + longestCommonSubSeq(a,b,a.length(),b.length()));
        int[][] memo = new int[a.length()+1][b.length()+1];
        for(int[] row: memo) Arrays.fill(row,-1);
        System.out.println("Memoization :" + longestCommonSubSeqUsingMemo(a,b,a.length(),b.length(),memo));
        System.out.println("Tabulation :" + longestCommonSubSeqUsingTabulation(a,b));
        System.out.println("Space Opt :" + longestCommonSubSeqSpaceOpt(a,b));
    }

    public static int coinChangeWays(int[] coins,int sum, int i){
        // manoj logic
        // parameter i is to eleminate duplicate combinations
        // if we take coins at 0,1,2 indexes as combination for given sum we shouldn't take 2,1,0 again
        if(sum == 0) return 1;
        if(sum < 0) return 0;
        int cnt = 0;
        for(int j = i; j<coins.length; j++){
            // passing j in i so only forward indexing passible backward is eliminated
            cnt += coinChangeWays(coins,sum-coins[j],j);
        }
        return cnt;
    }

    public static int coinChangeWaysAuthor(int[] coins,int sum, int n){
        if(sum == 0) return 1;
        if(sum < 0 || n == 0) return 0;
        // At this moment, include nth coin + exclude nth coin
        return coinChangeWaysAuthor(coins,sum-coins[n-1],n) + coinChangeWaysAuthor(coins,sum,n-1);
    }

    public static int coinChangeWays(int[] coins, int sum){
        int n = coins.length;
        int[][] dp = new int[n+1][sum+1];
        for(int len=0; len<=n; len++){
            for(int s=0; s<=sum; s++){
                if(s == 0){
                    // 1 way is possible to make sum of 0 irrespective of len of coins array considered for the sum
                    dp[len][s] = 1;
                }
                else if(len == 0){
                    // if len of coins array considered is 0 then no positive sum is possible
                    dp[len][s] = 0;
                }
                else{
                    // ways when last coin in coins[0:len] is excluded,
                    // then it's same as considering coins[0:len-1] for instant sum S
                    dp[len][s] = dp[len-1][s];
                    if(coins[len-1] <= s){
                        // last coin is included in sum
                        dp[len][s] += dp[len][s-coins[len-1]];
                    }

                }
            }
        }

        return dp[n][sum];
    }

    public static int coinChangeWays2(int[] coins, int sum){
        // Manoj you have to fix this, think it later, know why it's not working
        int n = coins.length;
        int[] dp = new int[sum+1];
        for(int coin: coins) dp[coin] = 1; // for sum which is equal to any of the coin there is 1 way by picking the direct coin intially
        for(int coin: coins){
            for(int i = sum-coin; i>=coin; i--){
                dp[i+coin] += dp[i];
            }
        }
        return dp[sum];
    }

    public static void testCoinChangeWays(){
        int[] coins = {2,5,3,6};
        int sum = 10;
        System.out.println(coinChangeWays(coins,sum,0));
        System.out.println(coinChangeWaysAuthor(coins,sum,coins.length));
        System.out.println(coinChangeWays(coins,sum));
        System.out.println(coinChangeWays2(coins,sum));
    }

    public static int editDistance(String a, String b, int m, int n){
        if(m == 0 || n == 0){
            // if any or both of the strings are empty then editDistance is max(m,n)
            return Math.max(m,n);
        }
        if(a.charAt(m-1) == b.charAt(n-1)){
            // if chars are matching then they don't contribute to editDistance
            return editDistance(a,b,m-1,n-1);
        }else{
            // you insert matching char in a at length m,now sizes are m+1 and n and last chars match,
            // so we call for m and n-1
            int insertCase = editDistance(a,b,m,n-1);
            int deleteCase = editDistance(a,b,m-1,n); // we delete mth char in a
            int replaceCase = editDistance(a,b,m-1,n-1);
            // after replacing a[m] and b[n] are same so calling for a[0:m-1] and b[0:n-1]
            // we do any one of operation(insert,delete,replace) which will be of min operations further
            // current operation is 1
            return 1 + Math.min(replaceCase,Math.min(insertCase,deleteCase));
        }
    }

    public static int editDistance(String a, String b){
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];

        for(int r = 0; r <= m; r++){
            for(int c = 0; c <= n; c++){
                if(r == 0 || c == 0) dp[r][c] = Math.max(r,c);
                else if(a.charAt(r-1) == b.charAt(c-1)){
                    dp[r][c] = dp[r-1][c-1];
                }
                else{
                    dp[r][c] = 1 + Math.min(dp[r-1][c-1],Math.min(dp[r][c-1],dp[r-1][c]));
                }
            }
        }

        return dp[m][n];
    }

    public static void testEditDistance(){
        String a = "geek";
        String b = "geeks";
        System.out.println(editDistance(a,b,a.length(),b.length()));
        System.out.println(editDistance(a,b));
    }

    public static int longestIncreasingSubSeq(int[] arr, int n, int rightMax){
        if(n == 0) return 0;
        if(arr[n-1] < rightMax){
            // including arr[n-1] and excluding, Max(both cases), when included we add 1
            return Math.max(1+longestIncreasingSubSeq(arr,n-1,arr[n-1]),longestIncreasingSubSeq(arr,n-1,rightMax));
        }else{
            return longestIncreasingSubSeq(arr,n-1,rightMax);
        }
    }

    public static int longestIncreasingSubSeq(int[] arr){
        int n = arr.length;
        int[] lis = new int[n];
        for(int i = 0; i<n; i++){
            int maxLis = 0;
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i]) maxLis = Math.max(maxLis,lis[j]);
            }
            lis[i] = 1+ maxLis; // (maxLis till arr[i] + 1) is maxLis for arr[i], 1 is for curr ith element count
        }
        return Arrays.stream(lis).max().getAsInt();
    }

    public static int longestIncreasingSubSeqUsingBinarySearch(int[] arr){
        int[] tail = new int[arr.length];
        int k = 0;
        tail[k++] = arr[0];
        for(int i = 1; i<arr.length; i++){
            if(arr[i] < tail[k-1]){
                // arr[i] is smaller than tail[k-1] so we need to find arr[i]'s would-be index in tail[0:k) [index of arr[i] ceiling]
                // Arrays.binarySearch() will return -ve value for non-existing element
                // -ve value will be given in the format : (-(insertion point) - 1)
                int ceilIdx = -1 * Arrays.binarySearch(tail,0,k,arr[i]) -1;
                tail[ceilIdx] = arr[i]; // replacing
            }else{
                tail[k++] = arr[i];
            }
        }
        return k;
    }

    public static int maxSumIncreasingSubSeq(int[] arr){
        int n = arr.length;
        int[] maxSumLis = new int[n];
        for(int i = 0; i<n; i++){
            int maxSum = 0;
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i]) maxSum = Math.max(maxSum,maxSumLis[j]);
            }
            maxSumLis[i] = arr[i] + maxSum; // (maxSum till arr[0:i-1] + arr[i]) is maxSum for arr[0:i]
        }
        return Arrays.stream(maxSumLis).max().getAsInt();
    }

    public static int longestBitonicSubSeq(int[] arr){
        // Bitonic means first increasing then decreasing, not pulsing
        int n = arr.length;
        int[] lis = new int[n]; // lis[i] - longestIncreasingSubSeq in arr[0:i]
        int[] lds = new int[n]; // lds[i] - longestDecreasingSubSeq in arr[i:n]
        for(int i = 0; i<n; i++){
            int maxLis = 0;
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i]) maxLis = Math.max(maxLis,lis[j]);
            }
            lis[i] = 1+ maxLis; // (maxLis till arr[i] + 1) is maxLis for arr[i]
        }
        for(int i = n-1; i>=0; i--){
            int maxLds = 0;
            for(int j = n-1; j>i; j--){
                if(arr[j] < arr[i]) maxLds = Math.max(maxLds,lds[j]);
            }
            lds[i] = 1 + maxLds;
        }
        // we added 1 in lis and 1 again in lds, so we need to subtract 1 while calculating bitonic length
        return IntStream.range(0,n).map(i -> lis[i]+lds[i]).max().getAsInt()-1;
    }

    public static int maxNoCrossingBridges(int[][] bridges){
        // this Q uses greedy algo to sort bridges based on fromPoints
        // then dynamic algo to find longestIncreasingSubSeq of toPoints
        int n = bridges.length;
        Arrays.sort(bridges,(b1,b2) -> b1[0] != b2[0] ? b1[0]-b2[0] : b1[1]-b2[1]);
        int[] toPoints = IntStream.range(0,n).map(i -> bridges[i][1]).toArray();
        return longestIncreasingSubSeq(toPoints);
    }

    public static int longestChainOfPairs(int[][] pairs){
        // each pair (a,b) is given as a<b
        int n = pairs.length;
        Arrays.sort(pairs, Comparator.comparingInt(pair -> pair[0])); // sort by starting value of pair
        int[] lis = new int[n];
        for(int i = 0; i<n; i++){
            int maxLis = 0;
            for(int j = 0; j<i; j++){
                // comparing previous pair last value with current pair start value to form chain
                if(pairs[j][1] < pairs[i][0]) maxLis = Math.max(maxLis,lis[j]);
            }
            lis[i] = 1+ maxLis; // (maxLis till arr[i] + 1) is maxLis for arr[i], 1 is for curr ith element count
        }
        return Arrays.stream(lis).max().getAsInt();
    }

    public static void testLongestIncreasingSubSeq(){
        int[] arr = {4,10,6,5,8,11,2};
        System.out.println(longestIncreasingSubSeq(arr,arr.length,Integer.MAX_VALUE));
        System.out.println(longestIncreasingSubSeq(arr));
        System.out.println(longestIncreasingSubSeqUsingBinarySearch(arr));

        // variations of LIS
        int[] arr2 = {3,20,4,6,7,30};
        System.out.println(maxSumIncreasingSubSeq(arr2));
        int[] arr3 = {1,11,2,10,4,5,2,1};
        System.out.println(longestBitonicSubSeq(arr3));
        int[][] bridges = {{6,2},{4,3},{2,6},{2,3},{1,5}};
        System.out.println(maxNoCrossingBridges(bridges));
        int[][] pairs = {{5,24},{39,60},{15,28},{27,40},{50,90}};
        System.out.println(longestChainOfPairs(pairs));
    }

    public static int nChooseK(int n, int k){
        int[][] nCk = new int[n+1][k+1];

        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=Math.min(i,k); j++){
                if(j == 0 || j == i) nCk[i][j] = 1;
                else{
                    nCk[i][j] = nCk[i-1][j-1] + nCk[i-1][j];
                }
            }
        }
        return nCk[n][k];
    }

    public static void testNChooseK(){
        System.out.println("5c2:"+nChooseK(5,2));
    }

    public static int minJumpsToReachArrEnd(int[] arr){
        int n = arr.length;
        if(n == 0 || arr[0] == 0){
            return Integer.MAX_VALUE; // because we have to start from arr[0]. if it is 0 then we can not jump to end
        }
        int[] minJumps = new int[n];
        minJumps[0] = 0; // to reach 0 index from 0 index we need 0 jumps. ha ha
        for(int i = 1; i<n; i++){
            minJumps[i] = Integer.MAX_VALUE;
            for(int j = 0; j<i; j++){
                if(i <= j + arr[j] && minJumps[j] != Integer.MAX_VALUE){
                    minJumps[i] = Math.min(minJumps[i],minJumps[j]+1);
                    break; // because first j such that i <= j + arr[j] is enough
                }
            }
        }
        return minJumps[n-1];
    }

    public static void testMinJumps(){
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumpsToReachArrEnd(arr));
    }

    public static void main(String[] args) {
        testNthFib();
        testLongestCommonSubSeq();
        testCoinChangeWays();
        testEditDistance();
        testLongestIncreasingSubSeq();
        testNChooseK();
    }
}
