/*
ID: xfrostb1
LANG: JAVA
TASK: heritage
*/

import java.io.*;
import java.util.*;

public class heritage {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
        char[] inorder = f.readLine().toCharArray();
        char[] preorder = f.readLine().toCharArray();

        out.println(new String(solve(inorder, preorder)));
        out.close();
    }

    static char[] solve(char[] inOrder, char[] preOrder) {
        if (preOrder.length == 0) return new char[0];
        if (preOrder.length == 1) return new char[] { preOrder[0] };
        if (preOrder.length == 2) return new char[] { preOrder[1], preOrder[0] };

        char head = preOrder[0];
        int headPos;
        int l = inOrder.length;
        for (headPos = 0; headPos < l; headPos++) {
            if (inOrder[headPos] == head) break;
        }

        char[] leftIn = new char[headPos];
        char[] leftPre = new char[headPos];
        for (int i = 0; i < headPos; i++) {
            leftIn[i] = inOrder[i];
            leftPre[i] = preOrder[i + 1];
        }
        char[] leftPost = solve(leftIn, leftPre);

        char[] rightIn = new char[l - headPos - 1];
        char[] rightPre = new char[l - headPos - 1];
        for (int i = 0; i < l - headPos - 1; i++) {
            rightIn[i] = inOrder[headPos + 1 + i];
            rightPre[i] = preOrder[headPos + 1 + i];
        }
        char[] rightPost = solve(rightIn, rightPre);

        char[] res = new char[l];
        for (int i = 0; i < leftPost.length; i++) {
            res[i] = leftPost[i];
        }
        for (int i = 0; i < rightPost.length; i++) {
            res[headPos + i] = rightPost[i];
        }
        res[l - 1] = head;

        return res;

    }
}
