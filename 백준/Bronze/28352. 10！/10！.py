n = int(input())
ans = 1
for i in range(8, n + 1):
    ans *= i
print(ans // 120)