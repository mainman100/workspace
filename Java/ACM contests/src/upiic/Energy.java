package upiic;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;



public class Energy {
	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main(String args[]) throws Exception{

		st=new StreamTokenizer(new BufferedInputStream(System.in));
		
	
		int T = readInt();
		int ca = 1;
		while(T>0){
			
			int n = readInt();
			int i = readInt();
			int k = readInt();
			
			boolean idle = false;
			int op[] = new int[n];
			
			for(int j=0;j<n;j++)
				op[j] = readInt();
			int lastOp = op.length;
			int cur = 0;
			int nIdle = 0;
			int nSkipped = 0;
			for(int j=0;cur<lastOp;){
				if(op[cur]<j){
					nSkipped++;
					cur++;
				}else{
				if(idle){
					j= k + op[cur];
					idle = false;
					cur++;
					continue;
				}else{
					if((op[cur] - j)<i){
						j = op[cur];
						cur++;
						continue;
					}else{
						j = op[cur];
						idle = true;
						nIdle++;
						continue;
					}
				}
				}
			}
			System.out.println("Case " + ca + ": " + nIdle + " " + nSkipped);
			ca++;
			T--;
		}
			
		}
		
	
	}
