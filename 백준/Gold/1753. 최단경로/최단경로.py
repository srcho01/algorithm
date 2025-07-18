# 백준 1753 최단경로

from math import inf
import heapq
import sys

input = sys.stdin.readline


def dijkstra(graph, start, n):
    path = [inf] * (n+1)
    path[start] = 0
    
    hq = []
    heapq.heappush(hq, (0, start))
    
    while hq:
        curr_w, curr = heapq.heappop(hq)
        
        if path[curr] < curr_w: # 현재 노드가 이미 처리됐다면 skip
            continue
        
        for next, next_w in graph[curr]: # 이웃 노드들에 대하여
            new_w = curr_w + next_w
            if new_w < path[next]: # 업데이트 됐다면
                path[next] = new_w
                heapq.heappush(hq, (new_w, next))
    
    return path[1:]


n, m = map(int, input().split())
start = int(input())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))
    
for i in dijkstra(graph, start, n):
    if i == inf:
        print("INF")
    else:
        print(i)