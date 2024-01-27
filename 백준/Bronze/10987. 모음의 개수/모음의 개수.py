s = input()
cnt = 0
for c in s:
    if c in ['a', 'e', 'i', 'o', 'u']:
        cnt += 1
print(cnt)