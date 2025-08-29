for tc in range(1, 11):
    n = int(input())
    bundle = list(map(str, input().split()))
    m = int(input())
    command = list(map(str, input().split()))
    
    idx = 0
    while idx < len(command):
        cmd = command[idx]
        if cmd == 'I':
            x = int(command[idx+1])
            y = int(command[idx+2])
            s = command[idx+3:idx+3+y]
            bundle = bundle[:x] + s + bundle[x:]
            idx += 3 + y
        elif cmd == 'D':
            x = int(command[idx+1])
            y = int(command[idx+2])
            bundle = bundle[:x] + bundle[x+y:]
            idx += 3
        elif cmd == 'A':
            y = int(command[idx+1])
            s = command[idx+2:idx+2+y]
            bundle += s
            idx += 2 + y
    
    print(f"#{tc} {' '.join(bundle[:10])}")