import sys
input = sys.stdin.readline
print = sys.stdout.write

t = int(input().rstrip())
for _ in range(t):
    n = int(input().rstrip())
    print(str(n * n) + "\n")