l, r = map(int, input().split())
if l == r and l > 0:
    print(f'Even {2 * l}')
elif l != r:
    print(f'Odd {2 * max(l, r)}')
else:
    print('Not a moose')