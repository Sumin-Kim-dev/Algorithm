import sys
sys.setrecursionlimit(10000)

n = int(input())
graph = [[] for _ in range(n + 1)]

for _ in range(n):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

is_cycle_check = [False] * (n + 1)

def dfs(start, now, cnt):
    global is_cycle
    # 현재 들어온놈 방문처리
    visited[now] = True
    # 만약 시작점과 도착점이 일치하고, 2개 이상의 역을 지났다면
    if start == now and cnt >= 2:
        # 사이클임
        is_cycle = True
        return

    # 시작 위치를 dfs로 시작
    for next in graph[now]:
        # 방문처리가 안되어있다면 dfs
        if not visited[next]:
            dfs(start, next, cnt + 1)
        # 만약 순환이 이미 되어있는 역을 방문했다면
        elif start == next and cnt >= 2:
            # 굳이 cnt + 1를 하지 않고 들어감
            dfs(start, next, cnt)

    return

distance = [-1] * (n + 1)

def bfs():
    from collections import deque
    vistied = [False] * (n + 1)
    queue = deque()

    for i in range(1, n + 1):
        if is_cycle_check[i]:
            vistied[i] = True
            distance[i] = 0
            queue.append(i)

    while queue:
        now = queue.popleft()
        for i in graph[now]:
            if is_cycle_check[i] == False and vistied[i] == False:
                vistied[i] = True
                queue.append(i)
                distance[i] = distance[now] + 1


for i in range(1, n + 1):
    visited = [False] * (n + 1)
    is_cycle = False
    dfs(i, i, 0)
    if is_cycle:
        is_cycle_check[i] = True

bfs()

# print(is_cycle_check)
# print()
print(*distance[1:])