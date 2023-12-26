d = 0
for _ in range(10):
    d += int(input())
d %= 4
if d == 0:
    print('N')
elif d == 1:
    print('E')
elif d == 2:
    print('S')
else:
    print('W')