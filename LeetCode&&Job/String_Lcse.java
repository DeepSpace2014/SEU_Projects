/**
 * 
 * @author Lael
 * @date  2016/11/16
 * @describe ���������ַ������ҳ��������ַ����������������
 * 
 * input:   1A2C3D4B56   B1D23CA45B6A
 * output:  123456 ���� 12C4B6
 * 
 * ʹ�ö�̬�滮��˼��,����DP�����е�ĳ��λ�þ��Ǽ򵥱Ƚ���ص�3��λ�õ�ֵ���ѣ�����ʱ�临�Ӷ�ΪO(1);
 * ��̬�滮��dp�Ĵ�СΪM*N,���Լ���dp�����ʱ�临�Ӷ�ΪO(M*N).
 * ͨ��dp�õ�����������еĹ���ΪO(M+N)����Ϊ��������ƶ�N��λ�ã���������ƶ�M��λ��
 * �����ܵ�ʱ�临�Ӷ�ΪO(M*N)������ռ临�Ӷ�ΪO(M*N)
 */
import java.util.Scanner;

public class String_Lcse {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		String str1 = scann.nextLine();
		String str2 = scann.nextLine();
		
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		
		//dp����������½ǵ�ֵ����str1�����str2���������������еĳ��ȡ�
		//ͨ������dp�����״̬�����Եõ������������
		int[][] dp = getDP(ch1, ch2);
		//ͨ��dp�������������еĹ��̾��ǻ�ԭ����ʱ������dp�Ĺ��̣������ĸ����Ծͳ��ĸ������ƶ���
		int m = ch1.length - 1;
		int n = ch2.length - 1;
		char[] res = new char[dp[m][n]]; //����һ�������������Ԫ�ظ���������
		int index = res.length - 1;
		//�������½�Ԫ�ؿ�ʼ���ε���: �ϣ������ϲ鿴��������ɵ�DP���󣬲���Ԫ�ؼ��뵽���ڵ�res������
		while(index >= 0){
			if(n > 0 && dp[m][n] == dp[m][n-1])
				n--;
			else if(m > 0 && dp[m][n] == dp[m-1][n])
				m--;
			else{
				//��ʱ���ʹ���������µĹ����ַ�
				res[index--] = ch1[m];
				m--;
				n--;
			}
		}
		
		String result = String.valueOf(res);
		System.out.println(result);			
	}

	
	public static int[][] getDP(char[] ch1, char[] ch2){
		int[][] dp = new int[ch1.length][ch2.length];
		dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
		for(int i = 1; i < ch1.length; i++){
			//����DP����ĵ�һ��
			dp[i][0] = Math.max(dp[i-1][0], ch1[i] == ch2[0] ? 1 : 0);
		}
		
		for(int j = 1; j < ch2.length; j++){
			//����DP����ĵ�һ��
			dp[0][j] = Math.max(dp[0][j-1], ch1[0] == ch2[j] ? 1 : 0);
		}
		
		for(int i = 1; i < ch1.length; i++){
			for(int j = 1; j < ch2.length; j++){
				//dp[i][j]����ȡֵ���������
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(ch1[i] == ch2[j]){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
				}
			}
		}
		return dp;
	}

}
