import sys
input = sys.stdin.readline
print = sys.stdout.write

while True:
    try:
        a, b = map(float, input().strip().split())
        if a > 0 and b > 0:
            print("Q1\n")
        elif a < 0 and b > 0:
            print("Q2\n")
        elif a < 0 and b < 0:
            print("Q3\n")
        elif a > 0 and b < 0:
            print("Q4\n")
        else:
            print("AXIS\n")
    except:
        break