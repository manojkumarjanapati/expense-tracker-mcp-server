package oldPractice.manojdsajava;

public class Mathematics {
	
	// Prime number checking Sieve of Eratosthenes
	public static boolean isPrime(int x) {
		if (x <= 1) return false;
		if ( x == 2 || x == 3) return true;
		if( x%2 == 0 || x%3 == 0) return false;
		for(int i = 5; i*i <= x ; i+=6) {
			if(x%i == 0 || x%(i+2) == 0) return false;
		}
		return true;
	}
	
	// Prime Factors of a Number
	public static void primeFactors(int n) {
		System.out.print("Prime factors of " + n + " are :");
		if( n <= 1 ) {
			System.out.print(" None");
			return;
		}
		while(n%2 == 0) {
			System.out.print(" "+2);
			n = n/2;
		}
		while(n%3 == 0) {
			System.out.print(" "+3);
			n = n/3;
		}
		for(int i = 5; i*i <= n; i+=6) {
			while(n%i == 0) {
				System.out.print(" "+i);
				n = n/i;
			}
			while(n%(i+2) == 0) {
				System.out.print(" "+(i+2));
				n = n/(i+2);
			}
		}
		if (n > 3)
			System.out.println(" "+n);
		System.out.println();
	}
	
	// Factors  of a number
	public static void factors(int n){
        for(int i = 1; i*i <= n; i++){
            if(n%i == 0){
                System.out.println(i);
                if(i != n/i)
                    System.out.println(n/i);
            }
        }
    }
    public static void recurFactorsSorted(int n,int i){
        if(i*i > n) return;
        if(n%i == 0)
            System.out.println(i);
        recurFactorsSorted(n,i+1);
        if(n%i == 0 && i != n/i)
            System.out.println(n/i);
    }
    public static void iterFactorsSorted(int n){
    	int i;
        for(i = 1; i*i <= n; i++){
            if(n%i == 0){
                System.out.println(i);
            }
        }
        for(;i>=1;i--) {
        	if(n%i == 0)
        		System.out.println(n/i);
        }
    }
    
	
	// No of digits of a number
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
	
	// Progressions AP & GP
	public static int nthTermOfAP(int a, int d, int n) {
		return a + (n -  1)*d;
	}
	public static int nthTermOfGP(int a, int r, int n) {
		return (int) (a * Math.pow(r, n-1));
	}
	
	public static int sumOfNTermsOfAP(int a, int d, int n) {
		return (n/2)*(2 * a + (n-1)*d);
	}
	public static int sumOfNTermsOfGP(int a, int r, int n) {
		return (int) (a * (1 - Math.pow(r, n)) / (1 - r));
	}
	
	// Trailing Zeros in Factorial of a number
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
	
	// GCD(a,b)
	public static int recurGCD(int a, int b) {
		if(b == 0) return a;
		return recurGCD(b,a%b);
	}
	
	public static int iterGCD(int a, int b) {
		int temp;
		while(b > 0) {
			temp = a;
			a = b;
			b = temp%b;
		}
		return a;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// IsPrime
		System.out.println(isPrime(1000000007));
		
		// Prime factors of a number
		primeFactors(45);
		
		// No of digits testing...
		long x = 2349;
		System.out.println(iterNoOfDigits(x));
		System.out.println(recurNoOfDigits(x));
		System.out.println(logNoOfDigits(x));
		
		// Progressions testing...
		// a[first term],d[common difference],r[common ratio],n[no Of Terms]
		int a = 2, d = 2, r = 2, n = 25;
		System.out.println(nthTermOfAP(a,d,n));
		System.out.println(nthTermOfGP(a,r,n));
		System.out.println(sumOfNTermsOfAP(a,d,n));
		System.out.println(sumOfNTermsOfGP(a,r,n));
		
		// Trailing Zeros  testing
		int k = 600;
		System.out.println(trailingZerosInFactorialBest(k));
		
		// GCD testing 
		System.out.println(recurGCD(35,42));
		System.out.println(iterGCD(52,24));
		
	}

}
