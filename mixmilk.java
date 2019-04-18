import java.io.*;
import java.util.*;

public class mixmilk {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
        int[] milk = new int[3];
        int[] cap = new int[3];

        for(int i = 0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            cap[i] = Integer.parseInt(st.nextToken());
            milk[i] = Integer.parseInt(st.nextToken());
        }
        int count = 0;
        for(int i = 0; i < 100; i++){
            int next = count+1;
            if(next == 3) next = 0;

            if(cap[next]-milk[next] > milk[count]){
                milk[next] += milk[count];
                milk[count] = 0;
            }else if(cap[next]-milk[next] < milk[count]){
                int temp = cap[next]-milk[next];
                milk[next] = cap[next];
                milk[count] -= temp;
            }else if(cap[next]-milk[next] == milk[count]){
                milk[next] = cap[next];
                milk[count] = 0;
            }


            count++;
            if(count == 3) count = 0;
        }

        out.println(milk[0]);
        out.println(milk[1]);
        out.println(milk[2]);
        out.close();
    }
}
