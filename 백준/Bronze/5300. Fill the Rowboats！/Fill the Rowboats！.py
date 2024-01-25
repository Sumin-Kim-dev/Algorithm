n = int(input())
ans = ""
for i in range(1, n + 1):
    ans += str(i) + ' '
    if i % 6 == 0 or i == n:
        ans += "Go! "
print(ans)