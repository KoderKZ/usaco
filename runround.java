/*
 ID: xfrostb1
 LANG: JAVA
 TASK: runround
 */
import java.io.*;

public class runround {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("runround.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        long n = Long.parseLong(f.readLine());
        boolean check = true;
        long i = n;
        while(check){
            i++;
            check = checkround(i);
        }
        out.println(i);
        out.close();
    }
    static boolean checkround(long n){
        String nString = String.valueOf(n);
        int value = Integer.parseInt(nString.substring(0,1));
        int index = 0;
        int[] check = new int[10];
        for(int i = 0; i < nString.length(); i++){
            index = ((value % nString.length()) + index) % nString.length();
            value = Integer.parseInt(nString.substring(index, index+1));
            if (check[value] == 1)return true;
            check[value] = 1;
        }
        return false;
    }
}