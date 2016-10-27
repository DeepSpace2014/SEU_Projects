# -*- coding: utf-8 -*-
'''
https://leetcode.com/problems/copy-list-with-random-pointer/

# Definition for singly-linked list with a random pointer.
# class RandomListNode(object):
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None

class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: RandomListNode
        :rtype: RandomListNode
        """
'''

import hues
hues.log('Mission',42)
hues.info('Finding',42)
hues.error(41,'is not',42)
hues.warn('We are distracted...')
hues.info('Found',24)
hues.success('Close enough')

class LinkListRandom:
    def __init__(self,x):
        self.label = x
        self.next = None
        self.random = None
 
    #插入一个节点   
    def insert(self,head,val):
        node = LinkListRandom(val)
        if head == None:
            return node
        else:
            tmp = head
            while(tmp.next != None):
                tmp = tmp.next
            tmp.next = node
        return head
    
    #打印当前链表
    def printList(self,head):
        tmp = head
        while(tmp != None):
            print 'label:',tmp.label,'  random:',(tmp.random.label if tmp.random is not None else 'none')
            tmp = tmp.next
        #print '----------------------------------------------------'


class Solution:
    # @param head: A LinkList Node
    # @return: A LinkList Node 
    def copyRandomList(self, head):
        if head == None :
            return None
        
        #首先复制原来的链表（没有random指针）
        cur = head
        headCopy = LinkListRandom(cur.label)
        curCopy = headCopy
        while(cur.next != None):
            cur = cur.next
            curCopy.next = LinkListRandom(cur.label)
            curCopy = curCopy.next
        
        #然后复制random指针
        cur = head
        curCopy = headCopy
        #计算当前cur指向的random节点距离头结点之间的距离
        while(cur != None):
            dis = self.getDis(head,cur)
            if dis != 0:
                #找出cur.random指向的那个结点
                tmp = headCopy
                for i in range(dis):
                    tmp = tmp.next
                curCopy.random = tmp
            elif cur.random == head:
                curCopy.random = head
            cur = cur.next
            curCopy = curCopy.next
            
        return headCopy
    
    def getDis(self,head,cur):
        dis = 0
        random = cur.random
        while(head != random):
            dis += 1
            head = head.next
        return dis
        
        
        




if __name__ == '__main__':
    head = None
    l = LinkListRandom('null')
    for i in range(1,10):
        head = l.insert(head,i)
    #创建了三个random指针
    head.next.next.random = head.next.next.next.next
    head.next.next.next.random = head
    head.next.next.next.next.random = head.next
    s = Solution()
    
    print 'Original:\n',l.printList(head)
    result = s.copyRandomList(head)
    print 'Changed:\n',l.printList(result)

    



            
    
    
