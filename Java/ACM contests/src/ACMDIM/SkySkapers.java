package ACMDIM;

public class SkySkapers {

	static sky[]sorted;
	static sky[]a;
	static int cur;
	public static int reigons(int h)
	{
		int r=0;
		while(cur<sorted.length&&sorted[cur].height<=h)
		{
			sky now=sorted[cur];
			sky right=a[now.right];
			sky left=a[now.left];
			if(right.height>h&&left.height>h&&now.height<=h)
				r++;
			right.left=left.index;
			left.right=right.index;
			cur++;
		}
		return r;
	}
	
	static class sky
	{
		int index;
		int height;
		int right;
		int left;
		public sky(int height, int right, int left,int index) {
			super();
			this.height = height;
			this.right = right;
			this.left = left;
			this.index=index;
		}
		
	}
}
