# 1288. 새로운 불면증 치료법

T = int(input())
for test_case in range(1, T+1):
    n = int(input())
    
    visited = 0
    x = 0
    while True:
        x += 1
        for i in str(n * x):
            visited |= (1 << int(i))
            
        if visited == (1 << 10) -1:
            break
            
    print(f"#{test_case} {x * n}")