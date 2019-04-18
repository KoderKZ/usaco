/*
ID: xfrostb1
LANG: JAVA
TASK: game1
*/

import java.io.*;
import java.util.*;

public class game1 {
    static int[][][] cache;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("game1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
        int N = Integer.parseInt(f.readLine());
        String s = f.readLine();
        numbers = new int[N];
        int counter = 0;
        while(s != null){
            StringTokenizer st = new StringTokenizer(s);
            while(st.hasMoreTokens()){
                numbers[counter++] = Integer.parseInt(st.nextToken());
            }
            s = f.readLine();
        }

        int[] ret = solve();
        out.println(ret[0]+" "+ret[1]);
        out.close();

    }



    // DFS with cache, which is in effect DP
    static int[] solve() {
        int l = numbers.length;
        cache = new int[l][l][2];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                cache[i][j][0] = cache[i][j][1] = -1;
            }
        }

        return dfs(0, l - 1);

    }

    static int[] dfs(int start, int end) {
        if (cache[start][end][0] > -1) return cache[start][end];

        int turn = (numbers.length - (end - start + 1)) % 2;

        int[] res;
        if (end - start + 1 == 2) {
            res = new int[2];
            res[turn] += Math.max(numbers[start], numbers[end]);
            res[1 - turn] += Math.min(numbers[start], numbers[end]);

            cache[start][end][0] = res[0];
            cache[start][end][1] = res[1];
            return res;
        }

        int[] rightSums = dfs(start, end - 1);
        rightSums[turn] += numbers[end];

        int[] leftSums = dfs(start + 1, end);
        leftSums[turn] += numbers[start];

        if (leftSums[turn] > rightSums[turn]) {
            res = leftSums;
        } else {
            res =  rightSums;
        }
        cache[start][end][0] = res[0];
        cache[start][end][1] = res[1];
        return res;
    }

}
