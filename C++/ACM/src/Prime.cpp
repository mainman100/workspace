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
#define max 31623
bool p[max];
void sieve()
{
    for(int i=0;i<=max;i++)
        p[i]=true;
    p[0]=p[1]=false;
    for(int i=2;i*i<=max;i++)
        if(p[i])
        {
            for(int j=i*i;j<=max;j+=i)
                p[j]=false;
        }
        int c=0;
       for(int i=2;i<=max;i++)
        if(p[i])
            c++;
        printf("%d",c);
}
int main()
{
    sieve();
}