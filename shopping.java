/*
ID: xfrostb1
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.*;

//public class shopping {
//    static int[] prices;
//    static Deal[] deals;
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
//        int S = Integer.parseInt(f.readLine());
//        deals = new Deal[S];
//        for(int i = 0; i < S; i++){
//            StringTokenizer st = new StringTokenizer(f.readLine());
//            int n = Integer.parseInt(st.nextToken());
//            Deal deal = new Deal();
//            for(int j = 0; j < n; j++){
//                deal.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//            }
//            deal.price = Integer.parseInt(st.nextToken());
//            deals[i] = deal;
//        }
//
//        int B = Integer.parseInt(f.readLine());
//        prices = new int[1000];
//        int[] requirements = new int[1000];
//        for(int i = 0; i < B; i++){
//            StringTokenizer st = new StringTokenizer(f.readLine());
//            int id = Integer.parseInt(st.nextToken());
//            requirements[id] = Integer.parseInt(st.nextToken());
//            prices[id] = Integer.parseInt(st.nextToken());
//        }
//
//        out.println(solve(requirements,  0));
//        out.close();
//    }
//
//    static int solve(int[] reqs, int price){
//        boolean check = false;
//        int min = Integer.MAX_VALUE;
//        for(Deal i : deals){
//            int[] newreq = reqs.clone();
//            boolean ok = true;
//            for(int j : i.amt.keySet()){
//                if(newreq[j]-i.amt.get(j) < 0) ok = false;
//                else newreq[j] -= i.amt.get(j);
//            }
//            if(ok){
//                min = Math.min(solve(newreq, price+i.price), min);
//                check = true;
//            }
//        }
//        if(!check){
//            for(int i = 0; i < 1000; i++){
//                if(reqs[i] > 0) price += prices[i]*reqs[i];
//            }
//            return price;
//        }else{
//            return min;
//        }
//
//    }
//    static class Deal{
//        int price;
//        HashMap<Integer,Integer> amt;
//        public Deal(){
////            price = p;
//            amt = new HashMap<>();
//        }
//
//        public void add(int num, int a){
//            amt.put(num, a);
//        }
//    }
//}
public class shopping {

    private static final int MAX = 7776 + 1;
    private static final int[] SIX_POW = new int[] { 1, 6, 36, 216, 1296, 7776 };

    public class Basket {
        int state;

        public int getItem(int item) {
            assert (item < 5);
            return (state % SIX_POW[item + 1]) / SIX_POW[item];
        }

        public Basket(int[] content) {
            assert (content.length == 5);
            for (int i = 0; i < content.length; i++) {
                assert (content[i] <= 5);
                state += (content[i] * SIX_POW[i]);
            }
        }

        public Basket(int state) {
            this.state = state;
        }

        public boolean contains(Basket other) {
            for (int i = 0; i < 5; i++) {
                if (getItem(i) < other.getItem(i)) return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                sb.append(getItem(i));
                sb.append(" ");
            }
            return sb.toString().trim();
        }

        public int hashCode() {
            return state;
        }
    }

    /*
     * DP: dp[this lot] = min (dp[this lot], dp[this lot - offer] +
     * price[offer]) lots are 0-5 items of 0-5 products each <= should encode
     * them int lotCode = encode(int[5] numOfItems) dp[lotCode] = lotPrice
     */

    private final HashMap<Integer, Integer> upcToItem = new HashMap<Integer, Integer>();
    private final HashMap<Basket, Integer> offerBaskets = new HashMap<Basket, Integer>();
    private final int[] prices = new int[5];
    private Basket goal;

    public int solve(String[] offers, String[] products) {
        init(offers, products);
        int[] dp = new int[MAX];

        fillRegularPrice(dp);

        for (int i = 0; i <= goal.state; i++) {
            Basket current = new Basket(i);
            if (i == 19) {
                int ii = 0;
            }
            for (Basket offer : offerBaskets.keySet()) {
                if (current.contains(offer)) {
                    dp[i] = Math.min(dp[i], dp[i - offer.state] + offerBaskets.get(offer));
                }
            }
        }

        return dp[goal.state];
    }

    private void fillRegularPrice(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            int sum = 0;
            Basket b = new Basket(i);
            for (int j = 0; j < 5; j++) {
                sum += (prices[j] * b.getItem(j));
            }
            dp[i] = sum;
        }
    }

    private void init(String[] offers, String[] products) {
        int[] content = new int[5];
        for (int i = 0; i < products.length; i++) {
            StringTokenizer st = new StringTokenizer(products[i]);
            int code = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            upcToItem.put(code, i);
            prices[i] = price;
            content[i] = number;
        }
        goal = new Basket(content);

        for (int i = 0; i < offers.length; i++) {
            int[] offerContent = new int[5];
            StringTokenizer st = new StringTokenizer(offers[i]);
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int code = Integer.parseInt(st.nextToken());
                int number = Integer.parseInt(st.nextToken());
                offerContent[upcToItem.get(code)] = number;
            }
            int price = Integer.parseInt(st.nextToken());

            offerBaskets.put(new Basket(offerContent), price);
        }
    }

    public static void main(String[] args) throws IOException {
        String problemName = "shopping";
        BufferedReader f = new BufferedReader(new FileReader(problemName + ".in"));

        int n = Integer.parseInt(f.readLine());
        String[] offers = new String[n];
        for (int i = 0; i < n; i++) {
            offers[i] = f.readLine();
        }

        n = Integer.parseInt(f.readLine());
        String[] products = new String[n];
        for (int i = 0; i < n; i++) {
            products[i] = f.readLine();
        }

        int res = (new shopping()).solve(offers, products);

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
        out.println(res);
        out.close(); // close the output file
        System.exit(0); // don't omit this!
    }

}