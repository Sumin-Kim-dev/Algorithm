tot = 0
ans = 0
for _ in range(10):
    a, b = map(int, input().split())
    tot += (b - a)
    if tot > ans:
        ans = tot
print(ans)
