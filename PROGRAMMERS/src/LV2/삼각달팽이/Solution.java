package LV2.삼각달팽이;


import java.util.Arrays;

public class Solution {

    public void main(String[] args){
        solution(4);
        solution(5);
        solution(6);
    }

    public int[] solution(int n) {
        int[] answer = {};
        int cnt = 1;

        int[][] snail = new int[n + 1][n + 1];
        int row=-1;
        int col=0;

        // 각 배열에 채울 값
        int curNum = 1;

        for(int i = n; i > 0; i-=3){
            for(int j = 0; j < i ; j++) {snail[++row][col] = curNum++;}
            for(int j = 0; j < i-1 ; j++) {snail[row][++col] = curNum++;}
            for(int j = 0; j < i-2 ; j++) {snail[--row][--col] = curNum++;}
        }
        for(int i = 0; i < n; i++)
            System.out.println(Arrays.toString(snail[i]));
        return answer;
    }
}
