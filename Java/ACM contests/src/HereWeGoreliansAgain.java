//http://acm.hdu.edu.cn/showproblem.php?pid=2722
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class HereWeGoreliansAgain {

    static int[][][][]adj;
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            StringTokenizer tok=new StringTokenizer(st.readLine());
            n=Integer.parseInt(tok.nextToken())+1;
            m=Integer.parseInt(tok.nextToken())+1;
            if(n==1&&m==1)
                break;
            adj=new int[n][m][n][m];
            for(int i=0;i<n-1;i++)
            {
                tok=new StringTokenizer(st.readLine());
                for(int j=0;j<m-1;j++)
                {
                    int c=Integer.parseInt(tok.nextToken());
                    char dir=tok.nextToken().charAt(0);
                    if(c==0)
                        continue;
                    c=2520/c;
                    if(dir!='<')
                        adj[i][j][i][j+1]=c;
                    if(dir!='>')
                        adj[i][j+1][i][j]=c;
                }
                tok=new StringTokenizer(st.readLine());
                for(int j=0;j<m;j++)
                {
                    int c=Integer.parseInt(tok.nextToken());
                    char dir=tok.nextToken().charAt(0);
                    if(c==0)
                        continue;
                    c=2520/c;
                    if(dir!='^')
                        adj[i][j][i+1][j]=c;
                    if(dir!='v')
                        adj[i+1][j][i][j]=c;
                }
            }
            tok=new StringTokenizer(st.readLine());
            for(int j=0;j<m-1;j++)
            {
                int c=Integer.parseInt(tok.nextToken());
                char dir=tok.nextToken().charAt(0);
                if(c==0)
                    continue;
                c=2520/c;
                if(dir!='<')
                    adj[n-1][j][n-1][j+1]=c;
                if(dir!='>')
                    adj[n-1][j+1][n-1][j]=c;
            }
            int res=min();
            if(res==-1)
                System.out.println("Holiday");
            else
                System.out.println(res+" blips");
        }

    }
    public static int min()
    {
        pair p=new pair(0,0,0);
        boolean[][]v=new boolean[n][m];
        PriorityQueue<pair> q =new PriorityQueue<pair>();
        q.add(p);
        while(!q.isEmpty())
        {
            pair e=q.poll();
            int i=e.i;
            int j=e.j;
            int c=e.c;
            if(i==n-1&&j==m-1)
                return c;
            if(v[i][j])
                continue;
            v[i][j]=true;
            for(int i1=0;i1<n;i1++)
                for(int j1=0;j1<m;j1++)
                    if(adj[i][j][i1][j1]!=0)
                        q.add(new pair(i1, j1, c+adj[i][j][i1][j1]));
        }
        return -1;
       
    }
    static class pair implements Comparable<pair>
    {
        int i,j,c;

        public pair(int i, int j,int c) {
            super();
            this.i = i;
            this.j=j;
            this.c = c;
        }

        @Override
        public int compareTo(pair o) {
            // TODO Auto-generated method stub
            return c-o.c;
        }
       
    }
}
