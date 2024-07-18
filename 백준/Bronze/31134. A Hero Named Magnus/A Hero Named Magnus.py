import sys
input = sys.stdin.readline
print = sys.stdout.write
t = int(input().strip())
for _ in range(t):
    x = int(input().strip())
    print(str(2 * x - 1) + "\n")