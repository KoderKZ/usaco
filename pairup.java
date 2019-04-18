import java.io.*;
import java.util.*;

public class pairup {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
        int n = Integer.parseInt(f.readLine());
        Cow[] freq = new Cow[n];
        int c = 0;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            freq[i] = new Cow(x, y);
        }

        Arrays.sort(freq);
        int ret = 0;
        int forward = 0;
        int backward = freq.length-1;
        while(forward <= backward){
            ret = Math.max(ret, freq[forward].milk+freq[backward].milk);
            if(freq[forward].amt > freq[backward].amt) {
                freq[forward].amt -= freq[backward].amt;
                backward--;
            }
            if(freq[forward].amt < freq[backward].amt) {
                freq[backward].amt -= freq[forward].amt;
                forward++;
            }
            if(freq[forward].amt == freq[backward].amt) {
                backward--;
                forward++;
            }
        }

        out.println(ret);
        out.close();
    }
    static class Cow implements Comparable<Cow>{
        int amt, milk;
        public Cow(int a, int m){
            amt = a;
            milk = m;
        }

        @Override
        public int compareTo(Cow o) {
            return milk-o.milk;
        }
    }
}
