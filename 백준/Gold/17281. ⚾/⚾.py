import sys
from itertools import permutations

input = sys.stdin.readline

n = int(input())
innings = [list(map(int, input().split())) for _ in range(n)]
    
def cal_point():
    global n
    
    order_idx = 0
    point = 0
    
    for round in innings:
        out = 0
        
        base1 = False
        base2 = False
        base3 = False
        while out < 3:
            this_result = round[order[order_idx]]
            
            if this_result == 0:  # out
                out += 1
            elif this_result == 1:
                if base3:
                    point += 1
                base3 = base2
                base2 = base1
                base1 = True
            elif this_result == 2:
                if base3:
                    point += 1
                if base2:
                    point += 1
                base3 = base1
                base2 = True
                base1 = False
            elif this_result == 3:
                if base3:
                    point += 1
                if base2:
                    point += 1
                if base1:
                    point += 1
                base3 = True
                base2 = False
                base1 = False
            else:
                if base3:
                    point += 1
                if base2:
                    point += 1
                if base1:
                    point += 1
                point += 1
                base3 = False
                base2 = False
                base1 = False
            
            order_idx = (order_idx + 1) % 9
        
    return point

ans = -1
for tup in permutations(range(1, 9), 8):
    order = [i for i in tup[:3]] + [0] + [i for i in tup[3:]]
    ans = max(ans, cal_point())

print(ans)