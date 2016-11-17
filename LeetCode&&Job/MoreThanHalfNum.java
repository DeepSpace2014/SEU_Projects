/**
 * 
 * @author Lael
 * @date  2016/11/17
 * @describe ����һ�����飬����������������ִ�������һ����Ǹ�����
 * 
 * input:   {12,3,4,3,3,3,3,23,34,5,56,3,3,41,23,3,67,3,3,89,3,12,3,3,3,10};
 * output:  3
 * 
 * �����ڱ��������ʱ�򱣴�����ֵ��һ���������е�ֵ��һ���Ǵ���
 * ����������һ�����ֵ�ʱ�������֮ǰ�����������ͬ���������1
 * �����һ�����ֺ�֮ǰ��������ֲ�ͬ���������1
 * �������Ϊ�㣬��Ҫ������һ�����֣����Ѵ�����Ϊ1
 * ----����Ҫ�ҵ����ֳ��ֵĴ����������������ֳ��ֵĴ���֮�ͻ�Ҫ�࣬��ôҪ�ҵ����ֿ϶������һ�ΰѴ�������Ϊ1�Ƕ�Ӧ������---
 */
public class MoreThanHalfNum {
	public static void main(String[] args){
	int[] arr = {12,3,4,3,3,3,3,23,34,5,56,3,3,41,23,3,67,3,3,89,3,12,3,3,3,10};
	int res = getResult(arr);
	System.out.println(res);
	}
	
	public static int getResult(int[] arr){
		if(arr.length == 0 || arr == null){
			return 0;
		}
		int cur = arr[0]; 
		int times = 1;
		for(int i = 0; i < arr.length; i++){
			if(times == 0){
				cur = arr[i];
				times = 1;
			}else if(cur == arr[i]){
				times++;
			}else
				times--;
		}
		return cur;
	}
}
