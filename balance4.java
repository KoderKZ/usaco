/*
ID: xfrostb1
LANG: JAVA
TASK: balance3
*/

import java.io.*;
import java.util.*;

public class balance4 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("balance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
        int N = Integer.parseInt(f.readLine());
        int[] board = new int[2*N];
        StringTokenizer st = new StringTokenizer(f.readLine());
        int xzeros = 0;
        int xones = 0;
        int yzeros = 0;
        int yones = 0;
        PriorityQueue<Integer> lo = new PriorityQueue<>();
        PriorityQueue<Integer> lz = new PriorityQueue<>();
        PriorityQueue<Integer> ro = new PriorityQueue<>();
        PriorityQueue<Integer> rz = new PriorityQueue<>();
        for(int i = 0; i < 2*N; i++){
            board[i] = Integer.parseInt(st.nextToken());
            if(board[i]==0){
                if(i<N){
                    xzeros++;
                    lz.add(-i);
                }
                else {
                    yzeros++;
                    rz.add(i);
                }
            }else{
                if(i<N){
                    xones++;
                    lo.add(-i);
                }
                else {
                    yones++;
                    ro.add(i);
                }
            }
        }
        int xmax = xones*xzeros;
        int ymax = yones*yzeros;

        int[] xz = new int[N];
        xz[N-1] = board[N-1]==0?1:0;
        for(int i = N-2; i >= 0; i--){
            xz[i] = xz[i+1]+(board[i]==0?1:0);
        }

        int xinv = 0;
        for(int i = N-1; i >= 0; i--){
            if(board[i]==1) xinv+=xz[i];
        }

        int[] yz = new int[N];
        yz[N-1] = board[2*N-1]==0?1:0;
        for(int i = N-2; i >= 0; i--){
            yz[i] = yz[i+1]+(board[N+i]==0?1:0);
        }

        int yinv = 0;
        for(int i = 2*N-1; i >= N; i--){
            if(board[i]==1) yinv+=yz[i-N];
        }

        long moves = 0;
        if((lo.size() > 0 && rz.size() > 0)) {
            int leftone = -lo.peek();
            int rightzero = rz.peek();
            int tempx = 0;
            int tempy = 0;
            for(int i = 0; i < leftone; i++) if(board[i]==1)tempx++;
            for(int i = leftone+1; i< N; i++)if(board[i]==0)tempx--;
            for(int i = N; i < rightzero; i++) if(board[i]==1)tempy--;
            for(int i = rightzero+1; i< 2*N; i++)if(board[i]==0)tempy++;
            while ((((tempx-tempy) > 0 && (yinv - xinv) > 0) || ((tempx-tempy) < 0 && (yinv - xinv) < 0)) ) {
                if(Math.abs(tempx-tempy)/(double)(rightzero-leftone) >1) {
                    moves += Math.abs(leftone + rightzero);

                    xinv += (tempx);
                    yinv += (tempy);
                    xones--;
                    yones++;
                    yzeros--;
                    xzeros++;

                    lo.remove();
                    rz.remove();
                    lz.add(N-1);
                    ro.add(N);
                    if (!(lo.size() > 0 && rz.size() > 0)) break;
                    leftone = -lo.peek();
                    rightzero = rz.peek();
                    tempx = 0;
                    tempy = 0;
                    for(int i = 0; i < leftone; i++) if(board[i]==1)tempx++;
                    for(int i = leftone+1; i< N; i++)if(board[i]==0)tempx--;
                    for(int i = N; i < rightzero; i++) if(board[i]==1)tempy--;
                    for(int i = rightzero+1; i< 2*N; i++)if(board[i]==0)tempy++;
                }else{
                    break;
                }
            }
        }

        if((lz.size() > 0 && ro.size() > 0)) {
            int leftzero = -lz.peek();
            int rightone = ro.peek();
            int tempx = 0;
            int tempy = 0;
            for(int i = 0; i < leftzero; i++) {
                if(board[i]==1)tempx--;
            }
            for(int i = leftzero+1; i< N; i++){
                if(board[i]==0)tempx++;
            }
            for(int i = N; i < rightone; i++) {
                if(board[i]==1)tempy++;
            }
            for(int i = rightone+1; i< 2*N; i++){
                if(board[i]==0)tempy--;
            }
            while ((((tempx-tempy) > 0 && (yinv - xinv) > 0) || ((tempx-tempy) < 0 && (yinv - xinv) < 0))) {

                if(Math.abs(tempx-tempy)/(double)(rightone-leftzero) >1) {

                    moves += Math.abs(leftzero - rightone);

                    xinv += (tempx);
                    yinv += (tempy);
                    xones++;
                    yones--;
                    yzeros++;
                    xzeros--;
                    lz.remove();
                    ro.remove();
                    lo.add(N-1);
                    rz.add(N);
                    if (!(lz.size() > 0 && ro.size() > 0)) break;
                    board[leftzero] = 1;
                    board[rightone] = 0;
                    leftzero = -lz.peek();
                    rightone = ro.peek();
                    tempx = 0;
                    tempy = 0;
                    for(int i = 0; i < leftzero; i++) if(board[i]==1)tempx--;
                    for(int i = leftzero+1; i< N; i++)if(board[i]==0)tempx++;
                    for(int i = N; i < rightone; i++) if(board[i]==1)tempy++;
                    for(int i = rightone+1; i< 2*N; i++)if(board[i]==0)tempy--;
                }else{
                    break;
                }
            }
        }



        out.println(moves+Math.abs(xinv-yinv));
        out.close();




    }
}
