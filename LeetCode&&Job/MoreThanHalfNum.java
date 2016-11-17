/**
 * 
 * @author Lael
 * @date  2016/11/17
 * @describe 输入一个数组，求出这个数组里面出现次数超过一半的那个数字
 * 
 * input:   {12,3,4,3,3,3,3,23,34,5,56,3,3,41,23,3,67,3,3,89,3,12,3,3,3,10};
 * output:  3
 * 
 * 考虑在遍历数组的时候保存两个值，一个是数组中的值，一个是次数
 * 当遍历到下一个数字的时候，如果和之前保存的数字相同，则次数加1
 * 如果下一个数字和之前保存的数字不同，则次数减1
 * 如果次数为零，需要保存下一个数字，并把次数设为1
 * ----由于要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设置为1是对应的数字---
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
