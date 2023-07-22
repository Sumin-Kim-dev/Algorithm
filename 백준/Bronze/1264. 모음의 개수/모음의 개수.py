consonants = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
while True:
    str = input()
    if (str == "#"):
        break
    count = 0
    for char in str:
        if (char in consonants):
            count = count + 1
    print(count)
    