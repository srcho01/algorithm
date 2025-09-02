import sys

input = sys.stdin.readline

n, m = map(int, input().split())
data = list(map(int, input().split()))
k, truth_people = data[0], data[1:]
party = [list(map(int, input().split()))[1:] for _ in range(m)]

ans = -1
def bt(depth, cnt, truth, lie):
    global ans
    
    if depth == m:
        ans = max(ans, cnt)
        return
    
    party_people = party[depth]
    # 거짓말을 하는 경우
    for p in party_people:
        if p in truth:
            break
    else:
        new_lie = lie.union(set(party_people))
        bt(depth+1, cnt+1, truth, new_lie)
    
    # 진실을 말하는 경우
    for p in party_people:
        if p in lie:
            break
    else:
        new_truth = truth.union(set(party_people))
        bt(depth+1, cnt, new_truth, lie)
        
truth = set()
for p in truth_people:
    truth.add(p)
    
bt(0, 0, truth, set())
print(ans)