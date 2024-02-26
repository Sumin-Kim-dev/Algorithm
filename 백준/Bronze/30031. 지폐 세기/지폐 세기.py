n = int(input())
tot = 0
for _ in range(n):
    a, b = map(int, input().split())
    if a == 136:
        tot += 1000
    elif a == 142:
        tot += 5000
    elif a == 148:
        tot += 10000
    elif a == 154:
        tot += 50000
print(tot)