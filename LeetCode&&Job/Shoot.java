//package puhuiFinance;

import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Lael
 * @date 2016/10/20 * һ������˶�Ա��У���һ����10��������10ǹ����90���Ŀ������ж����֣� *
 *       ���õݹ��㷨���ʵ�֡�[�й�ĳ����ͨ����ҵH������]
 *       �ջݽ��ڱ�����Ŀ2  
 *       ���92378������
 */

public class Shoot {
	static int time = 10;
	static String str = "";

	public static void main(String[] args) {
		// ��ǹ������
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
			// ÿһ��������Եõ��ķ���Ϊ0---10
			for (int i = 0; i < 11; i++) {
				// �����ǰ�����ܻ�õ���������ȥ�Ѿ���õķ������Լ����λ�õķ���
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
