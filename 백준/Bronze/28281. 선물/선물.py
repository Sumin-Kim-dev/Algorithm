import sys
input = sys.stdin.readline
n, x = map(int, input().strip().split())
a = list(map(int, input().strip().split()))
minCost = 10000
for i in range(1, n):
    minCost = min(minCost, a[i - 1] + a[i])
print(minCost * x)