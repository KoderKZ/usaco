/*
ID: xfrostb1
LANG: JAVA
TASK: milk4
*/

import java.io.*;
import java.util.*;

public class milk4 {

    static StringBuilder sb = new StringBuilder();
    static long start = System.currentTimeMillis();
    static int Q, P;
    static int arr[];
    static int table[];
    static int path[];
    static int index[];
    static int min_path[];
    static int best[];
    static boolean found = false;
    static Stack<Integer> s = new Stack<Integer>();
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("milk4.in"));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        Q = Integer.valueOf(st.nextToken());
        st = new StringTokenizer(reader.readLine());
        P = Integer.valueOf(st.nextToken());
        arr = new int[P];
        table = new int[Q+1];
        path = new int[Q+1];
        index = new int[Q+1];
        for(int i=0;i<P;i++){
            st = new StringTokenizer(reader.readLine());
            arr[i] = Integer.valueOf(st.nextToken());
        }
        Arrays.fill(table, 9999999);
        table[0]=0;
        Arrays.fill(path, 9999999);
        path[0]=0;
        for(int i=0;i<P;i++){
            int w=arr[P-i-1];
            for(int j=w;j<=Q;j++){
                if(w==path[j-w]){
                    if(table[j-w]<=table[j]){
                        table[j]=table[j-w];
                        path[j]=w;
                        index[j]=P-i-1;
                    }
                }else{
                    if(table[j-w]+1<=table[j]){
                        table[j]=table[j-w]+1;
                        path[j]=w;
                        index[j]=P-i-1;
                    }
                }
            }
        }
        min_path = new int[table[Q]+1];
        best = new int[table[Q]+1];
        Arrays.fill(min_path, 9999999);
        Arrays.fill(best, 9999999);
        min_path[1]=path[Q];
        best[1]=path[Q];
        sb.append(table[Q]);
        dfs(path[Q],path[Q],table[path[Q]],index[Q]);
        for(int i=1;i<=table[Q];i++){
            sb.append(" ").append(best[i]);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
        out.println(sb.toString());
        out.close();
        System.exit(0);
    }
    static void dfs(int start, int a, int b, int idx){
        if(start==Q) {
            int temp[] = Arrays.copyOf(min_path, table[Q]+1);
            Arrays.sort(temp,1,temp.length);
            for(int i=1;i<=table[Q];i++){
                if(temp[i]!=best[i]){
                    if(temp[i]>best[i]) break;
                    else{
                        for(int j=i;j<=table[Q];j++){
                            best[j]=temp[j];
                        }
                        break;
                    }
                }
            }
            return;
        }
        for(int i=idx;i<arr.length;i++){
            if(start+arr[i]>Q) break;
            if(arr[i]!=a&&b+1<=table[Q]&&b+1>=table[start+arr[i]]){
                int tmp=min_path[b+1];
                min_path[b+1]=arr[i];
                dfs(start+arr[i],arr[i],b+1,idx);
                min_path[b+1]=tmp;
            }
            if(arr[i]==a&&b>=table[start+arr[i]]){
                dfs(start+a,a,b,idx);
            }
        }
    }
}


//    static int M, N;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
//
//        N = Integer.parseInt(f.readLine());
//        M = Integer.parseInt(f.readLine());
//        int[] milk = new int[M];
//        for (int i = 0; i < M; i++) {
//            int add = Integer.parseInt(f.readLine());
//        }
//
//        for(int i = 0; i < M; i++){
//            for(int j = 0; j < M; j++){
//                if(j!=i){
//                    if(milk[i]%milk[j]==0) milk[i]=0;
//                    if(milk[j]%milk[i]==0) milk[j]=0;
//                }
//            }
//        }
//        Arrays.sort(milk);
//        boolean[] used = new boolean[M];
//        boolean[] ret = dfs(milk, used, N);
//
//        String output = "";
//        int count = 0;
//        for(int i = 0; i < M; i++){
//            if(ret[i]){
//                output+=" "+milk[i];
//                count++;
//            }
//        }
//
//        out.println(count+output);
//        out.close();
//    }
//
//    static boolean[] dfs(int[] milk, boolean[] used, int need){
//        if(need == 0){
//            return used;
//        }
//
//        for(int i = 0; i < milk.length; i++){
//            if(need-milk[i]>=0 && milk[i]!=0) {
//                boolean[] clone = Arrays.copyOf(used, used.length);
//                clone[i] = true;
//                boolean[] result = dfs(milk, clone, need - milk[i]);
//                if(result.length != 0){
//                    return result;
//                }
//            }
//        }
//        return new boolean[]{};
//    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
//
//        N = Integer.parseInt(f.readLine());
//        M = Integer.parseInt(f.readLine());
//        int[] milk = new int[M];
//        for (int i = 0; i < M; i++) {
//            milk[i] = Integer.parseInt(f.readLine());
//        }
//        Arrays.sort(milk);
//        state[] states = new state[N + 1];
//        states[0] = new state();
//        boolean check = true;
//        while (check) {
//            check = false;
//            for (int i = 0; i < M; i++) {
//                for (int j = milk[i]; j < N + 1; j++) {
//                    if (states[j - milk[i]] != null) {
//                        state newstate = new state(states[j - milk[i]], milk[i]);
//                        if (j == N) {
//                            if (states[j] == null || states[j].compareTo(newstate) > 0) {
//                                states[j] = newstate;
//                                check = true;
//                            }
//                        } else {
//                            if (states[j] == null || states[j].compareTo(newstate) > 0) {
//                                states[j] = newstate;
//                                check = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        out.print(states[N].k);
//        for (int i = 0; i < N; i++) {
//            if (states[N].contain[i]) out.print(" " + i);
//        }
//        out.println();
//        out.close();
//    }
//
//static class state implements Comparable<milk4.state> {
//    boolean[] contain = new boolean[N + 1];
//    int amt;
//    int k;
//
//    public state(milk4.state prev, int add) {
//        amt = prev.amt + add;
//        contain = Arrays.copyOf(prev.contain, prev.contain.length);
//        k = prev.k;
//        if (!contain[add]) {
//            k++;
//        }
//        contain[add] = true;
//
//    }
//
//    public state() {
//        amt = 0;
//        k = 0;
//    }
//
//    @Override
//    public int compareTo(milk4.state o) {
//        if (k != o.k) return k - o.k;
//        for (int i = 0; i < N; i++) {
//            if (contain[i] && !o.contain[i]) return -1;
//            if (o.contain[i] && !contain[i]) return 1;
//        }
//        return 0;
//    }
//}

