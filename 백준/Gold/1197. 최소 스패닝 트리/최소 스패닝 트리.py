import sys

input = sys.stdin.readline

v, e = map(int, input().split())
graph = [tuple(map(int, input().split())) for _ in range(e)]
graph.sort(key=lambda x: x[2])

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
        elif u_root > v_root:
            self.parent[u_root] = v_root
        else:
            return False

        return True
    
uf = UnionFind(v)
ans = 0
cnt = 0
for a, b, c in graph:
    if uf.union(a, b):
        ans += c
        cnt += 1
    
    if cnt == v-1:
        break

print(ans)