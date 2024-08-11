import sys
input = sys.stdin.readline
print = sys.stdout.write

while True:
    n = input().strip()
    if n == '0':
        break
    w = 4 * len(n) + 1
    for i in n:
        if i == '1':
            w -= 1
        elif i == '0':
            w += 1
    print(str(w) + '\n')
