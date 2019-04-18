/*
ID: xfrostb1
LANG: JAVA
TASK: asdf
*/

import java.io.*;
import java.util.*;

public class asdf {
    public static void main(String[] args) throws IOException {
        System.out.println(wiggleMaxLength(new int[] {3,3,3,2,5}));
    }
    public static int longestValidParentheses(String s) {
        int[][] value = new int[s.length()][s.length()+1];
        for(int i = 0; i < s.length(); i++){
            value[i][i+1] = s.charAt(i) == '(' ? 1 : -1;
        }
        int ret = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ')') continue;
            for(int j = i+1; j < s.length()+1 && j != i; j++){
                if(i == 0 && j == i+1) continue;
                if(value[i][j-1] >= 0){
//                    if((value[i][j-1] == 0 && s.charAt(j-1) != ')') || value[i][j-1] > 0){
                        value[i][j] = value[i][j-1] + (s.charAt(j-1) == '(' ? 1 : -1);
                        if(value[i][j] < 0) break;
                        if(value[i][j] == 0) {
                            ret = Math.max(ret, j-i);
                        }
//                    }
                }

            }
        }
        return ret;
    }

    public static boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for(int i = 0; i < dp.length; i++){
            if(dp[i]){
                for(int j = i+1; j-i <= nums[i] && j < dp.length; j++){
                    dp[j] = true;
                    System.out.println(i+j);
                }
            }
        }
        return dp[nums.length-1];
    }

    public int coinChange(int[] coins, int amount) {
        Queue<qcount> q = new PriorityQueue<>();
        int[] amt = new int[amount+1];
        Arrays.fill(amt, Integer.MAX_VALUE);
        amt[0] = 0;
        q.add(new qcount(0, 0));
        while(q.size() > 0){
            qcount index = q.poll();
            for(int i : coins){
                if((long)i+index.amt <= (long)amount && amt[i+index.amt] > index.length+1){
                    amt[i+index.amt] = index.length+1;
                    q.add(new qcount(i+index.amt, index.length+1));
                }
            }
        }
        if(amt[amount] == Integer.MAX_VALUE) return -1;
        return amt[amount];
    }
    class qcount implements Comparable<qcount>{
        int amt, length;
        public qcount(int a, int l){
            amt = a;
            length = l;
        }

        @Override
        public int compareTo(qcount o) {
            return length-o.length;
        }
    }

    public static int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) {
            System.out.println(i >> 1);
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }

    public static int wiggleMaxLength(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int diff, in=1, de=1;
        for(int i=1; i< nums.length;i++){
            diff = nums[i] - nums[i-1];
            if(diff>0){
                in = de +1;
            }else if(diff<0){
                de = in+1;
            }

        }
        return Math.max(in, de);
    }

}
