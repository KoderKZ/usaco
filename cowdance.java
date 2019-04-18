import javafx.collections.transformation.SortedList;

import java.io.*;
import java.util.*;

public class cowdance {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] times = new int[n];

        for(int i = 0; i < n; i++){
            times[i] = Integer.parseInt(f.readLine());
        }
        int min = 1;
        int max = n;
        while(min != max) {
            int mid = (min+max)/2;
            if(poss(times, mid, d)) {
                max = mid;
            }
            else {
                min = mid+1;
            }
        }
        out.println(min);
        out.close();

    }
    static boolean poss(int[] time, int k, int m){
        int lastTime = 0;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int i = 0; i < time.length; i++) {
            if(q.size() == k) {
                lastTime = q.poll();
            }
            if(lastTime + time[i] > m) {
                return false;
            }
            q.add(lastTime + time[i]);
        }
        return true;
    }
}
