/*
ID: xfrostb1
LANG: JAVA
TASK: homework
*/

import java.io.*;
import java.util.*;

public class homework {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("homework.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
        int N = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] hw = new int[N];
        int[] min = new int[N+1];
        int[] sum = new int[N+1];

        for(int i = 0; i < N; i++) {
            hw[i] = Integer.parseInt(st.nextToken());
        }
        sum[N] = 0;
        min[N] = Integer.MAX_VALUE;
        for(int i = N-1; i >= 0; i-- ){
            sum[i] = sum[i+1]+hw[i];
            min[i] = Math.min(min[i+1], hw[i]);
        }

        ArrayList<Integer> solutions = new ArrayList<>();
        double ret = 0;
        for(int i = 1; i < N-1; i++){
//            System.out.println(((double)sum[i]-min[i])/(N-i-1));
            if(((double)sum[i]-min[i])/(N-i-1) > ret){
                solutions.clear();
                ret = ((double)sum[i]-min[i])/(N-i-1);
                solutions.add(i);
            }else if(((double)sum[i]-min[i])/(N-i-1) == ret){
                solutions.add(i);
            }
        }
        for(int i : solutions) out.println(i);
        out.close();
    }
}
