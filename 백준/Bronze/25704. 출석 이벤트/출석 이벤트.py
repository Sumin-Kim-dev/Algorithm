n = int(input())
p = int(input())
if n >= 20:
    print(min(p * 3 // 4, max(p - 2000, 0)))
elif n >= 15:
    print(min(p * 9 // 10, max(p - 2000, 0)))
elif n >= 10:
    print(min(p * 9 // 10, max(p - 500, 0)))
elif n >= 5:
    print(max(p - 500, 0))
else:
    print(p)