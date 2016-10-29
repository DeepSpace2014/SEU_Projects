# -*- coding: utf-8 -*-

from itertools import *
'''
3 2
1 1
2 2

N = 3     M = 2
求N的全排列item in li=[item1,item2,....], 满足item[1-1] != 1, item[2-1] != 2(这里等号右边的数字为输入的后两组数据)
'''

line = raw_input()
N = int(line.split()[0])
M = int(line.split()[1])
#print N,M
li = []
for i in permutations(range(1,N+1),N):
    #print i
    li.append(i)
#print 'li:---',li
tmpLi = []
for i in range(M):
    b,c = raw_input().split()
    b,c = int(b),int(c)
    tmpLi.append([b,c])

#检测满足条件的元素
for item in li:
    #print 'item:',item
    count = 0
    for i in range(M):
        #print 'item[tmpLi[i][0]]:---> ',item[tmpLi[i][0]]
        #print tmpLi[i][0],tmpLi[i][0],'---+++-+++'
        if item[tmpLi[i][0]-1] == tmpLi[i][1]:
            #print 'item[tmpLi[i][0]]: ',item[tmpLi[i][0]]
            #print tmpLi[i][0],tmpLi[i][0],'----+++'
            break
        count += 1
    if count == M:
        for j in item:
            #print 'item:---',item3 2
            print j
        break
        
    
    