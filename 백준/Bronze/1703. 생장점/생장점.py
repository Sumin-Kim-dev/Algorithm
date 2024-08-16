import sys
input = sys.stdin.readline
print = sys.stdout.write

while True:
    arr = list(map(int, input().split()))
    a = arr[0]
    if a == 0:
        break
    ans = 1
    for i in range(a):
        ans *= arr[2 * i + 1]
        ans -= arr[2 * i + 2]
    print(str(ans) + "\n")
    