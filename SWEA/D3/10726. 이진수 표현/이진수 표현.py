T = int(input())
for tc in range(1, T+1):
    n, m = map(int, input().split())
    
    ones = (1 << n) - 1
    if m & ones == ones:
        print(f"#{tc} ON")
    else:
        print(f"#{tc} OFF")