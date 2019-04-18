import java.io.*;
import java.util.*;

public class blist {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("blist.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
        int n = Integer.parseInt(f.readLine());

        Event[] list = new Event[n*2];
        int[] buckets = new int[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            buckets[i] = Integer.parseInt(st.nextToken());
            list[2*i] = new Event(true, start, i);
            list[2*i+1] = new Event(false, end, i);
        }

        Arrays.sort(list);

        int maxBuckets = 0;
        int availBuckets = 0;
        for(Event e : list){
            if(!e.start) availBuckets += buckets[e.index];
            else{
                if(availBuckets <= buckets[e.index]) {
                    maxBuckets += availBuckets-buckets[e.index];
                    availBuckets = 0;
                }else{
                    availBuckets -= buckets[e.index];
                }

            }
        }

        out.println(-maxBuckets);
        out.close();

    }
    static class Event implements Comparable<Event>{
        int time, index;
        boolean start;
        public Event(boolean s, int t, int i){
            start = s;
            time = t;
            index = i;
        }

        @Override
        public int compareTo(Event o) {
            if(time != o.time)return time-o.time;

            if(o.start && !start) return 1;
            if(!o.start && start) return -1;
            return 0;
        }
    }
}
