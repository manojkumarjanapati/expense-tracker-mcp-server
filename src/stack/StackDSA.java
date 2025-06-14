package stack;

import java.util.*;

class ArrayStack {
    int[] arr;
    int capacity;
    int top;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        top = -1;
    }

    public void push(int x){
        if(top + 1 < capacity){
            top++;
            arr[top] = x;
        }else{
            System.out.println("over flow");
        }
    }

    public int peek(){
        if(top != -1){
            return arr[top];
        }
        System.out.println("under flow");
        return -1;
    }

    public int pop(){
        if(top >= 0){
            int res = arr[top];
            top--;
            return res;
        }
        System.out.println("under flow");
        return -1;
    }

    public int size(){
        return top+1;
    }

    public boolean isEmpty(){
        return (top == -1);
    }
}


class ArrayListStack{
    ArrayList<Integer> arr = new ArrayList<>();

    void push(int x){
        arr.add(x);
    }

    int pop(){
        int res = arr.get(arr.size()-1);
        arr.remove(arr.size()-1);
        return res;
    }

    int peek(){
        return arr.get(arr.size()-1);
    }

    boolean isEmpty(){
        return arr.isEmpty();
    }

    int size(){
        return arr.size();
    }
}


class LinkedListStack{
    class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    Node head;
    int size;

    public LinkedListStack() {
        size = 0;
        head = null;
    }

    void push(int x){
        Node tmp = new Node(x);
        tmp.next = head;
        head = tmp;
        size++;
    }

    int pop(){
        if(head == null){
            System.out.println("underflow");
            return Integer.MAX_VALUE;
        }
        int res = head.data;
        head = head.next;
        size--;

        return res;
    }

    int peek(){
        if(head == null){
            System.out.println("underflow");
            return Integer.MAX_VALUE;
        }
        return head.data;
    }

    int size(){
        return size;
    }

    boolean isEmpty(){
        return (head == null);
    }


}


class TwoStacks<T>{
    T[] arr;
    int cap;

    int top1,top2;

    public TwoStacks(int cap) {
        this.cap = cap;
        arr = (T[]) new Object[cap];
        top1 = -1;
        top2 = cap;
    }

    boolean push1(T item){
        if(top1<top2-1){
            top1++;
            arr[top1] = item;
            return true;
        }
        return false;
    }

    boolean push2(T item){
        if(top1<top2-1){
            top2--;
            arr[top2] = item;
            return true;
        }
        return false;
    }

    T peek1(){
        if(top1 == -1) return null;
        return arr[top1];
    }

    T peek2(){
        if(top2 == cap) return null;
        return arr[top2];
    }

    T pop1(){
        if(top1 == -1) return null;
        T res = arr[top1];
        top1--;
        return res;
    }

    T pop2(){
        if(top2 == cap) return null;
        T res = arr[top2];
        top2++;
        return res;
    }

    int size1(){
        return top1+1;
    }

    int size2(){
        return cap-top2;
    }
}



class KStacks{
    int[] arr;
    int[] top;
    int[] next;

    int cap;
    int k;
    int freeTop=0;

    public KStacks(int cap, int k) {
        this.cap = cap;
        this.k = k;
        arr = new int[cap];
        next = new int[cap];
        top = new int[k];

        Arrays.fill(top,-1);
        for(int i = 0; i<next.length-1; i++) next[i] = i+1;
        next[next.length-1] = -1;
    }

    boolean push(int sn, int x){
        if(isFull()){
            System.out.println("Stack overflow");
            return false;
        }

        int idx = freeTop;
        freeTop = next[idx];

        next[idx] = top[sn];
        top[sn] = idx;

        arr[idx] = x;
        return true;
    }

    int pop(int sn){
        if(isEmpty(sn)){
            System.out.println("Stack Underflow");
            return -1;
        }

        int idx = top[sn];
        top[sn] = next[idx];

        next[idx] = freeTop;
        freeTop = idx;

        return arr[idx];
    }

    boolean isEmpty(int sn){
        return top[sn] == -1;
    }

    boolean isFull(){
        return freeTop == -1;
    }
}


class StackWithMin{
    ArrayList<Integer> arr;
    int min;

    public StackWithMin() {
        arr = new ArrayList<>();
        min = Integer.MAX_VALUE;
    }

