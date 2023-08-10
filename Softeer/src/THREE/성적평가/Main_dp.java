package THREE.성적평가;

import java.util.*;
import java.io.*;


public class Main_dp
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] cnts, dp, nums, dnums = new int[N];
        for (int tc = 0; tc < 3; tc++) {
            st = new StringTokenizer(br.readLine());

            cnts = new int[1001];
            dp = new int[1001];
            nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                dnums[i] += nums[i];
                cnts[nums[i]]++;
            }

            dp[1000] = cnts[1000];
            for (int i = 999; i >= 0; i--)
                dp[i] = dp[i+1] + cnts[i];

            for (int i = 0; i < N; i++) {
                if (nums[i] == 1000) {
                    bw.append("1 ");
                    continue;
                }

                bw.append((dp[nums[i]+1] + 1) + " ");
            }
            bw.append("\n");
        }
        cnts = new int[3001];
        dp = new int[3001];
        for (int i = 0; i < N; i++)
            cnts[dnums[i]]++;

        dp[3000] = cnts[3000];
        for (int i = 2999; i >= 0; i--)
            dp[i] = dp[i+1] + cnts[i];

        for (int i = 0; i < N; i++) {
            if (dnums[i] == 3000) {
                bw.append("1 ");
                continue;
            }

            bw.append((dp[dnums[i]+1] + 1) + " ");
        }


        bw.flush();
    }
}

