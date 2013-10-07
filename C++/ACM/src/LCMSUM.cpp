

#include <cstdio>
#define MAX 1000001
using namespace std;
typedef long long ll;
ll lcm[MAX];
int phi[MAX];
void init()
{
    for(int i=0; i<MAX; i++)
    {
        phi[i]=i;
        lcm[i]=1;
    }
    for(int i=2; i<MAX; i++)
        if(phi[i]==i)
        {
            for(int j=i; j<MAX; j+=i)
                phi[j]-=phi[j]/i;
        }
    for(int i=2; i<MAX; i++)
        for(int j=i; j<MAX; j+=i)
            lcm[j]+=1LL*i*phi[i];
    for(int i=2; i<MAX; i++)
        lcm[i]=1LL*i*(1+lcm[i])/2;
}
int main()
{

    int t,n;
    scanf("%d",&t);
    while(t-->0)
    {
        scanf("%d",&n);
        printf("%lld\n",lcm[n]);
    }
    return 0;
}
