/*
 ID: xfrostb1
 LANG: JAVA
 PROG: prefix
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class prefix {

    List<String> dict;

    public static void main(String[] args) throws Exception {
        prefix main = new prefix();
        main.run();
        System.exit(0);
    }

    public void run() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("prefix.out"));

        dict = new ArrayList<String>();
        String str;
        while ((str = br.readLine()).compareTo(".") != 0) {
            String[] strs = str.split("\\s");
            for (int i = 0; i < strs.length; i++)
                dict.add(strs[i]);
        }

        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null)
            sb.append(str);

        String string = sb.toString();
        int[] length = new int[string.length() + 1];
        Arrays.fill(length, -1);
        length[0] = 0;
        int max = 0;
        for (int i = 1; i <= string.length(); i++) {
            for (int j = 0; j < dict.size(); j++) {
                String tmp = dict.get(j);
                int len = tmp.length();
                if (len <= i) {
                    // boolean flag = true;
                    // for (int k = i - len; k < i && flag; k++) {
                    // if (string.charAt(k) != tmp.charAt(k + len - i))
                    // flag = false;
                    // }
                    String cmp = string.substring(i - len, i);
                    // Key Part;
                    if (cmp.compareTo(tmp) == 0 && length[i - len] != -1) {
                        length[i] = Math.max(length[i], len + length[i - len]);
                    }
                }
            }
            max = Math.max(max, length[i]);
        }

        // System.out.println(max);
        // System.out.println(length[string.length()]);
        out.write(Integer.toString(max) + "\n");
        out.close();
    }
}