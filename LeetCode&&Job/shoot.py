# -*- coding: utf-8 -*-
from datetime import datetime


'''
@author: Lael
@Date: 2016/10/28
射靶问题    
'''

def shoot(st, time, boomCount):
    if time > 0:
        for i in range(0, 11):
            if 10 * (11 - time) - boomCount - i <= 10:
                st.append(i)
                shoot(st, time-1, boomCount+i)
                st.pop()
                        
    elif time == 0:
        if boomCount == 90:
            for i in st:
                print i,
            print ' '

if __name__ == '__main__':
    start = datetime.now()
    print 'Start at: ', start
    #scores = range(11)
    #oneTurn = [0 for i in range(10)]
    time = 10
    boomCount = 0
    st = []
    shoot(st,time,boomCount)
    
    finish = datetime.now()
    print 'Finish at: ', finish
    print 'Gap is: ', finish - start
        
    
    
