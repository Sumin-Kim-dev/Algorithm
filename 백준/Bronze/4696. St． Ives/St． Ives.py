import sys
input = sys.stdin.readline
while True:
    n = float(input())
    if n == 0:
        break
    else:
        print(f'{(1 + n + n * n + n ** 3 + n ** 4):.2f}')