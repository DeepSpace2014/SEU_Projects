# -*- coding: utf-8 -*-

'''
2017/3/25 360 求字符串子串
'''

while True:
    line = raw_input()
    n = len(list(line))
    num = n*(n+1)/2
    count = 0
    zichuan_len = 2
    li = []
    if n%2 == 0:

        while(zichuan_len <= n-2):
            for i in range(n-zichuan_len+1):
                li.append((line[i:i+zichuan_len]))
            zichuan_len += 2
    else:
        while(zichuan_len <= n-1):
            for i in range(n-zichuan_len+1):
                li.append(line[i:i+zichuan_len])
            zichuan_len += 2
    count = 0  
    print li
    for item in li:
        tmpDic = {}
        tmpLi = list(item)
        flag = 1
        for i in tmpLi:
            try:
                tmpDic[i] += 1
            except:
                tmpDic[i] = 1
            
        for j in tmpDic.values():
            if j%2 != 0:
                flag = 0
                break
        #print tmpDic.values()
        if flag == 1:
            count += 1
            print item,

    print '\n',count
        
    
        
        
        