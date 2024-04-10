string = input()
cntA = 0
cntB = 0
for c in string:
    if c == 'A':
        cntA += 1
    else:
        cntB += 1
print(f"{cntA} : {cntB}")