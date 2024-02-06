import sys
input = sys.stdin.readline
print = sys.stdout.write
table = str.maketrans('ieIE', 'eiEI')
while True:
    name = input().strip()
    if name == '':
        break
    print(name.translate(table) + '\n')