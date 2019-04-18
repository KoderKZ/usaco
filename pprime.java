/*
ID: xfrostb1
LANG: JAVA
TASK: pprime
*/
import java.util.*;
import java.io.*;

public class pprime {
    static int a;
    static int b;
    static List<Integer> palprimes;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        palprimes = new ArrayList <Integer> ();
        String [] start = new String[20];
        for(int i = 0; i <= 9; i++) {
            start[i] = Integer.toString(i);
            start[10 + i] = Integer.toString(i * 10 + i); }
        start[10] = "00";
        for(int i = 0; i < 20; i++) dfs(start[i]);
        Collections.sort(palprimes);
        for(Integer i : palprimes) out.println(i);
        out.close();
    }
    public static void dfs(String s) {
        if(isPrime(s) && a <= Integer.parseInt(s) && b >= Integer.parseInt(s)) palprimes.add(Integer.parseInt(s));
        if(s.length() + 2 > Integer.toString(b).length()) return;
        for(int i = 0; i <= 9; i++) dfs(i + s + i);
    }
    public static boolean isPrime(String s) {
        int n = Integer.parseInt(s);
        if(n % 2 == 0 || n % 3 == 0) return false;
        for(int i = 5; i <= (int) Math.sqrt(n); i += 2) if(n % i == 0) return false;
        return true;
    }
}
