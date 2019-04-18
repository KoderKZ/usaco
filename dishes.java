/*
ID: xfrostb1
LANG: JAVA
TASK: dishes
*/

import java.io.*;
import java.util.*;

public class dishes {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dishes.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
        int N = Integer.parseInt(f.readLine());
        ArrayList<Deque<Integer>> list = new ArrayList();
        list.add(new ArrayDeque<Integer>());
        list.get(0).add(Integer.parseInt(f.readLine()));
        int lo = 0;
        int hi = 0;
        boolean check = true;
        for(int i = 1; i < N; i++){
            int x = Integer.parseInt(f.readLine());
            if(x < hi){
                out.println(i);
                check = false;
                break;
            }
            int index = 0;
            while(index<list.size()&&(list.get(index).size() == 0||list.get(index).getLast()<x))index++;


            while((list.size() > index && list.get(index).getFirst() < x)||(hi+1==list.get(lo).getFirst())){
                hi = list.get(lo).pollFirst();
                if(list.get(lo).size() == 0) {
                    lo++;
                }
            }

            if(list.size()==index){
                list.add(new ArrayDeque<>());
                list.get(index).add(x);
            }else{
                list.get(index).addFirst(x);
            }
        }
//        out.println(4);
        if(check) out.println(N);
        out.close();
    }
}
