import java.io.*;
import java.util.*;

public class maxcross {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] lights = new int[n];
        for(int i = 0; i < b; i++){
            lights[Integer.parseInt(f.readLine())-1] = 1;
        }

        int[] fix = new int[n-k+1];
        int num = 0;
        for(int i = 0; i < k; i++){
            if(lights[i] == 1) num++;
        }
        fix[0] = num;

        for(int i = 1; i < fix.length; i++){
            fix[i] = fix[i-1];
            if(lights[i-1] == 1) fix[i]--;
            if(lights[i+k-1] == 1) fix[i]++;
        }
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < fix.length; i++){
            ret = Math.min(ret, fix[i]);
        }
        out.println(ret);
        out.close();
    }
}
