n, m = map(int, input().split())
cnt = 0
for _ in range(n):
    if '+' in input():
        cnt += 1
print(cnt)
