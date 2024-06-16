n = int(input())
a = list(set(list(map(int, input().split()))))
a.sort()
print(*a, sep="\n")