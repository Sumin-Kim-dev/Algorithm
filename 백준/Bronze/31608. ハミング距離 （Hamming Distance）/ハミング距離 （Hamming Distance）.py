n = int(input())
s = input()
t = input()
dist = 0
for i in range(n):
    if s[i] != t[i]:
        dist += 1
print(dist)
