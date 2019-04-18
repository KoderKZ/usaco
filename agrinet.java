/*
ID: xfrostb1
LANG: JAVA
TASK: agrinet
*/
import java.io.*;
import java.util.*;

public class agrinet {
    static int n;
    static boolean[] intree;
    static int[][] connect;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));

        n = Integer.parseInt(f.readLine());

        connect = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            for (int j = 0; j < n; j++) {
                if (st.hasMoreTokens()) {
                    connect[j][i] = Integer.parseInt(st.nextToken());
                } else {
                    st = new StringTokenizer(f.readLine());
                    connect[j][i] = Integer.parseInt(st.nextToken());
                }
            }
        }


        out.println(solve());
        out.close();


    }


    public static int solve(){
        int size = 0;
        int cost = 0;
        distance = new int[n];
        intree = new boolean[n];

        for(int i = 1; i < n; i++) distance[i] = Integer.MAX_VALUE;

        distance[0] = 0;

        while(size < n){
            int index = min();
            size++;
            cost += distance[index];
            intree[index] = true;

            for(int i : neighbors(index)){
                if(distance[i] > connect[index][i]){
                    distance[i] = connect[index][i];
                }
            }
        }
        return cost;
    }

    public static int min(){
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0; i < n; i++){
            if (intree[i]) continue;
            if(distance[i] < min){
                index = i;
                min = distance[i];
            }
        }
        return index;
    }
    public static ArrayList<Integer> neighbors(int node){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(connect[i][node] < Integer.MAX_VALUE && connect[i][node] > 0 && !intree[i]){
                list.add(i);
            }
        }
        return list;
    }
}
