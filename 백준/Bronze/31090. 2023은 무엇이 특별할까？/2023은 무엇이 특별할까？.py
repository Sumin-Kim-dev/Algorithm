import sys
input = sys.stdin.readline
print = sys.stdout.write

t = int(input().strip())
for _ in range(t):
    n = int(input().strip())
    print("Good\n" if (n + 1) % (n % 100) == 0 else "Bye\n")