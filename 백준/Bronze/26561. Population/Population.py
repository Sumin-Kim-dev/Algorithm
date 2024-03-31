import sys
input = sys.stdin.readline
print = sys.stdout.write

n = int(input().strip())
for _ in range(n):
    p, t = map(int, input().strip().split())
    print(str(p + t // 4 - t // 7) + "\n")