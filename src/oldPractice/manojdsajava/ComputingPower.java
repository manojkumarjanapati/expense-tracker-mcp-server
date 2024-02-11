package oldPractice.manojdsajava;

import java.util.*;
import java.lang.*;

public class ComputingPower {
	
	public static int recurPower(int x, int n) {
		if(n==0) return 1;
		int temp = recurPower(x,n/2);
		temp *= temp;
		return (n%2==0)? temp : temp*x;
		
	}
	
	public static int iterPower(int x, int n) {
		int ans = 1;
		while(n > 0) {
			if(n%2!=0) {
				// bit 1
				ans *= x;
			}
			else {
				// bit 0
				// we are doing nothing here
			}
			n = n>>1; // n = n/2
			x = x * x;
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(iterPower(5,10));
		System.out.println(recurPower(5,10));
		System.out.println(Math.pow(5,10));
	}

}
