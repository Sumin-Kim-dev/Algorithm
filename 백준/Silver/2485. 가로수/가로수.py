def gcd(a, b):
    if a > b:
        a, b = b, a
    if a == 0:
        return b
    return gcd(b % a, a)

n = int(input())
arr = [int(input()) for _ in range(n)]
arr.sort()
diff = arr[n - 1] - arr[0]
g = arr[1] - arr[0]
for i in range(1, n):
    g = gcd(g, arr[i] - arr[0])
result = diff // g + 1 - n
print(result)