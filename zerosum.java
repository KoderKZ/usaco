/*
 ID: xfrostb1
 LANG: JAVA
 TASK: zerosum
 */
import java.io.*;
import java.util.ArrayList;

public class zerosum {
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        int n = Integer.parseInt(f.readLine());

        dfs("1", 2, n);
        for(String str : list){
            out.println(str);
        }
        out.close();

    }
    static int sum(String curr){
        curr = curr.replace(" ", "");
        String[] nums = curr.split("[+-]");
        int count = 1;
        int charcount = 1;
        int sum = Integer.parseInt(nums[0]);
        while (count < nums.length){
            if (curr.charAt(charcount) == '+'){
                sum += Integer.parseInt(nums[count]);
                count++;
            }else if (curr.charAt(charcount) == '-'){
                sum -= Integer.parseInt(nums[count]);
                count++;
            }
            charcount++;
        }
        return sum;
    }
    static void dfs(String curr, int add, int target){
        if (add == target){
            if (sum(curr+" "+add) == 0) list.add(curr+" "+add);
            if (sum(curr) == -target) list.add(curr+"+"+add);
            if (sum(curr) == target) list.add(curr+"-"+add);
        }else{
            dfs(curr+" "+add, add +1, target);
            dfs(curr+"+"+add, add +1, target);
            dfs(curr+"-"+add, add +1, target);
        }
    }
}