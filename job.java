/*
ID: xfrostb1
LANG: JAVA
TASK: job
*/

import java.io.*;
import java.util.*;

public class job {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("job.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M1 = Integer.parseInt(st.nextToken());
        int M2 = Integer.parseInt(st.nextToken());

        int[] time1 = new int[M1];
        int[] time2 = new int[M2];
        int[] delay1 = new int[M1];
        int[] delay2 = new int[M2];
        int[] job1 = new int[N];
        int[] job2 = new int[N];

        String s = f.readLine();
        int count = 0;
        while(s != null){
            st = new StringTokenizer(s);
            while(st.hasMoreTokens()){
                if(count < M1) time1[count] = Integer.parseInt(st.nextToken());
                if(count >= M1) time2[count-M1] = Integer.parseInt(st.nextToken());

                count++;
            }
            s = f.readLine();
        }

        int max1 = 0;
        for(int i = 0; i < N; i++){
            int min = Integer.MAX_VALUE;
            int x = -1;
            for(int j = 0; j < M1; j++){
                if(delay1[j]+time1[j] < min){
                    min = delay1[j]+time1[j];
                    x = j;
                }
            }
            delay1[x] += time1[x];
            job1[i] = delay1[x];
            max1 = Math.max(max1, job1[i]);
        }

        int max2 = 0;
        for(int i = 0; i < N; i++){
            int min = Integer.MAX_VALUE;
            int x = -1;
            for(int j = 0; j < M2; j++){
                if(delay2[j]+time2[j] < min){
                    min = delay2[j]+time2[j];
                    x = j;
                }
            }
            delay2[x] += time2[x];
            job2[i] = delay2[x];
        }
        for(int i = 0; i < N; i++){
            max2 = Math.max(job1[i]+job2[N-i-1], max2);
        }


        out.println(max1+" "+max2);
        out.close();
    }
}
