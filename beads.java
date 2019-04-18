/*
ID: xfrostb1
LANG: JAVA
TASK: beads
*/
import java.io.*;

public class beads {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
        int n = Integer.parseInt(f.readLine());
        String s = f.readLine();

        int maxBeads = 0;


        for(int i = 0; i < n; i++) {
            int left = countLeft(s, i);
            int right = countRight(s, i);

            if(left >= s.length()) maxBeads = s.length();
            else if(right >= s.length()) maxBeads = s.length();

            else {
                int beads = left + right;
                if(beads > maxBeads)
                    maxBeads = beads;
            }
        }

        out.println(maxBeads > s.length() ? s.length() : maxBeads);
        out.close();

    }

    public static int countLeft(String s, int i) {
        int j = i;
        int l = s.length();
        int count = 0;
        char ch = s.charAt(i);
        for( ; j >= 0; j--) {
            if(ch == 'w') {
                ch = s.charAt(j);
                count++;
            }

            else if(s.charAt(j) == ch || s.charAt(j) == 'w')
                count++;

            else break;
        }

        if(j < 0) j = l - 1;

        for( ; j > i; j--) {
            if(ch == 'w') {
                ch = s.charAt(j);
                count++;
            }
            else if(s.charAt(j) == ch || s.charAt(j) == 'w')
                count++;

            else break;
        }

        return count;

    }

    public static int countRight(String s, int i) {
        int j = i;
        int l = s.length();
        if(i == l - 1) j = 0;
        else j = i + 1;
        int count = 0;
        char ch = s.charAt(j);

        for( ; j < l; j++) {
            if(ch == 'w') {
                ch = s.charAt(j);
                count++;
            }

            else if(s.charAt(j) == ch || s.charAt(j) == 'w')
                count++;

            else break;
        }

        if(j == l) j = 0;
        for( ; j < i - 1; j++) {
            if(ch == 'w') {
                ch = s.charAt(j);
                count++;
            }

            else if(s.charAt(j) == ch || s.charAt(j) == 'w')
                count++;

            else break;
        }

        return count;

    }

}