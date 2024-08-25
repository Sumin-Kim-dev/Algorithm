n = int(input())
arr = list(map(int, input().split()))
score = 0
curr = 0
for a in arr:
    if a == 0:
        curr = 0
        continue
    curr += 1
    score += curr
print(score)
