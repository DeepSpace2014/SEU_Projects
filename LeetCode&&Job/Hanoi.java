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
		System.out.println("请输入汉诺塔的数量:");
		int n = scann.nextInt();
		char A = 'A';
		char B = 'B';
		char C = 'C';
		move(n, A, B, C);
	}
	
	public static void move(int n, char A, char B, char C){
		if(n == 1){
			System.out.println("从" + A + "移动盘" + n + "到" + C);
		}else{
			move(n-1, A, C, B);
			System.out.println("从" + A + "移动盘" + n + "到" + C);
			move(n-1, B, A, C);
		}
	}

}
