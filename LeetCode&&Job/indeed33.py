# -*- coding: utf-8 -*-
'''
@author Lael
@date 2016/10/29


C - Anagram Multiple Number
Time limit : 2sec / Memory limit : 265MB

Score : 100 points

Problem Statement
1035 is an interesting number: 1035×3=3105 and 3105 is a permutation of 1035.

You are given an integer N. Count the number of integers M that satisfy the following conditions:

M≠N
M is a multiple of N
M is a permutation of N (when the numbers are written in decimal without leading zeroes)
Constraints
1≦N≦9,999,999
Input
The input is given from Standard Input in the following format:

N
Output
Print the answer in a line.

Sample Input 1
1035
Sample Output 1
1
3105 satisfies the conditions.

Sample Input 2
1234567
Sample Output 2
0
Sample Input 3
142857
Sample Output 3
5
285714,428571,571428,714285,857142 satisfy the conditions.
'''

M = raw_input()
li = list(M)
li.sort()
m = int(M)
count = 0
resLi = []
for i in range(2,10):
    res = m * i
    tmpLi = list(str(res))
    tmpLi.sort()
    if cmp(li,tmpLi) == 0:
        count += 1
        resLi.append(res) 
print count