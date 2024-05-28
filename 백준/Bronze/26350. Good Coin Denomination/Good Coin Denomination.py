import sys
input = sys.stdin.readline
n = int(input().strip())
for _ in range(n):
    d = input().strip().split()
    good = True
    for i in range(2, len(d)):
        good &= (int(d[i]) >= 2 * int(d[i - 1]))
    print("Denominations: " + ' '.join(d[1:]))
    if good:
        print("Good coin denominations!")
    else:
        print("Bad coin denominations!")
    print("")
