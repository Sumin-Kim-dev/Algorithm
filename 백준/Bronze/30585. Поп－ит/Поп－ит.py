h, w = map(int, input().split())
ans = 0
for _ in range(h):
    s = input()
    for c in s:
        ans += int(c)
print(min(h * w - ans, ans))
