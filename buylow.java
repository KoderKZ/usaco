/*
ID: xfrostb1
LANG: JAVA
TASK: buylow
*/

import java.io.*;
import java.util.*;

public class buylow {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("buylow.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
        int N = Integer.parseInt(f.readLine());
        int[] P = new int[N];
        String s = f.readLine();
        int x = 0;
        while(s != null){
            StringTokenizer st = new StringTokenizer(s);
            while(st.hasMoreTokens()){
                P[x] = Integer.parseInt(st.nextToken());
                x++;
            }
            s = f.readLine();
        }
        int[] length = new int[P.length];
        BigNum[] seqCount = new BigNum[P.length];
        boolean[] ignore = new boolean[P.length];

        for (int i = P.length - 1; i >= 0; i--) {
            int maxLength = 0;

            for (int j = P.length - 1; j > i; j--) {
                if (!ignore[j] && P[j] < P[i]) {
                    maxLength = Math.max(maxLength, length[j]);
                }
            }
            length[i] = Math.max(maxLength + 1, 1);

            for (int j = P.length - 1; j > i; j--) {
                if (P[i] == P[j] && length[i] == length[j]) {
                    ignore[j] = true;
                }
            }

            BigNum count = new BigNum(0);
            for (int j = P.length - 1; j > i; j--) {
                if (!ignore[j] && P[j] < P[i] && length[j] == maxLength) {
                    count.add(seqCount[j]);
                }
            }


            seqCount[i] = count.compareTo(BigNum.one) > 0 ? count:BigNum.one;
        }
        int maxLength = -1;
        for (int l : length) {
            maxLength = Math.max(maxLength, l);
        }

        BigNum maxCount = new BigNum(0);
        for (int i = 0; i < P.length; i++) {
            if (length[i] == maxLength && !ignore[i]) {
                maxCount.add(seqCount[i]);
            }
        }
        out.println(maxLength+" "+maxCount.toString());
        out.close();

    }

    static class BigNum implements Comparable<BigNum>{
        List<Integer> digits = new ArrayList<>();
        static BigNum one = new BigNum(1);
        public BigNum(int x){
            for(; x > 0; x/=10){
                digits.add(x%10);
            }
            if(digits.size() == 0) digits.add(0);
        }

        void add(BigNum x){
            List<Integer> digitsOther = x.digits;
            List<Integer> ans = new ArrayList<>();

            int carry = 0;
            int i = 0;
            int maxI = Math.max(digits.size(), digitsOther.size());

            while (i < maxI || carry > 0) {
                int a = i < digits.size()? digits.get(i) : 0;
                int b = i < digitsOther.size()? digitsOther.get(i) : 0;
                int sum = a + b + carry;

                ans.add(sum % 10);
                carry = sum / 10;
                i++;
            }

            digits = ans;
        }

        @Override
        public String toString() {
            String s = "";
            for(int i = digits.size()-1; i >= 0; i--){
                s += digits.get(i);
            }
            return s;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof BigNum){
                BigNum x = (BigNum) obj;
                if (x.digits.size() != digits.size()) return false;
                for(int i = 0; i < digits.size(); i--){
                    if(x.digits.get(i) != digits.get(i)) return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(BigNum o) {
            if (o.digits.size() != digits.size()) return digits.size()-o.digits.size();
            for(int i = digits.size()-1; i >= 0; i--){
                if(o.digits.get(i) != digits.get(i)) return digits.get(i)-o.digits.get(i);
            }
            return 0;
        }
    }
}
