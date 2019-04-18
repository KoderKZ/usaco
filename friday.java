/*
ID: xfrostb1
LANG: JAVA
TASK: friday
*/
import java.io.*;

public class friday {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        int n = Integer.parseInt(f.readLine());

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] days = new int[7];
        int day = 2;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 12; j++){
                days[(day + 12) % 7]++;
                if (isLeapYear(1900+i) && j == 1) day += 29;
                else day += months[j];
            }
        }
        out.println(days[0]+" "+days[1]+" "+days[2]+" "+days[3]+" "+days[4]+" "+days[5]+" "+days[6]);
        out.close();
    }
    static boolean isLeapYear(int n){
        if (n % 4 == 0){
            if (n % 100 != 0 || n % 400 == 0){
                return true;
            }
        }
        return false;
    }
}
