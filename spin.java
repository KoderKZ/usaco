/*
ID: xfrostb1
LANG: JAVA
TASK: spin
*/

import java.io.*;
import java.util.*;

public class spin {
    static int[] speeds;
    static wedge[][] wedges;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("spin.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
        speeds = new int[5];
        wedges = new wedge[5][];
        for(int i = 0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            speeds[i] = Integer.parseInt(st.nextToken());
            wedges[i] = new wedge[Integer.parseInt(st.nextToken())];

            for(int j = 0; j < wedges[i].length; j++){
                wedges[i][j] = new wedge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }

        boolean flag = false;
        for(int i = 0; i < 360; i++){
            if(pos(i)){
                out.println(i);
                flag = true;
                break;
            }
        }
        if(!flag){
            out.println("none");
        }
        out.close();
    }
    static boolean pos(int t){
        for(int i = 0; i < 720; i++){
            boolean[] found = new boolean[5];
            for(int j = 0; j < 5; j++){
                int add = (speeds[j]*t);

                for(wedge w : wedges[j]) {
                    int start = (add+w.start)%360;
                    if ((i >= start && i <= start+w.end) || (i % 360 >= start && i % 360 <= start+w.end)){
                        found[j] = true;
                    }
                }
            }
            if(found[0] && found[1] && found[2] && found[3] && found[4]) return true;
        }
        return false;
    }
    static class wedge{
        int start, end;
        public wedge(int s, int e){
            start = s;
            end = e;
        }
    }
}
