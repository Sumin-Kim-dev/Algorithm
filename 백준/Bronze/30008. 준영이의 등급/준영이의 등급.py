n, k = map(int, input().split())
gs = list(map(int, input().split()))
for g in gs:
    p = g * 100 // n
    if p <= 4:
        print(1, end=" ")
    elif p <= 11:
        print(2, end=" ")
    elif p <= 23:
        print(3, end=" ")
    elif p <= 40:
        print(4, end=" ")
    elif p <= 60:
        print(5, end=" ")
    elif p <= 77:
        print(6, end=" ")
    elif p <= 89:
        print(7, end=" ")
    elif p <= 96:
        print(8, end=" ")
    else:
        print(9, end=" ")
