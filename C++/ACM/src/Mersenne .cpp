#include <cstdio>
//#include <vector>
//#define max 6972593
using namespace std;
/*bool prime[max];
vector<int> primes;
void sieve()
{
    for(int i=0; i<=max; i++)
        prime[i]=false;
    prime[0]=prime[1]=true;
    for(int i=2; i*i<=max; i++)
        if(prime[i])
        {
            primes.push_back(i);
            for(int j=i*i; j<=max; j+=i)
                prime[j]=false;
        }
}


int main()
{
    int mer[]={2, 3,
5, 7, 13, 17, 19, 31, 61, 89, 107, 127, 521, 607, 1279, 2203, 2281, 3217, 4253,
4423, 9689,9941, 11213,19937,21701, 23209,44497, 86243,110503, 132049, 216091,756839,
859433,1257787,1398269,2976221,3021377,6972593};
    int t;
    scanf("%d",&t);
    int n;
    while(t-->0)
    {
        scanf("%d",&n);
        printf("%d\n",mer[n-1]);
    }
}