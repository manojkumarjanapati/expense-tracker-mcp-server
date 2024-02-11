package mathematics;

import java.util.ArrayList;
import java.util.List;

public class Mathematics {

    public static int countDigits(int num){
        int cnt = 0;
        while(num > 0){
            num /= 10;
            cnt++;
        }
        return cnt;
    }

    public static boolean isPalindrome(int num){
        int rev = 0;
        int temp = num;
        while(temp > 0){
            rev = rev * 10 + temp%10;
            temp /= 10;
        }

        return (num == rev);
    }

    public static int factorialIterative(int n){
        int res = 1;
        for(int i = 2; i<=n; i++){
            res *= i;
        }
        return res;
    }

    public static int factorialRecursive(int n){
        if(n == 0 || n == 1) return 1;
        return n * factorialRecursive(n-1);
    }


    public static int trailingZerosInFactorial(int n){
        int ans = 0;

        for(int i = 5; i<=n; i = i*5){
            ans += n/i;
        }

        return ans;
    }

    public static int gcdBySubtraction(int a, int b){
        // Euclidean Algorithm
        while(a != b){
            if(a > b) a -= b;
            else b -= a;
        }
        return a;
    }

    public static int gcdRecursive(int a, int b){
        if(b == 0) return a;
        return gcdRecursive(b,a%b);
    }

    public static int gcdIterative(int a, int b){
        int temp;
        while(b != 0){
            temp = b;
            b = a%b;
            a = temp;
        }

        return a;
    }

    public static int gcdIterative2(int a, int b){
        while(a > 0 && b > 0){
            if(a > b) a = a%b;
            else b = b%a;
        }

        return a + b;
    }

    public static int lcm(int a, int b){
        // formula :- a * b = lcm(a,b) * gcd(a,b)
        return (a * b) / gcdIterative(a,b);
    }

    public static boolean isPrime(int n){
        if(n < 2) return false; // 0 and 1 are not primes
        if(n < 4) return true; // 2 and 3 are primes
        if(n%2 == 0 || n%3 == 0) return false; // checking for 2 and 3 multiples
        for(int i = 5; i*i <= n; i+=6){
            // all primes are either 6*x + 1 or 6*x - 1
            if(n%i == 0 || n%(i+2) == 0) return false;
        }

        return true;
    }

    public static ArrayList<Integer> primeFactorization1(int n){
        ArrayList<Integer> primeFactors = new ArrayList<>();

        if(n <= 1) return primeFactors;
        int i = 2;
        while(n%i == 0){
            primeFactors.add(i);
            n /= i;
        }
        for(i = 3; i*i <= n; i+=2){ // did for i=2 outside loop because now i can increment by 2
            while(n%i == 0){
                primeFactors.add(i);
                n /= i;
            }
        }
        if(n > 1){
            primeFactors.add(n);
        }

        return primeFactors;
    }

    public static ArrayList<Integer> primeFactorization2(int n){
        ArrayList<Integer> primeFactors = new ArrayList<>();

        if(n <= 1) return primeFactors;

        while(n%2 == 0){
            primeFactors.add(2);
            n /= 2;
        }
        while(n%3 == 0){
            primeFactors.add(3);
            n /= 3;
        }
        for(int i = 5; i*i <= n; i+=6){ // did for i=2 outside loop because now i can increment by 2
            while(n%i == 0){
                primeFactors.add(i);
                n /= i;
            }

            while(n%(i+2) == 0){
                primeFactors.add(i+2);
                n /= (i+2);
            }
        }
        if(n > 3){
            primeFactors.add(n);
        }

        return primeFactors;
    }

    public static ArrayList<Integer> allDivisorsSorted(int n){
        ArrayList<Integer> divisors = new ArrayList<>();
        int i;

        // if x*y = n then 1<= x <= n^0.5 <= y <= n
        for(i = 1; i*i <= n; i++){ // traversing 1 --> n^0.5 to find all x
            if(n%i == 0) divisors.add(i);
        }
        for(;i>=1;i--){ // traversing back to 1 to find all y
            if(n%i == 0) divisors.add(n/i);
        }

        return divisors;
    }

    public static void sieveOfEratosthenes(int n){
        boolean[] notPrime = new boolean[n+1];

        for(int i = 2; i*i <= n; i++){
            if(!notPrime[i]){
                for(int j = i+i; j<=n; j+=i){
                    notPrime[j] = true;
                }
            }
        }

        for(int i = 2; i<=n; i++){
            if(!notPrime[i]) System.out.print(i+" ");
        }

    }

    public static void sieveOfEratosthenesOptimized(int n){

        boolean[] notPrime = new boolean[n+1];

        for(int i = 2; i <= n; i++){
            if(!notPrime[i]){
                System.out.print(i+" ");
                for(int j = i*i; j<=n; j+=i){ //  starting from i*i because smaller than i would have covered upto i*i already
                    notPrime[j] = true;
                }
            }
        }
    }

    public static int powerRecursive(int x, int n){
        // time : O(log n)
        // space : O(log n)

        if(n == 0) return 1;
        int temp = powerRecursive(x,n/2);
        temp *= temp;
        if(n%2 == 0) return temp;
        else return temp * x;
    }

    public static int powerIterative(int x, int n){
        // time : O(log n)
        // space : O(1)
        // every number x can be written as sum of powers of 2 [ that is set bits in its binary representation ]

        int res = 1;
        while(n > 0){
            if((n&1) == 1) res *= x;

            x *= x;
            n = n >> 1; // n =n/2;
        }
        return res;
    }

    static List<Integer> findPrimeFactors(int N) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 2;
        while(N>0 && N>=i){
            if(N%i == 0){
                // only primes come here because for all not primes their previous primes would have done the job
                // ex:- if 4 is divisor, then 2 would have done the job by dividing twice (2*2)
                // ex:- for 9, 3 would have done the job  (n/3) 3 times
                ans.add(i);
                N/=i;
            }
            else i++; // increment when current i is no longer a prime divisor
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countDigits(2345));
        System.out.println(isPalindrome(2332));
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(trailingZerosInFactorial(49));
        System.out.println(gcdBySubtraction(35,21));
        System.out.println(gcdRecursive(35,21));
        System.out.println(gcdIterative(35,21));
        System.out.println(gcdIterative2(35,21));
        System.out.println(lcm(20,30));
        System.out.println(isPrime(23));
        System.out.println(primeFactorization1(450));
        System.out.println(primeFactorization2(348));
        System.out.println(allDivisorsSorted(234));
        sieveOfEratosthenes(100);
        System.out.println();
        sieveOfEratosthenesOptimized(100);
        System.out.println();
        System.out.println(powerRecursive(3,4));
        System.out.println(powerIterative(3,4));
        System.out.println(findPrimeFactors(250));
    }
}
