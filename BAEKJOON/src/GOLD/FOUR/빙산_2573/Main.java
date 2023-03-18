package GOLD.FOUR.빙산_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] melt;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        melt = new int[N][M];
        map = new int[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                int t = Integer.parseInt(st.nextToken());
                map[i][j] = t;
            }
        }

        int year = 0;

        while(true){
            int count = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(!visited[i][j] && map[i][j] != 0){
                        dfs(i,j);
                        count++;
                    }
                }
            }
            if(count == 0){
                System.out.println(0);
                break;
            }else if(count >= 2){
                System.out.println(year);
                break;
            }

            melt();
            year++;
        }


    }

    private static void melt() {
        for(int i = 0; i< N; i++){
            for(int j = 0 ; j < M; j++){
                map[i][j] -= melt[i][j];

                if(map[i][j] < 0)
                    map[i][j] = 0;

                visited[i][j] = false;
                melt[i][j] = 0;
            }
        }
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        for(int i = 0 ; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nr < N && 0 <= nc && nc < M){
                if(map[nr][nc] == 0)
                    melt[r][c]++;

                if(!visited[nr][nc] && map[nr][nc] != 0)
                    dfs(nr, nc);

            }
        }
    }

}
