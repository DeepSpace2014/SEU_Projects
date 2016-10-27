import java.util.Random;

/**����������һ������
 * struct Node
 * {
 *     int value ;
 *    struct Node *next ;
 *     struct Node *random ;
 * }   
 *���У�randomָ������������һ���ڵ����NULL������ʵ�ָ�����������    
 * 
 *Node *deepCopy (Node *head)  
 * ���ĵ�˼���ǣ��׿�random���Բ��ܣ��ȸ�������ṹ��ֵ��Ȼ�����ԭ�����ÿһ���ڵ��random���������ͷ����ƫ����������ƫ������
 * ��Ҳ���ǵڼ���Ԫ�أ���ô��Ӧ�ģ����Ƶ������еĸ�Ԫ�ص�randomҲָ��ڼ���Ԫ�ء����⣬֮�����Ǽ������ƫ�������������ƫ����
 *��Ҳ����ĳ�ڵ㵽�ýڵ�random����ָ��Ľڵ����Ծ��룩������Ϊ�����ƫ���������Ǹ��ģ�����Ǹ��ģ��ǾͲ��ð��ˣ����������򷴷�����
 *http://www.nowcoder.com/questionTerminal/3791a1c091e74aebb1b9d7c6a097f527
 *
 *���ű����Ŀ
 * 2016��10��26�� ����10:06:39
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
	 * ����һ������
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
	 * �õ���ǰ�ڵ���ͷ�ڵ�֮��ľ���
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
	 * �������
	 * @param head
	 * @return
	 */
	public Node deepCopy(Node head){
		//���ȸ���ԭ��������,�Ӵ���һ���±�ͷ�ڵ㿪ʼ
		Node cur = head;
		Node headCopy = new Node(cur.value);
		//ʹ��curCopy�������µĵ�����
		Node curCopy = headCopy;
		while(cur.next != null){
			cur = cur.next;
			curCopy.next = new Node(cur.value);
			curCopy = curCopy.next;
		}//���Ƶ�����next�����
		
		//���Ƶ������random��
		cur = head;
		curCopy = headCopy;
		
		while(cur != null){
			//�������curָ��ڵ��random�ڵ���ԭ���ı�ͷ�ڵ�֮��ľ���
			int dis = getDis(head,cur);
			if(dis != 0){
				Node tmp = headCopy;
				for(int i = 0; i < dis; i++){
					//�ҳ�cur.randomָ����Ǹ��ڵ�
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
	 * �������
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
		//�½�һ��������
		LinkListCopy2 copy = new LinkListCopy2();
		Node head = copy.createList();
		Node headCopy = copy.deepCopy(head);
		printList(headCopy);
	}
	
	
}
