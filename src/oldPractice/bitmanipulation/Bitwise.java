package oldPractice.bitmanipulation;

import java.util.Arrays;

public class Bitwise {
	public static boolean isKthBitset(int n, int k) {
//		return ((n & (1 << (k-1))) != 0); // it will be 0 or non-zero
		return ((n >> (k-1) & 1) == 1); // it will be 1 or 0
	}
	
	public static int cntSetBits(int n) {
		// Naive solution O(number of bits)
		int ans = 0;
		while(n>0) {
			ans += n&1; //  ans += n%2;
			n = n>>1; // n = n/2;
		}
		return  ans;
	}
	
	public static int brianKerningamsAlgoCntSetBits(int n) {
		// Brian Kerningam Algorithm -- O(number of set Bits)
		int ans = 0;
		while(n>0) {
			n = (n & (n-1));
			ans++;
		}
		return ans;
	}
	
	public static int lookUpTableMethodCntSetBits(int n) {
		// O(1)
		int[] table = new int[256];
		table[0] = 0;
		for(int i = 1; i<256;i++) {
			table[i] = (i&1) + table[i/2];
		}
		System.out.println(Arrays.toString(table));
		int ans = 0;
		while(n>0) {
			ans += table[n & 0xff];
			n = n>>8;
		}
		return ans;
	}
	
	public static boolean isPowerOf2(int n) {
		
		//return ((n != 0) && ((n & (n-1)) == 0)); // one liner
		if(n == 0) return false;
		return ((n & (n-1)) == 0);
	}
	
	public static int oddOccurringNum(int[] arr) {
		/*
		 * XOR properties:
		 * x ^ 0 = x
		 * x ^ x = 0
		 * x^y = y^x
		 * x^(y^z) = (x^y)^z
		 * */
		int ans = 0;
		for(int x: arr) {
			ans = ans ^ x;
		}
		return ans;
	}
	
	public static int missingNumber(int[] arr) {
		// Q: you are given an array of n numbers ranging from 1 to n+1 and one number missing find missing???
		int n = arr.length;
		int ans = 0;
		for(int x: arr) {
			ans = ans ^ x;
		}
		for(int i = 1; i<=n+1; i++) {
			ans = ans ^ i;
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
		int k = (xor & (xor - 1)) ^ xor; // int k = xor & ~(xor -1);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5, k = 1;
		System.out.println(isKthBitset(n,k));
		System.out.println(cntSetBits(13));
		System.out.println(brianKerningamsAlgoCntSetBits(13));
		System.out.println(lookUpTableMethodCntSetBits(13));
		System.out.println(isPowerOf2(32));
		
		// Odd Occurring number in array
		int[] arr = {4,3,4,4,4,5,5,7,7,3,3};
		System.out.println(oddOccurringNum(arr));
		
		int[] nums = {1,4,3,5,7,6};
		System.out.println(missingNumber(nums));
		
		int[] arr2 = {3,4,5,3,4,4,4,5,3,6,6,7,8,7};
		twoOddOccurringNumbers(arr2);

		printPowerSet("manoj");
	}

}
