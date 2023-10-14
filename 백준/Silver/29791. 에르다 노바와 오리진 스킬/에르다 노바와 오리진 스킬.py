n, m = map(int, input().split())
a = list(map(int, input().split()))
a.sort()
na = 1
t = a[0]
for i in range(1, n):
    if a[i] - t >= 100:
        t = a[i]
        na += 1
b = list(map(int, input().split()))
b.sort()
nb = 1
t = b[0]
for i in range(1, m):
    if b[i] - t >= 360:
        t = b[i]
        nb += 1
print(f'{na} {nb}')