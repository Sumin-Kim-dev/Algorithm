from math import sqrt 
w, h = map(int, input().split())
print(w + h - sqrt(w * w + h * h))