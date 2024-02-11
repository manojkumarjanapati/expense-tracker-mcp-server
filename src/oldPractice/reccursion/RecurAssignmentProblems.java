package oldPractice.reccursion;

import java.util.*;

public class RecurAssignmentProblems {
	
	public static void words(String[] arr,String curr,int k, int N, ArrayList<String> ans) {
		if( k == N) {
			ans.add(curr);
			return;
		}
		for(int i = 0; i<arr[k].length(); i++) {
			words(arr,curr+arr[k].charAt(i),k+1,N,ans);
		}
		
	}
	
	public static ArrayList<String> wordsFromPhoneNumber(int[] a, int N){
		Map<Integer,String> map = new HashMap<>();
		map.put(2, "ABC");
		map.put(3, "DEF");
		map.put(4, "GHI");
		map.put(5, "JKL");
		map.put(6, "MNO");
		map.put(7, "PQRS");
		map.put(8, "TUV");
		map.put(9, "WXYZ");
		String[] arr = new String[N];
		for(int i = 0; i<N;i++) {
			arr[i] = map.get(a[i]);
		}
		
		ArrayList<String> ans = new ArrayList<String>();
		words(arr,"",0,N,ans);
		return ans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {2,3,4};
		ArrayList<String> ans = wordsFromPhoneNumber(a,a.length);
		for(String x: ans) {
			System.out.println(x);
		}
	}

}
