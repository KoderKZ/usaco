/*
ID: xfrostb1
LANG: JAVA
TASK: shuttle
*/

import java.io.*;
import java.util.*;



class node{
    String s;
    ArrayList<Integer> path;
    int level;
    int pos;
    public node(String x, int l, int p){
        s=x;
        level = l;
        pos = p;
        path=new ArrayList<Integer>();
    }
}




public class shuttle {
    static StringBuilder sb = new StringBuilder();
    static long start = System.currentTimeMillis();
    static int n;
    static String s="",target="";

    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("shuttle.in"));
        n = Integer.valueOf(reader.readLine());
        for(int i=0;i<n;i++) {
            s+='W';
            target+='B';
        }
        s+=' ';
        target+=' ';
        for(int i=0;i<n;i++){
            s+='B';
            target+='W';
        }
        solve();
        System.out.print(sb.toString());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
        pw.print(sb.toString());
        pw.close();
        System.exit(0);
    }
    static void solve(){
        Queue<node> q = new LinkedList<node>();
        node tmp = new node(s,0,n);
        q.add(tmp);
        boolean flag = false;
        map.put(s, 1);
        int count = 0;
        while(!q.isEmpty()){
            node temp = q.poll();
            if(flag) break;
            count++;
            if(temp.s.equals(target)){
                if(!flag) {
                    flag = true;
                }
                for(int i=0;i<temp.path.size();i++){
                    sb.append(temp.path.get(i)+1);
                    if((i+1)%20==0) sb.append("\n");
                    if(i!=temp.path.size()-1&&(i+1)%20!=0) sb.append(" ");
                }
                if(temp.path.size()%20!=0) sb.append("\n");
            }
            if(temp.pos>=2){
                if(temp.s.charAt(temp.pos-1)=='B' && temp.s.charAt(temp.pos-2)=='W'){
                    StringBuilder xx = new StringBuilder(temp.s);
                    xx.setCharAt(temp.pos, temp.s.charAt(temp.pos-2));
                    xx.setCharAt(temp.pos-2, ' ');
                    if(!map.containsKey(xx.toString())){
                        map.put(temp.s, 1);
                        node nx = new node(xx.toString(),temp.level+1,temp.pos-2);
                        nx.path=new ArrayList(temp.path);
                        nx.path.add(temp.pos-2);
                        q.add(nx);
                    }
                }
            }
            if((temp.pos>=1)&&(temp.s.charAt(temp.pos-1)=='W')){
                boolean tmp_flag = true;
                if(temp.pos+1<2*n && temp.s.charAt(temp.pos+1)=='W'){
                    for(int i=temp.pos+1;i<2*n;i++){
                        if(temp.s.charAt(i)=='B'){
                            tmp_flag=false;
                        }
                    }
                }
                if(tmp_flag){
                    StringBuilder xx = new StringBuilder(temp.s);
                    xx.setCharAt(temp.pos, temp.s.charAt(temp.pos-1));
                    xx.setCharAt(temp.pos-1, ' ');
                    if(!map.containsKey(xx.toString())){
                        map.put(temp.s, 1);
                        node nx = new node(xx.toString(),temp.level+1,temp.pos-1);
                        nx.path=new ArrayList(temp.path);
                        nx.path.add(temp.pos-1);
                        q.add(nx);
                    }
                }
            }
            if((2*n-1>=temp.pos)&&(temp.s.charAt(temp.pos+1)=='B')){
                boolean tmp_flag = true;
                if(temp.pos-1>=0 && temp.s.charAt(temp.pos-1)=='B'){
                    for(int i=0;i<temp.pos;i++){
                        if(temp.s.charAt(i)=='W'){
                            tmp_flag=false;
                        }
                    }
                }
                if(tmp_flag){
                    StringBuilder xx = new StringBuilder(temp.s);
                    xx.setCharAt(temp.pos, temp.s.charAt(temp.pos+1));
                    xx.setCharAt(temp.pos+1, ' ');
                    if(!map.containsKey(xx.toString())){
                        map.put(temp.s, 1);
                        node nx = new node(xx.toString(),temp.level+1,temp.pos+1);
                        nx.path=new ArrayList(temp.path);
                        nx.path.add(temp.pos+1);
                        q.add(nx);
                    }
                }
            }
            if(temp.pos<=2*n-2){
                if(temp.s.charAt(temp.pos+1)=='W' && temp.s.charAt(temp.pos+2)=='B'){
                    StringBuilder xx = new StringBuilder(temp.s);
                    xx.setCharAt(temp.pos, temp.s.charAt(temp.pos+2));
                    xx.setCharAt(temp.pos+2, ' ');
                    if(!map.containsKey(xx.toString())){
                        map.put(temp.s, 1);
                        node nx = new node(xx.toString(),temp.level+1,temp.pos+2);
                        nx.path=new ArrayList(temp.path);
                        nx.path.add(temp.pos+2);
                        q.add(nx);
                    }
                }
            }
        }
        System.out.println(count);
    }
}

