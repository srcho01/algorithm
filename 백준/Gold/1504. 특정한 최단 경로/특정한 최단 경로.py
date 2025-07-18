import sys
import heapq
from math import inf

input = sys.stdin.readline

n, e = map(int, input().split())
graph = [[] for _ in range(n+1)]

for _ in range(e):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))
    graph[v].append((u, w))
    
stopover1, stopover2 = map(int, input().split())

def dijkstra(start):
    dist = [inf] * (n+1)
    visited = [False] * (n+1)
    hq = []
    
    dist[start] = 0
    heapq.heappush(hq, (0, start))
    
    while hq:
        weight, curr = heapq.heappop(hq)
        
        if visited[curr]: continue
        
        visited[curr] = True
        
        for nxt, n_weight in graph[curr]:
            if not visited[nxt]:
                dist[nxt] = min(dist[nxt], weight + n_weight)
                heapq.heappush(hq, (dist[nxt], nxt))
                
    return dist

# start - 1 - 2 - end
# start - 2 - 1 - end

dist_start = dijkstra(1)
dist_stopover = dijkstra(stopover1)
dist_end = dijkstra(n)

ans = min(dist_start[stopover1] + dist_stopover[stopover2] + dist_end[stopover2],
          dist_start[stopover2] + dist_stopover[stopover2] + dist_end[stopover1])

print(ans if ans != inf else -1)