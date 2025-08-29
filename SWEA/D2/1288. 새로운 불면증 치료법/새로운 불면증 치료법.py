T = int(input())
for tc in range(1, T+1):
    n = int(input())
    kn = 0
    visited = 0
    while True:
        kn += n
        for i in str(kn):
            visited |= (1 << int(i))
            
        if visited == (1 << 10) - 1:
            break
        
    print(f"#{tc} {kn}")