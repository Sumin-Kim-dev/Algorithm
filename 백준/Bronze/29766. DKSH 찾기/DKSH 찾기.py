s = input()
i = 0
cnt = 0
while i + 4 <= len(s):
    if s[i] == 'D':
        if s[i + 1] == 'K' and s[i + 2] == 'S' and s[i + 3] == 'H':
            cnt += 1
            i += 4
        else:
            i += 1
    else:
        i += 1
print(cnt)