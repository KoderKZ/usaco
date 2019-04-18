/*
ID: xfrostb1
LANG: JAVA
TASK: frameup
*/

import java.io.*;
import java.util.*;

public class frameup
{
    class Rect
    {
        char c;
        int minRow,maxRow,minCol,maxCol;
        public String toString()
        {
            return c + "(" + minRow + "," + maxRow + "," + minCol + "," + maxCol + ")";
        }
        Rect(char c)
        {
            this.c = c;
            maxRow=-1;
            maxCol=-1;
            minRow=Integer.MAX_VALUE;
            minCol=Integer.MAX_VALUE;
        }
        void update(int row,int col)
        {
            if(row < minRow) minRow = row;
            if(row > maxRow) maxRow = row;
            if(col < minCol) minCol = col;
            if(col > maxCol) maxCol = col;
        }
        boolean check_char(char c)
        {
            return c==0 || c==this.c || mark[c];
        }
        int visible_index = Integer.MAX_VALUE;
        boolean check_visible(int index)
        {
            if(index>visible_index)
                return true;
            boolean r = this.check_visible();
            this.visible_index = r?index:Integer.MAX_VALUE;
            return r;
        }
        boolean check_visible()
        {
            char[][] map = frameup.this.map;
            for(int i=minRow;i<=maxRow;i++)
            {
                if(!check_char(map[i][minCol]))
                    return false;
                if(!check_char(map[i][maxCol]))
                    return false;
            }
            for(int i=minCol+1;i<maxCol;i++)
            {
                if(!check_char(map[minRow][i]))
                    return false;
                if(!check_char(map[maxRow][i]))
                    return false;
            }
            return true;
        }
    }
    int W,H,N;
    int W1,H1,N1;
    char[][] map;
    Rect[] rcs;
    Rect[] rc_map = new Rect[128];
    char[] searchs;
    char[] results;
    ArrayList resultList = new ArrayList();
    boolean[] mark = new boolean[128];
    public void main()
    {
        init();
        mark[0] = true;
        search(0,0);
        Collections.sort(resultList);
        for(int i=0;i<resultList.size();i++)
            out.println(resultList.get(i));
        if(log != null)
            log.println(resultList.size() + " result(s).");
    }
    void search(int index,int search_index)
    {
        if(index == N)
        {
            resultList.add(new String(results));
            return;
        }
        int search_index1 = search_rect(search_index);
        for(int i=search_index;i<search_index1;i++)
        {
            Rect rc = rc_map[this.searchs[i]];
            results[N1-index] = rc.c;
            mark[rc.c] = true;
            search(index+1,search_index1);
            mark[rc.c] = false;
        }
    }
    int search_rect(int index)
    {
        int start = index;
        for(int i=0;i<N;i++)
        {
            Rect rc = rcs[i];
            if(!mark[rc.c] && rc.check_visible(start))
                searchs[index++] = rc.c;
        }
        return index;
    }
    void init()
    {
        this.H = this.nextInt();
        this.W = this.nextInt();
        this.map = new char[H][W];
        this.N = 0;
        for(int i=0;i<H;i++)
        {
            String line = this.nextLine();
            for(int j=0;j<W&&j<line.length();j++)
            {
                char c = line.charAt(j);
                if(c>='A' && c<='Z')
                {
                    Rect rc = rc_map[c];
                    if(rc == null)
                    {
                        N++;
                        rc = new Rect(c);
                        rc_map[c] = rc;
                    }
                    map[i][j] = c;
                    rc.update(i,j);
                }
            }
        }
        this.W1 = W-1;
        this.H1 = H-1;
        this.N1 = N-1;
        this.rcs = new Rect[N];
        int index = 0;
        for(int i=0;i<rc_map.length;i++)
        {
            if(rc_map[i] != null)
                rcs[index++] = rc_map[i];
        }
        this.searchs = new char[N*N];
        this.results = new char[N];
    }
    public void main(BufferedReader _in,PrintWriter _out)
    {
        in = _in;
        out = _out;
        main();
    }
    public static final String FILE_NAME = "frameup";
    public static PrintWriter log;
    public static PrintWriter out;
    public static BufferedReader in;
    int nextInt()
    {
        return nextInt(in);
    }
    String nextToken()
    {
        return nextToken(in);
    }
    String nextLine()
    {
        return nextLine(in);
    }
    static int nextInt(BufferedReader in)
    {
        return Integer.parseInt(nextToken(in));
    }
    static StringTokenizer stringTokenizer = null;
    static String nextToken(BufferedReader in)
    {
        while(stringTokenizer==null || !stringTokenizer.hasMoreTokens())
        {
            stringTokenizer = new StringTokenizer(nextLine(in));
        }
        return stringTokenizer.nextToken();
    }
    static String nextLine(BufferedReader in)
    {
        try
        {
            for(;;)
            {
                String line = in.readLine().trim();
                if(line.length()>0) return line;
            }
        }
        catch(IOException ioe)
        {
            throw new RuntimeException(ioe);
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(FILE_NAME + ".in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME + ".out")));
        new frameup().main(in,out);
        out.close(); // close the output file
        System.exit(0); // don't omit this!
    }
    static int max(int a,int b)
    {
        return a>b?a:b;
    }
    static int min(int a,int b)
    {
        return a>b?b:a;
    }
}

