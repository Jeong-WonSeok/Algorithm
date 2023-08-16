package GOLD.FIVE.수고르기_2230;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        Integer minDiff = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while(right < n){
            int diff = arr[right] - arr[left];
            if(diff > m){
                minDiff = Math.min(minDiff, diff);
                left++;
            }else if( diff < m) {
                right++;
            }else {
                minDiff = diff;
                break;
            }

        }
        System.out.println(minDiff);
    }
}

