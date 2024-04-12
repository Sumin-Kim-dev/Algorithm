ans = 0
for _ in range(4):
    info = input().split()
    if info[0] == "Es":
        ans += int(info[1]) * 21
    else:
        ans += int(info[1]) * 17
print(ans)
