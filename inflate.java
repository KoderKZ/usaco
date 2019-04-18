/*
ID: xfrostb1
LANG: JAVA
TASK: inflate
*/
import java.io.*;
import java.util.*;

public class inflate
{
    public static void main(String[] args) throws IOException
    {
        long start = System.currentTimeMillis();
        BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int M = Integer.valueOf(st.nextToken());
        int N = Integer.valueOf(st.nextToken());

        int[][] dat = new int[N][2];
        for(int i = 0; i < N;i++)
        {
            st = new StringTokenizer(f.readLine());
            dat[i][0] = Integer.valueOf(st.nextToken());
            dat[i][1] = Integer.valueOf(st.nextToken());
        }
        System.out.println("$:"+(System.currentTimeMillis()-start));
        boolean[] rem = new boolean[N];
        int count = 0;
        for(int i = 0; i < N;i++)
        {
            if(rem[i]) continue;
            for(int j = 0; j < N;j++)
            {
                if(dat[j][1] > dat[i][1] && dat[j][0] < dat[i][0])
                {
                    if(rem[j] == false)
                    {
                        rem[j] = true;
                        count++;
                    }
                }
            }
        }

        int[][] newDat = new int[N-count][2];
        int at = 0;
        for(int i = 0; i < N;i++)
        {
            if(rem[i]) continue;
            newDat[at] = dat[i];

            at++;
        }
        dat = newDat;

        System.out.println("$:"+(System.currentTimeMillis()-start));
        int[] best = new int[M+1];
        int ans = 0;
        for(int i = 0; i <= M;i++)
        {
            for(int j = 0; j < dat.length;j++)
            {
                if(i-dat[j][1] >= 0)
                    best[i] = Math.max(best[i],best[i-dat[j][1]]+dat[j][0]);
            }
            ans = Math.max(ans,best[i]);
        }
        out.println(ans);

        out.close();
        System.out.println("$:"+(System.currentTimeMillis()-start));
        System.exit(0);
    }

}

//public class inflate {
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        int t = Integer.parseInt(st.nextToken());
//        int n = Integer.parseInt(st.nextToken());
//        int[] wts = new int[n];
//        int[] points = new int[n];
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(f.readLine());
//            points[i] = Integer.parseInt(st.nextToken());
//            wts[i] = Integer.parseInt(st.nextToken());
//        }
//        out.println(value(t, n, wts, points));
//        out.close();
//    }
//    public static int value(int w, int n, int[] wts, int[] points){
//        if(w == 0 || n == 0) return 0;
//
//        int amt = w/wts[n-1];
//            if (amt == 0) return value(w, n - 1, wts, points);
//            int best = 0;
//            for (int i = 0; i <= amt; i++) {
//                best = Math.max(best, (points[n - 1] * i) + value(w - (wts[n - 1] * i), n - 1, wts, points));
//            }
//            return best;
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        int t = Integer.parseInt(st.nextToken());
//        int n = Integer.parseInt(st.nextToken());
//        Event[] list = new Event[n];
//        for(int i = 0; i < n; i++){
//            st = new StringTokenizer(f.readLine());
//            list[i] = new Event(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//        }
//        Arrays.sort(list);
//
//        int points = 0;
//        int time = 0;
//        int counter = 0;
//        while(true){
//            if(counter >= n) break;
//            int amt = (t-time)/list[counter].time;
//            time += list[counter].time*amt;
//            if(amt == 0){
//                counter++;
//                continue;
//            }
//            points += amt*list[counter].points;
//            counter++;
//        }
//        out.println(points);
//        out.close();
//    }
//    static class Event implements Comparable<Event>{
//        int time,  points;
//        public Event( int p, int t){
//            time = t;
//            points = p;
//        }
//
//        @Override
//        public int compareTo(Event o) {
//            if((double)points/time - (double)o.points/o.time < 0) return 1;
//            if((double)points/time - (double)o.points/o.time > 0) return -1;
//            return 0;
//        }
//    }
// }