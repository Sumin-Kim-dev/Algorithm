ans = ""
for i in range(1, 6):
    name = input()
    if 'FBI' in name:
        ans += str(i) + " "
if ans == "":
    ans = "HE GOT AWAY!"
print(ans)
