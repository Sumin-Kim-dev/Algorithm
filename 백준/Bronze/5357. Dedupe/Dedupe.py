import sys
input = sys.stdin.readline
print = sys.stdout.write
n = int(input().strip())
for _ in range(n):
    str = input().strip()
    last = ''
    for c in str:
        if last == c:
            continue
        else:
            print(c)
            last = c
    print('\n')