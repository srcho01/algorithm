import sys
from itertools import combinations
from collections import deque
from math import inf

input = sys.stdin.readline


n = int(input())
people = [0] + list(map(int, input().split()))
adj = [[]]
for i in range(n):
    data = list(map(int, input().split()))[1:]
    adj.append(data)

def bfs(n, arr):
    visited = [False] * (n+1)
    queue = deque()
    
    visited[arr[0]] = True
    queue.append(arr[0])
    connected = 0
    population = 0
    while queue:
        x = queue.popleft()
        connected += 1
        population += people[x]
        
        for nxt in adj[x]:
            if not visited[nxt] and nxt in arr:
                queue.append(nxt)
                visited[nxt] = True
                
    return connected, population

ans = inf
for cnt in range(1, n//2+1):
    for true_combi in combinations(range(1, n+1), cnt):
        false_combi = [i for i in range(1, n+1) if not i in true_combi]
        
        true_connected, true_population = bfs(n, true_combi)
        false_connected, false_population = bfs(n, false_combi)
        
        if true_connected + false_connected == n:
            ans = min(ans, abs(true_population - false_population))
            
print(ans if ans != inf else -1)