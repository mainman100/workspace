package upiic;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class DigitalFortress {

	public static void main(String[] args) throws Exception{
		
		BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
			int c = Integer.parseInt(x.readLine());
			while(c>0){
				
				String cipher =  x.readLine();
				double n  = Math.sqrt(cipher.length());
				if(n%1 != 0){
					System.out.println("INVALID");
				}
				else
				{
					for(int i=0;i<n;i++){
						
						for(int j=i;j<cipher.length();j+=n)
							System.out.print(cipher.charAt(j));
					}	
					System.out.println();
				}	
				c--;
				
			}
		
		}
		
	}