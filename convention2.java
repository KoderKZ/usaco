import java.io.*;
import java.util.*;

public class convention2 {
    static boolean[] arrived, done;
    static int[] end, startArr;
    static long[] wait;
    static Event[] events;
    static int N;
    static long starta = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));

        N = Integer.parseInt(f.readLine());
        events = new Event[N];
        end = new int[N];
        startArr = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            events[i] = new Event(a, i, b);
            end[i] = b;
            startArr[i] = a;
        }
        Arrays.sort(events);
        arrived = new boolean[N];
        wait = new long[N];
        done = new boolean[N];

        done[events[0].index] = true;
        int a = iterate(0, events[0].time);
        for(int i = 1; i < N; i++){

            if(a == Integer.MAX_VALUE) {
                done[events[i].index] = true;
                wait[events[i].index] = starta-startArr[events[i].index];
                a = iterate(i, events[i].time);
            }else{
                done[a] = true;
                wait[a] = starta-startArr[a];
                for(int j = 0; j < N; j++) if(a == events[j].index) {
                    a = j;
                    break;
                }
                a = iterate(a, starta);
            }
        }

        long ret = 0;
        for(long n : wait) ret = Math.max(n, ret);


        out.println(ret);
        out.close();
    }

    static int iterate(int n, long start){
        int count = n+1;
        while(count < N && events[count].time < start+events[n].end){
            arrived[events[count].index] = true;
            count++;
        }
        starta = start+events[n].end;
        int prio = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            if(!done[i] && arrived[i]){
                prio = i;
                break;
            }
        }

        return prio;
    }


    static class Event implements Comparable<Event>{
        int time, index, end;
        public Event(int t, int i,int e){
            time = t;
            index = i;
            end = e;
        }

        @Override
        public int compareTo(Event o) {
            if(time!=o.time)return time-o.time;
            return index-o.index;
        }
    }
}
