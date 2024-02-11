package oldPractice.reccursion;

public class ReccursionDemo {
	public static void mirrorNumSeries(int n) {
		if(n == 0) return;
		System.out.println(n);
		mirrorNumSeries(n-1);
		System.out.println(n);
	}
	
	public static void treeSeries(int n) {
		if(n == 0) return;
		treeSeries(n-1);
		System.out.println(n);
		treeSeries(n-1);
	}
	
	public static int log2(int n) {
		if(n == 1) return 0;
		return 1 + log2(n/2);
	}
	
	public static int binary(int n) {
		if(n == 1) return 1;
		return binary(n/2)*10 + n%2;
	}
	
	public static void nToOne(int n) {
		if(n == 0) return;
		System.out.print(n + " ");
		nToOne(n-1);
	}
	
	public static void oneToN(int n) {
		if(n == 0) return;
		oneToN(n-1); // Not Tail recursion
		System.out.print(n + " ");
	}
	
	public static void oneToNTail(int n, int k) { // pass k = 1 initially
		if(n-k+1 == 0) return;
		System.out.print(k+" ");
		oneToNTail(n,k+1);
	}
	
	public static int factorialTail(int n,int ans) { // pass ans = 1 initially
		if(n <= 1) return ans;
		return factorialTail(n-1,ans*n);
	}
	
	public static void fibonacciPrint(int n, int a, int b) { // pass a = 0, b = 1 intially
		if( n == -1 ) {
			return;
		}
		System.out.print(a+" ");
		fibonacciPrint(n-1,b,a+b);
	}
	
	public static int fibonacciAt(int n) {
		if(n < 2) return n;
		return fibonacciAt(n-1) + fibonacciAt(n-2);
	}
	
	public static int getSum(int n) {
		if(n < 2) return n;
		return n+getSum(n-1);
	}
	
	public static boolean isPalindrome(String s, int start, int end) {// pass start = 0, end = s.length - 1
		if(start >= end) return true;
		return (s.charAt(start) == s.charAt(end)) && isPalindrome(s,start+1,end-1);
	}
	
	public static int getDigSum(int n) {
		if(n <= 9) return n;
		return (n%10) + getDigSum(n/10);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		mirrorNumSeries(5);
//		treeSeries(5);
		System.out.println(log2(5));
		System.out.println(binary(14));
		
		nToOne(5);
		oneToN(5);
		System.out.println();
		oneToNTail(6,1);
		System.out.println();
		System.out.println(factorialTail(5,1));
		fibonacciPrint(2,0,1);
		System.out.println();
		System.out.println(fibonacciAt(5));
		System.out.println(getSum(5));
		String s = "abjkjba";
		System.out.println(isPalindrome(s,0,s.length() - 1));
		System.out.println(getDigSum(9987));
	}

}
