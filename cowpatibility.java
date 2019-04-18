/*
ID: xfrostb1
LANG: JAVA
TASK: cowpatibility
*/

import java.io.*;
import java.util.*;

public class cowpatibility {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
        int N = Integer.parseInt(f.readLine());
        int[][] cows5 = new int[N][5];
        int[][] cows = new int[N][1000001];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0; j < 5; j++){
                int x = Integer.parseInt(st.nextToken());
                cows[i][x]++;
                cows5[i][j] = x;
            }
        }

        int ret = 0;

        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                boolean check = false;
                for(int k = 0; k < 5; k++){
                    if(cows[j][cows5[i][k]] == 1) {
                        check = true;
                        break;
                    }
                }
                if(!check) ret++;
            }
        }
        out.println(ret);
        out.close();
    }
}
