#include <cstdio>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;



int size=1;
int tree[1<<15][51];
int a[1<<14];
int mod=5000000;
int MAX;
void insert(int index,int k,int val)
{
    index+=size;
    tree[index][k]=(tree[index][k]+val)%mod;
    index>>=1;
    while(index>0)
    {
        tree[index][k]=(tree[index][k]+val)%mod;
        index>>=1;
    }
}
int query(int index,int k,int i,int j,int b,int e)
{
    if(i>e||j<b)
        return 0;
    if(i<=b&&j>=e)
        return tree[index][k];
    int mid=(b+e)/2;
    int ql=query(2*index,k,i,j,b,mid);
    int qr=query(2*index+1,k,i,j,mid+1,e);
    return (ql+qr)%mod;
}


int main()
{
    int n,k;
    scanf("%d %d",&n,&k);
    int x;
    vector<int> temp;
    for(int i=0; i<n; i++)
    {
        scanf("%d",&x);
        a[i]=x;
        temp.push_back(a[i]);
    }
    sort(temp.begin(),temp.end());
    //Hashing-------------------
    map<int, int> hash;
    int c=0;
    hash[temp[0]]=0;
    for(int i=1; i<n; i++)
    {
        if(temp[i]==temp[i-1])
            continue;
        hash[temp[i]]=++c;
    }
    for(int i=0; i<n; i++)
        a[i]=hash[a[i]];
    //-----------------------------
    while(size<n)
        size<<=1;
    for(int i=0; i<n; i++)
    {
        insert(a[i], 1, 1-tree[a[i]+size][1]);
        for(int l=2; l<=k; l++)
        {
            int sumTree=query(1,l-1,0,a[i]-1,0,size-1);
            int val=(sumTree-tree[a[i]+size][l]+mod)%mod;
            insert(a[i], l, val);
        }
    }
    int sumTree=query(1,k,0,n-1,0,size-1);
    printf("%d",sumTree);
    return 0;
}