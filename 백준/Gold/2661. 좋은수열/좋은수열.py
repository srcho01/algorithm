import sys

n = int(sys.stdin.readline())
seq = []

def bt(depth):
    global n
    
    for i in range(1, depth//2 + 1):
        if seq[depth-2*i:depth-i] == seq[depth-i:depth]:
            return
    
    if depth == n:
        print("".join(map(str, seq)))
        exit()
    
    for i in range(1, 4):
        seq.append(i)
        bt(depth+1)
        seq.pop()

bt(0)