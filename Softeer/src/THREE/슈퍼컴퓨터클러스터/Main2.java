package THREE.슈퍼컴퓨터클러스터;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        long B = Long.parseLong(str[1]);
        str = br.readLine().split(" ");
        int[] comList = new int[N];
        for (int i = 0; i < N; i++) {
            comList[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(comList);

        // 이분탐색 진행
        long left = comList[0];
        long right = comList[N - 1] + (long)Math.sqrt(B);
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (calculate(mid, comList, B)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    //cost 계산 비용 초과하면 false 아니면 true
    public static boolean calculate(long min, int[] comList, long B) {
        long cost = 0;
        for (int i : comList) {
            if (i < min) {
                cost += (min - i) * (min - i);
                if (cost > B) return false;
            }
        }
        return true;
    }
}