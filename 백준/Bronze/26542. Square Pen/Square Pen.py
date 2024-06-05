n = int(input())
for _ in range(n):
    s = int(input())
    arr = []
    for _ in range(s):
        arr.append(list(input()))
    cnt = 0
    for i in range(s):
        for j in range(s):
            if arr[i][j] == '.':
                cnt += 1
    if cnt == 0:
        print(0)
        continue
    ans = 1
    for l in range(1, s):
        for i in range(s - ans):
            for j in range(s - ans):
                if arr[i][j] == '#':
                    continue
                elif arr[i + 1][j] == '#':
                    arr[i][j] = '#'
                    continue
                elif arr[i][j + 1] == '#':
                    arr[i][j] = '#'
                    continue
                elif arr[i + 1][j + 1] == '#':
                    arr[i][j] = '#'
                    continue
                else:
                    ans = l + 1
    print(ans * ans)
       