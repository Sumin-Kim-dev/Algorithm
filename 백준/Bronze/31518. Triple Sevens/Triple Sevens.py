n = int(input())
for _ in range(3):
    a = list(map(int, input().split()))
    if 7 not in a:
        print(0)
        break
else:
    print(777)