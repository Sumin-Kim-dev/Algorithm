import sys
input = sys.stdin.readline
print = sys.stdout.write

n = int(input().strip())
for _ in range(n):
    word, si, sj = map(lambda x: x, input().strip().split())
    i = int(si)
    j = int(sj)
    print(word[0:i] + word[j:] + "\n")