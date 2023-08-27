for _ in range(3):
    t = list(map(int, input().split()))
    diff = (t[5] - t[2]) + (t[4] - t[1]) * 60 + (t[3] - t[0]) * 3600
    print(f'{diff // 3600} {(diff // 60) % 60} {diff % 60}')