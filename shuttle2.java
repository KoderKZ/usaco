/*
ID: xfrostb1
LANG: JAVA
TASK: shuttle2
*/

import java.io.*;
import java.util.*;

public class shuttle2 {
    static HashMap<String, Boolean> visited = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("shuttle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
        int N = Integer.parseInt(f.readLine());
        char[] board = new char[2*N+1];
        char[] end = new char[2*N+1];
        for(int i = 0; i < N; i++){
            board[i] = 'W';
            board[i+1+N] = 'B';
            end[i] = 'B';
            end[i+1+N] = 'W';
        }
        board[N] = ' ';
        end[N] = ' ';
        node start = new node(board, N, new ArrayList<>());
        node endboard = new node(end, N, new ArrayList<>());
        Queue<node> q = new PriorityQueue<>();
        q.add(start);
        int cos = 0;
        while(q.size() > 0) {
            node now = q.poll();
            cos++;
            if (now.steps.size() == 3 && now.steps.get(0) == 5 && now.steps.get(1) == 6 && now.steps.get(2) == 4) {
                System.out.println(now.toString());
            }
            if (now.equals(endboard)) {
                System.out.println(now.toString());
                int count = 0;
                while (count < now.steps.size()) {
                    if (count % 20 == 19 || count == now.steps.size() - 1) {
                        out.println(now.steps.get(count));
                    } else {
                        out.print(now.steps.get(count) + " ");
                    }

                    count++;
                }
                break;
            }
            if (now.blank != 0) {
                if (now.blank != 1) {
                    node node1 = node.create(now, 3);
                    if (!visited.keySet().contains(node1.toString())) q.add(node1);
                    visited.put(node1.toString(), true);
                }
                node node2 = node.create(now, 1);
                if (!visited.keySet().contains(node2.toString())) q.add(node2);
                visited.put(node2.toString(), true);
            }
            if (now.blank != N * 2) {
                if (now.blank != N * 2 - 1) {
                    node node1 = node.create(now, 4);
                    if (!visited.keySet().contains(node1.toString())) q.add(node1);
                    visited.put(node1.toString(), true);
                }
                node node2 = node.create(now, 2);
                if (!visited.keySet().contains(node2.toString())) q.add(node2);
                visited.put(node2.toString(), true);

            }

        }
        out.close();
        System.out.println(cos);
    }

    static class node implements Comparable<node>{
        char[] array;
        int blank;
        ArrayList<Integer> steps = new ArrayList<>();
        public node(char[] a, int b, ArrayList<Integer> s){
            array = a.clone();
            blank = b;
            steps = s;
        }

        @Override
        public String toString() {
            String s = "";
            for(char i : array){
                s += String.valueOf(i);
            }
            return s;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof node){
                if(array.length == ((node) obj).array.length){
                    for(int i = 0; i < array.length; i++){
                        if(array[i] != ((node) obj).array[i]) return false;
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public int compareTo(node o) {
            if(steps.size() != o.steps.size()) return steps.size()-o.steps.size();
            for(int i = 0; i < o.steps.size(); i++){
                if(steps.get(i) != o.steps.get(i)) return steps.get(i)-o.steps.get(i);
            }
            return 0;
        }

        static node create(node c, int mode){
            ArrayList<Integer> clone = (ArrayList<Integer>) c.steps.clone();
            node newnode = new node(c.array.clone(), c.blank, clone);
            if(mode == 1){
                newnode.blank--;
                newnode.array[c.blank] = newnode.array[c.blank-1];
                newnode.array[c.blank-1] = ' ';
                newnode.steps.add(c.blank);
            }else if (mode == 2){
                newnode.blank++;
                newnode.array[c.blank] = newnode.array[c.blank+1];
                newnode.array[c.blank+1] = ' ';
                newnode.steps.add(c.blank + 2);
            }else if(mode == 3){
                newnode.blank -= 2;
                newnode.array[c.blank] = newnode.array[c.blank-2];
                newnode.array[c.blank-2] = ' ';
                newnode.steps.add(c.blank - 1);
            }else if(mode == 4){
                newnode.blank += 2;
                newnode.array[c.blank] = newnode.array[c.blank+2];
                newnode.array[c.blank+2] = ' ';
                newnode.steps.add(c.blank + 3);
            }
            return newnode;
        }
    }
}