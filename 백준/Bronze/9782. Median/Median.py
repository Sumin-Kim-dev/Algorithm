i = 1
while True:
    arr = list(map(int, input().split()))
    n = arr[0]
    if n == 0:
        break
    median = arr[n // 2 + 1] * 1.0 if n % 2 == 1 else (arr[n // 2 + 1] + arr[n // 2]) / 2
    print(f"Case {i}: {median:.1f}")
    i += 1