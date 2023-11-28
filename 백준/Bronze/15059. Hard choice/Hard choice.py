a = list(map(int, input().split()))
b = list(map(int, input().split()))
n = 0
for i in range(3):
    n += max(b[i] - a[i], 0)
print(n)