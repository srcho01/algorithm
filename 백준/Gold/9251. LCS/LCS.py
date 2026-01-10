# 백준 9251 LCS

import sys

input = sys.stdin.readline

x = input().rstrip()
y = input().rstrip()
m = len(x)
n = len(y)  

def lcs_dp(x, y, m, n):
    # 0 초기화
    dp = [[0 for _ in range(n+1)] for _ in range(m+1)]
    
    for i in range(1, m+1):
        for j in range(1, n+1):
            if x[i-1] == y[j-1]: # 원래 재귀식은 x[i] == y[j]인데 인덱스 때문에
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
                
    return dp[m][n]

print(lcs_dp(x, y, m, n))