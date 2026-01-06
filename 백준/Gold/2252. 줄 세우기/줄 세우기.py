import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())

graph = [[] for _ in range(n+1)]
indegree = [0] * (n+1)

for _ in range(m):
    a, b = map(int, input().split())
    indegree[b] += 1
    graph[a].append(b)
    
queue = deque()
for i in range(1, n+1):
    if indegree[i] == 0:
        queue.append(i)

seq = []    
while queue:
    curr = queue.popleft()
    
    seq.append(curr)
    
    for nxt in graph[curr]:
        indegree[nxt] -= 1
        if indegree[nxt] == 0:
            queue.append(nxt)
            
print(*seq)