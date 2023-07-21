l, p = map(int, input().split())
real = l * p
article = list(map(lambda x: int(x) - real, input().split()))
print(*article)

