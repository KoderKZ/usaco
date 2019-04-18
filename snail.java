/*
ID: xfrostb1
LANG: JAVA
TASK: snail
*/

import java.io.*;
import java.util.*;

public class snail {
    static int N, ret = 0;
    static boolean[][] visited;
    static boolean[][] barrier;
    static int[][] dirp = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("snail.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];
        barrier = new boolean[N][N];
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            String s = f.readLine();

            System.out.println((Integer.parseInt(s.substring(1, s.length()))-1)+", "+(s.charAt(0)-'A'));
            barrier[s.charAt(0)-'A'][Integer.parseInt(s.substring(1, s.length()))-1] = true;
        }

        dfs(0, 0, 0, 1);
        dfs(0, 0, 1, 1);

        out.println(ret);
        out.close();
    }
    static void dfs(int x, int y, int dir, int count){
        if(!visited[x][y]) {
            int dx = dirp[dir][0] + x;
            int dy = dirp[dir][1] + y;
            visited[x][y] = true;
            if (dx < N && dx >= 0 && dy < N && dy >= 0 && barrier[dx][dy] == false) {
                if(visited[dx][dy] == false) {
                    dfs(dx, dy, dir, count + 1);
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if(i != dir) {
                        int nx = dirp[i][0] + x;
                        int ny = dirp[i][1] + y;
                        if (nx < N && nx >= 0 && ny < N && ny >= 0 && barrier[nx][ny] == false && visited[nx][ny] == false) {
                            dfs(nx, ny, i, count + 1);
                        }
                    }
                }
            }
            visited[x][y] = false;
            ret = Math.max(ret, count);
        }
    }
}
