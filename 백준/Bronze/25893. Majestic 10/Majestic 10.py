n = int(input())
for _ in range(n):
    s = list(map(int, input().split()))
    cnt = 0
    for i in s:
        if i >= 10:
            cnt += 1
    print(f"{s[0]} {s[1]} {s[2]}")
    if cnt == 0:
        print("zilch")
    elif cnt == 1:
        print("double")
    elif cnt == 2:
        print("double-double")
    else:
        print("triple-double")
    print("")
