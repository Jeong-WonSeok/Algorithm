package algo;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1, 1, 1, 1, 1}, 3));
		cnt = 0;
		System.out.println(solution(new int[] {4, 1, 2, 1}, 4));
		
	}
	
	static int cnt = 0 ;
	public static int solution(int[] numbers, int target) {
		System.out.println(Arrays.toString(numbers));
		
		dfs(numbers, 0, target, 0);
		return cnt;
	}
	
	public static void dfs(int[] numbers, int cur, int target, int sum) {
		if(cur == numbers.length) {
			if(sum == target) {
				cnt++;
			}
			return;
		}
		
		int curNum = numbers[cur];
		for(int i = 0; i < 2; i++) {
			curNum = -1 * curNum;
			dfs(numbers, cur+1, target, sum + curNum);
		}

	}
}
