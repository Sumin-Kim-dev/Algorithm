while True:
    name, age, weight = input().split()
    if (name == '#' and age == '0' and weight == '0'):
        break
    if int(age) > 17:
        print(name + " Senior")
    elif int(weight) >= 80:
        print(name + " Senior")
    else:
        print(name + " Junior")