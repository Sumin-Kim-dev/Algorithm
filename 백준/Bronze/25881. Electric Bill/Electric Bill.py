c1, c2 = map(int, input().split())
n = int(input())
for _ in range(n):
    e = int(input())
    c = 0
    if e <= 1000:
        c = e * c1
    else:
        c = 1000 * c1 + (e - 1000) * c2
    print(f"{e} {c}")
