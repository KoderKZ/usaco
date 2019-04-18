/*
ID: xfrostb1
LANG: JAVA
TASK: lgame
*/

import java.io.*;
import java.util.*;

public class lgame {
    static int[] dict = {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lgame.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));
        String s = f.readLine();
        int[] search = new int[26];
        for(int i = 0; i < s.length(); i++) search[s.charAt(i)-'a']++;

        BufferedReader read = new BufferedReader(new FileReader("lgame.dict"));
        ArrayList<int[]> words = new ArrayList<>();
        ArrayList<String> full = new ArrayList<>();
        s = read.readLine();
        while(!s.equals(".")){
            int[] count = new int[26];
            for(int i = 0; i < s.length(); i++){
                count[s.charAt(i)-'a']++;
            }
            words.add(count);
            full.add(s);
            s = read.readLine();
        }

        ArrayList<word> viable = new ArrayList<>();
        for(int j = 0; j < words.size(); j++){
            int[] word = words.get(j);
            boolean check = false;
            int points = 0;
            int[] copy = search.clone();
            for(int i = 0; i < word.length; i++){
                if(search[i] < word[i]){
                    check = true;
                    break;
                }else{
                    copy[i] -= word[i];
                    points += dict[i]*word[i];
                }
            }
            if(!check){
                viable.add(new word(word, points, copy, full.get(j)));
            }
        }

        int ret = 0;
        ArrayList<String> answer = new ArrayList<>();


        for(int i = 0; i < viable.size(); i++){
            if(viable.get(i).points > ret){
                ret = viable.get(i).points;
                answer.clear();
                answer.add(viable.get(i).word);
            }else if(viable.get(i).points == ret) answer.add(viable.get(i).word);
            for(int j = i+1; j < viable.size(); j++){
                if(viable(viable.get(i).letters, viable.get(j).lettersleft)){
                    if(viable.get(i).points+viable.get(j).points > ret){
                        ret = viable.get(i).points+viable.get(j).points;
                        answer.clear();
                        answer.add(viable.get(i).word+" "+viable.get(j).word);
                    }else if (viable.get(i).points+viable.get(j).points == ret) answer.add(viable.get(i).word+" "+viable.get(j).word);
                }
            }
        }
        out.println(ret);
        for(String str : answer) out.println(str);
        out.close();

    }
    static boolean viable(int[] s, int[] w){
        for(int i = 0; i < w.length; i++){
            if(s[i] > w[i]){
                return false;
            }
        }
        return true;
    }
    static class word{
        int[] letters;
        int[] lettersleft;
        int points;
        String word;
        public word(int[] l, int p, int[] ll, String w){
            letters = l.clone();
            points = p;
            lettersleft = ll;
            word = w;
        }
    }
}
