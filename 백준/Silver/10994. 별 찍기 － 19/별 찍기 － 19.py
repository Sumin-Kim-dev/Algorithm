n = int(input())
for i in range(1, n):
    row1 = ""
    for j in range(i - 1):
        row1 += "* "
    row1 += "*" * (4 * (n - i) + 1)
    for j in range(i - 1):
        row1 += " *"
    print(row1)
    row2 = ""
    for j in range(i):
        row2 += "* "
    row2 += " " * (4 * (n - i) - 3)
    for j in range(i):
        row2 += " *"
    print(row2)
print("* " * (2 * n - 1))
for i in range(n - 1, 0, -1):
    row2 = ""
    for j in range(i):
        row2 += "* "
    row2 += " " * (4 * (n - i) - 3)
    for j in range(i):
        row2 += " *"
    print(row2)
    row1 = ""
    for j in range(i - 1):
        row1 += "* "
    row1 += "*" * (4 * (n - i) + 1)
    for j in range(i - 1):
        row1 += " *"
    print(row1)
