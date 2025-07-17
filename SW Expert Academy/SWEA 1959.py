from math import inf

t = int(input())
for test_case in range(1, t+1):
    n, m = map(int, input().split())
    
    if n > m:
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))
    else:
        b = list(map(int, input().split()))
        a = list(map(int, input().split()))
    
    ans = -inf
    for i in range(abs(n-m)+1):
        total = 0
        for x, y in zip(a[i:i+len(b)], b):
            total += x * y
        
        ans = max(ans, total)
        
    print(f"#{test_case} {ans}")