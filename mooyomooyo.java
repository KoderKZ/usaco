import java.io.*;
import java.util.*;

public class mooyomooyo {
    static int[][] board;
    static int[] amt = new int[10];
    static int n, k;
    static boolean[][] visited, sec;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[10][n];
        for(int i = 0; i < n; i++){
            String x = f.readLine();
            for(int j = 0; j < 10; j++){
                board[j][i] = Integer.valueOf(String.valueOf(x.charAt(j)));
            }
        }
        visited = new boolean[10][n];
        sec = new boolean[10][n];
        boolean check = true;
        while(check){
            visited = new boolean[10][n];
            check = false;
//            for(int ia = 0; ia < n; ia++){
//                String x = f.readLine();
//                for(int ja = 0; ja < 10; ja++){
//                    System.out.print(board[ja][ia]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < 10; j++){
                    if(!visited[j][i] && board[j][i] != 0) {
                        dfs(j, i, board[j][i]);
                    }
                    if(amt[board[j][i]] >= k){
                        remove(j, i, board[j][i]);
                        sec = new boolean[10][n];
                        check = true;

                        amt[board[j][i]] = 0;
                    }
                    amt = new int[10];
                }
            }
            gravity();

        }

        for(int i = 0; i < n; i++){
            String x = f.readLine();
            for(int j = 0; j < 10; j++){
                out.print(board[j][i]);
            }
            out.println();
        }
        out.close();

    }
    static void gravity(){
        for(int i = 0; i < 10; i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = n-1; j >= 0; j--) if(board[i][j] != 0) list.add(board[i][j]);

            board[i] = new int[n];
            int count = n-1;
            for(int j : list){
                board[i][count] = j;
                count--;
            }
        }
    }
    static void dfs(int x, int y, int num){
        if(!visited[x][y] && board[x][y] == num){
            visited[x][y] = true;
            amt[num]++;

            if(x > 0) dfs(x-1, y, num);
            if(x < 9) dfs(x+1, y, num);
            if(y > 0) dfs(x, y-1, num);
            if(y < n-1) dfs(x, y+1, num);
        }
    }
    static void remove(int x, int y, int num){
        if(!sec[x][y] && board[x][y] == num){
            sec[x][y] = true;
            board[x][y] = 0;

            if(x > 0) remove(x-1, y, num);
            if(x < 9) remove(x+1, y, num);
            if(y > 0) remove(x, y-1, num);
            if(y < n-1) remove(x, y+1, num);
        }
    }
}
