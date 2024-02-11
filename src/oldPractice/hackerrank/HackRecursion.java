package oldPractice.hackerrank;

import java.util.*;

public class HackRecursion {
	public static int stoneDivision(long n,List<Long> s,int ind) {
		int ans = 0;
		for(int i = 0; i<s.size(); i++) {
			if( ind == i) continue;
			long x = s.get(i);
			if(n % x == 0) {
				int k = (int) (n/x)*stoneDivision(x,s,i) + 1;
				if(k > ans) ans = k;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 12;
		List<Long> s = new ArrayList<Long>();
		s.add(2L);
		s.add(3L);
		s.add(4L);
		int ans = stoneDivision(n,s,-1);
		System.out.println(ans);
	}

}
