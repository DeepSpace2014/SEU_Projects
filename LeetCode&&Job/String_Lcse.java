/**
 * 
 * @author Lael
 * @date  2016/11/16
 * @describe 输入两个字符串，找出这两个字符串的最长公共子序列
 * 
 * input:   1A2C3D4B56   B1D23CA45B6A
 * output:  123456 或者 12C4B6
 * 
 * 使用动态规划的思想,计算DP矩阵中的某个位置就是简单比较相关的3个位置的值而已，所以时间复杂度为O(1);
 * 动态规划表dp的大小为M*N,所以计算dp矩阵的时间复杂度为O(M*N).
 * 通过dp得到最长公共子序列的过程为O(M+N)，因为想做最多移动N个位置，向上最多移动M个位置
 * 所以总的时间复杂度为O(M*N)，额外空间复杂度为O(M*N)
 */
import java.util.Scanner;

public class String_Lcse {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		String str1 = scann.nextLine();
		String str2 = scann.nextLine();
		
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		
		//dp矩阵的最右下角的值代表str1整体和str2整体的最长公共子序列的长度。
		//通过整个dp矩阵的状态，可以得到最长公共子序列
		int[][] dp = getDP(ch1, ch2);
		//通过dp求解最长公共子序列的过程就是还原出当时如何求解dp的过程，来自哪个策略就吵哪个方向移动。
		int m = ch1.length - 1;
		int n = ch2.length - 1;
		char[] res = new char[dp[m][n]]; //生成一个含有最长子序列元素个数的数组
		int index = res.length - 1;
		//从最右下角元素开始依次递推: 上，左，左上查看是如何生成的DP矩阵，并将元素加入到现在的res数组中
		while(index >= 0){
			if(n > 0 && dp[m][n] == dp[m][n-1])
				n--;
			else if(m > 0 && dp[m][n] == dp[m-1][n])
				m--;
			else{
				//此时，就代表添加了新的公共字符
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
			//计算DP矩阵的第一列
			dp[i][0] = Math.max(dp[i-1][0], ch1[i] == ch2[0] ? 1 : 0);
		}
		
		for(int j = 1; j < ch2.length; j++){
			//计算DP矩阵的第一行
			dp[0][j] = Math.max(dp[0][j-1], ch1[0] == ch2[j] ? 1 : 0);
		}
		
		for(int i = 1; i < ch1.length; i++){
			for(int j = 1; j < ch2.length; j++){
				//dp[i][j]可能取值的三种情况
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(ch1[i] == ch2[j]){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
				}
			}
		}
		return dp;
	}

}
