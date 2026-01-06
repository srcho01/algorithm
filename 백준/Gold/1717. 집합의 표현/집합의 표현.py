import sys

input = sys.stdin.readline

class UnionFind:
    def __init__(self, size):
        self.parent = [i for i in range(size+1)]
        
    def find(self, x):
        while x != self.parent[x]:
            self.parent[x] = self.parent[self.parent[x]]
            x = self.parent[x]
        
        return x

    def union(self, u, v):
        u_root = self.find(u)
        v_root = self.find(v)
        
        if u_root < v_root:
            self.parent[v_root] = u_root
        else:
            self.parent[u_root] = v_root
    
    def is_connected(self, u, v):
        return self.find(u) == self.find(v)

n, m = map(int, input().split())
uf = UnionFind(n)
for _ in range(m):
    c, a, b = map(int, input().split())
    if c == 0:
        uf.union(a, b)
    else:
        if uf.is_connected(a, b):
            print("YES")
        else:
            print("NO")