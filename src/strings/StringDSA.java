package strings;

import java.util.Arrays;
import java.util.HashMap;

public class StringDSA {

    public static void printFrequenciesOfCharactersInAString(String s){
        int[] freq = new int[26];

        for(int i=0; i<s.length(); i++){
            freq[s.charAt(i)-'a']++;
        }

        // printing in sorted order
        for(int i = 0; i<freq.length; i++){
            if(freq[i] > 0){
                System.out.println((char)('a'+i) + " " + freq[i]);
            }
        }
    }


    public static boolean isPalindrome1(String str){
        int n = str.length();
        for(int i = 0; i<n/2; i++){
            if(str.charAt(i) != str.charAt(n-i-1)) return false;
        }

        return true;
    }

    public static boolean isPalindrome2(String str){
        int start = 0;
        int end = str.length()-1;
        while(start<end){
            if(str.charAt(start) != str.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }


    public static boolean isSubsequence1(String str, String subSeq){
        int i = 0;
        int j = 0;
        while(i < str.length() && j<subSeq.length()){
            if(str.charAt(i) == subSeq.charAt(j)){
                j++;
            }
            i++;
        }

        return (j==subSeq.length());
    }

    public static boolean isSubsequence2(String str, String subSeq){
        int n = str.length();
        int m = subSeq.length();
        int j= 0;
        for(int i=0; i<n && j<m; i++){
            if(str.charAt(i) == subSeq.charAt(j)) j++;
        }
        return (j==m);
    }

    public static boolean isSubsequenceRecursion1(String str, String subSeq,int i, int j){
        if(j == subSeq.length()) return true;
        if(i < str.length() && j<subSeq.length()){
            if(str.charAt(i) == subSeq.charAt(j)) {
                j++;
            }
            i++;
            return isSubsequenceRecursion1(str,subSeq,i,j);
        }
        else return false;
    }

    public static boolean isSubsequenceRecursion2(String str, String subSeq,int n, int m){
        if(m==0) return true;
        if(n==0) return false;
        if(str.charAt(n-1) == subSeq.charAt(m-1)){
            return isSubsequenceRecursion2(str,subSeq,n-1,m-1);
        }
        else{
            return isSubsequenceRecursion2(str,subSeq,n-1,m);
        }
    }


    public static boolean areAnagram(String str1, String str2){
        int n = str1.length();
        int m = str2.length();
        if(n != m) return false;

        int[] freq = new int[256]; // total 256 characters in ascii...
        // if str1 and str2 are lowercase only then we can take 26 length. and do ch-'a' to get index.

        for(int i = 0; i<n; i++){
            // char automatically converts to int when we use it in place of int.
            // ex:- as index, or in any mathematical operation
            freq[str1.charAt(i)]++;
            freq[str2.charAt(i)]--;
        }

        for(int i = 0; i<freq.length; i++){
            if(freq[i] != 0) return false;
        }

        return true;
    }


    public static int leftMostRepeatingChar1(String str){
        int[] freq = new int[256];
        for(int i=0; i<str.length(); i++){
            freq[str.charAt(i)]++;
        }

        for(int i=0; i<str.length(); i++){
            if(freq[str.charAt(i)] > 1) return i;
        }

        return -1;
    }

    public static int leftMostRepeatingChar2(String str){
        int[] freq = new int[256];

        Arrays.fill(freq,-1);

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<str.length(); i++){
            int k = freq[str.charAt(i)];
            if(k == -1) freq[str.charAt(i)] = i;
            else ans = Math.min(ans,k);
        }

        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }


    public static int leftMostRepeatingChar3(String str){
        // efficient
        boolean[] visited = new boolean[256];

        int ans = -1;

        for(int i = str.length()-1; i>=0; i--){
            if(visited[str.charAt(i)]) ans = i;
            else visited[str.charAt(i)] = true;
        }

        return ans;
    }


    public static int leftMostNonRepeatingChar(String str){
        int[] freq = new int[256];
        Arrays.fill(freq,-1);

        for(int i=0; i<str.length();i++){
            if(freq[str.charAt(i)] == -1) freq[str.charAt(i)] = i;
            else freq[str.charAt(i)] = -2;
        }

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<freq.length; i++){
            if(freq[i] > -1){
                // it has a non repeating element index
                ans = Math.min(ans,freq[i]);
            }
        }

        return (ans == Integer.MAX_VALUE)?-1:ans;
    }

    public static void reverseString(char[] str, int left, int right){
        while(left<right){
            char ch = str[left];
            str[left] = str[right];
            str[right] = ch;
            left++;
            right--;
        }
    }

