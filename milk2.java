/*
ID: xfrostb1
LANG: JAVA
TASK: milk2
*/
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int n = Integer.parseInt(f.readLine());
        Point[] times = new Point[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            times[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(times);

        int begin = times[0].x;
        int end = times[0].y;
        int milktime = times[0].y - times[0].x;
        int stopping = 0;
        for(int i = 1; i < times.length; i++) {
            if(end >= times[i].x && end <= times[i].y) {
                end = times[i].y;
            }
            milktime = Math.max(end - begin, milktime);
            if(times[i].x > end) {
                stopping = Math.max(stopping, times[i].x - end);

                begin = times[i].x;
                end = times[i].y;

            }
        }
        out.println(milktime + " " + stopping);
        out.close();
    }
}
 class Point implements Comparable <Point> {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		Integer a = this.x;
		Integer b = o.x;
		return a.compareTo(b);
	}

}