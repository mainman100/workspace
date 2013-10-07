import java.util.*;
import java.io.*;

public class TEST {
        
	public void doJob() throws FileNotFoundException
	{
		System.setIn(new FileInputStream("C:\\Users\\Loai_Ghoraba\\Desktop\\in.txt"));
		System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\Loai_Ghoraba\\Desktop\\out.txt")));
		Scanner in = new Scanner(new BufferedInputStream(System.in));
        
        int n=in.nextInt();
        int[][] tab=new int[n][n];
        
        int c0=1<<30, c2, c5;
        char[] w0=new char[n*2-2], w2, w5;
        
        for (int r=0; r<n; r++) {
                for (int c=0; c<n; c++) {
                        tab[r][c]=in.nextInt();
                        if (tab[r][c]==0) {
                                tab[r][c]=10;
                                if (c0!=1) {
                                        c0=1;
                                        int j=0;
                                        for (int i=0; i<r; i++) w0[j++]='D';
                                        for (int i=0; i<n-1; i++) w0[j++]='R';
                                        for (int i=r; i<n-1; i++) w0[j++]='D';
                                }
                        }
                }
        }

        c2 = findWay(tab, 2, w2=new char[n*2-2]);
        c5 = findWay(tab, 5, w5=new char[n*2-2]);
        
        if (c2<c0) { c0=c2; w0=w2; }
        if (c5<c0) { c0=c5; w0=w5; }    

        System.out.println(c0);
        System.out.println(w0);
}
        
         int findWay(int[][] tab, int ele, char[] way) {
                int n=tab.length;
                int[][] ce=new int[n][n];
                
                ce[0][0]=count(tab[0][0], ele);
                for (int c=1; c<n; c++) ce[0][c]=ce[0][c-1]+count(tab[0][c], ele);
                for (int r=1; r<n; r++) ce[r][0]=ce[r-1][0]+count(tab[r][0], ele);
                for (int r=1; r<n; r++) 
                        for (int c=1; c<n; c++) ce[r][c]=count(tab[r][c], ele) + Math.min(ce[r-1][c], ce[r][c-1]);
                
                int r0=n-1, c0=n-1;     
                for (int i=n*2-2-1; i>=0; i--) {
                        if (r0==0) {
                                way[i]='R';
                                --c0;
                        } else if (c0==0) {
                                way[i]='D';
                                --r0;
                        } else {
                                if (ce[r0-1][c0]<ce[r0][c0-1]) {
                                        way[i]='D';
                                        --r0;
                                } else {
                                        way[i]='R';
                                        --c0;
                                }
                        }
                }
                
                return ce[n-1][n-1];
        }
        
         int count(int x, int ele) {
                int re=0;
                while (x%ele==0) {
                        x/=ele;
                        ++re;
                }
                return re;
        }

}