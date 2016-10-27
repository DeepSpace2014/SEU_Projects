# -*- coding: utf-8 -*-

import sys
from collections import defaultdict
import os

reload(sys)
sys.setdefaultencoding('utf8')


def process(path):
    #path = path.encode('gb2312')
    count = 0
    numCount = 0
    count2 = 0
    resultLi = []
    tt = 0
    resultDic = defaultdict(lambda: defaultdict(list))
    resultDic2 = defaultdict(lambda: defaultdict(list))
    for line in open(path, 'r'):
        if count != 14:
            count += 1
            continue

        line1 = line.strip().split(':')[1]
        # print line1
        result = []
        for item in line1.split(','):
            try:
                result.append(int(item))
                numCount += 1
            except Exception, ex:
                tt += 1
        resultLi.append(result)
        count2 += 1
        if count2 == 4:
            break

    k1 = 'k'
    kCount = 1
    for item in resultLi:
        resultDic[k1 + str(kCount)] = item
        kCount += 1
    resultDic2['left1'] = resultDic['k1']
    resultDic2['right1'] = resultDic['k2']
    resultDic2['left2'] = resultDic['k3']
    resultDic2['right2'] = resultDic['k4']

    timeList = []
    for x in range(400):
        timeList.append(x * 10)

    resultDic2['time1'] = timeList
    resultDic2['time2'] = timeList

    resultDic2 = fill400(resultDic2)
    resultDic2 = getResult(resultDic2)

    return resultDic2


def fill400(resultDic2):
    l1Length = len(resultDic2['left1'])
    l2Length = len(resultDic2['left2'])
    r1Length = len(resultDic2['right1'])
    r2Length = len(resultDic2['right2'])

    while (len(resultDic2['left1']) != 400):
        resultDic2['left1'].append(0)
    while (len(resultDic2['left2']) != 400):
        resultDic2['left2'].append(0)
    while (len(resultDic2['right1']) != 400):
        resultDic2['right1'].append(0)
    while (len(resultDic2['right2']) != 400):
        resultDic2['right2'].append(0)

    return resultDic2


def getFilePath(path):
    rootList = []
    filesList = []
    for root, dirs, files in os.walk(path):
        rootList.append(root)
        filesList.append(files)
    return rootList, filesList


def getResult(resultDic2):
    minus1 = list(map(lambda x: abs(x[0] - x[1]), zip(resultDic2['left1'], resultDic2['right1'])))
    minus2 = list(map(lambda x: abs(x[0] - x[1]), zip(resultDic2['left2'], resultDic2['right2'])))

    resultDic2["m1Max"] = max(minus1)
    resultDic2["l1Max"] = max(resultDic2['left1'])
    resultDic2["r1Max"] = max(resultDic2['right1'])

    resultDic2["m2Max"] = max(minus2)
    resultDic2["l2Max"] = max(resultDic2['left2'])
    resultDic2["r2Max"] = max(resultDic2['right2'])
    resultDic2['table_1'] = get_table(resultDic2['left1'], resultDic2['right1'], minus1)
    resultDic2['table_2'] = get_table(resultDic2['left2'], resultDic2['right2'], minus2)

    return resultDic2


def get_table(left, right, error):
    table = []

    for l, r, e in zip(left, right, error):
        d = {}
        d['left'] = l
        d['right'] = r
        d['error'] = e
        table.append(d)

    return table


if __name__ == '__main__':
    pathList = ['好A9W151_20160715_145016.txt']
    for item in pathList:
        resultDic2 = process(item.encode('gb2312'))
        print resultDic2
