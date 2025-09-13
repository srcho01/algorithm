import heapq

MOD = 20171109

T = int(input())
for tc in range(1, T+1):
    n, a = map(int, input().split())
    
    min_heap, max_heap = [], []  # 중간값보다 더 큰 바구니, 중간값보다 더 작은 바구니
    heapq.heappush(min_heap, a)
    
    ans = 0
    for _ in range(n):
        x, y = map(int, input().split())
        
        # 일단 하나씩 넣음
        heapq.heappush(max_heap, min(x, y) * (-1))  # 더 작은 것
        heapq.heappush(min_heap, max(x, y))  # 더 큰 것
        
        # 중간값보다 더 큰 바구니의 최소값(min_heap[0])이 중간값보다 더 작은 바구니의 최댓값(-max_heap[0])보다 더 작으면
        # 바꿔줌
        while (len(min_heap) > 0 and len(max_heap) > 0) and min_heap[0] < -max_heap[0]:
            a = -heapq.heappop(max_heap)
            b = heapq.heappop(min_heap)
            
            heapq.heappush(max_heap, -b)
            heapq.heappush(min_heap, a)
            
        ans = (ans + min_heap[0]) % MOD
    
    print(f"#{tc} {ans}")