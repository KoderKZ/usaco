/*
ID: xfrostb1
LANG: JAVA
TASK: theme
*/

import java.io.*;
import java.util.*;

public class theme {
    static int n;
    static int[] numbers, arr, header, prev;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("theme.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));
        n=Integer.valueOf(reader.readLine());
        arr=new int[n-1];
        prev = new int[n-1];
        numbers = new int[n];
        header = new int[200];
        Arrays.fill(header, -1);
        StringTokenizer st;
        int i=0; String tmp;
        while((tmp=reader.readLine())!=null){
            st = new StringTokenizer(tmp);
            while(st.hasMoreTokens())
                numbers[i++]=Integer.valueOf(st.nextToken());
        }
        for(int j=1;j<numbers.length;j++){
            arr[j-1]=numbers[j]-numbers[j-1];
            prev[j-1]=header[arr[j-1]+100];
            header[arr[j-1]+100]=j-1;
        }
        pw.println(dp());
        pw.close();
        System.exit(0);
    }
    static int dp(){
        int min=0;
        int dp[]=new int[n];
        for(int i=n-2;i>=0;i--){
            int cur;
            for(cur=i-min;cur>=0;cur--){
                if(arr[cur]==arr[i]) break;
            }
            if(cur<=min) continue;
            while(cur>=0){
                for(int j=1;j<i-cur-1;j++){
                    if(cur<j) break;
                    if(arr[i-j]!=arr[cur-j]){
                        break;
                    }else{
                        dp[i]=Math.max(dp[i], j+1);
                    }
                }
                cur = prev[cur];
            }
            min = Math.max(dp[i], min);
        }
        if(min>=4)
            return min+1;
        else return 0;
    }
}
