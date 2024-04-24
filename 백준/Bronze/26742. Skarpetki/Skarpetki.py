string = input()
cntB = 0
for c in string:
    if c == 'B':
        cntB += 1
print(cntB // 2 + (len(string) - cntB) // 2)
