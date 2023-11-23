d, h, m = map(int, input().split())
t = (d - 11) * 24 * 60 + (h - 11) * 60 + (m - 11)
if t < 0:
    print(-1)
else:
    print(t)