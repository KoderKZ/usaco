/*
 ID: xfrostb1
 LANG: JAVA
 TASK: lamps
 */
import java.io.*;
import java.util.*;

public class lamps {
    static boolean[] ret;
    static ArrayList<String> list = new ArrayList<>();
    static TreeMap<Integer, Boolean> map;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        int n = Integer.parseInt(f.readLine());
        int c = Integer.parseInt(f.readLine());
        ret = new boolean[n];
        Arrays.fill(ret, true);
        String on = f.readLine();
        String off = f.readLine();
        map = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(on);
        int countTokens = st.countTokens();
        for(int i = 0; i < countTokens-1; i++){
            map.put(Integer.parseInt(st.nextToken()), true);
        }
        st = new StringTokenizer(off);
        System.out.println(st.countTokens());
        countTokens = st.countTokens();
        for(int i = 0; i < countTokens-1; i++){
            map.put(Integer.parseInt(st.nextToken()), false);
        }
        if (c == 0){
            if (map.containsValue(false)){
                out.println("IMPOSSIBLE");
            }else {
                String str = "";
                for (int i = 0; i < ret.length; i++) {
                    str += "1";
                }
                out.println(str);
            }
        }
        if (c >= 1){
            check1(1);
            check1(2);
            check1(3);
            check1(4);
        }
        if (c >= 2){
            check2(1, 2);
            check2(1, 3);
            check2(1, 4);
            check2(2, 3);
            check2(2, 4);
            check2(3, 4);
        }
        if (c >= 3){
            check3(1, 2, 3);
            check3(1, 2, 4);
            check3(1, 3, 4);
            check3(2, 3, 4);
        }
        Collections.sort(list);
        for(String str : list){
            out.println(str);
        }
        if (list.size() == 0 && c != 0) out.println("IMPOSSIBLE");
        out.close();

    }
    static void check1(int x){
        Arrays.fill(ret, true);

        if (x == 1) trans1();

        if (x == 2) trans2();

        if (x == 3) trans3();

        if (x == 4) trans4();

        for(int a : map.keySet()){
            if (ret[a-1] != map.get(a)) return;
        }
        String n = "";
        for(int i = 0; i < ret.length; i++){
            if (ret[i]) n += "1";
            else n += "0";
        }
        if (!list.contains(n)) list.add(n);
    }
    static void check2(int x, int y){
        Arrays.fill(ret, true);

        if (x == 1) trans1();
        if (x == 2) trans2();
        if (x == 3) trans3();
        if (x == 4) trans4();
        if (y == 1) trans1();
        if (y == 2) trans2();
        if (y == 3) trans3();
        if (y == 4) trans4();

        for(int a : map.keySet()){
            if (ret[a-1] != map.get(a)) return;
        }
        String n = "";
        for(int i = 0; i < ret.length; i++){
            if (ret[i]) n += "1";
            else n += "0";
        }
        if (!list.contains(n)) list.add(n);
    }
    static void check3(int x, int y, int z){
        Arrays.fill(ret, true);

        if (x == 1) trans1();
        if (x == 2) trans2();
        if (x == 3) trans3();
        if (x == 4) trans4();
        if (z == 1) trans1();
        if (z == 2) trans2();
        if (z == 3) trans3();
        if (z == 4) trans4();
        if (y == 1) trans1();
        if (y == 2) trans2();
        if (y == 3) trans3();
        if (y == 4) trans4();

        for(int a : map.keySet()){
            if (ret[a-1] != map.get(a)) return ;
        }
        String n = "";
        for(int i = 0; i < ret.length; i++){
            if (ret[i]) n += "1";
            else n += "0";
        }
        if (!list.contains(n)) list.add(n);
    }
    static void trans1(){
        for(int i = 0; i < ret.length; i++){
            ret[i] = !ret[i];
        }
    }
    static void trans2(){
        for(int i = 0; i < ret.length; i += 2){
            ret[i] = !ret[i];
        }
    }
    static void trans3(){
        for(int i = 1; i < ret.length; i += 2){
            ret[i] = !ret[i];
        }
    }
    static void trans4(){
        for(int i = 0; i < ret.length; i += 3){
            ret[i] = !ret[i];
        }
    }
}
