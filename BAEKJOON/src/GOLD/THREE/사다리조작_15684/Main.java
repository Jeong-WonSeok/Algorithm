package GOLD.THREE.사다리조작_15684;

import java.io.*;
import java.util.*;


public class Main {

    static int N, M, H;
    static int[][] map;
    static int[] dr = {0, 0, -1};
    static int[] dc = {1, -1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H+2][2*N - 1];

        for(int i = 1; i < H+1; i++){
            for(int j = 0; j < 2*N - 1; j++ ){
                if(j % 2 == 0) map[i][j] = 1;
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c*2 - 1] = 2;
        }

        for(int i = 0; i < H+2; i++){
            System.out.println(Arrays.toString(map[i]));
        }

    }
}
