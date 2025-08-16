T = int(input())
for test_case in range(1, T+1):
    n, m = map(int, input().split())
    
    ans = "ON"
    for i in range(n):
        if ((m >> i) & 1) == 0:
            ans = "OFF"
            break
        
    print(f"#{test_case} {ans}")