//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
//
//        int N = Integer.parseInt(f.readLine());
//        int M = Integer.parseInt(f.readLine());
//        int[] milk = new int[M];
//        for(int i = 0; i < M; i++){
//            milk[i] = Integer.parseInt(f.readLine());
//        }
//        Arrays.sort(milk);
//
//        PriorityQueue<state> states = new PriorityQueue<>();
//        states.add(new state(0, new ArrayList<>(), 0, 0));
//
//        state ret;
//        ArrayList<state> visited = new ArrayList<>();
//        while(true){
//            state curr = states.poll();
//            if(curr.milk == N) {
//                ret = curr;
//                break;
//            }else if(curr.milk > N) continue;
//            for(int i : milk){
//                if(curr.milk+i <= N) {
//                    state newcurr = new state(curr.amt + 1, curr.list, curr.milk + i, i);
//                    if(!visited.contains(newcurr)){
//                        states.add(newcurr);
//                        visited.add(newcurr);
//                    }
//                }
//            }
//        }
////        ArrayList<Integer> used = new ArrayList<>();
//        int count = 0;
//        String finals = "";
//        for(int i = 0; i < ret.list.size(); i++){
////            if(used.contains(ret.list.get(i))) continue;
//
////            used.add(ret.list.get(i));
//            finals += " "+ret.list.get(i);
//            count++;
//        }
//        out.println(count+finals);
//        out.close();
//    }
//
//    static class state implements Comparable<state>{
//        int amt;
//        int milk;
//        ArrayList<Integer> list;
//        public state(int a, ArrayList<Integer> l, int m, int newlist){
//            amt = a;
//            milk = m;
//            if(newlist != 0) {
//                list = new ArrayList<>(l);
//                if(!list.contains(newlist)) list.add(newlist);
//            }else{
//                list = new ArrayList<>();
//            }
//        }
//
//        @Override
//        public int compareTo(state o) {
////            ArrayList<Integer> first = new ArrayList<>();
////            ArrayList<Integer> second = new ArrayList<>();
//            int i = 0;
//            int j = 0;
//            boolean checked = false;
//            int returnv = 0;
////            while(i < list || j < o.list.length){
////                if(i < list.length && first.contains(list[i])){
////                    i++;
////                    continue;
////                }
////                if(j < o.list.length && second.contains(o.list[j])){
////                    j++;
////                    continue;
////                }
////                if(i < list.length && j < o.list.length) {
////                    if (list[i] != o.list[j]) {
////                        checked = true;
////                        returnv = list[i] - o.list[j];
////                    }
////                }
////                if(i < list.length) {
////                    first.add(list[i]);
////                    if(i < list.length-1)i++;
////                }
////                if(j < o.list.length) {
////                    second.add(o.list[j]);
////                    if(j < o.list.length-1)j++;
////                }
////
////            }
//            if(list.size()!=o.list.size())return list.size()-o.list.size();
////            if(returnv != 0) return returnv;
////            if(milk != o.milk) return o.milk-milk;
//            while(i < list.size() && j < o.list.size()){
//                if(list.get(i) != o.list.get(j))return list.get(i)-o.list.get(j);
//                i++;
//                j++;
//            }
//            return 0;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(obj instanceof state) {
//                state o = (state)obj;
//                if(o.milk != milk) return false;
//                for (int i = 0; i < Math.min(o.list.size(), list.size()); i++) {
//                    if (o.list.get(i) != list.get(i)) return false;
//                }
//                if(list.size()!=o.list.size())return false;
//                return true;
//            }
//            return false;
//        }
//    }
//}
