import sys
input = sys.stdin.readline
print = sys.stdout.write

k = int(input())
for i in range(1, k + 1):
    n, s, d = map(int, input().strip().split())
    tot = 0
    for _ in range(n):
        di, vi = map(int, input().strip().split())
        if di <= s * d:
            tot += vi
    print(f"Data Set {i}:\n{tot}\n\n")