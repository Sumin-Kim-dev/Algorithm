while True:
    n = int(input())
    if n == 0:
        break
    arr = list(map(int, input().split()))
    curr = arr[0] + arr[1] + arr[2]
    ans = curr
    for i in range(3, n):
        curr += arr[i]
        curr -= arr[i - 3]
        if curr > ans:
            ans = curr
    print(ans)
