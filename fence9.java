/*
ID: xfrostb1
LANG: JAVA
TASK: fence9
*/

import java.io.*;
import java.util.*;

public class fence9 {
    static int N, M, P;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        out.println((P*M)/2+1-(gcd(N,M)+gcd(Math.abs(N-P),M)+P)/2);
        out.close();
    }
    static int gcd(int a,int b)
    {
        if(a==0) return b;
        int t;
        while(b!=0)
        {
            t = b;
            b = a%b;
            a = t;
        }
        return a;
    }
}
