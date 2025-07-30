import sys
from itertools import permutations

input = sys.stdin.readline

n, m, k = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
operator = [list(map(int, input().split())) for _ in range(k)]
operator = [[r-1, c-1, s] for r, c, s in operator]
    
def rotate(r, c, s):
    tmp = 0
    for ly in range(1, s+1):
        nxt = A[r-ly][c-ly]
        for i in range(c-ly+1, c+ly+1):
            tmp = A[r-ly][i]
            A[r-ly][i] = nxt
            nxt = tmp
        
        for i in range(r-ly+1, r+ly+1):
            tmp = A[i][c+ly]
            A[i][c+ly] = nxt
            nxt = tmp
        
        for i in range(c+ly-1, c-ly-1, -1):
            tmp = A[r+ly][i]
            A[r+ly][i] = nxt
            nxt = tmp
            
        for i in range(r+ly-1, r-ly-1, -1):
            tmp = A[i][c-ly]
            A[i][c-ly] = nxt
            nxt = tmp

ans = 50 * 100 + 1
for order in permutations(operator):
    A = [[x for x in line] for line in arr]
    for r, c, s in order:
        rotate(r, c, s)
    
    ans = min(ans, min(sum(line) for line in A))
    
print(ans)