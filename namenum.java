import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
ID: xfrostb1
LANG: JAVA
TASK: namenum
*/
class namenum {
    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader br = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        ArrayList<String> solution = new ArrayList<String>();
        String s = read.readLine();
        String st;
        while ((st = br.readLine()) != null) {
            String ss = Stringvalue(st);
            if (ss.equals(s))
                solution.add(st);
        }

        Collections.sort(solution);

        if (solution.isEmpty()) {
            pw.println("NONE");
        } else {
            for (String a : solution) {
                pw.println(a);
            }
        }

        pw.close();
        System.exit(0);

    }

    public static String Stringvalue(String s) {
        String c = "";
        for (int i = 0; i < s.length(); i++) {
            c += letterValue(s.charAt(i));
        }

        return c;
    }

    public static int letterValue(char c) {
        String s = c + "";
        if (s.matches("[A-C]")) return 2;
        else if (s.matches("[D-F]")) return 3;
        else if (s.matches("[G-I]")) return 4;
        else if (s.matches("[J-L]")) return 5;
        else if (s.matches("[M-O]")) return 6;
        else if (s.matches("[P-S]")) return 7;
        else if (s.matches("[T-V]")) return 8;
        else return 9;

    }

}


/*
2: A,B,C     5: J,K,L    8: T,U,V
          3: D,E,F     6: M,N,O    9: W,X,Y
          4: G,H,I     7: P,R,S
 */