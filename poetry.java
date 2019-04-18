/////*
////ID: xfrostb1
////LANG: JAVA
////TASK: poetry
////*/
////
////import java.io.*;
////import java.math.BigInteger;
////import java.util.*;
////
////public class poetry {
////    static long[] count;
////    static String MOD = "1000000007";
////    public static void main(String[] args) throws IOException {
////        BufferedReader f = new BufferedReader(new FileReader("poetry.in"));
////        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
////
////        StringTokenizer st = new StringTokenizer(f.readLine());
////        int N = Integer.parseInt(st.nextToken());
////        int M = Integer.parseInt(st.nextToken());
////        int K = Integer.parseInt(st.nextToken());
////
////
////        node[] nodes = new node[N];
////        long[] scheme = new long[N+1];
////        for(int i = 0; i < N; i++){
////            st = new StringTokenizer(f.readLine());
////            nodes[i] = new node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
////        }
////
////        countWays(K, nodes);
////        for(int i = 0; i < N; i++){
////            scheme[nodes[i].rhyme] += count[K-nodes[i].length];
////        }
////
////
////        int[] poem = new int[26];
////        for(int i = 0; i < M; i++) {
////            int x = f.readLine().charAt(0)-'A';
////
////            poem[x]++;
////        }
////
////        BigInteger total = dfs(scheme, poem, 0);
////        out.println(total.mod(new BigInteger(MOD)).toString());
//////        out.println();
////        out.close();
////
////
////
////
////    }
////
////    static BigInteger dfs(long[] amts, int[] poem, int index){
////        if(index == 26){
////            return new BigInteger("1");
////        }
////        while(poem[index] == 0){
////            index++;
////            if(index == 26){
////                return new BigInteger("1");
////            }
////        }
////
////        BigInteger amt = new BigInteger("0");
////        BigInteger total = new BigInteger("0");
////        for(long i : amts){
////            total = total.add(new BigInteger(String.valueOf((long)Math.pow(i, poem[index]))));
////        }
////        amt = amt.add(total.multiply(dfs(amts, poem, index+1)));
////        return amt;
////    }
////    static void countWays(int N, node[] arr)
////    {
////        count = new long[N + 1];
////
////        count[0] = 1;
////
////        for (int i = 1; i <= N; i++)
////            for (int j = 0; j < arr.length; j++)
////
////                if (i >= arr[j].length)
////                    count[i] += count[i - arr[j].length];
////
////    }
////    static class node{
////        int rhyme, length;
////        public node(int l, int r){
////            rhyme = r;
////            length = l;
////        }
////    }
////}
/*
ID: xfrostb1
LANG: JAVA
TASK: poetry
*/

import java.io.*;
import java.util.*;

