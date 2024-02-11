package oldPractice.manojdsajava;

public class NoOfDigits {
	public static int iterNoOfDigits(long x) {
		// iterative solution
		int cnt = 0;
		while(x != 0) {
			x = x/10;
			++cnt;
		}
		return cnt;
	}
	
	public static int recurNoOfDigits(long x) {
		// recursive solution
		if (x == 0) return 0;
		return 1 + recurNoOfDigits(x/10);
	}
	
	public static int logNoOfDigits(long x) {
		// logarithmic solution
		return (int)Math.ceil(Math.log10(x));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long x = 2349;
		System.out.println(iterNoOfDigits(x));
		System.out.println(recurNoOfDigits(x));
		System.out.println(logNoOfDigits(x));
	}

}
