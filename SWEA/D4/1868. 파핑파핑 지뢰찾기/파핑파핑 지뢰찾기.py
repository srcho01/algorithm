from collections import deque

direction = [(0, 1), (0, -1), (1, 0), (-1, 0),
             (-1, -1), (1, -1), (-1, 1), (1, 1)]

T = int(input())
for tc in range(1, T+1):
    n = int(input())
    board = [list(input().rstrip()) for _ in range(n)]
    marked = [line[:] for line in board]
    
    for x in range(n):
        for y in range(n):
            if board[x][y] == '.':
                cnt = 0
                for dx, dy in direction:
                    nx, ny = x + dx, y + dy
                    if nx < 0 or nx >= n or ny < 0 or ny >= n:
                        continue
                    
                    if board[nx][ny] == '*':
                        cnt += 1
                
                marked[x][y] = cnt
                
    queue = deque()
    visited = [[False] * n for _ in range(n)]
    ans = 0
    for i in range(n):
        for j in range(n):
            if marked[i][j] == 0 and not visited[i][j]:
                ans += 1
                queue.append((i, j))
                visited[i][j] = True
                while queue:
                    x, y = queue.popleft()
                    
                    for dx, dy in direction:
                        nx, ny = x + dx, y + dy
                        if nx < 0 or nx >= n or ny < 0 or ny >= n:
                            continue
                        
                        if not visited[nx][ny] and marked[nx][ny] != '*':
                            visited[nx][ny] = True
                            if marked[nx][ny] == 0:
                                queue.append((nx, ny))
                            
    for i in range(n):
        for j in range(n):
            if marked[i][j] != '*' and not visited[i][j]:
                ans += 1
                            
    print(f"#{tc} {ans}")