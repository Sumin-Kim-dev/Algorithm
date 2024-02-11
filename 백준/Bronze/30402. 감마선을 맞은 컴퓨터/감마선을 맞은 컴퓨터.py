import sys
input = sys.stdin.readline
while True:
    for c in input().strip().split():
        if c == 'r' or c == 'o' or c == 'y' or c == 'p':
            continue
        elif c == 'w':
            print("chunbae")
            exit()
        elif c == 'b':
            print("nabi")
            exit()
        elif c == 'g':
            print("yeongcheol")
            exit()