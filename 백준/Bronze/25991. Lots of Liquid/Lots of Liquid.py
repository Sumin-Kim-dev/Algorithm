n = int(input())
arr = list(map(float, input().split()))
v = 0
for a in arr:
    v += a ** 3
print(v ** (1 / 3))