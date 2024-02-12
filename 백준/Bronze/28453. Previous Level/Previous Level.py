def gugan(m):
    if m == 300:
        return "1"
    elif m >= 275:
        return "2"
    elif m >= 250:
        return "3"
    else:
        return "4"

n = int(input())
print(" ".join(list(map(gugan, list(map(int, input().split()))))))
