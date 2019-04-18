import java.io.*;

/*
ID: xfrostb1
LANG: JAVA
TASK: palsquare
*/
public class palsquare {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int x = Integer.parseInt(f.readLine());
        for(int i = 1; i <= 300; i++){
            String number = Integer.toString(i * i, x);
            if (pali(number)) out.println(Integer.toString(i, x).toUpperCase() + " " + number.toUpperCase());
        }
        out.close();
    }
    static boolean pali(String x){
        for(int i = 0; i < x.length()/2; i++){
            if (x.charAt(i) != x.charAt(x.length()-i-1)) return false;
        }
        return true;
    }
}
