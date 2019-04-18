/*
ID: xfrostb1
LANG: JAVA
TASK: starry
*/

import java.io.*;
import java.util.*;

class cluster{
    int x,y,ones;
    int table[][];
    int a,b,c,d,e;
    public cluster(int vis[][], int x1, int x2, int y1, int y2, int t){
        x=x2-x1;
        y=y2-y1;
        a=x1;b=x2;c=y1;d=y2;
        e=t;
        table = new int[x+1][y+1];
        for(int i=x1;i<=x2;i++){
            for(int j=y1;j<=y2;j++){
                if(vis[i][j]==t){
                    table[i-x1][j-y1] = 1;
                    ones++;
                }
            }
        }
    }
    public void pint(){
        //System.out.println(ones);
        //Dumper.print_2_arr(table, x+1, y+1);
    }
    boolean rotate_0(cluster k){
        if(this.x!=k.x||this.y!=k.y) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[i][j]) return false;
            }
        }
        return true;
    }
    boolean rotate_90(cluster k){
        if(this.x!=k.y||this.y!=k.x) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[j][x-i]) return false;
            }
        }
        return true;
    }
    boolean rotate_180(cluster k){
        if(this.x!=k.x||this.y!=k.y) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[x-i][y-j]) return false;
            }
        }
        return true;
    }
    boolean rotate_270(cluster k){
        if(this.x!=k.y||this.y!=k.x) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[y-j][i]) return false;
            }
        }
        return true;
    }
    boolean flip(cluster k){
        if(this.x!=k.x||this.y!=k.y) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[i][y-j]) return false;
            }
        }
        return true;
    }
    boolean flip_rotate_90(cluster k){
        if(this.x!=k.y||this.y!=k.x) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[y-j][x-i]) return false;
            }
        }
        return true;
    }
    boolean flip_rotate_180(cluster k){
        if(this.x!=k.x||this.y!=k.y) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[x-i][j]) return false;
            }
        }
        return true;
    }
    boolean flip_rotate_270(cluster k){
        if(this.x!=k.y||this.y!=k.x) return false;
        for(int i=0;i<x+1;i++){
            for(int j=0;j<y+1;j++){
                if(this.table[i][j] != k.table[j][i]) return false;
            }
        }
        return true;
    }
    boolean cmp(cluster o){
        if(this.ones!=o.ones) return false;
        if(o.ones==1) return true;
        if(this.rotate_0(o)) return true;
        if(this.rotate_90(o)) return true;
        if(this.rotate_180(o)) return true;
        if(this.rotate_270(o)) return true;
        if(this.flip(o)) return true;
        if(this.flip_rotate_90(o)) return true;
        if(this.flip_rotate_180(o)) return true;
        if(this.flip_rotate_270(o)) return true;
        return false;
    }
    void fill_color(int[][] vis, char[][] res, char s){
        for(int i=a;i<=b;i++){
            for(int j=c;j<=d;j++){
                if(vis[i][j]==e){
                    res[i][j] = s;
                }
            }
        }
    }
}

