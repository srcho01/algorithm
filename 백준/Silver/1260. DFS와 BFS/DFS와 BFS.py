import sys
from collections import deque

input = sys.stdin.readline

n, m, start = map(int, input().split())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(n+1):
    graph[i].sort()

visited = [False] * (n+1)
def dfs(curr):
    visited[curr] = True
    print(curr, end=' ')
    
    for nxt in graph[curr]:
        if not visited[nxt]:
            dfs(nxt)
            
def bfs(start):
    visited = [False] * (n+1)
    queue = deque([start])
    visited[start] = True
    
    while queue:
        curr = queue.popleft()
        print(curr, end=' ')
        
        for nxt in graph[curr]:
            if not visited[nxt]:
                visited[nxt] = True
                queue.append(nxt)
                
dfs(start)
print()
bfs(start)