package oldPractice.bitmanipulation;

public class CountBits {
	public static int cntBitsN(int n) {
		int k =(int) (Math.log(n)/Math.log(2));
		return k;
	}
	
	public static int fun(int n) {
		int[] table = new int[256];
		int[] tablesum = new int[256];
		table[0] = 0;
		tablesum[0] = 0;
		int k = 0;
		for(int i = 1;i<256; i++) {
			table[i] = (i&1) + table[i/2];
			k += table[i];
			tablesum[i] = k;
		}
		return tablesum[n];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(cntBitsN(16));
		System.out.println(fun(8));
	}

}