class problem2{
    StringBuilder sb = new StringBuilder();
    int w,h;
    int arr[][];
    int vis[][];
    boolean mark[];
    int min_x,max_x,max_y,min_y;
    cluster[] clusters = new cluster[500];
    int size=0;
    char[][] result;
    int dir[][] = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
    void solver() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("starry.in"));
        h = Integer.valueOf(reader.readLine());//cloumns
        w = Integer.valueOf(reader.readLine());//rows
        arr = new int[w][h];
        vis = new int[w][h];
        result = new char[w][h];
        for(int i=0;i<w;i++){
            String tmp = reader.readLine();
            for(int j=0;j<h;j++){
                arr[i][j] = tmp.charAt(j)-'0';
                result[i][j] = '0';
            }
        }
        floodfill();
        cmp1();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));
        pw.print(sb.toString());
        pw.close();
        System.exit(0);
    }
    void floodfill(){
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(vis[i][j]==0&&arr[i][j]==1){
                    min_x=i;max_x=i;max_y=j;min_y=j;
                    dfs(i,j);
                    clusters[size] = new cluster(vis,min_x,max_x,min_y,max_y,size+1);
                    size++;
                }
            }
        }
    }
    void dfs(int x,int y){
        vis[x][y]=size+1;
        if(x>max_x)max_x=x;
        if(x<min_x)min_x=x;
        if(y>max_y)max_y=y;
        if(y<min_y)min_y=y;
        for(int i=0;i<8;i++){
            int nx = x+dir[i][0];
            int ny = y+dir[i][1];
            if(nx>=0&&ny>=0&&nx<w&&ny<h&&arr[nx][ny]==1&&vis[nx][ny]==0) dfs(nx,ny);
        }
    }
    void cmp1(){
        mark = new boolean[size];
        char s = 'a';
        for(int i=0;i<size;i++){
            if(mark[i]) continue;
            clusters[i].fill_color(vis, result, s);
            mark[i]=true;
            for(int j=i+1;j<size;j++){
                if(mark[j]) continue;
                if(clusters[i].cmp(clusters[j])){
                    mark[j]=true;
                    clusters[j].fill_color(vis, result, s);
                }
            }
            s++;
        }
        for(int i=0;i<w;++i){
            for(int j=0;j<h;++j){
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
    }
}
public class starry {
    public static void main(String[] args) throws Exception {
        problem2 p = new problem2();
        p.solver();
    }

}
//
//public class starry {
//    static int[][] sky;
//    static char[][] ret;
//    static boolean[][] visited;
//    static int W, H, maxx = Integer.MIN_VALUE, minx = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE, miny = Integer.MAX_VALUE;
//    static TreeMap<Character, int[][]> clusters = new TreeMap<>();
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("starry.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));
//        StringTokenizer st;
//        W = Integer.parseInt(f.readLine());
//        H = Integer.parseInt(f.readLine());
//        sky = new int[W][H];
//        ret = new char[W][H];
//        visited = new boolean[W][H];
//        for(int i = 0; i < H; i++){
//            String s = f.readLine();
//            for(int j = 0; j < W; j++){
//                sky[j][i] = Integer.parseInt(s.substring(j,j+1));
//                ret[j][i] = s.charAt(j);
//            }
//        }
//        char count = 'a';
//        for(int i = 0; i < H; i++){
//            for(int j = 0; j < W; j++){
//
//                if(sky[j][i] == 1 && !visited[j][i]){
//                    for(int a = 0; a < H;a++){
//                        for(int b = 0; b < W; b++){
//                            System.out.print(ret[b][a]);
//                        }
//                        System.out.println();
//                    }
//                    maxx = Integer.MIN_VALUE;
//                    minx = Integer.MAX_VALUE;
//                    maxy = Integer.MIN_VALUE;
//                    miny = Integer.MAX_VALUE;
//                    dfs(j,i,count);
//
//                    System.out.println();
//                    int[][] cluster = new int[maxx-minx+1][maxy-miny+1];
//                    for(int l = miny; l <= maxy; l++) {
//                        for (int k = minx; k <= maxx; k++) {
//                            if(ret[k][l] == count){
//                                cluster[k-minx][l-miny] = sky[k][l];
//                            }else{
//                                cluster[k-minx][l-miny] = 0;
//                            }
//                        }
//                    }
//                    char compare = compare(cluster);
//                    if(compare != '@'){
//                        for(int l = miny; l <= maxy; l++) {
//                            for (int k = minx; k <= maxx; k++) {
//                                if(sky[k][l] == 1){
//                                    ret[k][l] = compare;
//                                }
//                            }
//                        }
//
//                    }else{
//                        clusters.put(count, cluster);
//                        count+=1;
//
//                    }
//
//                }
//            }
//        }
//
//        for(int i = 0; i < H; i++){
//            for(int j = 0; j < W; j++){
//                out.print(ret[j][i]);
//            }
//            out.println();
//        }
//        out.close();
//
//
//    }
//    static void dfs(int x, int y, char i){
//        if(!visited[x][y] && sky[x][y] == 1) {
//            visited[x][y] = true;
//            ret[x][y] = i;
//            maxx = Math.max(maxx,x);
//            maxy = Math.max(maxy,y);
//            minx = Math.min(minx,x);
//            miny = Math.min(miny,y);
//            if (x < W - 1 && sky[x + 1][y] == sky[x][y]) dfs(x + 1, y, i);
//            if (y < H - 1 && sky[x][y + 1] == sky[x][y]) dfs(x, y + 1, i);
//            if (x > 0 && sky[x - 1][y] == sky[x][y]) dfs(x - 1, y, i);
//            if (y > 0 && sky[x][y - 1] == sky[x][y]) dfs(x, y - 1, i);
//            if (x < W - 1 && y < H - 1 && sky[x + 1][y + 1] == sky[x][y]) dfs(x + 1, y + 1, i);
//            if (x > 0 && y > 0 && sky[x - 1][y - 1] == sky[x][y]) dfs(x - 1, y - 1, i);
//            if (x < W - 1 && y > 0 && sky[x + 1][y - 1] == sky[x][y]) dfs(x + 1, y - 1, i);
//            if (x > 0 && y < H - 1 && sky[x - 1][y + 1] == sky[x][y]) dfs(x - 1, y + 1, i);
//        }
//    }
//
//    static char compare(int[][] cluster){
//        for(char x : clusters.keySet()){
//            int[][] i = clusters.get(x);
//            if(cluster.length == i.length && cluster[0].length == i[0].length){
//                if(comp(cluster,i)) return x;
//                if(comp(cluster,rotateCW(rotateCW(i)))) return x;
//                if(comp(cluster, flipx(i))) return x;
//                if(comp(cluster, flipy(i))) return x;
//                if(comp(cluster, rotateCW(rotateCW(flipx(i))))) return x;
//                if(comp(cluster, rotateCW(rotateCW(flipy(i))))) return x;
//            }
//            if(cluster.length == i[0].length && cluster[0].length == i.length){
//                if(comp(cluster,rotateCW(i))) return x;
//                if(comp(cluster, rotateCW(flipx(i)))) return x;
//                if(comp(cluster, rotateCW(flipy(i)))) return x;
//                if(comp(cluster,rotateCW(rotateCW(rotateCW(i))))) return x;
//                if(comp(cluster, rotateCW(rotateCW(rotateCW(flipx(i)))))) return x;
//                if(comp(cluster, rotateCW(rotateCW(rotateCW(flipy(i)))))) return x;
//            }
//        }
//        return '@';
//    }
//    static boolean comp(int[][] a, int[][] b){
//        for(int i = 0; i < a.length; i++){
//            for(int j = 0; j < a[0].length; j++){
//                if(a[i][j] != b[i][j]) return false;
//            }
//        }
//        return true;
//    }
//    static int[][] rotateCW(int[][] mat) {
//        final int M = mat.length;
//        final int N = mat[0].length;
//        int[][] ret = new int[N][M];
//        for (int r = 0; r < M; r++) {
//            for (int c = 0; c < N; c++) {
//                ret[c][M-1-r] = mat[r][c];
//            }
//        }
//        return ret;
//    }
//    static int[][] flipx(int[][] mat){
//        int[][] ret = new int[mat.length][mat[0].length];
//        for(int i = 0; i < ret[0].length; i++){
//            for(int j = 0; j < ret.length; j++){
//                ret[j][i] = mat[j][ret[0].length-i-1];
//            }
//        }
//        return ret;
//    }
//    static int[][] flipy(int[][] mat){
//        int[][] ret = new int[mat.length][mat[0].length];
//        for(int i = 0; i < ret[0].length; i++){
//            for(int j = 0; j < ret.length; j++){
//                ret[j][i] = mat[ret.length-j-1][i];
//            }
//        }
//        return ret;
//    }
//}
