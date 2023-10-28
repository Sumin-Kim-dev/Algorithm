n = int(input())
arr = list(map(int, input().split()))
cnt = 0
for i in arr:
    if i == n:
        cnt = cnt + 1
print(cnt)