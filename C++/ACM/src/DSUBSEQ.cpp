#include <cstdio>
#include <algorithm>
#include <string>
using namespace std;



int size=1;
int tree[1<<18];
pair<int,int> temp[1<<17];
int a[1<<17];
int mod=5000000;
int MAX;
void insert(int index,int val)
{
    index+=size;
    tree[index]=(tree[index]+val)%mod;
    index>>=1;
    while(index>0)
    {
        tree[index]=(tree[index]+val)%mod;
        index>>=1;
    }
}
int query(int index,int i,int j,int b,int e)
{
    if(i>e||j<b)
        return 0;
    if(i<=b&&j>=e)
        return tree[index];
    int mid=(b+e)/2;
    int ql=query(2*index,i,j,b,mid);
    int qr=query(2*index+1,i,j,mid+1,e);
    return (ql+qr)%mod;
}


int main()
{
    int cases;
    scanf("%d",&cases);
    while(cases-->0)
    {
        char s[100001];
        scanf("%s",&s);
        for(int i=0;i<sizeof(s);i++)
            a[i]=s[i]-'A';
    }
}