heart = ''' @@@   @@@  
@   @ @   @ 
@    @    @ 
@         @ 
 @       @  
  @     @   
   @   @    
    @ @     
     @      '''.split('\n')
n = int(input())
for s in heart:
    print(s * n)