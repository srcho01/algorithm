T = int(input())
for test_case in range(1, T+1):
    n = int(input())
    tree = list(map(int, input().split()))
    
    max_height = max(tree)
    
    # 1 크는 것보다 2 크는 게 무조건 더 빨리 크기 때문에
    # 짝수날에 물을 최대한 많이 줘야 한다
    ones = 0  # 홀수 날이 며칠 필요한 지
    twos = 0  # 짝수 날이 며칠 필요한 지
    
    for h in tree:
        diff = max_height - h
        twos += diff // 2  # 짝수 날은 2일에 한 번씩 돌아옴 최대한 많이 물 줘야 함
        ones += diff % 2  # 홀수 날은 최소화
    
    # 하나의 홀수날과 하나의 짝수날이 짝을 이뤄 물을 주는 경우
    pair = min(ones, twos)  # 짝을 이룰 수 있는 최대의 수
    ones -= pair
    twos -= pair
    days = pair * 2  # (홀수 날 1일, 짝수날 1일) 1쌍은 2일이므로 X2 해준다
    
    # 남은 것들은 홀수 날만 커야 하거나, 짝수 날만 커야 하거나
    # 1. 홀수 날 커야 하는 경우
    # 1 남았을 경우 1일 필요 (pair는 짝수날로 끝남. 1일이 홀수 날이기 때문)
    # 2 남았을 경우 3일 필요
    # 3 남았을 경우 5일 필요
    # 4 남았을 경우 7일 필요
    # 5 남았을 경우 9일 필요
    # f(n) = 2n - 1
    if twos == 0 and ones != 0:
        days += 2 * ones - 1
    
    # 2. 짝수 날 커야 하는 경우
    # 2cm 남았을 경우 필요한 날 2일 필요  => 1 2   (2일날만 물을 준다)
    # 4cm 남았을 경우 필요한 날 3일 필요   => 1 2 1 (1,2,3 일날 물을 준다)
    # 6cm 남았을 경우 필요한 날 4일 필요  => 1 2 1 2
    # 8cm 남았을 경우 필요한 날 8일 필요 => 1 2 1 2 0 2 (1,2,3,4,6 일날 물을 준다)
    # f(n) = n + 1 + (n-1)/3
    if twos != 0 and ones == 0:
        days += twos + 1 + (twos-1)//3
        
    print(f"#{test_case} {days}")