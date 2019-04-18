/*
ID: xfrostb1
LANG: JAVA
TASK: cbarn
*/

import java.util.*;
import java.io.*;

public class cbarn {

    public static void main(String[] args) throws Exception {

        // Read in data - store two copies.
        BufferedReader stdin = new BufferedReader(new FileReader("cbarn.in"));
        int n = Integer.parseInt(stdin.readLine());
        int[] data = new int[2*n];
        for (int i=0; i<n; i++)
            data[i] = Integer.parseInt(stdin.readLine());
        for (int i=n; i<2*n; i++)
            data[i] = data[i-n];

        // Find the "lowest point", ie the prefix with the lowest sum minus number of terms.
        int min = data[0], minI = 0, sum = 0;
        for (int i=0; i<n; i++) {
            sum += data[i];
            sum--;
            if (sum < min) {
                min = sum;
                minI = i;
            }
        }

        // This shifted array always has a prefix sum if i items i or greater.
        int[] use = new int[n];
        for (int i=minI+1; i<=minI+n; i++)
            use[i-minI-1] = data[i];

        // Store result here.
        long cost = 0;
        int last = n-1;

        // Now go backwards.
        for (int i=n-1; i>=0; i--) {

            // We have a cow here.
            if (use[i] != 0) continue;

            // Go back until we find the next "extra" cow.
            while (last >= i) last--;
            while (last >= 0 && use[last] == 0) last--;
            if (last == -1) break;

            // Move this cow and add its cost.
            use[last]--;
            use[i]++;
            cost = cost + ((long)(last-i))*(last-i);
        }

        // Write result.
        PrintWriter out = new PrintWriter(new FileWriter("cbarn.out"));
        out.println(cost);
        out.close();
        stdin.close();
    }
}
