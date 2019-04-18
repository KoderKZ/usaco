import java.util.StringTokenizer;
import java.io.*;

public class sleepy {
    static int[] tree;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader x = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
        n = Integer.parseInt(x.readLine());
        int[] p = new int[n];
        StringTokenizer st = new StringTokenizer(x.readLine());
//        String[] str = x.readLine().split(" ");
        for(int i = 0; i < n;i++){
            p[i] = Integer.parseInt(st.nextToken());
        }
        tree = new int[n + 1];
        int i = n-1;
        while(i > 0 && p[i-1] < p[i]){
            i--;
        }
        out.println(i);
        for(int j = i; j < n; j++){
            update(p[i]);
        }
        for(int j = 0; j < i; j++){
            out.print((i - 1 - j) + pSum(p[j]));
            if (i - j > 1){
                out.print(" ");
            }
            update(p[j+1]);
        }
        out.println();
        out.close();
    }
    static int pSum(int ind) {
        int sum = 0;
        while (ind > 0){
            sum += tree[ind];
            ind -= ind & (-ind);
        }
        return sum;
    }
    static void update(int val){
        while (val < tree.length) {
            tree[val]++;
            val += val & (-val);
        }
    }
}