from collections import deque
import sys
sys.setrecursionlimit(10000)
n = int(input())
graph = [[] for _ in range(n + 1)]

for _ in range(n):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [False] * (n + 1)
is_cycle = [False] * (n + 1)
prev = [-1] * (n + 1)


def dfs(node, parent):
    visited[node] = True
    prev[node] = parent
    for neighbor in graph[node]:
        if not visited[neighbor]:
            if dfs(neighbor, node):
                return True
        elif prev[node] != neighbor:
            # Cycle detected
            mark_cycle(node, neighbor)
            return True
    return False


def mark_cycle(start, end):
    is_cycle[start] = True
    while start != end:
        start = prev[start]
        is_cycle[start] = True


dfs(1, -1)

distances = [-1] * (n + 1)


def bfs():
    q = deque()
    for i in range(1, n + 1):
        if is_cycle[i]:
            distances[i] = 0
            q.append(i)

    while q:
        curr = q.popleft()
        for neighbor in graph[curr]:
            if distances[neighbor] == -1:
                q.append(neighbor)
                distances[neighbor] = distances[curr] + 1


bfs()

print(*distances[1:])