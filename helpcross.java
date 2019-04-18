import java.io.*;
import java.util.*;

public class helpcross {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] chickens = new int[n];
        Cow[] cows = new Cow[m];
        for(int i = 0; i < n; i++) chickens[i] = Integer.parseInt(f.readLine());

        Arrays.sort(chickens);
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

    }
    static class Cow implements Comparable<Cow>{
        int start, end;
        public Cow(int s, int e){
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Cow o) {
            return end-o.end;
        }
    }
}
