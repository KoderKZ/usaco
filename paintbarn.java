/*
ID: xfrostb1
LANG: JAVA
TASK: paintbarn
*/

import java.io.*;
import java.util.*;
//
//public class paintbarn {
//    static int[][] coats;
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        coats = new int[201][201];
//        int minx = 200;
//        int maxx = 0;
//        int miny = 200;
//        int maxy = 0;
//        int ret = 0;
//        int[][][][] dp = new int[201][201][201][201];
//
//        for(int i = 0; i < N; i++){
//            st = new StringTokenizer(f.readLine());
//            int x1 = Integer.parseInt(st.nextToken());
//            int y1 = Integer.parseInt(st.nextToken());
//            int x2 = Integer.parseInt(st.nextToken());
//            int y2 = Integer.parseInt(st.nextToken());
//            for(int j = Math.min(x1,x2); j <= Math.max(x1,x2); j++){
//                for(int k = Math.min(y1,y2); k <= Math.max(y1,y2); k++){
//                    coats[j][k]++;
//                    if(coats[j][k]==M)ret++;
//                    if(coats[j][k]==M+1)ret--;
//                    dp[j][k][j][k] = coats[j][k]==2?1:0;
//                }
//            }
//            minx = Math.min(minx, Math.min(x1,x2));
//            maxx = Math.max(maxx, Math.max(x1,x2));
//            miny = Math.min(miny, Math.min(y1,y2));
//            maxy = Math.max(maxy, Math.max(y1,y2));
//        }
//
//        for(int i = 0; i <= 200; i++){
//            dp[0][i][0][i] = coats[0][i]==M-1?1:0;
//            dp[i][0][0][i] = coats[i][0]==M-1?1:0;
//        }
//
//        for(int i = 1; i < 201; i++){
//            for(int j = 0; j <= i; j++){
//                for(int k = 1; k < 201; k++){
//                    for(int n = 0; n <= k; n++){
//                        dp[n][j][k][i] = dp[n][j][k-1][i-1]+dp[k][j][k][i]+dp[n][i][k][i];
//                        if(coats[k][i]==M-1){
//                            dp[n][j][k][i]++;
//                        }else if(coats[k][i]==M){
//                            dp[n][j][k][i]--;
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//}
//
//
public class paintbarn {
    static int[][] coats;
    static int minx, maxx, miny, maxy;
    public static void main(String[] args) throws IOException {
        isMatch("aa","*");
//        BufferedReader f = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        coats = new int[201][201];
//        minx = 200;
//        maxx = 0;
//        miny = 200;
//        maxy = 0;
//        int ret = 0;
////        int[][][][] dp = new int[201][201][201][201];
//        int[][] real = new int[201][201];
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(f.readLine());
//            int x1 = Integer.parseInt(st.nextToken());
//            int y1 = Integer.parseInt(st.nextToken());
//            int x2 = Integer.parseInt(st.nextToken());
//            int y2 = Integer.parseInt(st.nextToken());
//            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
//                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
//                    coats[j][k]++;
//                    if (coats[j][k] == M) {
//                        ret++;
//                        real[j][k] = -1;
//                    }
//                    if (coats[j][k] == M + 1) ret--;
//                    if(coats[j][i] == M-1)real[j][k] = 1;
//                }
//            }
//            minx = Math.min(minx, Math.min(x1, x2));
//            maxx = Math.max(maxx, Math.max(x1, x2));
//            miny = Math.min(miny, Math.min(y1, y2));
//            maxy = Math.max(maxy, Math.max(y1, y2));
//        }
    }

    static int maxsum (int a[][]){
        return 0;
    }
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
    static boolean isMatch(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }
}