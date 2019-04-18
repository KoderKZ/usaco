/*
ID: xfrostb1
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.*;

public class ditch {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] direction = new int[N+1][N+1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            direction[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }

        int total = 0;


        while(true){
            int[] prevnode = new int[N+1];
            Arrays.fill(prevnode, -1);
            int[] flow = new int[N+1];
            boolean[] visited = new boolean[N+1];
            flow[1] = Integer.MAX_VALUE;
            int maxflow;
            int maxloc;
            int pathcap;
            while(true) {
                maxflow = 0;
                maxloc = -1;
                for (int i = 1; i < N + 1; i++) {
                    if (flow[i] > maxflow && !visited[i]) {
                        maxflow = flow[i];
                        maxloc = i;
                    }
                }
                if (maxloc == -1) break;
                if (maxloc == N) break;
                visited[maxloc] = true;

                for (int i = 1; i < N + 1; i++) {
                    if (direction[maxloc][i] != 0) {
                        if (flow[i] < Math.min(maxflow, direction[maxloc][i])) {
                            prevnode[i] = maxloc;
                            flow[i] = Math.min(maxflow, direction[maxloc][i]);
                        }
                    }
                }

            }
            if (maxloc == -1)break;
            pathcap = flow[N];
            total += pathcap;

            int curr = N;
            while(curr != 1){
                int next = prevnode[curr];

                direction[next][curr] -= pathcap;
                direction[curr][next] += pathcap;
                curr = next;
            }
        }
        out.println(total);
        out.close();
    }
}


//35     curnode = sink
//         # for each arc, prevnode(curnode),
//         # curnode on path:
//36     while (curnode != source)
//38       nextnode = prevnode(curnode)
//39       capacity(nextnode,curnode) =
//           capacity(nextnode,curnode) -
//40                           pathcapacity
//41       capacity(curnode,nextnode) =
//           capacity(curnode,nextnode) +
//42                           pathcapacity
//43       curnode = nextnode