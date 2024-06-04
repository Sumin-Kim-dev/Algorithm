s = input()
cnt1 = 0
cnt2 = 0
for c in s:
    if c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u':
        cnt1 += 1
    elif c == 'y':
        cnt2 += 1
print(cnt1, cnt1 + cnt2)
