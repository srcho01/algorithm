t = int(input())
for test_case in range(1, t+1):
    n = int(input())
    
    matrix = [list(map(int, input().split())) for _ in range(n)]
    
    print(f"#{test_case}")
    for i in range(n):
        for j in range(n-1, -1, -1):  # 90도 회전
            print(matrix[j][i], end='')
        
        print(" ", end='')
        
        for j in range(n-1, -1, -1):  # 180도 회전
            print(matrix[n-i-1][j], end='')
        
        print(" ", end='')
        
        for j in range(n):  # 270도 회전
            print(matrix[j][n-i-1], end='')
        
        print()