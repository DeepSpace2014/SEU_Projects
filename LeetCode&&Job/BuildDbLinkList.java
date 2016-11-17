import java.util.Scanner;


/**
 * 
 * @author Lael
 * @date  2016/11/17
 * @describe ����һ�����飬����������������ִ�������һ����Ǹ�����
 * 
 * input:   
 * output:  
 *  1   2   3   4   5   6   ��ʼ�����
	1   2   3   123   4   5   6   �������
	1   2   3   4   5   6   ɾ��Ԫ�����
 * 
 */


class DNode{
	String data;
	DNode left;
	DNode right;
}

public class BuildDbLinkList{
	//����ѭ��˫����
	public static void buildList(DNode head){
		DNode cur = head;
		String s;
		Scanner scann = new Scanner(System.in);
		while(true){
			s = scann.next();
			if(s.equals("#")){
				cur.right = head;
				head.left = cur;
				break;
			}
			DNode node = new DNode();
			node.data = s;
			
			cur.right = node;
			node.left = cur;
			node.right = null;	
			
			cur = cur.right;
		}
		scann.close();
	}
	
	public static void printList(DNode head){
		DNode cur = head;
		cur = cur.right;
		//�����ѭ����������Ҫ----------
		while(cur != null && cur != head){
			System.out.print(cur.data + "   ");
			cur = cur.right;
		}
	}
	
	public static void insertNode(DNode head, int k, String str){
		DNode cur = head;
		int i = 0;
		while(cur != null && i < k){
			cur = cur.right;
			i++;
		}
		if(cur == null || i > k){
			return;
		}
		
		DNode node = new DNode();
		node.data = str;
		node.right = cur.right;
		node.left = cur;
		cur.right.left = node;
		cur.right = node;
	}
	
	public static void deleteNode(DNode head, int k){
		DNode cur = head;
		int i = 0;
		while(cur != null && i < k){
			cur = cur.right;
			i++;
		}
		if(cur == null || i > k){
			return;
		}
		
		cur.right.left = cur.left;
		cur.left.right = cur.right;
	}
	
	public static void main(String[] args){
		DNode head = new DNode();
		buildList(head);
		printList(head);
		System.out.println("��ʼ�����");
		
		insertNode(head,3,"123");
		printList(head);
		System.out.println("�������");
		
		deleteNode(head,4);
		printList(head);
		System.out.println("ɾ��Ԫ�����");
	} 
	
}