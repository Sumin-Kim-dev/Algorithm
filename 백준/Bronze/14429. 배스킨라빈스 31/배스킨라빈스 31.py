n = int(input())
minNo = 0
minL = 10000
for i in range(1, n + 1):
    j, m = map(int, input().split())
    currL = 2 * ((j - 1) // (m + 1) + 1)
    if currL < minL:
        minL = currL
        minNo = i
print(minNo, minL)
