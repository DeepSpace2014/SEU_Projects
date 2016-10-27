import sys 
from collections import defaultdict
import os



reload(sys) 
sys.setdefaultencoding('utf8') 

def process_data(path):
    count = 0
    numCount = 0
    count2 = 0
    resultLi = []
    tt = 0
    resultDic = defaultdict(lambda:defaultdict(list))
    resultDic2 = defaultdict(lambda:defaultdict(list))
    for line in open('path','r'):
        if count != 14:
            count += 1
            continue
        
        line1 = line.strip().split(':')[1]
        print line1
        result = []
        for item in line1.split(','):
            try:
                result.append(int(item))
                numCount += 1
            except Exception,ex:
                tt += 1
        resultLi.append(result)  
        count2 += 1
        if count2 == 4:
            break
    
    k1 = 'k'
    kCount = 1
    for item in resultLi:
        resultDic[k1+str(kCount)] = item
        kCount += 1
    resultDic2['left1'] = resultDic['k1']
    resultDic2['right1'] = resultDic['k2']
    resultDic2['left2'] = resultDic['k3']
    resultDic2['right2'] = resultDic['k4']
    
    
    timeList = []
    for x in range(199):
        timeList.append(x*10)
        
    resultDic2['time1'] = timeList
    resultDic2['time2'] = timeList
    
    print resultDic2.items()

def getFilePath(path):
    rootList = []
    filesList = []
    for root,dirs,files in os.walk(path):
        rootList.append(root)
        filesList.append(files)
    return filesList

def showFiles():
    cwd = os.getcwd()
    date = raw_input('\nPlease input datetime:')
    path = cwd + '/' + date + '/'
    print 'cwd:',cwd
    print 'path:',path
    filesList = getFilePath(path)
    print filesList
    filesShowList = []
    for item in filesList[0]:
        tmp = item.decode('gb2312')
        filesShowList.append(tmp)
    return filesShowList
 
if __name__ == '__main__':
    #process_data('data2.txt')
    print showFiles()       
   