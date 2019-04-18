/*
ID: xfrostb1
LANG: JAVA
TASK: sort
*/

import java.io.*;
import java.util.*;

public class sort {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        int N = Integer.parseInt(f.readLine());
        int[] list = new int[N];
        for(int i = 0; i < N; i++){
            list[i] = Integer.parseInt(f.readLine());
        }
        int[] sorted = Arrays.copyOf(list, N);
        Arrays.sort(sorted);
        TreeMap<Integer, Boolean> map = new TreeMap<>();
        int[] ret = new int[N+1];
        ret[0] = 0;
        for(int i = 0; i < N; i++){
            map.put(sorted[i], true);
            if(!map.containsKey(list[i])){
                ret[i+1] = ret[i]+1;
            }else{
                ret[i+1] = ret[i];
            }
        }
        out.println(ret[N]);
        out.close();
    }
}
