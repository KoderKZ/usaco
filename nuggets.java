/*
ID: xfrostb1
LANG: JAVA
TASK: nuggets
*/

import java.io.*;
import java.util.*;

public class nuggets {
    static int max = 100000;
    static boolean[] visited = new boolean[max+1];
    static int[] nuggs;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
        int N = Integer.parseInt(f.readLine());
        nuggs = new int[N];
        for(int i = 0; i < N; i++) nuggs[i] = Integer.parseInt(f.readLine());

        boolean poss = false;
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                if(gcd(nuggs[i], nuggs[j]) == 1) poss = true;
            }
        }
        if(!poss){
            out.println(0);
            out.close();
            System.exit(0);
        }


        visited[0] = true;
        int ret = 0;
        for(int i = 0; i < max; i++){
            if(!visited[i]){
                ret = i;
            }else {
                for (int j : nuggs) {
                    if(i+j < max) visited[i + j] = true;
                }
            }
        }

        out.println(ret);
        out.close();

    }



    public static int gcd(int a, int b)
    {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
