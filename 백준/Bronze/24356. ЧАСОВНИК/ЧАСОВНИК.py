t1, m1, t2, m2 = map(int, input().split())
dm = t2 * 60 + m2 - t1 * 60 - m1
if dm < 0:
    dm += 1440
print(dm, dm // 30)