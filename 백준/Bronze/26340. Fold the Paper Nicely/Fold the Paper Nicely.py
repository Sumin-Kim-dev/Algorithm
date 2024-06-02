n = int(input())
for _ in range(n):
    a, b, k = map(int, input().split())
    print("Data set:", a, b, k)
    if a < b:
        a, b = b, a
    for _ in range(k):
        a = a // 2
        if a < b:
            a, b = b, a
    print(a, b)
    print("")
