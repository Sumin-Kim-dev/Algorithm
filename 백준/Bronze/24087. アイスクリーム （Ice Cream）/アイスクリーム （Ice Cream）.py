s = int(input())
a = int(input())
b = int(input())
print(250 + 100 * max((s - a + b - 1) // b, 0))