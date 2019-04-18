/*
ID: xfrostb1
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.*;

public class contact {
    static TreeMap<String, Integer> freqs = new TreeMap<>();
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("contact.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        String s = f.readLine();
        while (s != null) {
            sb.append(s);
            s = f.readLine();
        }
        solve(a, b, n, sb.toString());
        out.close();
    }

    static void solve(int a, int b, int n, String s){
        for (int i = a; i <= b; i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                update(s.substring(j, j+i));
            }
        }

        ArrayList<Freq> list = new ArrayList<>();
        for(String str : freqs.keySet()){
            list.add(new Freq(freqs.get(str), str));
        }

        Collections.sort(list);

        int counter = 0;
        int amt = 0;
        int currCount = -1;
        int lines = 0;
        StringBuilder sb = new StringBuilder();

        while(counter < list.size()){
            if(currCount != list.get(counter).freq) {
                if(amt == n) break;
                amt++;
                lines = 0;
                if(counter != 0) out.println(sb.toString());
                sb = new StringBuilder();
                currCount = list.get(counter).freq;
                out.println(currCount);
                sb.append(list.get(counter).str);
                lines++;
            }else{
                if(lines == 6){
                    out.println(sb.toString());
                    sb = new StringBuilder();
                    sb.append(list.get(counter).str);
                    lines = 1;
                }else {
                    sb.append(" " + list.get(counter).str);
                    lines++;
                }
            }
            counter++;
        }
        if(!sb.toString().equals("")) out.println(sb.toString());

    }
    static void update(String s){
        if(!freqs.keySet().contains(s)){
            freqs.put(s, 0);
        }
        freqs.put(s, freqs.get(s)+1);
    }
    static class Freq implements Comparable<Freq>{
        int freq;
        String str;
        public Freq(int f, String s){
            freq = f;
            str = s;
        }

        @Override
        public int compareTo(Freq o) {
            if(freq != o.freq) return o.freq-freq;
            if(str.length() != o.str.length()) return str.length()-o.str.length();
            return str.compareTo(o.str);
        }
    }

}
