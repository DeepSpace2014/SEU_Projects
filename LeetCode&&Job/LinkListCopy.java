import java.util.Random;

/**假设有如下一个链表：
 * struct Node
 * {
 *     int value ;
 *    struct Node *next ;
 *     struct Node *random ;
 * }   
 *其中，random指向该链表的任意一个节点或者NULL，请编程实现该链表的深拷贝。    
 * 
 *Node *deepCopy (Node *head)  
 * 核心的思想是：抛开random属性不管，先复制链表结构和值。然后计算原链表的每一个节点的random属性相对于头结点的偏移量（绝对偏移量）
 * ，也就是第几个元素，那么相应的，复制的链表中的该元素的random也指向第几个元素。另外，之所以是计算绝对偏移量而不是相对偏移量
 *（也就是某节点到该节点random属性指向的节点的相对距离），是因为，相对偏移量可以是负的，如果是负的，那就不好办了，单链表不能向反方向走
 *http://www.nowcoder.com/questionTerminal/3791a1c091e74aebb1b9d7c6a097f527
 *
 *宜信编程题目
 * 2016年10月26日 上午10:06:39
 */


public class LinkListCopy {
	class Node{
		int value;
		Node next;
		Node random;
		public Node(int value){
			this.value = value;
		}
	}
	
	/**
	 * 创建一个链表
	 * @return
	 */
	public Node createList(){
		Node head = new Node(new Random().nextInt(10));
		Node tmp = head;
		for(int i = 0; i < 10; i++){
			tmp.next = new Node(new Random().nextInt(10));
			tmp = tmp.next;
		}
		head.next.next.random = head.next.next.next.next.next;
		head.next.next.next.random = head;
		
		return head;
	}
	
	/**
	 * 得到当前节点与头节点之间的距离
	 * @param head
	 * @param cur
	 * @return
	 */
	public int getDis(Node head, Node cur){
		int dis = 0;
		Node random = cur.random;
		if(random == null) return 0;
		while(head != random){
			dis++;
			head = head.next;
		}
		return dis;
	}
	
	/**
	 * 深复制链表
	 * @param head
	 * @return
	 */
	public Node deepCopy(Node head){
		//首先复制原单链表中,从创建一个新表头节点开始
		Node cur = head;
		Node headCopy = new Node(cur.value);
		//使用curCopy来生成新的单链表
		Node curCopy = headCopy;
		while(cur.next != null){
			cur = cur.next;
			curCopy.next = new Node(cur.value);
			curCopy = curCopy.next;
		}//复制单链表next域完成
		
		//复制单链表的random域
		cur = head;
		curCopy = headCopy;
		
		while(cur != null){
			//首先求得cur指向节点的random节点与原来的表头节点之间的距离
			int dis = getDis(head,cur);
			if(dis != 0){
				Node tmp = headCopy;
				for(int i = 0; i < dis; i++){
					//找出cur.random指向的那个节点
					tmp = tmp.next;
				}
				curCopy.random = tmp;
				
			}
			else if(cur.random == head){
				curCopy.random = headCopy;
			}
			cur = cur.next;
			curCopy = curCopy.next;
		}
		
		return headCopy;
		
	}
	
	/**
	 * 输出链表
	 * @param head
	 */
	public static void printList(Node head){
		String list = "";
		while(head != null){
			list += "(" + "value:" + head.value + ",random:" + (head.random == null ? "null":head.random.hashCode()) + ")--->";
			//list+=",rando:"+(head.random==null?"":head.random.hashCode())+")-->";
			head = head.next;
		}
		System.out.println(list);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		//新建一个单链表
		LinkListCopy2 copy = new LinkListCopy2();
		Node head = copy.createList();
		Node headCopy = copy.deepCopy(head);
		printList(headCopy);
	}
	
	
}
