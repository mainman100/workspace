#include <cstdio>

int primes[1229];
void sieve()
{
    bool isPrime[10001];
    for(int i=0; i<=10000; i++)
        isPrime[i]=true;
    for(int i=2; i*i<=10000; i++)
    {
        if(isPrime[i])
        {
            for(int j=i*i; j<=10000; j+=i)
                isPrime[j]=false;
        }
    }
    int c=0;
    for(int i=2; i<=10000; i++)
        if(isPrime[i])
            primes[c++]=i;
}
int main()
{
    sieve();
    int k;
    scanf("%d",&k);
    if(k%4==0&&k%3!=0)
    {
        printf("%d",3);
        return 0;
    }
    if((k&1)==0)
        k>>=1;
    for(int i=1; i<1229; i++)
        if(k%primes[i]==0)
        {
            printf("%d",primes[i]-1);
            return 0;
        }
    printf("%d",k-1);
    return 0;
}