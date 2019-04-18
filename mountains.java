/*
ID: xfrostb1
LANG: JAVA
TASK: mountains
*/

import java.io.*;
import java.util.*;

public class mountains {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        int N = Integer.parseInt(f.readLine());
//        int[] xa = new int[N];
//        int[] ya = new int[N];
        point[] points = new point[N];
        int x, y, x1, x2;
        int counter = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());

//                int x = Integer.parseInt(String.valueOf(s.charAt(0)));
//                int y = Integer.parseInt(String.valueOf(s.charAt(2)));
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            x1 = x - y+1;
            x2 = x + y-1;
            points[i] = new point(x1, x2, y);

        }
        Arrays.sort(points);
        boolean[] covered = new boolean[N];
        int ret = N;
        for(int i = 0; i < N; i++){
            if(!covered[i]){
                for(int j = i+1; j < N && j != i; j++){
                    if(!covered[j]){
                        if(points[j].x >= points[i].x && points[j].y <= points[i].y){
                            covered[j] = true;
                            ret--;
                        }
                        if(points[j].x <= points[i].x && points[j].y >= points[i].y){
                            covered[i] = true;
                            ret--;
                        }
                    }
                }
            }
        }
//        int[] start = new int[N];
//        int started = 0;
//        int ret = N;
//        for(point i : mounts){
//            if(i.start) {
////                start[i.index] = started;
//                started++;
//
//            }
//            if(!i.start) {
//                started--;
//                if(started > 0) ret--;
//            }

        out.println(ret);
        out.close();
    }
    static class point implements Comparable<point>{
        int x, y, y1;
        boolean start;
        public point(int X, int Y, int Y1){
            x = X;
            y = Y;
            y1 = Y1;
        }

        @Override
        public int compareTo(point o) {
            if(o.y1 != y1) return o.y1-y1;
            return x-o.x;
        }
    }

}
