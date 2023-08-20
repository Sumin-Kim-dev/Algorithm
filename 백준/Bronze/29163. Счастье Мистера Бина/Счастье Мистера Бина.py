n = int(input())
a = list(map(int, input().split()))
count = 0
for x in a:
    if x % 2 == 0:
        count += 1
if (2 * count > n):
    print("Happy")
else:
    print("Sad")