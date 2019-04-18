/*
ID: xfrostb1
LANG: JAVA
TASK: ratios
*/

import java.io.*;
import java.util.*;

public class ratios {
    static Amt first, second, third;
    static double x, y, z;
    static PrintWriter out;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        Amt goal = new Amt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(f.readLine());
        first = new Amt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(f.readLine());
        second = new Amt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(f.readLine());
        third = new Amt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        solve2(goal);

        if(!flag) out.println("NONE");
        out.close();

//        boolean flag = false;
//        for(int i = 1; i < 100; i++){
//            x = 0;
//            y = 0;
//            z = 0;
//            if(solve(goal.barley*i, goal.oats*i, goal.wheat*i)){
//                out.println(x+" "+y+" "+z+" "+i);
//                flag = true;
//                break;
//            }
//        }
//        if(!flag) out.println("none");
//
//        out.close();
    }
    static void solve2(Amt goal){
        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 101; j++){
                for(int k = 0; k < 101; k++){
                    x = (double)(i*first.barley + j*second.barley + k*third.barley)/goal.barley;
                    y = (double)(i*first.wheat + j*second.wheat + k*third.wheat)/goal.wheat;
                    z = (double)(i*first.oats + j*second.oats + k*third.oats)/goal.oats;
                    if(i == 0 && j == 0 && k == 0) continue;
                    if(goal.barley == 0){
                        if(y != Double.POSITIVE_INFINITY && y != 0.0/0.0) x = y;
                        else x = z;
                    }
                    if(goal.wheat == 0){
                        if(x != Double.POSITIVE_INFINITY && x != 0.0/0.0) y = x;
                        else y = z;
                    }
                    if(goal.oats == 0){
                        if(x != Double.POSITIVE_INFINITY && x != 0.0/0.0) z = x;
                        else z = y;
                    }
                    if(x == y && y == z) {
                        if (((double) (i * first.barley + j * second.barley + k * third.barley) / goal.barley) % 1 == 0) {
                            out.println(i + " " + j + " " + k + " " + (i * first.barley + j * second.barley + k * third.barley) / goal.barley);
                            flag = true;
                            return;
                        }
                    }

                }
            }
        }
    }
    static boolean solve(int a, int b, int c){
        if(a == 0 && b == 0 && c == 0) {
            return true;
        }

        if(a < 0 || b < 0 || c < 0) return false;

        x++;
        if(solve(a-first.barley, b-first.oats, c-first.wheat)) return true;
        else x--;

        y++;
        if(solve(a-second.barley, b-second.oats, c-second.wheat)) return true;
        else y--;

        z++;
        if(solve(a-third.barley, b-third.oats, c-third.wheat)) return true;
        else z--;

        return false;
    }


    static class Amt{
        int barley, oats, wheat;
        public Amt(int b, int o, int w){
            barley = b;
            oats = o;
            wheat = w;
        }
    }
}
