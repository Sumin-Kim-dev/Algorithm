a, b, c = map(int, input().split())
if a == b or b == c or c == a or a + b == c or a + c == b or b + c == a:
    print('S')
else:
    print('N')