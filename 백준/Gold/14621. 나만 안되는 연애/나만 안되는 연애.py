import sys

input = sys.stdin.readline

n, m = map(int, input().split())
univ = [0] + list(map(str, input().split()))
path = [list(map(int, input().split())) for _ in range(m)]
path.sort(key=lambda x: x[2])

class UnionFind:
    def __init__(self, n):
        self.parent = [i for i in range(n+1)]
    
    def find_root(self, x):
        while x != self.parent[x]:
            self.parent[x] = self.parent[self.parent[x]]
            x = self.parent[x]
        
        return x
    
    def union(self, x, y):
        x_root = self.find_root(x)
        y_root = self.find_root(y)
        
        if x_root < y_root:
            self.parent[x_root] = y_root
        else:
            self.parent[y_root] = x_root
    
    def is_connected(self, x, y):
        return self.find_root(x) == self.find_root(y)
    
uf = UnionFind(n)
cnt, total = 0, 0
for u, v, d in path:
    if univ[u] == univ[v]:
        continue
    
    if not uf.is_connected(u, v):
        uf.union(u, v)
        total += d
        cnt += 1
        
if cnt == n-1:
    print(total)
else:
    print(-1)