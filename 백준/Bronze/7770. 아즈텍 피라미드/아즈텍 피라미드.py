n = int(input())
for i in range(1, 100000):
    if i * (2 * i * i + 1) // 3 > n:
        print(i - 1)
        break
