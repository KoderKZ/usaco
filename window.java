/*
ID: xfrostb1
LANG: JAVA
TASK: window
*/

import java.io.*;
import java.util.*;

public class window {
    static int total = 0;
    static Window[] windows = new Window[1000];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("window.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));

        String s = f.readLine();
        while(s != null){
            if(s.charAt(0)=='w'){
                windows[total] = new Window(s);
                total++;
            }
            if(s.charAt(0)=='t'){
                if(total>1) {
                    int index = index(s.charAt(2));
                    Window temp = windows[index];
                    for (int i = index + 1; i < total; i++) {
                        windows[i - 1] = windows[i];
                    }
                    windows[total - 1] = temp;
                }
            }
            if (s.charAt(0) == 'b') {
                if(total>1) {
                    int index = index(s.charAt(2));
                    Window temp = windows[index];
                    for (int i = index - 1; i >= 0; i--) {
                        windows[i + 1] = windows[i];
                    }
                    windows[0] = temp;
                }
            }
            if(s.charAt(0)=='d'){
                int index = index(s.charAt(2));
                for(int i = index+1; i < total; i++){
                    windows[i-1] = windows[i];
                }
                total--;
            }
            if(s.charAt(0)=='s'){
                int index = index(s.charAt(2));
                Window w = windows[index];
                out.println(String.format("%.3f", (double)area(w.x1, w.x2, w.y1, w.y2, index+1)/w.area*100));
            }
            s = f.readLine();
        }
        out.close();
    }
    static int area(int x1, int x2, int y1, int y2, int pos){
        int ret = 0;
        while(pos<total && (windows[pos].x1>=x2 || windows[pos].x2 <= x1 || windows[pos].y1 >= y2 || windows[pos].y2 <= y1)) {
            pos++;
        }
        if(pos==total) return Math.abs(x1-x2)*Math.abs(y1-y2);
        if(windows[pos].x1 > x1) {
            ret+=area(x1, windows[pos].x1, y1, y2, pos+1);
            x1 = windows[pos].x1;
        }
        if(windows[pos].x2 < x2) {
            ret+=area(windows[pos].x2, x2, y1, y2, pos+1);
            x2 = windows[pos].x2;
        }
        if(windows[pos].y1 > y1) {
            ret+=area(x1, x2, y1, windows[pos].y1, pos+1);
            y1 = windows[pos].y1;
        }
        if(windows[pos].y2 < y2) {
            ret+=area(x1, x2, windows[pos].y2, y2, pos+1);
            y2 = windows[pos].y2;
        }

        return ret;
    }
    static int index(char x){
        for(int i = 0; i < total; i++){
            if(windows[i].id == x) return i;
        }
        return -1;
    }
    static class Window{
        char id;
        int x1, x2, y1, y2;
        int area;
        public Window(String s){
            id = s.charAt(2);
            int p = 4, num = 0;
            int nx1, nx2, ny1, ny2;
            while(s.charAt(p)!=','){
                num=num*10+s.charAt(p)-'0';
                p++;
            }
            nx1=num;
            p++;
            num=0;
            while(s.charAt(p)!=','){
                num=num*10+s.charAt(p)-'0';
                p++;
            }
            ny1=num;
            p++;
            num=0;
            while(s.charAt(p)!=','){
                num=num*10+s.charAt(p)-'0';
                p++;
            }
            nx2=num;
            p++;
            num=0;
            while(s.charAt(p)!=')'){
                num=num*10+s.charAt(p)-'0';
                p++;
            }
            ny2=num;
            p++;
            num=0;

            x1 = Math.min(nx1, nx2);
            x2 = Math.max(nx1, nx2);
            y1 = Math.min(ny1, ny2);
            y2 = Math.max(ny1, ny2);
            area = Math.abs(x1-x2)*Math.abs(y1-y2);
        }
    }
}
