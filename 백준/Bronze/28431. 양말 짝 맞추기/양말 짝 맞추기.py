a = []
for _ in range(5):
    a.append(int(input()))
a.sort()
if a[0] != a[1]:
    print(a[0])
elif a[2] != a[3]:
    print(a[2])
else:
    print(a[4])