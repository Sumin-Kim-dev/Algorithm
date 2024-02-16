import sys
input = sys.stdin.readline
n = int(input().strip())
for _ in range(n):
    no, s1, s2, s3 = map(int, input().strip().split())
    s = s1 + s2 + s3
    pf = "PASS" if s1 >= 10.5 and s2 >= 7.5 and s3 >= 12 and s >= 55 else "FAIL"
    print(f'{no} {s} {pf}')