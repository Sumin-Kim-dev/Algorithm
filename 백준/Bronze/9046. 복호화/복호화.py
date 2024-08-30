t = int(input())
for _ in range(t):
    freq = {}
    s = input()
    for c in s:
        if c == " ":
            continue
        if c not in freq:
            freq[c] = 0
        freq[c] += 1
    m = 0
    mc = ''
    for c in freq:
        if freq[c] > m:
            m = freq[c]
            mc = c
        elif freq[c] == m:
            mc = '?'
    print(mc)
