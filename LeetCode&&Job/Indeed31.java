package indeed;
/**
 * @author Lael
 * @date 2016/10/29
 * 
 * indeed31
 * 쳲���������  ֻ�ǳ�ʼ�������������������Scanner����
 */


import java.util.Scanner;
public class Indeed31 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		// get a integer
		int a = sc.nextInt();
		int b = sc.nextInt();
		int res = getResult(a,b,10);
		System.out.println(res);
	}
	
	public static int getResult(int a, int b, int n ){
		int res = 0;
		if(n < 1)
			return 0;
		if(n == 1 )
			return a;
		if(n == 2)
			return b;
		
		
		return getResult(a,b,n-1) + getResult(a,b,n-2);
	}
}