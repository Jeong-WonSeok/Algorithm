package GOLD.TWO.케이크자르기2_10714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static long[] arr;
    static long[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp = new long[n+1][n+1];
        for(int i = 0; i < n+1; i++){
            Arrays.fill(dp[i], -1);
        }
        long answer = 0;
        for(int i=0; i < n; i++){
            answer = Math.max(answer, arr[i] + ioi(i, i));
        }
        System.out.println(answer);

    }

    static long joi(int l, int r){
        // 왼쪽과 오른쪽이 만난다면 dp의 값은 0으로 저장
        if( goRight(r) == l) return dp[l][r] = 0;

        // 이미 갱신이 된 상태라면
        if (dp[l][r] != -1)    return dp[l][r];

        // 왼쪽으로 진행했을 때의 값과 오른쪽으로 진행했을 때의 값을 구함
        long leftSum = arr[goLeft(l)] + ioi(goLeft(l), r);
        long rightSum = arr[goRight(r)] + ioi(l, goRight(r));

        // 위의 경우 중 큰 값을 dp에 저장하고 return 한다.
        return dp[l][r] = Math.max(leftSum, rightSum);

    }

    // ioi의 값은 구하지 않기 때문에 따로 저장하지 않는다.
    private static long ioi(int l, int r) {
        // 왼쪽과 오른쪽이 만난다면 return 0
        if(goRight(r) == l) return 0;

        // 왼쪽 값이 오른쪽값보다 더 크다면 왼쪽으로 진행
        if(arr[goLeft(l)] > arr[goRight(r)]) return joi(goLeft(l), r);

        // 아니면 오른쪽으로 진행
        return joi(l, goRight(r));
    }

    private static int goLeft(int l) {
        return (l + n - 1) % n;
    }

    private static int goRight(int r) {
        return (r + 1) % n;
    }

}
