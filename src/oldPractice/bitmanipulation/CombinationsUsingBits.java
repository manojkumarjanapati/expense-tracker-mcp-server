package oldPractice.bitmanipulation;


public class CombinationsUsingBits {
	public static String[] combinations(String s) {
		// Time complexity: O(2**n * n)
		int n = s.length();
		int k = (int)Math.pow(2,n);
		String[] ans = new String[k];
		for(int i = 0; i< k; i++) {
			String sub = "";
			for(int j = 0; j<n; j++) {
				if((i & (1 << j)) != 0) sub += s.charAt(j);
			}
			ans[i] = sub;
		}
		return ans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "Manoj";
		String[] ans = combinations(s);
		for(String x : ans) {
			System.out.println(x);
		}
	}

}
