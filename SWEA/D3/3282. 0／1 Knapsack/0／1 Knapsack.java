T = int(input())
for tc in range(1, T+1):
    n, k = map(int, input().split())
    V = [0]
    C = [0]
    for _ in range(n):
        v, c = map(int, input().split())
        V.append(v)
        C.append(c)
        
    dp = [[0] * (k+1) for _ in range(n+1)]
    
    for i in range(1, n+1):
        for j in range(1, k+1):
            if j - V[i] < 0:
                dp[i][j] = dp[i-1][j]
            else:
                dp[i][j] = max(dp[i-1][j-V[i]] + C[i], dp[i-1][j])
    
    print(f"#{tc} {dp[n][k]}")