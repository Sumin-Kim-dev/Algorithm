no = 0
score = 0
for i in range(1, 6):
    curr = sum(list(map(int, input().split())))
    if curr > score:
        score = curr
        no = i
print(no, score)
