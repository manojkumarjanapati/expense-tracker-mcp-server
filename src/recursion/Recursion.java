package recursion;

public class Recursion {

    public static void recursionPractice1(int n){
        if(n == 0) return;
        recursionPractice1(n-1);
        System.out.print(n+" ");
        recursionPractice1(n-1);
    }

    public static void printBinary(int n){
        if(n == 0) return;
        printBinary(n/2);
        System.out.print(n%2);
    }

    public static void printNTo1(int n){
        if(n == 0) return;
        System.out.print(n+" ");
        printNTo1(n-1);
    }

    public static void print1ToN(int n){
        if(n == 0) return;
        print1ToN(n-1);
        System.out.print(n + " ");
    }

    public static void print1toNTailRecursion(int n, int k){ // initially pass k as 1
        // Tail recursion is faster than non-tail recursion because of tail call elimination in modern compilers
        // Tail recursion doesn't need to store tha caller function status. so O(1) auxilary space

        // i am adding k as additional parameter to traverse in reverse order
        if(n == 0) return;
        System.out.print(k+" ");
        print1toNTailRecursion(n-1,k+1);
    }

    public static int factorial(int n){
        if(n < 0) return -1; // just returning -1 in case of -ve numbers
        if(n <= 1) return 1; // 0! and 1! are 1

        return n * factorial(n-1);

    }

    public static int factorialTailRecursion(int n, int k){ // initially pass k as 1
        if(n < 0) return -1; // just returning -1 in case of -ve numbers
        if(n <= 1) return k; // returning k as it is factorial of n now

        return factorialTailRecursion(n-1,k*n);

    }

    public static int nthFibonacci(int n){

        // what is Base case : which directly have an answer or which can not be further divided into subproblems.
        // Base case terminates the recursion
        
//        if(n < 0) return -1; // -1 for -ve numbers
//        if(n == 0) return 0; // first fib number
//        if(n == 1) return 1; // second fib number

        if(n <= 1) return n; // single basecase for 0 and 1 and for -ve numbers i'm just returning same number

        return nthFibonacci(n-1) + nthFibonacci(n-2);
    }

    public static int sumOfN(int n){
        if(n == 0) return 0;
        return n + sumOfN(n-1);
    }

    public static boolean isPalindrome(String s,int start, int end){
        if(start >= end) return true;

        // logical && below works in short circuit manner. it will return false when first condition is false.
        return (s.charAt(start) == s.charAt(end)) && isPalindrome(s,start+1,end-1);
    }

    public static int sumOfDigits(int n){
        if(n <= 9) return n;
        return sumOfDigits(n/10) + n%10;
    }

    public static int cutTheRope(int n, int a, int b, int c){
        /* Problem Statement
        * rope length : n
        * allowed piece lengths : a,b,c
        * cut the rope into maximum number of pieces with allowed piece lengths.
        * if not possible return -1
        * */

        if(n < 0) return -1;
        if(n == 0) return 0;

        int max = cutTheRope(n-a,a,b,c);
        int next = cutTheRope(n-b,a,b,c);
        if(next > max) max = next;
        next = cutTheRope(n-c,a,b,c);
        if(next > max) max = next;

        if(max == -1) return -1;

        return max + 1; // adding 1 as this cut is valid.
    }

    public static void printAllSubSequences(String s, String currSubSeq, int idx){
        // initial call with currSubSeq = "", idx = 0;
        if(idx == s.length()){
            System.out.print(currSubSeq+" ");
            return;
        }

        printAllSubSequences(s,currSubSeq,idx+1);
        printAllSubSequences(s,currSubSeq+s.charAt(idx),idx+1);
    }

    public static void towerOfHanoi(int n,char a, char b, char c){
        if(n == 0) return;
        towerOfHanoi(n-1,a,c,b);
        System.out.println("Move Disc "+ n + " : " + a + "->" + c);
        towerOfHanoi(n-1,b,a,c);
    }

    public static int josephus(int n, int k){
        if(n == 1) return 0;

        return (josephus(n-1,k)+k)%n;
    }

    public static int countOfSubSetWithGivenSum(int[] arr, int n, int sum){
        if(n == 0){
            return (sum == 0) ? 1 : 0;
        }

        return countOfSubSetWithGivenSum(arr,n-1,sum) + countOfSubSetWithGivenSum(arr,n-1,sum-arr[n-1]);
    }

    public static void printAllPermutations(String s,int n,String curr){
        /* My logic

        * case s="" : nothing
        * case s="A" : A
        * case s="AB" : we add B in every place while we traverse one of the permutations of previous sub string "A" -> BA, AB
        * case s="ABC" :
        * we add C in every place while we traverse one of the permutations of previous sub string "AB"
        *  permutaions of "AB" are :  BA , AB
        * SO,
        * FOR BA: CBA,BCA,BAC
        * FOR AB: CAB,ACB,ABC
        *
        * */

        if(n == 0){
            System.out.print(curr+" ");
            return;
        }
        for(int i = 0; i<=curr.length(); i++){
            printAllPermutations(s,n-1,curr.substring(0,i)+s.charAt(n-1)+curr.substring(i));
        }

    }

    public static void permute(char[] s, int i,int n) {
        if(i == n-1) {
            System.out.println(s);
            return;
        }
        char tmp;
        for(int j = i; j<n; j++) {
//            System.out.println(i + " " + j);
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            permute(s,i+1,n);
//            System.out.println(i + " " + j);
            tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;
        }
    }


    public static void main(String[] args) {

        /* THINK RECURSIVELY
        * IF YOU KNOW SOLUTION FOR A SUB PROBLEM, HOW DO SPLIT BIG PROBLEM INTO SUB PROBLEMS
        * THINK ABOUT BASE CASES FIRST [TERMINATING CASES]
        * */

        recursionPractice1(5);
        System.out.println();
        printBinary(13);
        System.out.println();
        printNTo1(5);
        System.out.println();
        print1ToN(9);
        System.out.println();
        print1toNTailRecursion(9,1);
        System.out.println();

        System.out.println(factorial(5));
        System.out.println(factorialTailRecursion(5,1));

        System.out.println(nthFibonacci(6));

        System.out.println(sumOfN(5));

        System.out.println(isPalindrome("racear",0,5));

        System.out.println(sumOfDigits(2234));

        System.out.println(cutTheRope(6,4,3,2));

        printAllSubSequences("ABC","",0);
        System.out.println();

        towerOfHanoi(4,'A','B','C');

        System.out.println(josephus(5,3));

        System.out.println(countOfSubSetWithGivenSum(new int[]{10,20,15,5},4,25));

        printAllPermutations("ABC",3,"");

        System.out.println();

        System.out.println("Permutations :");
        permute("ABC".toCharArray(),0,3);


    }
}
