package DPDIM;

public class BracketsSequence {

	static String[][]dp;
	static boolean isReg[][];
	static boolean v[][];
	public static boolean isReg(int i,int j)
	{
		if(v[i][j])
			return isReg[i][j];
		boolean iSReg=isReg(i+1,j-1);
		for(int k=i+1;k<j;k++)
			iSReg=iSReg|isReg(i,k)|isReg(k+1,j);
		v[i][j]=true;
		return isReg[i][j]=iSReg;
	}
	
	public static void main(String[] args) {
		
	}
}
