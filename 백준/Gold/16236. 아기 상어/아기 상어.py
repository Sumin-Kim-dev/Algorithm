from collections import deque

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

shark_size = 2

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def find_shark():
    for i in range(n):
        for j in range(n):
            if graph[i][j] == 9:
                return i, j

def bfs(x, y):
    dist = [[-1] * n for _ in range(n)]
    queue = deque()
    queue.append((x, y))
    dist[x][y] = 0

    while queue:
        x, y = queue.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < n and 0 <= ny < n:
                if dist[nx][ny] == -1 and graph[nx][ny] <= shark_size:
                    queue.append((nx, ny))
                    dist[nx][ny] = dist[x][y] + 1
    return dist

def ate_food(dist):
    check = []
    for i in range(n):
        for j in range(n):
            # 이동이 가능하고, 빈칸이 아니고 해당칸의 물고기가 상어 사이즈보다 작을때
            if dist[i][j] > 0 and 0 < graph[i][j] < shark_size:
                check.append((i, j, dist[i][j]))
    check.sort(key=lambda x : x[2])
    if check:
        x, y, distance = check[0]
        return x, y, distance
    else:
        x, y, distance = -1, -1, -1
        return x, y, distance

# x, y = find_shark()
# dist = bfs(x, y)
# print(ate(dist))
# for i in dist:
#     print(i)

answer = 0
ate = 0
while True:
    # 만약 먹을게 없다면 break 하고 거리 출력
    x, y = find_shark()
    dist = bfs(x, y)
    ate_x, ate_y, ate_distance = ate_food(dist)
    if ate_x == -1 and ate_y == -1 and ate_distance == -1:
        break
    # 먹을게 있다면 먹고 다시 진행, 상어 크기 증가시키기
    else:
        # 먹은 자리를 0처리
        graph[ate_x][ate_y] = 0
        graph[ate_x][ate_y] = 9
        graph[x][y] = 0
        answer += ate_distance
        ate += 1
        if ate >= shark_size:
            ate = 0
            shark_size += 1

print(answer)
# 초기 아기상어 크기 2
# 아기상어는 1초에 상하좌우로 한칸씩 이동가능
# 자신의 크기보다 큰 물고기가 있는 칸은 지날 수 없고, 나머지 칸은 모두 지나갈 수 있다.
# 자신의 크기보다 작은 물고기만 먹을수 있음
# 더이상 먹을수 있는 물고기 없으면 엄마상어에게 도움요청
# 먹을수 있는 물고기 한마리면 물고기 먹으러감
# 먹을수 있는 물고기가 1마리보다 많다면 거리가 가장 가까운 물고기를 먹으러 간다
# 자기 자신의 크기의 물고기를 먹으면 크기가 1 증가한다.