/*
 ID: xfrostb1
 LANG: JAVA
 TASK: hamming
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hamming {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < (int)Math.pow(2, b); i++){
            boolean check = true;
            for(int a : list){
                if (!valid(a, i, d)) check = false;
            }
            if (check) list.add(i);
            if (list.size() >= n) break;
        }


        for(int i = 0; i < list.size(); i++) {
            if(i % 10 == 9 || i == list.size() - 1) out.println(list.get(i));
            else out.print(list.get(i) + " ");
        }
        out.close();
    }
    static boolean valid(int a, int b, int d){
        String x = Integer.toBinaryString(a);
        String y = Integer.toBinaryString(b);

        while(x.length() > y.length()) x = "0"+y;
        while(y.length() > x.length()) x = "0"+x;

        int count = 0;
        for(int i = 0; i < x.length(); i++){
            if (x.charAt(i) != y.charAt(i)) count++;
        }
        return count >= d;
    }
}