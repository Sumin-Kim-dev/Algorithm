n, k = map(int, input().split())
# 0은 흰색, 1은 빨간색, 2는 파란색
board = [list(map(int, input().split())) for _ in range(n)]

graph = [[[] for _ in range(n)] for _ in range(n)]

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
horse = []
for i in range(k):
    x, y, d = map(int, input().split())
    # 말의 정보에 idx값, x, y, d 좌표를 넣어줌
    horse.append([x - 1, y - 1, d - 1])
    # 말판에 말의 idx를 넣어줌
    graph[x - 1][y - 1].append(i)



def print_graph():
    for i in graph:
        print(i)


def change_dir(d):
    if d in [0, 2]:
        d += 1
    elif d in [1, 3]:
        d -= 1
    return d


def move(index):
    x, y, d = horse[index]

    nx = x + dx[d]
    ny = y + dy[d]

    if(nx < 0 or ny < 0 or nx >= n or ny >= n or board[nx][ny] == 2):
        d = change_dir(d)
        horse[index][2] = d
        nx = x + dx[d]
        ny = y + dy[d]
        if(nx < 0 or ny < 0 or nx >= n or ny >= n or board[nx][ny] == 2):
            return True

    temp = []
    for idx, horse_num in enumerate(graph[x][y]):
        if index == horse_num:
            temp.extend(graph[x][y][idx:])
            graph[x][y] = graph[x][y][:idx]
            break

    if board[nx][ny] == 1:  # 빨간색인 경우 순서를 반전
        temp = temp[::-1]

    for h in temp:
        horse[h][0], horse[h][1] = nx, ny
        graph[nx][ny].append(h)

    if len(graph[nx][ny]) >= 4:
        return False

    return True

turn = 0

while True:
    flag = False
    # 만약 1000보다 크면 -1 출력하고 종료
    if turn >= 1000:
        print(-1)
        exit(0)

    # 말 하나씩 이동
    for idx in range(k):
        if move(idx) == False:
            flag = True
            break

    turn += 1

    if flag:
        print(turn)
        exit(0)