/*
 ID: xfrostb1
 LANG: JAVA
 TASK: frac1
 */
import java.io.*;
import java.util.TreeMap;

public class frac1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        int n = Integer.parseInt(f.readLine());
        TreeMap<Double, String> frac = new TreeMap <Double, String> ();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                frac.put((double) j / i, simplify(j, i));
            }
        }

        frac.put((double) 0, "0/1");

        for(String s : frac.values()) out.println(s);
        out.close();
    }

    public static String simplify(int a, int b) {
        int gcd = GCD(a, b);
        return (a / gcd) + "/" + (b / gcd);
    }

    public static int GCD(int a, int b) {
        if(b == 0) return a;
        else return GCD(b, a % b);
    }

}
