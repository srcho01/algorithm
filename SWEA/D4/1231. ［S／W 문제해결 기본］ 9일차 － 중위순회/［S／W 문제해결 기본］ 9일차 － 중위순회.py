def inorder(root):
    if root != -1:
        inorder(tree[root][0])
        print(key[root], end='')
        inorder(tree[root][1])

for tc in range(1, 11):
    n = int(input())
    key = ['' for _ in range(n+1)]
    tree = {}
    
    for _ in range(n):
        line = list(map(str, input().split()))
        root = int(line[0])
        left = int(line[2]) if len(line) >= 3 else -1
        right = int(line[3]) if len(line) >= 4 else -1
        key[root] = line[1]
        tree[root] = [left, right]
    
    print(f"#{tc} ", end='')
    inorder(1)
    print()