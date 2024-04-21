from math import pi
n = int(input())
for _ in range(n):
    a1, p1 = map(int, input().split())
    r1, p2 = map(int, input().split())
    if (a1 / p1) > (pi * r1 * r1 / p2):
        print("Slice of pizza")
    else:
        print("Whole pizza")