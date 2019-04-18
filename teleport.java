import java.io.*;
import java.util.*;

public class teleport {
    static int[] start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
        int n = Integer.parseInt(f.readLine());
        start = new int[n];
        end = new int[n];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, Math.max(start[i], end[i]));
            min = Math.min(min, Math.min(start[i], end[i]));

        }
        long ret = Long.MAX_VALUE;
        for(int i = min; i <= max; i++){
            long count = dist(i);
            if(count > ret) break;
            ret = Math.min(ret, dist(i));
        }

        out.println(ret);
        out.close();




    }
    static long dist(int point){
        long dist = 0;
        for(int i = 0; i < start.length; i++){
            dist += Math.min(Math.abs(start[i]-end[i]), Math.abs(start[i])+Math.abs(end[i]-point));
        }
        return dist;
    }
}
