h, m, s = map(int, input().split())
d = int(input())
curr = h * 3600 + m * 60 + s + d
print((curr // 3600) % 24, (curr // 60) % 60, curr % 60)