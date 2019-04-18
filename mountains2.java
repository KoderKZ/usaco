/*
ID: xfrostb1
LANG: JAVA
TASK: mountains
*/

import java.io.*;
import java.util.*;

public class mountains2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        int N = Integer.parseInt(f.readLine());
        point[] mounts = new point[N];
        for(int i = 0; i < 2*N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());

            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            long x1 = x - y;
            long x2 = x + y;
            mounts[2*i] = new point(x1, i, true);
            mounts[2*i+1] = new point(x1, i, true);
        }
        Arrays.sort(mounts);

        int[] start = new int[N];
        int started = 0;
        int ret = N;
        for(point i : mounts) {
            if (i.start) {
                start[i.index] = started;
                started++;

            }
            if (!i.start) {
                started--;
                if (started > 0) ret--;
            }
        }
        out.println(2);
        out.close();
    }
    static class point implements Comparable<point>{
        long x;
        int index;
        boolean start;
        public point(long X, int i, boolean s){
            x = X;
            index = i;
            start = s;
        }

        @Override
        public int compareTo(point o) {
            return (int)(x-o.x);
        }
    }

}
