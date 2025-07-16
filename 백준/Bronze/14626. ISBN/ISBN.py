import sys

input = sys.stdin.readline

isbn = input().rstrip()
m = int(isbn[-1])

sm = 0
coeff = 1
for i in range(0, 12, 2):
    if isbn[i].isdigit():
        sm += int(isbn[i])
    else:
        coeff = 1
    
    if isbn[i+1].isdigit():
        sm += int(isbn[i+1]) * 3
    else:
        coeff = 3

# X mod 10 = 10 - m
for i in range(10):
    x = sm + coeff * i
    if (10 - m) % 10 == x % 10:
        print(i)
        exit()