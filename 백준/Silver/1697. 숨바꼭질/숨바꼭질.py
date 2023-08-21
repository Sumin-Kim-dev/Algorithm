from collections import deque

n, k = map(int, input().split())

dist = [-1] * 1000000

def bfs():
    queue = deque()
    queue.append(n)
    dist[n] = 0

    while queue:
        x = queue.popleft()
        if x == k:
            print(dist[x])
            return

        for i in (x * 2, x - 1, x + 1):
            if 0 <= i < 1000000 and dist[i] == -1:
                dist[i] = dist[x] + 1
                queue.append(i)

bfs()

# print(dist[:20])