package THREE.징검다리;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int minStone = 0;
        int max = 100000001;
        int[] arr = new int[3001];
        for(int i = 0; i < n; i++){
            int curN = Integer.parseInt(st.nextToken());
            if(max > curN) {
                max = Math.min(max,curN);
                minStone = i;
            }
            arr[i] = curN;
        }
        int cntR = 1;
        int cntL = 1;

        for(int i = 0; i < n; i++ ){

        }


        System.out.println(cntR > cntL ? cntR : cntL);
    }


}
