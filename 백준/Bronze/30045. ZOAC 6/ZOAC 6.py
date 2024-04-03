import sys
input = sys.stdin.readline
n = int(input().strip())
cnt = 0
for _ in range(n):
    string = input().strip()
    if "OI" in string or "01" in string:
        cnt += 1
print(cnt)