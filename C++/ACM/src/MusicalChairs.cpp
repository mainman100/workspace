#include <cstdio>
int size=1<<20;
int tree[1<<21];

	 void insert(int index,int val)
	{
		index+=size;
		tree[index]+=val;
		index>>=1;
		while(index>0)
		{
			tree[index]+=val;
			index>>=1;
		}
	}
	void remove(int index)
	{
		index+=size;
		tree[index]=0;
		index>>=1;
		while(index>0)
		{
			tree[index]--;
			index>>=1;
		}
	}
	int access(int k)
	{
		int start=1;
		while(start<size)
		{
			start<<=1;
			if(tree[start]<k)
			{
				k-=tree[start];
				start++;
			}
		}
		return start-size;
	}
	int main(){
		while(true)
		{
		    int n,d;
		    scanf("%d %d",&n,&d);
		    if(n==0&&d==0)
                break;
            size=1;
            while(size<=n)
                size<<=1;
            for(int i=1;i<2*size;i++)
                tree[i]=0;
			for(int i=1;i<=n;i++)
				insert(i,1);
			int get=0;
			for(int i=n;i>1;i--)
			{
				get=(get+d)%i;
				if(get==0)
					get=i;
				int pos=access(get);
				insert(pos,-1);
				get--;
			}
			printf("%d %d %d\n",n,d,access(1));
		}
		return 0;
	}


