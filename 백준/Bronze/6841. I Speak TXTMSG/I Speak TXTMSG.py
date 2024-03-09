import sys
input = sys.stdin.readline
print = sys.stdout.write

table = {"CU" : "see you", 
         ":-)" : "I’m happy", 
         ":-(" : "I’m unhappy", 
         ";-)" : "wink", 
         ":-P" : "stick out my tongue", 
         "(~.~)" : "sleepy", 
         "TA" : "totally awesome", 
         "CCC" : "Canadian Computing Competition", 
         "CUZ" : "because", 
         "TY" : "thank-you", 
         "YW" : "you’re welcome", 
         "TTYL" : "talk to you later"}
while True:
    word = input().strip()
    if word in table.keys():
        print(table[word] + "\n")
    else:
        print(word + "\n")
    if word == "TTYL":
        break