string = input()
s = 65 * 26 + 25 * 26 // 2
for c in string:
    s -= ord(c)
print(chr(s))
