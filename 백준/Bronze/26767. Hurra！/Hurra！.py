import sys
print = sys.stdout.write

n = int(input())
for i in range(1, n + 1):
    if i % 77 == 0:
        print("Wiwat!\n")
    elif i % 7 == 0:
        print("Hurra!\n")
    elif i % 11 == 0:
        print("Super!\n")
    else:
        print(str(i) + "\n")