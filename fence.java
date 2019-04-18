/*
ID: xfrostb1
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.*;

public class fence {
    static int N, circuitpos = 0, max, start = 0;
    static int[] circuit;
    static HashMap<Integer, TreeMap<Integer, Integer>> neighbors;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fence.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
        N = Integer.parseInt(f.readLine());
        int[][] connected = new int[501][501];
        max = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            connected[x][y]++;
            connected[y][x]++;
            max = Math.max(x, Math.max(y, max));
        }
        neighbors = new HashMap<>();
        for(int i = 1; i <= max; i++) {
            neighbors.put(i, new TreeMap<>());
            int size = 0;
            for(int j = 1; j <= max; j++){
                if(j!=i && connected[i][j] > 0) {
                    neighbors.get(i).put(j, connected[i][j]);
                    size += connected[i][j];
                }
            }
            if(size%2 == 1 && start == 0)start = i;
        }
        circuit = new int[N+1];

        if(start == 0) start = 1;

        findcircuit(start);

        for(int i = circuit.length-1; i >= 0; i--) out.println(circuit[i]);
        out.close();
    }
    static void findcircuit(int node){
        if(neighbors.get(node).size() == 0){
            circuit[circuitpos] = node;
            circuitpos++;
        }else{
            Iterator<Integer> iterator = neighbors.get(node).keySet().iterator();
            int index = iterator.next();
            while(neighbors.get(node).size() > 0) {
                if(neighbors.get(node).get(index) > 0) {
                    neighbors.get(index).put(node, neighbors.get(index).get(node) - 1);
                    neighbors.get(node).put(index, neighbors.get(node).get(index) - 1);

                    findcircuit(index);
                    if (neighbors.get(node).get(index) == 0 && iterator.hasNext()) {
                        index = iterator.next();
                    }
                }else if(iterator.hasNext()){
                    index = iterator.next();
                }else{
                    break;
                }
            }
            circuit[circuitpos] = node;
            circuitpos++;
        }
    }
}
/*# circuit is a global array
   find_euler_circuit
     circuitpos = 0
     find_circuit(node 1)

# nextnode and visited is a local array
# the path will be found in reverse order
  find_circuit(node i)

    if node i has no neighbors then
      circuit(circuitpos) = node i
      circuitpos = circuitpos + 1
    else
      while (node i has neighbors)
          pick a random neighbor node j of node i
          delete_edges (node j, node i)
          find_circuit (node j)
      circuit(circuitpos) = node i
      circuitpos = circuitpos + 1
      */