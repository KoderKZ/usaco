/*
ID: xfrostb1
LANG: JAVA
TASK: art2
*/

import java.io.*;
import java.util.*;

public class art2 {
    public static void main(String[] args) throws Exception{
        Scanner scan=new Scanner(new File("art2.in"));
        PrintWriter writer = new PrintWriter(new File("art2.out"));

        int n=scan.nextInt();
        int[] paint=new int[n];
        for(int i = 0; i < n; i++){
            paint[i] = scan.nextInt();
        }

        writer.println(layers(paint));
        writer.close();

    }

    //find longest palindrome of abc...nxxxxxxn...cba
    public static int layers(int[] paint){
        if(paint.length==1){
            return 1;
        }
        if(paint[0]==paint[paint.length-1]){
            int[] sub = Arrays.copyOfRange(paint, 1, paint.length-1);
            return 1+layers(sub);
        }

        return Math.max(layers(Arrays.copyOfRange(paint, 0, paint.length-1)), layers(Arrays.copyOfRange(paint, 1, paint.length)));

    }


}