#include <cstdio>
using namespace std;
    bool like[20][20] ;
    long long dp[1<<20] ;
	int n;
	int count[1<<20] ;
	 void initCount() {
		for (int i = 0; i < (1 << 20); i++) {
			int c = 0;
			for (int j = 0; j < 20; j++)
				if (((1 << j) & i) != 0)
					c++;
			count[i] = c;
		}
	}

	int main(){

		int t ;
		scanf("%d",&t);
		initCount();
        for(int i=0;i<(1<<20);i++)
            dp[i]=0;
		while (t-- > 0) {
			scanf("%d",&n);
			int x;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
				    scanf("%d",&x);
				    like[i][j]=x;
				}
			}
            for(int i=0;i<(1<<n);i++)
                dp[i]=0;
			dp[(1 << n) - 1] = 1L;
			for (int j = (1 << n) - 2; j >= 0; j--) {
				int idx = count[j];
				for (int k = 0; k < n; k++) {
					if (like[idx][k] && (j & (1 << k)) == 0)
						dp[j] += dp[j | (1 << k)];
				}
			}
            printf("%lld\n",dp[0]);
		}
		return 0;
	}
