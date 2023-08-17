# 3
# 2 2 2
# 4 4 4
# 8 8 8

# 3
# 2 2 2
# 2 2 2
# 4 4 4

import copy

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

answer = 0

# 상 하 좌 우 (0 , 1 , 2, 3)

def up_dir(graph):
    for y in range(n):
        index = 0
        for x in range(1, n):
            if graph[x][y] == 0:
                continue
            else:
                temp = graph[x][y]
                graph[x][y] = 0

                if graph[index][y] == 0:
                    graph[index][y] = temp

                elif  graph[index][y] == temp:
                    graph[index][y] *= 2
                    index += 1

                else:
                    index += 1
                    graph[index][y] = temp
    return graph

def down_dir(graph):
    for y in range(n):
        index = n - 1
        for x in range(n - 2, -1, -1):
            if graph[x][y] == 0:
                continue
            else:
                temp = graph[x][y]
                graph[x][y] = 0

                if graph[index][y] == 0:
                    graph[index][y] = temp

                elif graph[index][y] == temp:
                    graph[index][y] *= 2
                    index -= 1

                else:
                    index -= 1
                    graph[index][y] = temp
    return graph


def left_dir(graph):
    for x in range(n):
        index = 0
        for y in range(1, n):
            if graph[x][y] == 0:
                continue
            else:
                temp = graph[x][y]
                graph[x][y] = 0

                if graph[x][index] == 0:
                    graph[x][index] = temp
                elif graph[x][index] == temp:
                    graph[x][index] *= 2
                    index += 1
                else:
                    index += 1
                    graph[x][index] = temp
    return graph


def right_dir(graph):
    for x in range(n):
        index = n - 1
        for y in range(n - 2, -1, -1):
            if graph[x][y] == 0:
                continue
            else:
                temp = graph[x][y]
                graph[x][y] = 0

                if graph[x][index] == 0:
                    graph[x][index] = temp
                elif graph[x][index] == temp:
                    graph[x][index] *= 2
                    index -= 1  # right_dir이므로 왼쪽으로 이동해야 함
                else:
                    index -= 1  # right_dir이므로 왼쪽으로 이동해야 함
                    graph[x][index] = temp

    return graph



def back_tracking(depth, graph):
    global answer
    # 5번을 채우면 최댓값을 구해야됨
    if depth == 5:
        for i in range(n):
            answer = max(answer, max(graph[i]))
        return

    back_tracking(depth + 1, up_dir(copy.deepcopy(graph)))
    back_tracking(depth + 1, down_dir(copy.deepcopy(graph)))
    back_tracking(depth + 1, right_dir(copy.deepcopy(graph)))
    back_tracking(depth + 1, left_dir(copy.deepcopy(graph)))

back_tracking(0, graph)

print(answer)