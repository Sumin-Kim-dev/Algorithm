s = input()
for i in range((len(s) + 9) // 10):
    print(s[10 * i:10 * (i + 1)])