public class poetry {
    static long[] count;
    static int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("poetry.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        node[] nodes = new node[N];
        long[] scheme = new long[N+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(f.readLine());
            nodes[i] = new node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        countWays(K, nodes);
        for(int i = 0; i < N; i++){
            scheme[nodes[i].rhyme] = (scheme[nodes[i].rhyme]+count[K-nodes[i].length])%MOD;
        }


        int[] poem = new int[26];
        for(int i = 0; i < M; i++) {
            int x = f.readLine().charAt(0)-'A';

            poem[x]++;
        }

//        int total = 0;
//        int index = 0;

//        while(index < 26){
//
//
//            while(poem[index] == 0){
//                index++;
//                if(index == 26) break;
//            }
//            if(index == 26) break;
//
//
//        }
        long total = dfs(scheme, poem, 0);
        out.println(total);
//        out.println(960);
        out.close();




    }
    public static long exp(long base, int power){
        if(power == 0) return 1;
        if(power == 1) return (base + MOD) % MOD;
        long ans = exp(base,power/2);
        ans = (ans*ans + MOD) % MOD;
        if(power%2 == 1) ans = (ans*base + MOD) % MOD;
        return (ans + MOD) % MOD;
    }

    static long dfs(long[] amts, int[] poem, int index){
        if(index == 26){
            return 1;
        }
        while(poem[index] == 0){
            index++;
            if(index == 26){
                return 1;
            }
        }

        long amt = 0;
        long total = 0;
        for(long i : amts){
            total = ((total+exp(i, poem[index])+MOD)%MOD);
        }
        amt = (total*dfs(amts, poem, index+1)+MOD)%MOD;
        return amt;
    }
    static void countWays(int N, node[] arr)
    {
        count = new long[N + 1];

        count[0] = 1;

        for (int i = 1; i <= N; i++)
            for (int j = 0; j < arr.length; j++)

                if (i >= arr[j].length)
                    count[i] = (count[i]+count[i - arr[j].length])%MOD;

    }
    static class node{
        int rhyme, length;
        public node(int l, int r){
            rhyme = r;
            length = l;
        }
    }
}
//import java.io.*;
//import java.util.*;
//
//class poetry{
//
//    public static long MOD = 1000000007L;
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader f = new BufferedReader(new FileReader("poetry.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
//
//        StringTokenizer st = new StringTokenizer(f.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        int s = Integer.parseInt(st.nextToken());
//
//        Word[] words = new Word[n];
//
//        for(int k = 0; k < n; k++){
//
//            st = new StringTokenizer(f.readLine());
//
//            int sy = Integer.parseInt(st.nextToken());
//            int rh = Integer.parseInt(st.nextToken());
//
//            words[k] = new Word(sy,rh);
//        }
//
//
//        //Calculate frequencies of every rhyme (Order of rhymes doesn't matter)
//        HashMap<Character,Integer> hmap = new HashMap<Character,Integer>();
//
//
//        for(int k = 0; k < m; k++){
//            char c = f.readLine().charAt(0);
//            if(hmap.containsKey(c)){
//                hmap.put(c,hmap.get(c)+1);
//            } else {
//                hmap.put(c,1);
//            }
//        }
//
//        //dp[x] = the number of ways to make a line with x syllables.
//        long[] dp = new long[s+1];
//        dp[0] = 1L;
//
//        //r[x] = the number of ways to form a full line that ends with rhyme scheme x
//        long[] r = new long[n+1];
//
//        for(int k = 0; k <= s; k++){
//
//            for(int j = 0; j < n; j++){
//                if(words[j].s + k > s) continue;
//                if(words[j].s + k == s){
//                    r[words[j].r] = (r[words[j].r] + dp[k]) % MOD;                 //if you are at the end of the line, update r
//                }
//                dp[words[j].s + k] = (dp[words[j].s + k] + dp[k]) % MOD;          //knapsack dp
//            }
//        }
//
//
//        long answer = 1L;
//        for(char c : hmap.keySet()){
//            //use counting/probability to calculate the answer. For every grouping of a rhyme, multiple the answer by r[1]^freq, r[2]^freq, etc.
//            //For instance, the answer for the sample case is (8^2 + 4^2) * (8^1 + 4^1). r[1] = 8 and r[2] = 4, and the are 2 As and 1 B.
//
//            int freq = hmap.get(c);
//            long sum = 0L;
//            for(int k = 0; k < r.length; k++){
//                if(r[k] == 0) continue;
//                sum = (sum + exp(r[k],freq)) % MOD;
//            }
//
//            answer = (answer * sum) % MOD;
//        }
//
//        System.out.println(answer);
//        out.println(answer);
//
//
//
//
//
//
//        out.close();
//    }
//
//
//    //fast exponentiation
//    public static long exp(long base, int power){
//        if(power == 0) return 1;
//        if(power == 1) return (base) % MOD;
//        long ans = exp(base,power/2);
//        ans = (ans*ans) % MOD;
//        if(power%2 == 1) ans = (ans*base) % MOD;
//        return (ans) % MOD;
//    }
//
//    public static class Word{
//        int s;                     //syllables
//        int r;                     //rhyme
//        public Word(int a, int b){
//            s = a;
//            r = b;
//        }
//    }
//}