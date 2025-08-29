T = int(input())
for tc in range(1, T+1):
    n, m, l = map(int, input().split())
    seq = list(map(int, input().split()))
    for _ in range(m):
        data = list(map(str, input().split()))
        cmd = data[0]
        idx = int(data[1])
        
        if cmd == 'I':
            val = int(data[2])
            seq = seq[:idx] + [val] + seq[idx:]
        elif cmd == 'D':
            seq = seq[:idx] + seq[idx+1:]
        elif cmd == 'C':
            val = int(data[2])
            seq[idx] = val
        
    if l < len(seq):
        print(f"#{tc} {seq[l]}")
    else:
        print(f"#{tc} -1")