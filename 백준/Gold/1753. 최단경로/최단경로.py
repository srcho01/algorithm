import sys
import heapq
from math import inf

input = sys.stdin.readline

v, e = map(int, input().split())
start = int(input())

graph = [[] for _ in range(v+1)]
for _ in range(e):
    a, b, w = map(int, input().split())
    graph[a].append((b, w))

dist = [inf] * (v+1)
dist[start] = 0
heap = [(0, start)]

while heap:
    cd, curr = heapq.heappop(heap)
    
    if cd > dist[curr]:
        continue
    
    dist[curr] = cd
    
    for nxt, nd in graph[curr]:
        if cd + nd < dist[nxt]:
            dist[nxt] = cd + nd
            heapq.heappush(heap, (dist[nxt], nxt))

for i in range(1, v+1):
    if dist[i] == inf:
        print("INF")
    else:
        print(dist[i])