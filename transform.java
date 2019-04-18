/*
ID: xfrostb1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.Arrays;

public class transform {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int n = Integer.parseInt(f.readLine());
        int[][] x = new int[n][n];
        int[][] y = new int[n][n];
        int[][] copy = new int[n][n];
        for(int i = 0; i < n; i++){
            String line = f.readLine();
            for(int j = 0; j < n; j++){
                if (line.charAt(j) == '@'){
                    x[j][i] = 1;
                    copy[j][i] = 1;
                }
            }
        }
        for(int i = 0; i < n; i++){
            String line = f.readLine();
            for(int j = 0; j < n; j++){
                if (line.charAt(j) == '@') y[j][i] = 1;
            }
        }
        if (check(rotateCW(copy), y)) out.println("1");
        else if (check(rotateCW(rotateCW(copy)), y)) out.println("2");
        else if (check(rotateCW(rotateCW(copy)), y)) out.println("3");
        else if (check(horizontal(copy), y)) out.println("4");
        else if (check(x, y)) out.println("6");
        else if (check(rotateCW(horizontal(copy)), y) || check(rotateCW(rotateCW(horizontal(copy))), y) || check(rotateCW(rotateCW(rotateCW(horizontal(copy)))), y)) out.println("5");
        else out.println("7");

        out.close();
    }
    static int[][] rotateCW(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length;
        int[][] ret = new int[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = mat[r][c];
            }
        }
        return ret;
    }
    static int[][] horizontal(int[][] mat) {
        int[][] ret = new int[mat.length][mat.length];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                ret[mat.length-1-j][i] = mat[j][i];
            }
        }
        return ret;
    }
    static boolean check(int[][] x, int[][] y){
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x.length; j++){
                if (x[i][j] != y[i][j]) return false;
            }
        }
        return true;
    }
}