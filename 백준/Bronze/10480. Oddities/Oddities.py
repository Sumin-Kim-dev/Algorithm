import sys
input = sys.stdin.readline

n = int(input())
for _ in range(n):
    a = int(input())
    print(f'{a} is {"even" if a % 2 == 0 else "odd"}')