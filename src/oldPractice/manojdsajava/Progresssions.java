package oldPractice.manojdsajava;

public class Progresssions {
	public static int nthTermOfAP(int a, int d, int n) {
		return a + (n -  1)*d;
	}
	public static int nthTermOfGP(int a, int r, int n) {
		return (int) (a * Math.pow(r, n-1));
	}
	
	public static int sumOfNTermsOfAP(int a, int d, int n) {
		return (n/2)*(2 * a + (n-1)*d);
	}
	public static int sumOfNTermsOfGP(int a, int r, int n) {
		return (int) (a * (1 - Math.pow(r, n)) / (1 - r));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// a[first term],d[common difference],r[common ratio],n[no Of Terms]
		int a = 2, d = 2, r = 2, n = 25;
		System.out.println(nthTermOfAP(a,d,n));
		System.out.println(nthTermOfGP(a,r,n));
		System.out.println(sumOfNTermsOfAP(a,d,n));
		System.out.println(sumOfNTermsOfGP(a,r,n));
	}

}
