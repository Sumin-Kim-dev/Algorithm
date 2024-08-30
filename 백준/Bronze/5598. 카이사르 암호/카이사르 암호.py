s = input()
t = ""
for i in range(len(s)):
    t += chr((ord(s[i]) - ord('A') + 23) % 26 + ord('A'))
print(t)
