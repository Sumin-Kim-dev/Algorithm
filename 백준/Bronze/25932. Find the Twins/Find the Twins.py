n = int(input())
for _ in range(n):
    arr = input().split()
    zack = False
    mack = False
    for i in arr:
        if i == '17':
            zack = True
        elif i == '18':
            mack = True
    print(" ".join(arr))
    if zack and mack:
        print("both\n")
    elif zack:
        print("zack\n")
    elif mack:
        print("mack\n")
    else:
        print("none\n")
        