import sys

input = sys.stdin.readline

n = 10
paper = [list(map(int, input().split())) for _ in range(n)]
left_paper = [5] * 6
ans = 100

def canAttach(x, y, size):
    global n
    
    if x + size > n or y + size > n:
        return False
    
    for i in range(x, x+size):
        for j in range(y, y+size):
            if paper[i][j] == 0:
                return False
    
    return True

def updatePaper(x, y, size, toggle):
    for i in range(x, x+size):
        for j in range(y, y+size):
            paper[i][j] = toggle


def bt(pos, cnt):
    global n, ans
    if cnt >= ans:
        return
    
    for x in range(pos, n*n):
        i, j = x // 10, x % 10
        if paper[i][j] == 1:
            for paper_size in range(5, 0, -1):
                if left_paper[paper_size] > 0 and canAttach(i, j, paper_size):
                    # attach
                    updatePaper(i, j, paper_size, 0)
                    left_paper[paper_size] -= 1
                    
                    bt(pos+1, cnt+1)
                    
                    # detach
                    updatePaper(i, j, paper_size, 1)
                    left_paper[paper_size] += 1
            
            return
            
    ans = min(ans, cnt)

            
bt(0, 0)
print(ans if ans != 100 else -1)