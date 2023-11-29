package COS_PRO_1차.문제01;
// You may use import as below.
//import java.util.*;

class Solution {
    public long solution(long num) {
        // Write code here.
        long answer = 0;
        num++;
        int cnt = 0;
        while(num / 10 != 0){

            long div = num / 10;
            long remain = num % 10;

            answer += Math.pow(10, cnt) * remain;
            num /= 10;
            cnt++;
        }
        return answer;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
        Solution sol = new Solution();
        long num = 9949999;
        long ret = sol.solution(num);

        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret + " .");
    }
}