w = int(input())
l = int(input())
h = int(input())
if w < l:
    w, l = l, w
if w / l <= 2 and l / h >= 2:
    print("good")
else:
    print("bad")
