p1, s1 = map(int, input().split())
s2, p2 = map(int, input().split())
if p1 + p2 > s1 + s2 or (p1 + p2 == s1 + s2 and p2 > s1):
    print("Persepolis")
elif p1 + p2 < s1 + s2 or (p1 + p2 == s1 + s2 and p2 < s1):
    print("Esteghlal")
else:
    print("Penalty")