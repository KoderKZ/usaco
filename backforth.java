import java.io.*;
import java.util.*;

public class backforth {
    static ArrayList<Integer> first = new ArrayList<>();
    static ArrayList<Integer> second = new ArrayList<>();
    static boolean[] poss = new boolean[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        for(int i = 0; i < 10; i++){
            first.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < 10; i++){
            second.add(Integer.parseInt(st.nextToken()));
        }

        int ret = 0;
        for(int i = 9; i >= 0; i--){
            ArrayList<Integer> firstc = new ArrayList<>(first);
            ArrayList<Integer> clone = new ArrayList<>(second);
            clone.add(firstc.get(i));
            int a = firstc.get(i);
            firstc.remove(firstc.get(i));

            for(int j = 10; j >= 0; j--){
                ArrayList<Integer> clone2 = new ArrayList<>(firstc);
                clone2.add(clone.get(j));
                int b = clone.get(j);
                clone.remove(clone.get(j));
                for(int k = clone2.size()-1; k >= 0 ; k--){
                    ArrayList<Integer> clone3 = new ArrayList<>(clone);
                    clone3.add(clone2.get(k));
                    int c = clone2.get(k);
                    clone2.remove(clone2.get(k));
                    for(int m : clone3){
                        if(!poss[sum(clone2)+m]){
                            poss[sum(clone2)+m] = true;
                            ret++;
                        }
                    }
                    clone2.add(c);
                }
                clone.add(b);
            }
            firstc.add(a);
        }
        out.println(ret);
        out.close();

    }

    static int sum(ArrayList<Integer> list){
        int s = 0;
        for(int n : list) s += n;

        return s;
    }
}
