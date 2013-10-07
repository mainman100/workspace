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



int max=1000000;
int p[1000001];
void sieve()
{
    for(int i=2; i<=1000; i++)
    {
        if(p[i]==0)
        {
            p[i]=i;
            for(int j=i*i; j<=1000000; j+=i)
                if(p[j]==0)
                    p[j]=i;
        }
    }
    for(int i=1001; i<=1000000; i++)
        if(p[i]==0)
            p[i]=i;
}
int main()
{
    sieve();
    int n,m;
    int t=1;
    while(true)
    {
        scanf("%d %d",&n,&m);
        if(n==0&&m==0)
            break;
        int s=0,moves=0;
        int f,e1,e2;
        while(n>1)
        {
            s++;
            f=p[n];
            e1=0;
            e2=0;
            while(n%f==0)
            {
                e1++;
                n/=f;
            }
            while(m%f==0)
            {
                e2++;
                m/=f;
            }
            moves+=abs(e1-e2);
        }
        while(m>1)
        {
            s++;
            f=p[m];
            while(m%f==0)
            {
                moves++;
                m/=f;
            }
        }
        printf("%d. %d:%d\n",t,s,moves);
        t++;
    }

}

