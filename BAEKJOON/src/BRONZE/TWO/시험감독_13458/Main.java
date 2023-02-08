package BRONZE.TWO.시험감독_13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //https://www.acmicpc.net/problem/13458
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        long[] room = new long[n];

        for(int i = 0 ; i < n; i++){
            room[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] viewer = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        long sum = 0;
        for(int i = 0; i < n; i++) {
            long div = (room[i] - viewer[0]) / viewer[1];
            long mod = (room[i] - viewer[0]) % viewer[1];

            if((div > 0 && mod > 0) || (div == 0 && mod > 0)){
                sum += div + 2;
            }else if((div > 0 && mod == 0)){
                sum += div + 1;
            }else if(viewer[0] > room[i]){
                sum += 1;
            }
        }


        System.out.println(sum);



    }
}
