import java.util.Scanner;
/**
 * 
 * @author Lael
 * @date 2016/11/1
 *
 */

public class Hanoi {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		System.out.println("�����뺺ŵ��������:");
		int n = scann.nextInt();
		char A = 'A';
		char B = 'B';
		char C = 'C';
		move(n, A, B, C);
	}
	
	public static void move(int n, char A, char B, char C){
		if(n == 1){
			System.out.println("��" + A + "�ƶ���" + n + "��" + C);
		}else{
			move(n-1, A, C, B);
			System.out.println("��" + A + "�ƶ���" + n + "��" + C);
			move(n-1, B, A, C);
		}
	}

}
