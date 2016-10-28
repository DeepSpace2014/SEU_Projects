//package puhuiFinance;

import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Lael
 * @date 2016/10/20 * 一个射击运动员打靶，靶一共有10环，连开10枪打中90环的可能性有多少种？ *
 *       请用递归算法编程实现。[中国某著名通信企业H面试题]
 *       普惠金融笔试题目2  
 *       输出92378条数据
 */

public class Shoot {
	static int time = 10;
	static String str = "";

	public static void main(String[] args) {
		// 开枪的总数
		int boomCount = 0;
		Stack<Integer> st = new Stack<Integer>();
		getResult(st, time, boomCount);
		FileWriter writer;
		try {
			writer = new FileWriter("D:\\Test_Workspace\\JOB\\src\\puhuiFinance\\result.txt");
			writer.write(str + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	public static void getResult(Stack<Integer> st, int time, int boomCount) {
		if (time > 0) {
			// 每一次射击可以得到的分数为0---10
			for (int i = 0; i < 11; i++) {
				// 如果当前次数能获得的最大分数减去已经获得的分数，以及本次获得的分数
				if (10 * (11 - time) - boomCount - i <= 10) {
					st.push(i);
					getResult(st, time - 1, boomCount + i);
					st.pop();
				}
			}

		}

		else if (time == 0) {
			if (boomCount == 90) {
				for (int i : st) {
					System.out.print(i + " ");
					str += i + " ";
				}
				str += "\n";
			
				System.out.println();
			}
		}
	}
}
