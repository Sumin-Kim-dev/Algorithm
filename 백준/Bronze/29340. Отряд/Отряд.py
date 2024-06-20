a = input()
b = input()
s = ""
for i in range(len(a)):
    if ord(a[i]) > ord(b[i]):
        s += a[i]
    else:
        s += b[i]
print(s)
