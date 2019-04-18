/*
ID: xfrostb1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ariprog {
    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList <Pair> pairs = new ArrayList <Pair> ();
    public static void main(String[] args) throws IOException {
        int[] set = new int[125001];

        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        int n = Integer.parseInt(f.readLine());
        int m = Integer.parseInt(f.readLine());

        getNums(m);
        Collections.sort(nums);
        for(int i = 0; i < nums.size(); i++){
            set[nums.get(i)] = 1;
        }

        for(int i = 0; i < nums.size() - n; i++) {
            for(int j = 1; j <= (m * m + m * m - nums.get(i)) / (n - 1); j++) {
                boolean seq = true;
                for(int k = 0; k < n; k++) {
                    if(set[nums.get(i) + j * k] == 0) {
                        seq = false;
                        break;
                    }
                }

                if(seq) pairs.add(new Pair(nums.get(i), j));
            }
        }
        Collections.sort(pairs);
        if(pairs.isEmpty()) out.println("NONE");
        else {
            for(Pair p : pairs)
                out.println(p.x + " " + p.y);
        }

        f.close();
        out.close();
    }

    static void getNums(int n){
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                if (!nums.contains(i*i+j*j)) nums.add(i*i+j*j);
            }
        }
    }
    static class Pair implements Comparable<Pair> {

        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair o) {
            if(this.y == o.y) return this.x - o.x;
            return this.y - o.y;
        }

    }

}
