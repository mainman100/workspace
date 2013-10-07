#include <cstdio>
#include <algorithm>
#include <map>
#include <vector>
#define FOR(i,a,b) for(int i=a;i<b;i++)
#define REP(i,n) FOR(i,0,n)
using namespace std;

int a[1 << 14];
int tree[1 << 15][51];
int mod = 5000000;
int size = 1;
void insert(int index, int k, int val) {
	index += size;
	tree[index][k] = (tree[index][k] + val) % mod;
	index >>= 1;
	while (index > 0) {
		tree[index][k] = (tree[index][k] + val) % mod;
		index >>= 1;
	}
}
int query(int index, int k, int i, int j, int b, int e) {
	if (i > e || j < b)
		return 0;
	if (i <= b && j >= e)
		return tree[index][k];
	int mid = (b + e) / 2;
	int ql = query(2 * index, k, i, j, b, mid);
	int qr = query(2 * index + 1, k, i, j, mid + 1, e);
	return (ql + qr) % mod;
}
int main() {
	int n, k;
	scanf("%d %d", &n, &k);
	int x;
	vector<int> temp;
	REP(i,n)
	{
		scanf("%d", &x);
		a[i] = x;
		temp.push_back(a[i]);
	}
	sort(temp.begin(), temp.end());
	map<int, int> hash;
	hash[temp[0]] = 0;
	int c = 1;
	FOR(i,1,n)
	{
		if (temp[i] == temp[i - 1])
			continue;
		hash[temp[i]] = c++;
	}
	for (int i = 0; i < n; i++)
		a[i] = hash[a[i]];
	while (size < n)
		size <<= 1;
	REP(i,n)
	{
		insert(a[i], 1, 1);
		FOR(l,2,k+1)
		{
			int sum = query(1, l - 1, 0, a[i] - 1, 0, size - 1);
			insert(a[i], l, sum);
		}
	}
	int sum = query(1, k, 0, n - 1, 0, size - 1);
	printf("%d\n", sum);
}
