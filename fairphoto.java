/*
ID: thisisomar
LANG: JAVA
PROG: Fairphoto
*/
import java.util.*;
import java.io.*;

public class fairphoto {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Scanner in = new Scanner(new FileReader("fairphoto.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
        int sizes = Integer.parseInt(in.next());
        Map<Integer, Character> ourCows = new HashMap<Integer, Character>();

        for (int i = 0; i < sizes; i++) {
            int nAInt = Integer.parseInt(in.next());
            Character nAString = in.next().charAt(0);
            ourCows.put(nAInt, nAString);
        }
        System.out.println(ourCows);
        Set<Integer> ourNums = ourCows.keySet();
        ArrayList<Integer> ourFences = new ArrayList<Integer>();
        ourFences.addAll(ourNums);
        int[] startPointsData = new int[ourFences.size()];
        int[] largestSingleBreed = new int[ourFences.size()];
        int index = 0;
        Collections.sort(ourFences);
        for (int i : ourFences) {
            int thisZero = 0;
            int bestEnd = i;
            boolean stillSame = true;
            int sameDist = i;
            ArrayList<Integer> zeroes = new ArrayList<Integer>();
            for (int k : ourFences) {
                if (k < i)
                    continue;
                if (ourCows.get(k) != ourCows.get(i))
                    stillSame = false;
                if (stillSame)
                    sameDist = k;
                System.out.println(k + "," + (ourCows.get(k) == 'H')+ "," + thisZero);
                if (ourCows.get(k) == 'H') {
                    thisZero = thisZero - 1;
                }
                else if (ourCows.get(k) == 'G') {
                    thisZero = thisZero + 1;
                }
                if (thisZero == 0)
                    if (k > bestEnd)
                        bestEnd = k;
            }
            System.out.println("====");
            startPointsData[index] =bestEnd - i;
            largestSingleBreed[index] = sameDist - i;
            //System.out.println(bestEnd + " " + i);
            index++;
        }

        System.out.println(Arrays.toString(startPointsData));
        System.out.println(Arrays.toString(largestSingleBreed));
        int betterOne = Math.max(maxValue(startPointsData), maxValue(largestSingleBreed));
        out.println(betterOne);
        in.close();
        out.close();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0);
        System.exit(0);
    }
    private static int maxValue(int[] ints) {
        int max = ints[0];
        for (int ind = 0; ind < ints.length; ind++) {
            if (ints[ind] > max) {
                max = ints[ind];
            }
        }
        return max;
    }

}