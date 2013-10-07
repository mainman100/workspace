/*
 * zigzag.cpp
 *
 *  Created on: Sep 12, 2013
 *      Author: loai
 */

/**
 * traverses m*n grid in zigzag order
 */
#include <cstdio>
using namespace std;

void traverseZigZag(int m, int n) {

	int i = 0, j = 0, up = 1;
	bool turned = false;
	int d[2][2] = { { 1, -1 }, { -1, 1 } };
	int corner[2][4] = { { 1, 0, 0, 1 }, { 0, 1, 1, 0 } };
	while (i < m && j < n) {
		//here you have your (i,j), do what you want with them.
		printf("%d %d\n", i, j);
		if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
			if (!turned) {
				int k = 2 * (up * (j / (n - 1)) | (1 - up) * (i / (m - 1)));
				i += corner[up][k];
				j += corner[up][k + 1];
				turned = true;
				up = 1 - up;
				continue;
			} else
				turned = false;
		}
		i += d[up][0];
		j += d[up][1];
	}
}

