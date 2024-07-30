n, m, k, a, b, c = map(int, input().split())
p = [[n * a, "Joffrey"], [m * b, "Robb"], [k * c, "Stannis"]]
p.sort(key = lambda x: x[0], reverse = True)
if p[0][0] == p[1][0] == p[2][0]:
    print(" ".join(map(lambda x: x[1], sorted(p, key = lambda x: x[1]))))
elif p[0][0] == p[1][0] and p[1][0] > p[2][0]:
    print(" ".join(map(lambda x: x[1], sorted(p[0:2], key = lambda x: x[1]))))
else:
    print(p[0][1])
