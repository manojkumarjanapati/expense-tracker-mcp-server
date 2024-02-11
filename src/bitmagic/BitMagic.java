package bitmagic;

public class BitMagic {

    public static boolean isKthBitSet(int n,int k){

//        int x = n >> (k-1); // getting kth bit of n to lsb
//        return (x&1) != 0;

        int x = 1 << (k-1); // x is an integer with only kth bit set
        if((n&x) != 0){
            return true;
        }else{
            return false;
        }
    }

    public static int countSetBits(int n){
        // time :- O(no Of set bits)

        /* BRIAN KERNINGAM'S ALGORITHM
        * Traverse through only set bits
        * */

        int ans = 0;
        while(n > 0){
            // the below logic is that when we subtract n by 1 all the trailing zeros will become 1 and last set bit will become 0.
            // so n & (n-1) will remove last set bit
            n = n & (n-1); // this will unset last set bit of n
            ans++;
        }
        return ans;

    }

    public static int countSetBitsLookupTable(int n){
        // time : O(1)
        // assuming n will be only 32 bit integer

        int[] lookupTable = new int[256];

        // filling lookup table
        lookupTable[0] = 0;
        for(int i = 1; i<256; i++){
            // both logics works
//            lookupTable[i] =  (i & 1) + lookupTable[i/2];
            lookupTable[i] = lookupTable[i & (i-1)] + 1;
        }

        int cnt = 0;
        // now counting individual segments of 8 bit chunks
        for(int j = 0; j<4; j++){
            // in 32-bit number, four 8-bit chunks will be there
            cnt  += lookupTable[n & 255]; // n & 255 will extract last 8-bit chunk
            n = n>>8;
        }

        return cnt;

    }

    public static boolean isPowerOf2(int n){
        // if n is power of 2 then it will have count of set bits as only 1
        if(n == 0) return false;
        return (n  & (n-1)) == 0;
    }

    public static int oneOddOccurring(int[] arr){
        // bitwise xor properties :- x ^ x = 0 and x ^ 0 = x
        int ans = 0;
        for(int x: arr){
            ans ^= x;
        }

        return ans;
    }

    public static void twoOddOccurringNumbers(int[] arr) {
        // xor of all elements
        int xor = 0;
        for(int x: arr) {
            xor = xor ^ x;
        }
        // finding right most set bit number in xor
//        int k = (xor & (xor - 1)) ^ xor;
        int k = xor & ~(xor -1);
        int a = 0, b = 0;
        for(int x: arr) {
            if((x&k) != 0) {
                a = a^x;
            }
            else {
                b = b^x;
            }
        }
        System.out.println(a+" "+b);

    }

    public static void printPowerSet(String s){
        int n = s.length();

        int noOfSet = 1 << n;

        for(int i = 0; i<noOfSet; i++){
            for(int j = 0; j<n; j++){
                if((i & (1<<j)) != 0){
                    System.out.print(s.charAt(j));
                }
            }
            System.out.println();
        }
    }

    public static int xorOfAllNumbers(int n){
        // O(n)
        int ans = 0;
        for(int i = 0; i<=n; i++){
            ans ^= i;
        }
        return ans;


    }

    public static int xorOfAllNumbersOptimized(int n){
        // xor of all numbers from 1 to n : O(1) optimized
        /*
         * Find the remainder of N by moduling it with 4. N%4
         * If rem = 0, then xor will be same as N.
         * If rem = 1, then xor will be 1.
         * If rem = 2, then xor will be N+1.
         * If rem = 3 ,then xor will be 0.
         * */


        switch (n%4) {
            case 0:
                return n;
            case 1:
                return 1;
            case 2:
                return n + 1;
            case 3:
                return 0;
        }
        return -1; // obviously n%4 will result in 0,1,2,3
    }

    public static void main(String[] args) {

        System.out.println(isKthBitSet(5,3));
        System.out.println(countSetBits(5));
        System.out.println(Integer.toBinaryString(12345)+" : " +countSetBitsLookupTable(12345));
        System.out.println(isPowerOf2(16));
        System.out.println(oneOddOccurring(new int[]{1,2,2,1,3,4,4}));
        twoOddOccurringNumbers(new int[]{1,1,1,2,2,2,3,3,4,4});

        printPowerSet("manoj");

        System.out.println(xorOfAllNumbers(89));
        System.out.println(xorOfAllNumbersOptimized(89));

    }



}
