w = float(input())
h = float(input())
bmi = w / h / h
if bmi > 25.0:
    print("Overweight")
elif bmi >= 18.5:
    print("Normal weight")
else:
    print("Underweight")