    boolean push(int x){
        if(arr.isEmpty()){
            arr.add(x);
            min = x;
        }
        else if(x < min){
            int prevMin = min;
            min = x;
            arr.add(2*min-prevMin);
        }
        else{
            arr.add(x);
        }
        return true;
    }

    int pop(){
        if(arr.isEmpty()){
            System.out.println("underflow");
            return Integer.MAX_VALUE;
        }

        int ele = arr.get(arr.size()-1);
        int res = ele;
        if(ele < min){
            res = min;
            int prevMin = 2*min-ele;
            min = prevMin;
        }
        arr.remove(arr.size()-1);
        return res;
    }

    int peek(){
        int res = arr.get(arr.size()-1);
        return (res < min) ? min : res;
    }

    int getMin(){
        return min;
    }

    @Override
    public String toString() {
        return "StackWithMin{" +
                "arr=" + arr +
                ", min=" + min +
                '}';
    }
}




public class StackDSA {

    public static void testArrayStack(){
        ArrayStack s = new ArrayStack(10);
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        System.out.println(s.size());
    }

    public static void testArrayListStack(){
        ArrayListStack s = new ArrayListStack();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        System.out.println(s.size());
    }

    public static void testLinkedListStack(){
        LinkedListStack s = new LinkedListStack();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        System.out.println(s.size());
    }

    public static boolean balancedParenthesis(String str){
        HashMap<Character,Character> hm = new HashMap<>();
        hm.put('}','{');
        hm.put(']','[');
        hm.put(')','(');

        Stack<Character> s = new Stack<>();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(hm.get(ch) != null){
                if(s.isEmpty() || s.peek() != hm.get(ch)) return false;
                s.pop();
            }
            else{
                s.push(ch);
            }
        }

