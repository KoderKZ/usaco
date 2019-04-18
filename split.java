/*
ID: xfrostb1
LANG: JAVA
TASK: split
*/

import java.io.*;
import java.util.*;

public class split {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("split.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));

        int N = Integer.parseInt(f.readLine());

        long maxx = Integer.MIN_VALUE;
        long maxy = Integer.MIN_VALUE;
        long minx = Integer.MAX_VALUE;
        long miny = Integer.MAX_VALUE;
        cow[] cows = new cow[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            maxx = Math.max(x, maxx);
            maxy = Math.max(y, maxy);
            minx = Math.min(x, minx);
            miny = Math.min(y, miny);
            cows[i] = new cow(x, y);
        }
        long[] hleft = new long[N];
        Arrays.sort(cows, xcompare);
        long hmaxx = cows[0].x;
        long hminx = cows[0].x;
        long hmaxy = cows[0].y;
        long hminy = cows[0].y;
        hleft[0] = 0;
        for(int i = 1; i < cows.length; i++){
            hmaxx = Math.max(cows[i].x, hmaxx);
            hmaxy = Math.max(cows[i].y, hmaxy);
            hminx = Math.min(cows[i].x, hminx);
            hminy = Math.min(cows[i].y, hminy);
            hleft[i] = (hmaxx-hminx)*(hmaxy-hminy);
        }
        long[] hright = new long[N];
        hmaxx = cows[N-1].x;
        hminx = cows[N-1].x;
        hmaxy = cows[N-1].y;
        hminy = cows[N-1].y;
        hright[N-1] = 0;
        for(int i = cows.length-2; i >= 0; i--){
            hmaxx = Math.max(cows[i].x, hmaxx);
            hmaxy = Math.max(cows[i].y, hmaxy);
            hminx = Math.min(cows[i].x, hminx);
            hminy = Math.min(cows[i].y, hminy);
            hright[i] = (hmaxx-hminx)*(hmaxy-hminy);
        }




        long[] vleft = new long[N];
        Arrays.sort(cows, ycompare);
        long vmaxx = cows[0].x;
        long vminx = cows[0].x;
        long vmaxy = cows[0].y;
        long vminy = cows[0].y;
        hleft[0] = 0;
        for(int i = 1; i < cows.length; i++){
            vmaxx = Math.max(cows[i].x, vmaxx);
            vmaxy = Math.max(cows[i].y, vmaxy);
            vminx = Math.min(cows[i].x, vminx);
            vminy = Math.min(cows[i].y, vminy);
            vleft[i] = (vmaxx-vminx)*(vmaxy-vminy);
        }
        long[] vright = new long[N];
        vmaxx = cows[N-1].x;
        vminx = cows[N-1].x;
        vmaxy = cows[N-1].y;
        vminy = cows[N-1].y;
        vright[N-1] = 0;
        for(int i = cows.length-2; i >= 0; i--){
            vmaxx = Math.max(cows[i].x, vmaxx);
            vmaxy = Math.max(cows[i].y, vmaxy);
            vminx = Math.min(cows[i].x, vminx);
            vminy = Math.min(cows[i].y, vminy);
            vright[i] = (vmaxx-vminx)*(vmaxy-vminy);
        }

        long ret = Long.MAX_VALUE;
        for(int i = 1; i < N; i++){
            ret = Math.min(hleft[i-1]+hright[i], ret);
            ret = Math.min(vleft[i-1]+vright[i], ret);
        }
        out.println(((maxx-minx)*(maxy-miny))-ret);
        out.close();
    }

    static class cow{
        int x, y;
        public cow(int X, int Y){
            x = X;
            y = Y;
        }
    }
    static Comparator<cow> xcompare = new Comparator<cow>() {
        @Override
        public int compare(cow o1, cow o2) {
            return o1.x-o2.x;
        }
    };
    static Comparator<cow> ycompare = new Comparator<cow>() {
        @Override
        public int compare(cow o1, cow o2) {
            return o1.y-o2.y;
        }
    };
}
