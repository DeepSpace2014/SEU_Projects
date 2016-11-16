/**
 * 
 * @author Lael
 * @date  2016/11/16
 * @describe ���������ַ������ҳ��������ַ�����������Ӵ�
 * 
 * input:   abcde   bdbcd
 * output:  bcd
 * ʹ�ö�̬�滮��˼�룬ʱ�临�Ӷȼ��ռ临�Ӷȶ�ΪΪO(M*N)��
 */

import java.util.Scanner;
public class String_Lcs {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		String str1 = scann.nextLine();
		String str2 = scann.nextLine();
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		
		int[][] dp = getDP(ch1,ch2);
		scann.close();
		
		int max = 0;
		int end = 0;
		for(int i = 0; i < str1.length(); i++){
			for(int j = 0; j < str2.length(); j++){
				if(dp[i][j] > max){
					max = dp[i][j];
					end = i;
				}
			}
		}
		
		System.out.println(max);
		System.out.println(str1.substring(end+1-max, end+1));
		
	}
	
	public static int[][] getDP(char[] ch1, char[] ch2){
		int[][] dp = new  int[ch1.length][ch2.length];
		//�ж��ַ���2�еĵ�һ���ַ��Ƿ���DP����ĵ�һ��
		for(int i = 0; i < ch1.length; i++){
			if(ch1[i] == ch2[0])
				dp[i][0] = 1;
		}
		//�ж��ַ���1�еĵ�һ���ַ��Ƿ���DP����ĵ�һ��
		for(int j = 1; j < ch2.length; j++){
			if(ch2[j] == ch1[0])
				dp[0][j] = 1;
		}
		for(int i = 1; i < ch1.length; i++){
			for(int j = 1; j < ch2.length; j++){
				if(ch1[i] == ch2[j])
					dp[i][j] = dp[i-1][j-1] + 1;
				//System.out.println( dp[i][j]);
			}
		}
		return dp;
	}

}
