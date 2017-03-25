# -*- coding: utf-8 -*-

'''
2017/3/25 360 求期望
'''

while True:
    n = raw_input()
    li = []
    for i in range(int(n)):
        x,p = raw_input().strip().split()
        x,p = int(x), int(p)
        li.append([x,p])
    
    sum = 0
    for i in range(int(n)):
        sum += li[i][0] * li[i][1]/float(100)
    print "%.3f" % sum