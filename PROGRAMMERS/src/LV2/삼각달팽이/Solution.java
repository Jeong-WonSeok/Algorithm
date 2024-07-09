package LV2.삼각달팽이;


import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args){
        solution(4);
        System.out.println();
        solution(5);
        System.out.println();
        solution(6);
    }

    public static int[] solution(int n) {
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

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i <= n; i++)
            for(int j = 0; j <= n; j++)
                if(snail[i][j] != 0) ans.add(snail[i][j]);

        int[] answer = new int[ans.size()];
        for(int i = 0 ; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
