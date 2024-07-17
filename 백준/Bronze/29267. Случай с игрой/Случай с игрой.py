n, k = map(int, input().split())
curr = 0
save = 0
for _ in range(n):
    cmd = input()
    if cmd == 'ammo':
        curr += k
        print(curr)
    elif cmd == 'shoot':
        curr -= 1
        print(curr)
    elif cmd == 'load':
        curr = save
        print(curr)
    elif cmd == 'save':
        save = curr
        print(curr)
    else:
        break