    public static void reverseWords(char[] str){
        int n = str.length;
        int start = 0;
        for(int end = 0;end<n; end++){
            if(str[end] == ' '){
                reverseString(str,start,end-1);
                start = end+1;
            }
        }

        reverseString(str,start,n-1); // done reversing all individual words
        reverseString(str,0,n-1); // reversing whole string
    }


    public static void patternSearchingNaive(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        for(int i = 0; i<=n-m; i++){
            int j;
            for(j=0;j<m; j++){
                if(text.charAt(i+j) != pattern.charAt(j)) break;
            }
            if(j == m) System.out.print(i + " ");
        }

        System.out.println();
    }


    public static void patternSearchingForDistinctPatternManoj(String text, String pattern){
        int n = text.length();
        int m = pattern.length();
        int j=0;
        for(int i = 0; i<n; i++){
            if(text.charAt(i)==pattern.charAt(j)){
                j++;
            }
            else{
                if(text.charAt(i) == pattern.charAt(0)) j = 1;
                else j = 0;
            }

            if(j == m){
                System.out.print((i-m+1) + " ");
                j = 0;
            }
        }

        System.out.println();
    }


    public static void patternSearchingForDistinctPatternAuthor(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        for(int i = 0; i<=n-m;){
            int j;
            for(j = 0; j<m; j++){
                if(text.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
            }
            if(j == m) System.out.print(i+" ");
            if(j == 0) i++;
            else i = i+j;
        }
        System.out.println();
    }


    public static void rabinKarpAlgoPatternSearch(String txt, String pat){
        int N = txt.length();
        int M = pat.length();

        final int d=256;
        final int q=101;

        //Compute (d^(M-1))%q
        int h=1;
        for(int i=1;i<=M-1;i++)
            h=(h*d)%q;

        //Compute p and t0
        int p=0,t=0;
        for(int i=0;i<M;i++){
            p=(p*d+pat.charAt(i))%q;
            t=(t*d+txt.charAt(i))%q;
        }

        for(int i=0;i<=(N-M);i++){
            //Check for hit
            if(p==t){
                int j;
                for(j=0;j<M;j++){
                    if(txt.charAt(i+j)!=pat.charAt(j)) break;
                }

                if(j==M)System.out.print(i+" ");
            }
            //Compute ti+1 using ti
            if(i<N-M){
                t=((d*(t-txt.charAt(i)*h))+txt.charAt(i+M))%q; // Rolling hashing
                if(t<0)t=t+q; // dealing with negative hash value
            }
        }
    }


    public static int longestProperPrefixSuffix(String str, int n){

        for(int len = n-1; len>0; len--){
            // proper prefix will have lenghts between 1<=len<=n-1
            int j;
            for(j=0; j<len; j++){
                // we will check string part [0 -> len] with string part [n-len -> n];
                if(str.charAt(j) != str.charAt(n-len+j)) break;
            }

            if(j == len) return len;
        }

        return 0;

    }



    public static void fillLps(String str, int[] lps){
        for(int i = 0; i<str.length(); i++){
            lps[i] = longestProperPrefixSuffix(str,i+1);
        }
    }

    public static void fillLpsManoj(String str, int[] lps){
        int n = str.length();
        lps[0] = 0;
        for(int i=1;i<n; i++){
            int len = lps[i-1];
            if(str.charAt(i) == str.charAt(len)){
                lps[i] = len+1;
            }else{
                if(len == 0) lps[i] = 0;
                else{
                    while(str.charAt(i) != str.charAt(len) && len > 0){
                        len = lps[len-1];
                    }

                    if(str.charAt(i) == str.charAt(len)) lps[i] = len+1;
                    else {
                        // len = 0 && str.charAt(i) != str.charAt(len) case
                        lps[i] =  0;
                    }
                }
            }
        }
    }

    public static void fillLpsEfficient(String str, int[] lps){
        lps[0] = 0;
        int len = 0;
        int i = 1;
        while(i<str.length()){
            if(str.charAt(i) == str.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{
                if(len == 0){
                    lps[i] = 0;
                    i++;
                }else{
                    len = lps[len-1];
                }
            }
        }
    }


    public static void kmpStringMatching(String text, String pattern){
        // Knuth-Morris-Pratt Algorithm
        int n = text.length();
        int m = pattern.length();

        int[] lps = new int[m];
        fillLpsEfficient(pattern,lps);

        int i = 0, j = 0;
        while(i<n){
            if(pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if(j == m){
                System.out.print((i-j) + " ");
                j = lps[j-1];
            }
            else if(i<n && pattern.charAt(j) != text.charAt(i)){
                if(j == 0) i++;
                else j = lps[j-1];
            }
        }
    }


    public static boolean isRotated(String str1, String str2){
        if(str1.length() != str2.length()) return false;
        return (str1+str1).indexOf(str2) > -1;
    }


    public static boolean isAnagramPresentAtIndex(String text, String pattern, int i){
        int n = text.length();
        int m = pattern.length();

        int[] freq = new int[256]; // total 256 characters in ascii...
        // if str1 and str2 are lowercase only then we can take 26 length. and do ch-'a' to get index.

        for(int j = 0; j<m; j++){
            // char automatically converts to int when we use it in place of int.
            // ex:- as index, or in any mathematical operation
            freq[text.charAt(i+j)]++;
            freq[pattern.charAt(j)]--;
        }

        for(int j = 0; j<freq.length; j++){
            if(freq[j] != 0) return false;
        }

        return true;
    }


    public static boolean anagramSearchNaive(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        for(int i = 0; i<=n-m; i++){
            if(isAnagramPresentAtIndex(text,pattern,i)) return true;
        }

        return false;
    }


    public static boolean anagramSearchEfficient(String text, String pattern){
        // Author wrote it wrong. I corrected it
        int n = text.length();
        int m = pattern.length();

        int[] freqTextWindow = new int[256];
        int[] freqPattern = new int[256];

        for(int i = 0; i<m; i++){
            freqPattern[pattern.charAt(i)]++;
            freqTextWindow[text.charAt(i)]++;
        }

        if(Arrays.equals(freqPattern,freqTextWindow)) return true;

        for(int i = m; i<n; i++){
            freqTextWindow[text.charAt(i-m)]--; // reducing 1st char frequency of last window
            freqTextWindow[text.charAt(i)]++; // adding last char frequency of current window

            if(Arrays.equals(freqPattern,freqTextWindow)) return true;
        }

        return false;
    }


    public static int lexicographicRank(String str){
        int n = str.length();

        int res = 1; // basically we add 1 to count of smaller lexicographic strings... so initializing with 1
        int mul = 1;

        for(int i = 2; i<=n; i++) mul *= i; // factorial calculation

        int[] cnt = new int[256];

        for(int i = 0; i<n; i++){
            cnt[str.charAt(i)]++;
        }

        for(int i = 1; i<cnt.length; i++) cnt[i] += cnt[i-1]; // cumilative count -> count of smaller characters in whole string

        for(int i = 0; i<n-1; i++){
            // remember for the first character in string all smaller characters in whole string = smaller characters on right side of it
            mul = mul/(n-i);

            res += cnt[str.charAt(i)-1] * mul;

            // to maintain count of smaller characters on right side for every i.
            // we reduce all greater characters cumilative count by 1 as current i is now processed
            for(int j = str.charAt(i); j<cnt.length; j++){
                cnt[j]--;
            }
        }

        return res;
    }

    public static int longestSubstringWithDistinctCharsManoj1(String str){
        int n = str.length();



        int[] index = new int[256];
        Arrays.fill(index,-1);

        int res = 1; // atleast 1 length disctinct char will be there
        int cnt = 0;
        for(int i = 0;i<n;){
            if(index[str.charAt(i)] == -1){
                cnt++;
                index[str.charAt(i)] = i;
                i++;
            }else{
                res = Math.max(res,cnt);
                cnt = 0;
                i = index[str.charAt(i)] + 1;
                Arrays.fill(index,-1);
            }
        }

        return res;
    }

    public static int longestSubstringWithDistinctCharsManoj2(String str){
        // space: O(n) so not efficient
        int n = str.length();

        int[] index = new int[256]; // to store indexes of occurence
        Arrays.fill(index,-1);

        int[] maxEnd = new int[n]; // it will store length of distinct substrings that end with current index

        index[str.charAt(0)] = 0; // stored 1st char
        maxEnd[0] = 1; // for 1st char. 1 is the length of distinct substring that ends with index 0;

        for(int i = 1; i<n; i++){
            int prevMaxEnd = maxEnd[i-1];
            int prevIdx = index[str.charAt(i)]; // -1 in case of first occurance

            if(prevIdx > (i-1) - prevMaxEnd ){
                // Occured in prevMaxEnd
                maxEnd[i] = i - prevIdx;
            }
            else{
                // so distinct in prevMaxEnd
                maxEnd[i] = maxEnd[i-1] + 1;
            }

            index[str.charAt(i)] = i; // updating index
        }

        System.out.println(Arrays.toString(maxEnd));
        int ans = 1; // atleast 1 will be answer
        for(int i = 1; i<n; i++) ans = Math.max(maxEnd[i],ans);

        return ans;

    }


    public static int longestSubstringWithDistinctCharsEfficientManoj(String str){
        // space: O(n) so not efficient
        int n = str.length();


        int[] index = new int[256]; // to store indexes of occurence
        Arrays.fill(index,-1);

        index[str.charAt(0)] = 0; // stored 1st char

        int ans = 1; // atleast 1 will be the answer

        int maxEnd = 1;

        for(int i = 1; i<n; i++){
            int prevMaxEnd = maxEnd;
            int prevIdx = index[str.charAt(i)]; // -1 in case of first occurance

            if(prevIdx > (i-1) - prevMaxEnd ){
                // Occured in prevMaxEnd
                maxEnd = i - prevIdx;
            }
            else{
                // so distinct in prevMaxEnd
                maxEnd = prevMaxEnd + 1;
            }

//            System.out.print(maxEnd+" ");
            ans = Math.max(ans,maxEnd);
            index[str.charAt(i)] = i; // updating index
        }


        return ans;
    }

    public static int longestSubstringWithDistinctCharsEfficientAuthor(String str){
        int n = str.length();

        int ans = 1; // atleast 1 will be the answer

        int[] prevIndex = new int[256];
        Arrays.fill(prevIndex,-1);

        // i,j are starting and ending indexes of current distinct substring
        int i = 0;
        for(int j = 0; j<n; j++){
            i = Math.max(i,prevIndex[str.charAt(j)] + 1);
            // i will remain same or will be updated to prevIndex[j]+1 if prevIndex[j]+1 > i;
            int maxEnd = j-i+1; // this is actually length of j to i;
            ans = Math.max(ans,maxEnd);
            prevIndex[str.charAt(j)] = j;
        }

        return ans;
    }



    public static void main(String[] args) {
        printFrequenciesOfCharactersInAString("geeks");

        System.out.println(isPalindrome1("abcba"));
        System.out.println(isPalindrome2("abcba"));

        System.out.println(isSubsequence1("abcde","ade"));
        System.out.println(isSubsequence2("abcde","ade"));
        System.out.println(isSubsequenceRecursion1("abcde","ade",0,0));
        System.out.println(isSubsequenceRecursion2("abcde","ade",5,3));

        System.out.println(areAnagram("aabca","acaba"));

        System.out.println(leftMostRepeatingChar1("abccbd"));
        System.out.println(leftMostRepeatingChar2("abccbd"));
        System.out.println(leftMostRepeatingChar3("abccbd"));

        System.out.println(leftMostNonRepeatingChar("abbcbda"));

        char[] str = "abc def ghi".toCharArray();
        reverseWords(str);
        System.out.println(str);


        patternSearchingNaive("abababcd","abab");
        patternSearchingForDistinctPatternManoj("geeksforgeeks","eks");
        patternSearchingForDistinctPatternAuthor("geeksforgeeks","eks");

        rabinKarpAlgoPatternSearch("abababcd","abab");

        String s1 = "abbbacab";
        int[] lps = new int[s1.length()];
        fillLps(s1,lps);
        System.out.println( s1 + " : " + Arrays.toString(lps));

        String s2 = "aaabaaaac";
        lps = new int[s2.length()];
        fillLpsEfficient(s2,lps);
        System.out.println( s2 + " : " + Arrays.toString(lps));

        String s3 = "aaabaaaac";
        lps = new int[s3.length()];
        fillLpsManoj(s3,lps);
        System.out.println( s3 + " : " + Arrays.toString(lps));



        kmpStringMatching("aaaaab","aaaa");
        System.out.println();

        System.out.println(isRotated("abcd","cdab"));

        System.out.println(anagramSearchNaive("geeksforgeeks","frog"));
        System.out.println(anagramSearchEfficient("geeksforgeeks","fro"));

        System.out.println(lexicographicRank("string"));

        System.out.println(longestSubstringWithDistinctCharsManoj1("abcaabcdecd"));
        System.out.println(longestSubstringWithDistinctCharsManoj2("abcaabcdecd"));
        System.out.println(longestSubstringWithDistinctCharsEfficientManoj("abcaabcdecd"));
        System.out.println(longestSubstringWithDistinctCharsEfficientAuthor("abcaabcdecd"));
    }
}
