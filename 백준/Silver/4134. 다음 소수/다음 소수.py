t = int(input())
for _ in range(t):
    n = int(input())
    p = n
    if n <= 1:
        p = 2
    while True:
        for i in range(2, (int) (p ** 0.5) + 1):
            if p % i == 0:
                break
        else:
            print(p)
            break
        p += 1