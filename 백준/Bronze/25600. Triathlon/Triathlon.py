import sys
input = sys.stdin.readline
n = int(input().strip())
mscore = 0
for _ in range(n):
    a, d, g = map(int, input().strip().split())
    score = a * (d + g)
    if a == (d + g):
        score *= 2
    if score > mscore:
        mscore = score
print(mscore)