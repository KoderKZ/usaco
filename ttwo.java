/*
 ID: xfrostb1
 LANG: JAVA
 TASK: ttwo
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ttwo {
    static String[][] grid = new String[10][10];
    static int fx, fy, cx, cy, fd = 0, cd = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

        for(int i = 0; i < 10; i++){
            String x = f.readLine();
            for(int j = 0; j < 10; j++){

                grid[j][i] = String.valueOf(x.charAt(j));
                if (grid[j][i].equals("F")){
                    fx = j;
                    fy = i;
                }else if (grid[j][i].equals("C")){
                    cx = j;
                    cy = i;
                }
            }
        }

        int i = 1;
        for(; i < 10000; i++){
            move();
            if(valid()) break;
        }
        if(i == 10000) out.println(0);
        else out.println(i);
        out.close();
    }
    static void move(){
        if(fd == 0 && (fy == 0 || grid[fx][fy-1].equals("*"))) fd = 1;
        else if(fd == 2 && (fy == 9 || grid[fx][fy+1].equals("*"))) fd = 3;
        else if(fd == 1 && (fx == 9 || grid[fx+1][fy].equals("*"))) fd = 2;
        else if(fd == 3 && (fx == 0 || grid[fx-1][fy].equals("*"))) fd = 0;
        else if(fd == 0) fy--;
        else if(fd == 1) fx++;
        else if(fd == 2) fy++;
        else if(fd == 3) fx--;

        if(cd == 0 && (cy == 0 || grid[cx][cy-1].equals("*"))) cd = 1;
        else if(cd == 2 && (cy == 9 || grid[cx][cy+1].equals("*"))) cd = 3;
        else if(cd == 1 && (cx == 9 || grid[cx+1][cy].equals("*"))) cd = 2;
        else if(cd == 3 && (cx == 0 || grid[cx-1][cy].equals("*"))) cd = 0;
        else if(cd == 0) cy--;
        else if(cd == 1) cx++;
        else if(cd == 2) cy++;
        else if(cd == 3) cx--;
    }
    static boolean valid(){
        return fx == cx && fy == cy;
    }

}