package oldPractice.reccursion;

public class  RecurProblems {
	
	public static int maxPieces(int n, int a, int b,int c) {
		if(n == 0) return 0;
		if(n < 0) return -1;
		
		int x = maxPieces(n-a,a,b,c);
		int y = maxPieces(n-b,a,b,c);
		int z = maxPieces(n-c,a,b,c);
		
		int ans = (x>y? (x>z?x:z):(y>z?y:z));
		if (ans == -1) return -1;
		return ans + 1;
	}
	
	
	// My code
	public static void generateSubsets(String s, String curr, int k, int n) {
//		if(k == n-1) {
//			System.out.println(curr);
//			System.out.println(curr+s.charAt(k));
//			return;
//		}
		if(k == n) {
			System.out.print(curr + " ");
			return;
		}
		
		generateSubsets(s,curr,k+1,n);
		generateSubsets(s,curr+s.charAt(k),k+1,n);
	}
	
//	public static void generateSubsets(String s, String curr,int n) {
//		if(n == 0) {
//			System.out.print(curr + " ");
//			return;
//		}
//		
//		generateSubsets(s,curr,n-1);
//		generateSubsets(s,curr+s.charAt(n-1),n-1);
//	}
	
	public static void towerOfHanoi(int n, char A, char B, char C) {
		if(n == 1) {
			System.out.println("Move 1 from "+A+" to "+C);
			return;
		}
		towerOfHanoi(n-1,A,C,B);
		System.out.println("Move "+n+" from "+A+" to "+C);
		towerOfHanoi(n-1,B,A,C);
	}
	
	public static int josephus(int n, int k) {
		if(n == 1) return 0;
		return (josephus(n-1,k) + k)%n;
	}
	
	// My code
//	public static int cntSubsetSum(int[] arr, int sum, int k, int n, int givenSum) {
//		if(k == n) {
//			return (sum == givenSum)? 1 : 0;
//		}
//		
//		return cntSubsetSum(arr,sum,k+1,n,givenSum) + cntSubsetSum(arr,sum+arr[k],k+1,n,givenSum);
//	}
	
	public static void permute(char[] s, int i,int n) {
		if(i == n-1) {
			System.out.println(s);
			return;
		}
		char tmp;
		for(int j = i; j<n; j++) {
			System.out.println(i + " " + j);
			tmp = s[i];
			s[i] = s[j];
			s[j] = tmp;
			permute(s,i+1,n);
			System.out.println(i + " " + j);
			tmp = s[j];
			s[j] = s[i];
			s[i] = tmp;
		}
	}
	
	public static int cntSubsetSum(int[] arr, int n, int sum) {
		if(n == 0) {
			return (sum == 0)? 1 : 0;
		}
		
		return cntSubsetSum(arr,n-1,sum) + cntSubsetSum(arr,n-1,sum-arr[n-1]);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 23,a = 11,b=9,c=12;
		System.out.println("Rope cutting Problem: ");
		System.out.println(maxPieces(n,a,b,c));
		
		System.out.println("All subsets problem: ");
		String s = "ABC";
		String curr = "";
		int k = 0;
		generateSubsets(s,curr,k,s.length());
		
		System.out.println("\nTower Of Hanoi Problem: ");
		towerOfHanoi(4,'A','B','C');
		
		System.out.println("Josephus Problem: ");
		System.out.println("Survived : "+josephus(8,3));
		
		int[] arr = {10,5,2,3,6};
		int sum = 8;
		System.out.println("cntSubsetSum Problem: "+cntSubsetSum(arr,arr.length,sum));
		
		System.out.println("Permutations :");
		permute("ABC".toCharArray(),0,3);
	}

}
