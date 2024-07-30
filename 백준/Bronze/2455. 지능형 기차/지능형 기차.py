cnt = [0] * 5
for i in range(1, 5):
    a, b = map(int, input().split())
    cnt[i] = cnt[i - 1] + b - a
print(max(cnt))
