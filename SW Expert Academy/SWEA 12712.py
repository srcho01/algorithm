t = int(input())

for test_case in range(1, t+1):
    n, m = map(int, input().split())
    
    matrix = [list(map(int, input().split())) for _ in range(n)]
    
    directions = [[(-1, 0), (1, 0), (0, -1), (0, 1)],
           [(-1, -1), (1, -1), (-1, 1), (1, 1)]]
    
    ans = -1
    for i in range(n):
        for j in range(n):
            for dir in directions:
                total = matrix[i][j]
                for dx, dy in dir:
                    for am in range(1, m):
                        x, y = i + dx*am, j + dy*am
                        if 0 <= x and x < n and 0 <= y and y < n:
                            total += matrix[x][y]
                ans = max(ans, total)    
            
    print(f"#{test_case} {ans}")
            