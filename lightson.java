import java.io.*;
import java.util.*;

public class lightson {
    static ArrayList[][] X, Y;
    static boolean[][] lit, visited, real;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        X = new ArrayList[n][n];
        Y = new ArrayList[n][n];
        lit = new boolean[n][n];
        visited = new boolean[n][n];
        real = new boolean[n][n];
        lit[0][0] = true;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                X[i][j] = new ArrayList<Integer>();
                Y[i][j] = new ArrayList<Integer>();
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            X[x1-1][y1-1].add(x2);
            Y[x1-1][y1-1].add(y2);
        }
        dfs(0,0);
        int ret = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(lit[j][i]) ret++;
            }
        }
        out.println(ret);
        out.close();

    }
    static boolean dfs(int x, int y){
        if(!visited[x][y] && lit[x][y]) {
            visited[x][y] = true;
            boolean check = false;
            boolean check2 = false;
            if(X[x][y].size() > 0 && !real[x][y]) {
                for(int i = 0; i < X[x][y].size(); i++){
                    if(!lit[(int)X[x][y].get(i)-1][(int)Y[x][y].get(i)-1]) check2 = true;
                    lit[(int)X[x][y].get(i)-1][(int)Y[x][y].get(i)-1] = true;

                    if ((int)X[x][y].get(i)-1 > 0 && visited[(int)X[x][y].get(i)-2][(int)Y[x][y].get(i)-1]) dfs((int)X[x][y].get(i)-1, (int)Y[x][y].get(i)-1);
                    else if ((int)X[x][y].get(i)-1 < n - 1 && visited[(int)X[x][y].get(i)][(int)Y[x][y].get(i)-1]) dfs((int)X[x][y].get(i)-1, (int)Y[x][y].get(i)-1);
                    else if ((int)Y[x][y].get(i)-1 > 0 && visited[(int)X[x][y].get(i)-1][(int)Y[x][y].get(i)-2]) dfs((int)X[x][y].get(i)-1, (int)Y[x][y].get(i)-1);
                    else if ((int)Y[x][y].get(i)-1 < n-1 && visited[(int)X[x][y].get(i)-1][(int)Y[x][y].get(i)]) dfs((int)X[x][y].get(i)-1, (int)Y[x][y].get(i)-1);
                }
            }
            real[x][y] = true;
            if (x > 0 && dfs(x - 1, y)) check = true;
            if (x < n - 1 && dfs(x + 1, y)) check = true;
            if (y > 0 && dfs(x, y - 1)) check = true;
            if (y < n-1 && dfs(x, y + 1)) check = true;


            System.out.println(x+" "+ y);
            return check2;
        }
        return false;

    }
}
