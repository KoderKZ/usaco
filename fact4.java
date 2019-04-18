/*
ID: xfrostb1
LANG: JAVA
TASK: fact4
*/

import java.io.*;
import java.util.*;

public class fact4 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
        int n = Integer.parseInt(f.readLine());

        long at = 1;
        for(int i = 2; i <= n;i++)
        {
            at *= i;
            while(at %10 == 0) at /= 10;
            at = at %100000000000L;
        }
        out.println(at%10);
        out.close();
    }
}
