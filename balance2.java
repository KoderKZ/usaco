/*
ID: xfrostb1
LANG: JAVA
TASK: balance
*/

import java.io.*;
import java.util.*;

public class balance2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("balance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
        int N = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] board = new int[2*N];
        for(int i = 0; i < 2*N; i++) board[i] = Integer.parseInt(st.nextToken());

        int[] xzeros = new int[N];
        xzeros[N-1] = board[N-1]==0?1:0;
        for(int i = N-2; i >= 0; i--){
            xzeros[i] = xzeros[i+1]+(board[i]==0?1:0);
        }

        int xinv = 0;
        for(int i = N-1; i >= 0; i--){
            if(board[i]==1) xinv+=xzeros[i];
        }

        int[] yzeros = new int[N];
        yzeros[N-1] = board[2*N-1]==0?1:0;
        for(int i = N-2; i >= 0; i--){
            yzeros[i] = yzeros[i+1]+(board[N+i]==0?1:0);
        }

        int yinv = 0;
        for(int i = 2*N-1; i >= N; i--){
            if(board[i]==1) yinv+=yzeros[i-N];
        }


        PriorityQueue<node> pq = new PriorityQueue<>();
        HashMap<node, Boolean> visited = new HashMap<>();
        pq.add(new node(board, xinv, yinv, 0));
        while(!pq.isEmpty()){
            node state = pq.poll();
            if(!visited.containsKey(state)) {
                if (state.xinversions - state.yinversions == 0) {
                    out.println(state.moves);
                    break;
                }

                for (int i = 1; i < N - 1; i++) {
                    if (state.board[i] == 1 && state.board[i - 1] == 0) {
                        int[] temp = Arrays.copyOf(state.board, state.board.length);
                        temp[i - 1] = 1;
                        temp[i] = 0;
                        pq.add(new node(temp, state.xinversions + 1, state.yinversions, state.moves + 1));
                    }
                    if (state.board[i] == 1 && state.board[i + 1] == 0) {
                        int[] temp = Arrays.copyOf(state.board, state.board.length);
                        temp[i + 1] = 1;
                        temp[i] = 0;
                        pq.add(new node(temp, state.xinversions - 1, state.yinversions, state.moves + 1));
                    }

                }
                for (int i = 1; i < 2 * N - 1; i++) {
                    if (state.board[i] == 1 && state.board[i - 1] == 0) {
                        int[] temp = Arrays.copyOf(state.board, state.board.length);
                        temp[i - 1] = 1;
                        temp[i] = 0;
                        pq.add(new node(temp, state.xinversions, state.yinversions + 1, state.moves + 1));
                    }
                    if (state.board[i] == 1 && state.board[i + 1] == 0) {
                        int[] temp = Arrays.copyOf(state.board, state.board.length);
                        temp[i + 1] = 1;
                        temp[i] = 0;
                        pq.add(new node(temp, state.xinversions, state.yinversions - 1, state.moves + 1));
                    }
                }

                if (state.board[N - 1] == 1 && state.board[N] == 0) {
                    int x = 0;
                    for(int i = 0; i < N-1; i++){
                        x+=state.board[i]==1?1:0;
                    }
                    int y = 0;
                    for(int i = N+1; i < 2*N; i++){
                        y+=state.board[i]==0?1:0;
                    }
                    int[] temp = Arrays.copyOf(state.board, state.board.length);
                    temp[N] = 1;
                    temp[N - 1] = 0;
                    pq.add(new node(temp, state.xinversions+x, state.yinversions+y, state.moves + 1));
                }
                if (state.board[N] == 1 && state.board[N-1] == 0) {
                    int x = 0;
                    for(int i = 0; i < N-1; i++){
                        x+=state.board[i]==1?1:0;
                    }
                    int y = 0;
                    for(int i = N+1; i < 2*N; i++){
                        y+=state.board[i]==0?1:0;
                    }
                    int[] temp = Arrays.copyOf(state.board, state.board.length);
                    temp[N] = 1;
                    temp[N - 1] = 0;
                    pq.add(new node(temp, state.xinversions-x, state.yinversions-y, state.moves + 1));
                }
            }
        }
        out.close();

    }

    static class node implements Comparable<node>{
        int[] board;
        int xinversions;
        int yinversions;
        int moves;
        public node(int[] b, int x, int y, int n){
            xinversions = x;
            yinversions = y;
            board = b;
            moves = n;
        }

        @Override
        public int compareTo(node o) {
            if(Math.abs(xinversions-yinversions)-Math.abs(o.xinversions-o.yinversions)!=0)return Math.abs(xinversions-yinversions)-Math.abs(o.xinversions-o.yinversions);
            if(moves!=o.moves)return moves-o.moves;
            return 0;
        }
    }
}