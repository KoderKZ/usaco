/*
ID: xfrostb1
LANG: JAVA
TASK: ride
*/
import java.io.*;

public class ride {
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        String x = f.readLine();
        String y = f.readLine();
        int a = 1, b = 1;
        for(int i = 0; i < x.length(); i++){
            a *= x.charAt(i)-'A'+1;
        }
        for(int i = 0; i < y.length(); i++){
            b *= y.charAt(i)-'A'+1;
        }

        if (a%47 == b%47) out.println("GO");
        else out.println("STAY");

        out.close();
    }
}