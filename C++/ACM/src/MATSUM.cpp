/*#include <cmath>
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

int tree[1026][1026];
int max_x;
int max_y;

int read(int x,int y)
{
    int sumx = 0;
    int y1;
    while (x> 0)
    {
        int sumy = 0;
        y1=y;
        while (y1 > 0)
        {
            sumy += tree[x][y1];
            y1 -= (y1 & -y1);
        }
        x -= (x & -x);
        sumx+=sumy;
    }
    return sumx;
}

void update(int x , int y , int val)
{
    int y1;
    while (x <= max_x)
    {
        y1 = y;
        while (y1 <= max_y)
        {
            tree[x][y1] += val;
            y1 += (y1 & -y1);
        }
        x += (x & -x);
    }
}

int main()
{
    int cases;
    scanf("%d",&cases);
    while(cases-->0)
    {
        int size;
        scanf("%d",&size);
        max_x=size;
        max_y=size;
        for(int i=0;i<=size;i++)
        for(int j=0;j<=size;j++)
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
                x++;
                y++;
                int cur=read(x,y)-read(x-1,y)-read(x,y-1)+read(x-1,y-1);
                update(x,y,val-cur);
            }
            else
            {
                int x1,x2,y1,y2;
                scanf("%d %d %d %d",&x1,&y1,&x2,&y2);
                x1++;
                x2++;
                y1++;
                y2++;
                int sum=read(x2,y2)-read(x1-1,y2)-read(x2,y1-1)+read(x1-1,y1-1);
                printf("%d\n",sum);
            }
        }
    }
}*/
