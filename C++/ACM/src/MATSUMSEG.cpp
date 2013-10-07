#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>
#define FOR(i,a,b) for(int i=a;i<b;i++)
#define REP(i,n) FOR(i,0,n)

using namespace std;

int tree[1<<11][1<<11];
int size;

void update (int x,int y,int val)
{
    x+=size;
    y+=size;
    int diff=val-tree[x][y];
    int y1;
    while(x>0)
    {
        y1=y;
        while(y1>0)
        {
            tree[x][y1]+=diff;
            y1>>=1;
        }
        x>>=1;
    }
}
int sumy(int x,int y,int y1,int y2,int b,int e)
{
    if(y1>e||y2<b)
        return 0;
    if(y1<=b&&y2>=e)
        return tree[x][y];
    int mid=(b+e)/2;
    int ql=sumy(x,2*y,y1,y2,b,mid);
    int qr=sumy(x,2*y+1,y1,y2,mid+1,e);
    return ql+qr;
}
int sum(int x,int x1,int x2,int y1,int y2,int b,int e)
{
    if(x1>e||x2<b)
        return 0;
    if(x1<=b&&x2>=e)
        return sumy(x,1,y1,y2,0,size-1);
    int mid=(b+e)/2;
    int ql=sum(2*x,x1,x2,y1,y2,b,mid);
    int qr=sum(2*x+1,x1,x2,y1,y2,mid+1,e);
    return ql+qr;
}
int main()
{
    int t;
    scanf("%d",&t);
    while(t-->0)
    {
        int n;
        scanf("%d",&n);
        size=1;
        while(size<n)
            size<<=1;
        for(int i=0; i<2*size; i++)
            for(int j=0; j<2*size; j++)
                tree[i][j]=0;
        char s[3];
        while(true)
        {
            scanf("%s",s);
            if(s[0]=='E')
                break;
            if(s[1]=='E')
            {
                int x,y,val;
                scanf("%d %d %d",&x,&y,&val);
                update(x,y,val);
            }
            else
            {

                int x1,x2,y1,y2;
                scanf("%d %d %d %d",&x1,&y1,&x2,&y2);
                int res=sum(1,x1,x2,y1,y2,0,size-1);
                printf("%d\n",res);
            }
        }
    }
    return 0;
}