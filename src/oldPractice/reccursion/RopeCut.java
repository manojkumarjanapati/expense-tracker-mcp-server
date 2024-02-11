package oldPractice.reccursion;

public class RopeCut {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 23,a = 11,b=9,c=12;
		System.out.println(maxPieces(n,a,b,c));
	}

}
