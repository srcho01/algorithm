import sys

input = sys.stdin.readline

n, m = map(int, input().split())
data = list(map(int, input().split()))
k = data[0]
truth_people = set(data[1:])

if k == 0:
    print(m)
else:
    parties = [set(map(int, input().split()[1:])) for _ in range(m)]
    for _ in range(m):
        for party in parties:
            if truth_people & party:
                truth_people = truth_people.union(party)
    
    cnt = 0
    for party in parties:
        if all(not p in truth_people for p in party):
            cnt += 1
    
    print(cnt)