        return s.isEmpty();
    }

    public static boolean matching(char a, char b){
        return ((a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}'));
    }
    public static boolean balancedParenthesisAuthor(String str){

        Deque<Character> s = new ArrayDeque<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{'){
                s.push(ch);
            }else{
                if(s.isEmpty() || matching(s.peek(),ch)) return false;
                s.pop();
            }
        }

        return s.isEmpty();
    }

    public static void testTwoStacks(){
        TwoStacks<Integer> stacks = new TwoStacks<>(10);
        stacks.push1(1);
        stacks.push1(2);
        stacks.push1(3);
        stacks.push2(8);
        stacks.push2(9);
        stacks.push2(10);

        System.out.println(stacks.size1());
        System.out.println(stacks.size2());

        System.out.println(stacks.peek1());
        stacks.pop1();
        System.out.println(stacks.peek1());
        System.out.println(stacks.pop2());
        System.out.println(stacks.peek2());

    }

    public static void testKStack(){

        // Let us create 3 stacks in an array of size 10
        int k = 3, n = 10;

        KStacks ks = new KStacks(n, k);

        ks.push(2, 15);
        ks.push(2, 45);

        // Let us put some items in stack number 1
        ks.push(1, 17);
        ks.push(1, 49);
        ks.push(1, 39);

        // Let us put some items in stack number 0
        ks.push(0, 11);
        ks.push(0, 9);
        ks.push(0, 7);

        System.out.println("Popped element from stack 2 is " + ks.pop(2));
        System.out.println("Popped element from stack 1 is " + ks.pop(1));
        System.out.println("Popped element from stack 0 is " + ks.pop(0));
    }


    public static int[] stockSpanManoj(int[] stocks){
        int n = stocks.length;
        int[] ans = new int[n];

        Stack<Integer> prevMaxIndex = new Stack<>();
        prevMaxIndex.push(-1);

        for(int i = 0; i<n; i++){
            while(prevMaxIndex.peek() != -1 && stocks[prevMaxIndex.peek()] <= stocks[i]){
                prevMaxIndex.pop();
            }

            int span = i - prevMaxIndex.peek();
            ans[i] = span;
            prevMaxIndex.push(i);
        }

        return ans;
    }

    public static int[] stockSpanAuthor(int[] stocks){
        int n = stocks.length;
        int[] ans = new int[n];
        int k = 0;

        Stack<Integer> prevMaxIndex = new Stack<>();
        prevMaxIndex.push(0); // processing first element
        ans[k++] = 1; // span of first element

        for(int i = 1; i<n; i++){
            while(prevMaxIndex.isEmpty() == false && stocks[prevMaxIndex.peek()] <= stocks[i]){
                prevMaxIndex.pop();
            }

            int span = (prevMaxIndex.isEmpty())? i+1 : i - prevMaxIndex.peek();
            ans[k++] = span;
            prevMaxIndex.push(i);
        }

        return ans;
    }

    public static int[] previousGreaterElement(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];

        Stack<Integer> prevGreaterElements = new Stack<>();

        int k = 0;
        for(int x: arr){
            while(prevGreaterElements.isEmpty() == false && prevGreaterElements.peek() <= x){
                prevGreaterElements.pop();
            }

            ans[k++] = (prevGreaterElements.isEmpty()) ? -1 : prevGreaterElements.peek();
            prevGreaterElements.push(x);

        }

        return ans;
    }


    public static int[] nextGreaterElement(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];

        Stack<Integer> nextGreaterElements = new Stack<>();

        for(int i = n-1; i>=0; i--){
            int x = arr[i];
            while(nextGreaterElements.isEmpty() == false && nextGreaterElements.peek() <= x){
                nextGreaterElements.pop();
            }

            ans[i] = (nextGreaterElements.isEmpty()) ? -1 : nextGreaterElements.peek();
            nextGreaterElements.push(x);
        }

        return ans;
    }


    public static int largestRectangularAreaInHistogramBetter(int[] arr){
        int ans = 0; // area will be greater than 0

        int n = arr.length;
        int[] spans = new int[n];
        Stack<Integer> prevMinIndex = new Stack<>();
        prevMinIndex.push(-1);
        Stack<Integer> nextMinIndex = new Stack<>();
        nextMinIndex.push(n);

        for(int i = 0; i<n; i++){
            int x = arr[i];
            while(prevMinIndex.peek() != -1 && arr[prevMinIndex.peek()] >= x){
                prevMinIndex.pop();
            }

            int leftSpan = i - prevMinIndex.peek();
            prevMinIndex.push(i);

            spans[i] += leftSpan;
        }

        for(int i = n-1; i>=0; i--){
            int x = arr[i];
            while(nextMinIndex.peek() != n && arr[nextMinIndex.peek()] >= x){
                nextMinIndex.pop();
            }

            int rightSpan = nextMinIndex.peek() - i;
            nextMinIndex.push(i);

            spans[i] += rightSpan-1; // current element already counted in leftspan

            ans = Math.max(ans,x*spans[i]);
        }

        return ans;

    }


    public static int largestRectangularAreaInHistogramBest(int[] arr){
        int n = arr.length;
        int ans = 0;
        Stack<Integer> prevMinIndex = new Stack<>();
        prevMinIndex.push(-1);

        for(int i = 0; i<n; i++){
            int x = arr[i];
            while(prevMinIndex.peek() != -1 && arr[prevMinIndex.peek()] >= x){
                int prevSmaller = arr[prevMinIndex.pop()]; // removing prev smaller
                // after poping prevMinIndex.peek() return previous smaller for prevSmaller
                // since it is being popped by x. x is the next smaller
                // so i-prevIndex.peek()-1 is the total span. I mean this is base in area
                // prevSmaller is height. so area = height * base
                int areaOfPrevSmaller = prevSmaller * ( i - prevMinIndex.peek() - 1);

                ans = Math.max(ans,areaOfPrevSmaller);

            }
            prevMinIndex.push(i);
        }

        while(prevMinIndex.peek() != -1){
            int prevSmaller = arr[prevMinIndex.pop()];
            int areaOfPrevSmaller = prevSmaller * ( n - prevMinIndex.peek() - 1);
            ans = Math.max(ans,areaOfPrevSmaller);
        }

        return ans;
    }



    public static int largestRectangularAreaInHistogramBestAuthor(int[] arr){
        int n = arr.length;
        int ans = 0;
        Stack<Integer> prevMinIndex = new Stack<>();

        for(int i = 0; i<n; i++){
            int x = arr[i];
            while( !prevMinIndex.isEmpty() && arr[prevMinIndex.peek()] >= x){
                int prevSmaller = arr[prevMinIndex.pop()]; // removing prev smaller
                // after poping prevMinIndex.peek() return previous smaller for prevSmaller
                // since it is being popped by x. x is the next smaller
                // so i-prevIndex.peek()-1 is the total span. I mean this is base in area
                // prevSmaller is height. so area = height * base
                int areaOfPrevSmaller = prevSmaller * (prevMinIndex.isEmpty()? i : i - prevMinIndex.peek() - 1);

                ans = Math.max(ans,areaOfPrevSmaller);

            }
            prevMinIndex.push(i);
        }

        while(!prevMinIndex.isEmpty()){
            int prevSmaller = arr[prevMinIndex.pop()];
            int areaOfPrevSmaller = prevSmaller * ( prevMinIndex.isEmpty()? n : n - prevMinIndex.peek() - 1);
            ans = Math.max(ans,areaOfPrevSmaller);
        }

        return ans;
    }


    public static int largestRectangleWithAll1sInMatrix(int[][] mat){
        int ans = largestRectangularAreaInHistogramBest(mat[0]);

        for(int r = 1; r<mat.length; r++){
            for(int c = 0; c<mat[0].length;c++){
                if(mat[r][c] == 1){
                    mat[r][c] += mat[r-1][c];
                }
            }

            ans = Math.max(ans,largestRectangularAreaInHistogramBest(mat[r]));
        }

        return ans;

    }




    public static void testStackWithMin(){
        System.out.println("Testing Stack with getMin in O(1)");
        StackWithMin s = new StackWithMin();
        s.push(5);System.out.println(s);
        s.push(10);System.out.println(s);
        s.push(20);System.out.println(s);
        s.push(2);System.out.println(s);
        s.push(6);System.out.println(s);
        s.push(4);System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        s.push(2);System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        s.push(1);System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
        System.out.println(s.pop());System.out.println(s);
    }



    public static String infixToPostfix(String infix){
        String postfix = "";
        Stack<Character> operators = new Stack<>();

        HashMap<Character,Integer> precedence = new HashMap<>();
        // precedence order for prefix : +,- -> *,/ -> ^
        precedence.put('(',0);
        precedence.put('+',1);
        precedence.put('-',1);
        precedence.put('*',2);
        precedence.put('/',2);
        precedence.put('^',3);

        for(int i = 0; i<infix.length(); i++){
            char ch = infix.charAt(i);
            // left parenthesis
            if(ch == '('){
                operators.push(ch);
            }
            // right parenthesis
            else if(ch == ')'){
                while(!operators.isEmpty() && operators.peek() != '('){
                    postfix += operators.pop();
                }
                if(operators.isEmpty()) return "Invalid Expression";
                else operators.pop(); // removing '('
            }
            // operator
            else if(precedence.containsKey(ch)){
                // current operator ch should be the highest precedence in stack after pushing
                while(!operators.isEmpty() && precedence.get(ch) <= precedence.get(operators.peek())){
                    if( ch == '^' && ch == operators.peek() ){
                        // since ^ has right to left associativity
                        // current ch='^' is high precedence than operator.peek()='^'
                        // later occurred '^' is high precedence than previously occurred '^'
                        break;
                        // breaking because we can push ch as it is high now.
                    }
                    postfix += operators.pop();
                }
                operators.push(ch); // it is high precence operator in stack
            }
            else{
                // operand
                postfix += ch;
            }
        }

        while(!operators.isEmpty()) postfix += operators.pop();

        return postfix;
    }

    public static String infixToPrefix(String infix){
        StringBuilder prefix = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        HashMap<Character,Integer> precedence = new HashMap<>();
        // precedence order for prefix : ) -> +,- -> *,/ -> ^
        precedence.put(')',0);
        precedence.put('+',1);
        precedence.put('-',1);
        precedence.put('*',2);
        precedence.put('/',2);
        precedence.put('^',3);

        for(int i = infix.length()-1; i>=0; i--){
            char ch = infix.charAt(i);
            // right parenthesis
            if(ch == ')'){
                operators.push(ch);
            }
            // left parenthesis
            else if(ch == '('){
                while(!operators.isEmpty() && operators.peek() != ')'){
                    prefix.append(operators.pop());
                }
                if(operators.isEmpty()) return "Invalid Expression";
                else operators.pop(); // removing ')'
            }
            // operator
            else if(precedence.containsKey(ch)){
                // current operator ch should be the highest precedence in stack after pushing
                while(!operators.isEmpty() && precedence.get(ch) <= precedence.get(operators.peek())){
                    if( ch != '^' && ch == operators.peek() ){
                        // +,- & *,/ have left to right associativity
                        // but we are traversing infix in right to left
                        // so the current operator will have high precedence as it is the left one
                        // ch is left occurred and operators.peek is right occured
                        // as ch is already has high associativity we are not poping
                        break;
                    }
                    prefix.append(operators.pop());
                }
                operators.push(ch); // it is high precence operator in stack
            }
            else{
                // operand
                prefix.append(ch);
            }
        }

        while(!operators.isEmpty()){
            prefix.append(operators.pop());
        }

        return prefix.reverse().toString();
    }

    public static int evaluatePostfix(String postfix){
        String[] tokens = postfix.split(" ");

        Stack<Integer> s = new Stack<>();
        HashSet<String> hs = new HashSet<>();
        hs.add("+"); hs.add("-"); hs.add("*"); hs.add("/"); hs.add("^");

        for(int i = 0; i<tokens.length;i++){
            String token = tokens[i];
            if(hs.contains(token)){
                int op2 = s.pop();
                int op1 = s.pop();
                int res = 0;
                if(token.equals("+")) res = op1 + op2;
                else if(token.equals("-")) res = op1 - op2;
                else if(token.equals("*")) res = op1 * op2;
                else if(token.equals("/")) res = op1 / op2;
                else if(token.equals("^")) res = (int) Math.pow(op1,op2);
                s.push(res);
            }else{
                s.push(Integer.valueOf(token));
            }
        }

        return s.peek();
    }

    public static int evaluatePrefix(String prefix){
        String[] tokens = prefix.split(" ");

        Stack<Integer> s = new Stack<>();
        HashSet<String> hs = new HashSet<>();
        hs.add("+"); hs.add("-"); hs.add("*"); hs.add("/"); hs.add("^");

        for(int i = tokens.length-1; i>=0; i--){
            String token = tokens[i];
            if(hs.contains(token)){
                int op1 = s.pop();
                int op2 = s.pop();
                int res = 0;
                if(token.equals("+")) res = op1 + op2;
                else if(token.equals("-")) res = op1 - op2;
                else if(token.equals("*")) res = op1 * op2;
                else if(token.equals("/")) res = op1 / op2;
                else if(token.equals("^")) res = (int) Math.pow(op1,op2);
                s.push(res);
            }else{
                s.push(Integer.valueOf(token));
            }
        }

        return s.peek();
    }



    public static void main(String[] args) {
        testArrayStack();
        testArrayListStack();
        testLinkedListStack();

        String str = "(()))";
        System.out.println(balancedParenthesis(str));
        System.out.println(balancedParenthesisAuthor(str));

        testTwoStacks();
        testKStack();

        int[] stocks = {60,10,20,40,35,38,50,70,65};
//        int[] spans = stockSpanManoj(stocks);
        int[] spans = stockSpanAuthor(stocks);
        System.out.println(Arrays.toString(spans));

        int[] arr1 = {15,10,18,12,4,6,2,8};

        System.out.println(Arrays.toString(previousGreaterElement(arr1)));
        System.out.println(Arrays.toString(nextGreaterElement(arr1)));

        int[] arr2 = {2,5,1};
        System.out.println(largestRectangularAreaInHistogramBetter(arr2));
        System.out.println(largestRectangularAreaInHistogramBest(arr2));
        System.out.println(largestRectangularAreaInHistogramBestAuthor(arr2));

        int[][] mat = {{1,0,0,1,1},{0,0,0,1,1},{1,1,1,1,1},{0,1,1,1,1}};
        System.out.println(largestRectangleWithAll1sInMatrix(mat));

        testStackWithMin();

        String infix1 = "x^(y+k)^z";
        System.out.println(infixToPostfix(infix1));

        String postfix1 = "10 2 * 3 +";
        String postfix2 = "10 2 + 3 *";
        String postfix3 = "10 2 3 ^ ^";
        System.out.println(evaluatePostfix(postfix1));
        System.out.println(evaluatePostfix(postfix2));
        System.out.println(evaluatePostfix(postfix3));

        String infix2 = "x^y^z";
        System.out.println(infixToPrefix(infix2));

        String prefix1 = "+ * 10 2 3";
        String prefix2 = "* + 10 2 3";
        System.out.println(evaluatePrefix(prefix1));
        System.out.println(evaluatePrefix(prefix2));

        Stack<Integer> k = new Stack<>();

    }
}
