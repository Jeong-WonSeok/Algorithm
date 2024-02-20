package COS_PRO_1차.문제02;// You may use import as below.
//import java.util.*;

import java.util.Arrays;

class Solution {
    public int solution(int n) {
        // 이렇게 풀어도 되고 규칙 만들어서 풀어도 됨
        int[][] arr = new int[n][n];
        int i = 0, j = 0;
        int dir = 0; // dir : 0 -> → , dir : 1 -> ↓, dir : 2 -> ←, dir : 3 -> ↑
        int num = 1;

        while(true) {
            arr[i][j] = num;

            if(dir == 0 && (j == n-1 || (j < n-1 && arr[i][j+1] != 0 ))) dir++;
            else if(dir == 1 && (i == n-1 || (i < n-1 && arr[i+1][j] != 0))) dir++;
            else if(dir == 2 && (j == 0 || (j > 0 && arr[i][j-1] != 0))) dir++;
            else if(dir == 3 && (i == 0 || (i > 0 && arr[i-1][j] != 0))) dir = 0;

            if(dir == 0) j++;
            else if(dir == 1) i++;
            else if(dir == 2) j--;
            else if(dir == 3) i--;

            if(num == (int)Math.pow(n, 2)) break;
            num++;
        }

        int answer = 0;
        i = 0;

        while(i <= n-1) {
            answer += arr[i][i]; i++;
        }
        for(int t = 0; t < n; t++){
            System.out.println(Arrays.toString(arr[t]));
        }
        return answer;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n1 = 8;
        int ret1 = sol.solution(n1);

        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret1 + " .");
        
        int n2 = 2;
        int ret2 = sol.solution(n2);
        
        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret2 + " .");
    }
}