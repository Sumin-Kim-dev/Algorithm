n = int(input())
mx = 10000
my = 10000
for _ in range(n):
    x, y = map(int, input().split())
    if y < my:
        mx = x
        my = y
print(mx, my)
