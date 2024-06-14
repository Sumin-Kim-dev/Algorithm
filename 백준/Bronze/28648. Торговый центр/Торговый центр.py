n = int(input())
ans = 1000000
for _ in range(n):
    ans = min(ans, sum(list(map(int, input().split()))))
print(ans)
