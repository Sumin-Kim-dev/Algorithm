code = input()[0:5]
n = int(input())
ans = 0
for _ in range(n):
    if code == input()[0:5]:
        ans += 1
print(ans)
