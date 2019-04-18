/*
ID: xfrostb1
LANG: JAVA
TASK: walk3
*/

import java.io.*;
import java.util.*;

public class walk3 {
    static long X = 2019201913;
    static long Y = 2019201949;
    static long MOD = 2019201997;
    static boolean[] visited;
    static ArrayList<Integer>[] connect;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("walk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        out.println(((N*Y)+(X*(M-1)))%MOD);
        out.close();
    }
}