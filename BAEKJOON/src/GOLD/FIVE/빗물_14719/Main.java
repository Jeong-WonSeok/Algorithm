package GOLD.FIVE.빗물_14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] height = new int[w];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        //첫, 마지막 제외
        for(int i = 1; i < w - 1; i++) {
            int left = 0;
            int right = 0;

            // 현재 위치 기준 왼쪽에서 가장 높은 위치 찾기
            for(int j = 0; j < i; j++) {
                left = Math.max(height[j], left);
            }

            // 현재 위치 기준 오른쪽에서 가장 높은 위치 찾기
            for(int j = i + 1; j < w;  j++) {
                right = Math.max(height[j], right);
            }

            // 높이가 왼쪽과 오른쪽 보다 낮다면 낮은 위치 - 현재위치로 빗물을 체크한다.
            if(height[i] < left && height[i] < right) result += Math.min(left, right) - height[i];
        }
        System.out.println(result);
    }
}
