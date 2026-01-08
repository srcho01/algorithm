import sys
from math import inf
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())

graph = [[] for _ in range(n)]
direction = [(0, -1), (0, 1), (-1, 0), (1, 0)]

sx, sy = 0, 0
queue = deque()
for i in range(n):
    line = list(input().rstrip())
    for j, c in enumerate(line):
        if c == 'J':
            sx, sy = i, j
            graph[i].append(inf)
        elif c == 'F':
            queue.append((i, j, 0))
            graph[i].append(0)
        elif c == '#':
            graph[i].append(-2)
        elif c == '.':
            graph[i].append(inf)

def fire():
    global n, m
    
    while queue:
        x, y, time = queue.popleft()

        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            
            if time + 1 < graph[nx][ny]:
                graph[nx][ny] = time + 1
                queue.append((nx, ny, time+1))

def escape():
    global sx, sy, n, m
    
    queue = deque([(sx, sy, 0)])
    visited = [[False] * m for _ in range(n)]
    visited[sx][sy] = True
    
    while queue:
        x, y, time = queue.popleft()
        
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                return time + 1
            
            if visited[nx][ny]:
                continue
            
            if graph[nx][ny] != -2 and time + 1 < graph[nx][ny]:
                visited[nx][ny] = True
                queue.append((nx, ny, time+1))
                
    return "IMPOSSIBLE"

fire()
print(escape())