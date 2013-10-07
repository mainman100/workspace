package CentralEuropa2001;

import java.io.*;
import java.util.StringTokenizer;
public class FillContainers {
    public static void main(String[] args) throws IOException{
        BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in)) ;
        int cases = new Integer(rdr.readLine().trim()) ;
        for(int c = 0 ; c < cases ; c++)
        {
            int n = new Integer(rdr.readLine().trim()) ;
            Container[]all = new Container[n] ;
            long volumeContainers = 0;
            
            for(int i = 0 ; i < n ; i++){
                StringTokenizer st = new StringTokenizer(rdr.readLine()) ;
                int b = new Integer(st.nextToken()) ;
                int h = new Integer(st.nextToken()) ;
                int w = new Integer(st.nextToken()) ;
                int d = new Integer(st.nextToken()) ;
                all[i] = new Container(b, h, w, d) ;
                volumeContainers += h*w*d;
            }
            long volume = Long.parseLong(rdr.readLine().trim());
            if(volumeContainers < volume)
                System.out.println("OVERFLOW");
            else{
                double min = 0, max = 2000000;
                double mid = (min+max)/2;
                for(int t = 0;t < 50;t++){
                    mid = (min+max)/2;
                    //System.out.println(min+" "+max+" "+mid);
                    long volumeUsed = 0;
                    for(int i = 0;i < n;i++){
                        /*if(all [i].b+all [i].h < mid)
                            volumeUsed += all [i].h*all [i].d*all [i].w;
                        else if(mid > all [i].b)
                            volumeUsed += (mid-all[i].b)*all [i].d*all [i].w;*/
                        if(mid > all [i].b)
                            volumeUsed += all [i].d*all[i].w*Math.min(mid-all[i].b,all[i].h);
                    }
                    //System.out.println(volumeUsed);
                    if(volumeUsed < volume)
                        min = mid;
                    else
                        max = mid;
                }
                System.out.printf("%.2f\n",mid);
            }
         }
    }
}
class Container implements Comparable<Container>{
    int b,h,w,d ;
    public Container(int b, int h, int w, int d) {
        super();
        this.b = b;
        this.h = h;
        this.w = w;
        this.d = d;
    }
    public int compareTo(Container e){
        return b+h - e.b - e.h ;
    }
}
