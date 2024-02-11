package oldPractice.manojdsajava;

public class TrailingZerosInFactorial {
	public static int trailingzerosInFact(int x) {
		// For even smaller values of n, The factorial is going to be large and our datatypes over flow.
		int fact = 1;
		for(int i = 2; i<=x; i++) {
			fact = fact * i;
		}
		System.out.println(fact);
		int cnt = 0;
		while (fact%10 == 0) {
			cnt++;
			fact = fact/10;
		}
		return cnt;
	}
	
	public static int trailingZerosInFactorialBest(int x) {
		// In factorial representation, we need to find number of 5s and 2s as they contribute for zeros...
		// number of 2s is going to be definitely large enough than number of 5s...so we just find no Of 5s...
		int ans = 0;
		for(int i = 5; i <= x; i = i*5) {
			ans += x/i;
		}
		return ans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 600;
		System.out.println(trailingZerosInFactorialBest(x));

	}

}
