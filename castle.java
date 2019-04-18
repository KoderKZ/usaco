/*
ID: xfrostb1
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class castle {
    static int[][] visited;
    static room[][] rooms;
    static int x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        visited = new int[x][y];
        rooms = new room[x][y];
        for(int i = 0; i < y; i++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < x; j++){
                rooms[j][i] = new room(Integer.parseInt(st.nextToken()));
            }
        }
        ArrayList<Integer> flood = new ArrayList<>();
        int count = 0;
        int ret = 0;
        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                if (visited[j][i] == 0) {
                    count++;
                    int newret = dfs(j, i, count, 0);
                    ret = Math.max(newret, ret);
                    flood.add(newret);
                }
            }
        }
        int ret2 = 0;
        int retx = -1;
        int rety = -1;
        int retd = -1;
        for(int j = 0; j < x; j++){
            for(int i = y-1; i > -1; i--){
                if (i > 0){
                    if (!rooms[j][i].n && !rooms[j][i-1].s && visited[j][i] != visited[j][i-1]){
                        if (ret2 < flood.get(visited[j][i]-1)+flood.get(visited[j][i-1]-1)) {

                            ret2 = Math.max(ret2, flood.get(visited[j][i]-1) + flood.get(visited[j][i - 1]-1));
                            retx = j + 1;
                            rety = i + 1;
                            retd = 0;
                        }
                    }
                }
                if (j < x-1){
                    if (!rooms[j][i].e && !rooms[j+1][i].w && visited[j][i] != visited[j+1][i]){
                        if (ret2 < flood.get(visited[j][i]-1)+flood.get(visited[j+1][i]-1)){
                            retx = j+1;
                            rety = i+1;
                            retd = 1;
                            ret2 = flood.get(visited[j][i]-1)+flood.get(visited[j+1][i]-1);
                        }
                        ret2 = Math.max(ret2, flood.get(visited[j][i]-1)+flood.get(visited[j+1][i]-1));
                    }
                }

            }
        }
        out.println(flood.size());
        out.println(ret);
        out.println(ret2);
        out.print(rety + " " + retx + " ");
        if (retd == 0) {
            out.println("N");
        }
        if (retd == 1){
            out.println("E");
        }
        out.close();

    }
    static int dfs(int n, int m, int index, int count){
        if (visited[n][m] != 0) return count;
        count++;
        visited[n][m] = index;
        if (n > 0){
            if (rooms[n][m].w && rooms[n-1][m].e) count = dfs(n-1, m, index, count);
        }
        if (n < x-1){
            if (rooms[n][m].e && rooms[n+1][m].w) count = dfs(n+1, m, index, count);
        }
        if (m > 0){
            if (rooms[n][m].n && rooms[n][m-1].s) count = dfs(n, m-1, index, count);
        }
        if (m < y-1){
            if (rooms[n][m].s && rooms[n][m+1].n) count = dfs(n, m+1, index, count);
        }
        return count;
    }
    static class room{
        boolean n = true, s = true, e = true, w = true;
        public room(int in){
            if (in % 2 == 1){
                w = false;
                in--;
            }
            if (in >= 8){
                s = false;
                in -= 8;
            }
            if (in >= 4){
                e = false;
                in -= 4;
            }
            if (in >= 2){
                n = false;
            }
        }
    }
}
