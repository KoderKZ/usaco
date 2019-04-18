import java.io.*;
import java.util.*;

public class reststops {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int rf = Integer.parseInt(st.nextToken());
        int rb = Integer.parseInt(st.nextToken());

        int[] stops = new int[n];

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stops[x] = y;
        }

        int index = index(stops, 0);
        long ret = 0;
        long prev = 0;
        while(index != -1) {
            long F = rf * (index-prev);
            long B = rb * (index-prev);
            ret += (F - B) * stops[index];

            prev = index;
            index = index(stops, index+1);
        }
        out.println(ret);
        out.close();
    }
    static int index(int[] n, int index){
        int max = 0;
        int ind = -1;
        for(int i = index; i < n.length; i++){
            if(n[i] > max){
                ind = i;
                max = n[i];
            }
        }
        return ind;
    }
}
