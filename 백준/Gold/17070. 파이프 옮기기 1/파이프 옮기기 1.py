import sys

input = sys.stdin.readline

n = int(input())
field = [list(map(int, input().split())) for _ in range(n)]

cnt = 0
def dfs(n, x1, y1, x2, y2):
    global cnt
    
    if x2 == n-1 and y2 == n-1:
        cnt += 1
        return
    
    if x1 == x2: # 가로
        for dx, dy in [(0, 1), (1, 1)]:
            if dx == 1 and dy == 1: # 대각선 이동
                nx = x2 + dx
                ny = y2 + dy
                if 0 <= nx < n and 0 <= ny < n and (field[nx][ny] == 0 and field[nx-1][ny] == 0 and field[nx][ny-1] == 0):
                    dfs(n, x2, y2, nx, ny)
            else:
                nx = x2 + dx
                ny = y2 + dy
                if 0 <= nx < n and 0 <= ny < n and field[nx][ny] == 0:
                    dfs(n, x2, y2, nx, ny)
    elif y1 == y2: # 세로
        for dx, dy in [(1, 0), (1, 1)]:
            if dx == 1 and dy == 1: # 대각선 이동
                nx = x2 + dx
                ny = y2 + dy
                if 0 <= nx < n and 0 <= ny < n and (field[nx][ny] == 0 and field[nx-1][ny] == 0 and field[nx][ny-1] == 0):
                    dfs(n, x2, y2, nx, ny)
            else:
                nx = x2 + dx
                ny = y2 + dy
                if 0 <= nx < n and 0 <= ny < n and field[nx][ny] == 0:
                    dfs(n, x2, y2, nx, ny)
    else: # 대각선
        for dx, dy in [(0, 1), (1, 0), (1, 1)]:
            if dx == 1 and dy == 1: # 대각선 이동
                nx = x2 + dx
                ny = y2 + dy
                if 0 <= nx < n and 0 <= ny < n and (field[nx][ny] == 0 and field[nx-1][ny] == 0 and field[nx][ny-1] == 0):
                    dfs(n, x2, y2, nx, ny)
            else:
                nx = x2 + dx
                ny = y2 + dy
                if 0 <= nx < n and 0 <= ny < n and field[nx][ny] == 0:
                    dfs(n, x2, y2, nx, ny)

if field[n-1][n-1] == 1:
    print(0)
else:
    dfs(n, 0, 0, 0, 1)
    print(cnt)