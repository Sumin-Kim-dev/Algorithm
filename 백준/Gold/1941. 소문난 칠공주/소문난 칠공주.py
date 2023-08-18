from collections import deque
n = 5
graph = [list(input()) for _ in range(n)]
visited = [[False] * n for _ in range(n)]

# for i in graph:
#     print(i)

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

answer = 0


def bfs(check_arr):
    cnt = 0
    check = [[False] * n for _ in range(n)]
    queue = deque()

    # 첫번째 인원만 가지고 감
    now_x, now_y = check_arr[0][0], check_arr[0][1]
    queue.append((now_x, now_y))
    check[now_x][now_y] = True

    while queue:
        x, y = queue.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if (0 <= nx < n and 0 <= ny < n):
                if check[nx][ny] == False and (nx, ny) in check_arr:
                    queue.append((nx, ny))
                    check[nx][ny] = True
                    cnt += 1
    if cnt == 6:  # 첫번째는 위에서 시작했기 때문에 나머지 6명만 탐색
        return True
    else:
        return False


def back_tracking(x, y, depth, s_cnt):
    global answer
    if depth == 7:
        # 만약에 이다솜파가 4명 이상이라면 연결되어있는지 확인해야함.
        if s_cnt >= 4:
            check_arr = []
            # 완탐을 진행하면서
            for i in range(n):
                for j in range(n):
                    # 만약 방문처리가 되어있다면
                    if visited[i][j] == True:
                        # check_arr에 넣어주기
                        check_arr.append((i, j))
            # bfs로 7개가 연결되어 있는지 확인
            if (bfs(check_arr)):
                answer += 1
        return

    for nx in range(x, n):
        for ny in range(n):
            # 겹치지 않게 가지치기
            if nx == x and ny < y:
                continue
            # 만약 이미 방문했다면 멈춰야함
            if visited[nx][ny] == True:
                continue

            ## 백트래킹 시작
            visited[nx][ny] = True

            # 만약 이다솜파면 s_cnt + 1
            if graph[nx][ny] == "S":
                back_tracking(nx, ny, depth + 1, s_cnt + 1)
            # 아니면 그냥 들어가기
            else:
                back_tracking(nx, ny, depth + 1, s_cnt)

            # 원복해주기
            visited[nx][ny] = False


back_tracking(0, 0, 0, 0)
print(answer)