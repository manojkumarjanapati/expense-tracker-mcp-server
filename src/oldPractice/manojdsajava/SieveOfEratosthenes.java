package oldPractice.manojdsajava;

public class SieveOfEratosthenes {
	
	// Primes in given range 2 - n
	public static void sieveOfEratosthenes(int n) {
		boolean[] arr = new boolean[n+1];
		for(int i = 2; i*i<=n; i++) {
			if(!arr[i]) {
				for(int j = i*i; j<=n; j += i) {
					arr[j] = true;
				}
			}
		}
		for(int i = 2; i<=n; i++) {
			if(!arr[i])
				System.out.println(i);
		}
	}
	
	public static void sieveOptimized(int n) {
		System.out.print("All Primes less than " + n + " :");
		boolean[] arr = new boolean[n+1];
		int i = 2, j;
		System.out.print(" "+2); // printing 2
		for(j = i*i; j<=n; j+=i)
			arr[j] = true;
		// printing primes from 3 to n**0.5 and marking composites
		for(i = 3; i*i <= n; i+=2) {
			// only odds are primes except 2
			if(!arr[i]) {
				System.out.print(" "+i);
				for(j = i*i; j<=n; j+=i)
					arr[j] = true;
			}
		}
		// printing primes from n**0.5 to n as all composites marked already by above code
		for(; i<=n; i+=2) {
			if(!arr[i])
				System.out.print(" "+i);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sieveOptimized(1000);
	}

}
