/*
ID: xfrostb1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class skidesign {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("skidesign.in"));
        PrintWriter pw = new PrintWriter(new File("skidesign.out"));
        int n = in.nextInt();
        int [] hills = new int [10000];
        for(int i = 0; i < n; i++) {
            hills[i] = in.nextInt();
        }

        in.close();
        int mincost = Integer.MAX_VALUE;

        for(int i = 0; i <= 83; i++) {

            int cost = 0, x;

            for(int j = 0; j < n; j++) {

                if(hills[j] < i)
                    x = i - hills[j];

                else if(hills[j] > i + 17)
                    x = hills[j] - (i + 17);

                else
                    x = 0;

                cost += x * x;
            }

            if(cost < mincost)
                mincost = cost;

        }

        pw.println(mincost);
        pw.close();
    }
}