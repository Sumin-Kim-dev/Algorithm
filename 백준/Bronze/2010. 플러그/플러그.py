import sys
input = sys.stdin.readline

n = int(input().strip())
ans = 1 - n
for _ in range(n):
    ans += int(input().strip())
print(ans)
