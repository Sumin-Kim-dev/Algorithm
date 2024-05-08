n = int(input())
ans = 0
for _ in range(n):
    name = input()
    if name == "Poblano":
        ans += 1500
    elif name == "Mirasol":
        ans += 6000
    elif name == "Serrano":
        ans += 15500
    elif name == "Cayenne":
        ans += 40000
    elif name == "Thai":
        ans += 75000
    else:
        ans += 125000
print(ans)
