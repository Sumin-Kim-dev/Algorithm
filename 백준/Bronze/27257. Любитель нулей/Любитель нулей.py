n = str(int(input()[::-1]))
cnt = 0
for i in n:
    if i == '0':
        cnt += 1
print(cnt)
