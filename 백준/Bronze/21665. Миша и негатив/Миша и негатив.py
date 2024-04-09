n, m = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(input())
    
input()

count = 0
for i in range(n):
    row = input()
    for j in range(m):
        if arr[i][j] == row[j]:
            count += 1
print(count)
