x0, y0, z0 = map(int, input().split())
x, y, z = map(int, input().split())
if x >= x0 and y >= y0 and z >= z0:
    print("A")
elif 2 * x >= x0 and y >= y0 and z >= z0:
    print("B")
elif y >= y0 and z >= z0:
    print("C")
elif 2 * y >= y0 and z >= z0:
    print("D")
else:
    print("E")