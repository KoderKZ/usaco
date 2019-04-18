//import java.io.*;
//import java.util.*;
//
//public class multimoo {
//    static boolean[][] visited;
//    static int[][] board;
//    static LinkedList<edge> list;
//    static ArrayList[] g_orig;
//    static int N;
//    static ArrayList<Integer> counts;
//    static int count = 0;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("multimoo.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
//        N = Integer.parseInt(f.readLine());
//        g_orig = new ArrayList[1000001];
//        board = new int[N][N];
//        counts = new TreeMap<>();
//        for(int i = 0; i < N; i++){
//            StringTokenizer st = new StringTokenizer(f.readLine());
//            for(int j = 0; j < N; j++){
//                board[j][i] = Integer.parseInt(st.nextToken());
//            }
//        }
//        for (int i=0; i<1000001; i++) g_orig[i] = new ArrayList<Integer>();
//        visited = new boolean[N][N];
//        int oneret = 0;
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                if(!visited[i][j]){
//                    count = 0;
//                    counts.add(dfs(board[i][j], i, j));
//                }
//            }
//        }
//        for(int i : counts) oneret = Math.max(oneret, i);
//
//        int tworet = 0;
//        for(int i = 0; i < g_orig.length; i++){
//            if(g_orig[i].size() != 0) {
//                for (int j = 0; j < g_orig[i].size(); j++) {
//
//                    tworet = Math.max(tworet, counts.get(i)+(int)g_orig[i].get(j));
//                }
//            }
//        }
//        out.println(oneret);
//        out.println(tworet);
//        out.close();
//
//    }
//    static int dfs(int num, int x, int y){
//        if(!visited[x][y]) {
//
//            if(board[x][y] != num && (!g_orig[board[x][y]].contains(num) && !g_orig[num].contains(board[x][y]))) {
//                if (board[x][y] < num) {
//                    g_orig[board[x][y]].add(num);
//                } else {
//                    g_orig[num].add(board[x][y]);
//                }
//            }
//
//            if (board[x][y] == num) {
//                count++;
//                visited[x][y] = true;
//
//                if (x > 0) dfs(num, x - 1, y);
//                if (x < N - 1) dfs(num, x + 1, y);
//                if (y > 0) dfs(num, x, y - 1);
//                if (y < N - 1) dfs(num, x, y + 1);
//            }
//            return count;
//        }
//        return -1;
//    }
//    static class edge implements Comparable<edge>{
//        int x, y;
//        public edge(int X, int Y){
//            x = X;
//            y = Y;
//        }
//
//        @Override
//        public int compareTo(edge o) {
//            if(x-o.x == 0 && y-o.y == 0) return 0;
//            return 1;
//        }
//    }
//}
