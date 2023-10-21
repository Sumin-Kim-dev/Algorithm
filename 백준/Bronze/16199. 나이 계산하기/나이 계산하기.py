y, m, d = map(int, input().split())
cy, cm, cd = map(int, input().split())
if cm > m or cm == m and cd >= d:
    print(cy - y)
else:
    print(cy - y - 1)
print(cy - y + 1)
print(cy - y)