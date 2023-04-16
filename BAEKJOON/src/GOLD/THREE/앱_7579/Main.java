package GOLD.THREE.앱_7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/7579

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;

        int[] memoryArr = new int[n];
        int[] costArr = new int[n];
        int[][] dp = new int[n][100001];


        st = new StringTokenizer(br.readLine());

        // 비용과 메모리 초기화부분
        for(int i = 0 ; i < n; i++){
            memoryArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            costArr[i] = Integer.parseInt(st.nextToken());
        }


        for(int i = 0 ; i < n; i++){
            int cost = costArr[i];
            int memory = memoryArr[i];


            for(int j = 0; j <= 10000; j++){
                // 앱이 하나일 경우 예외처리
                if(i == 0) {
                    if (j >= cost) dp[i][j] = memory;
                }
                else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j - cost] + memory, dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                // 필요한 메모리보다 커지는 코스트 중 가장 최소 정답
                if(dp[i][j] >= m) ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
    }

}
