package GOLD.FOUR.치즈_2636;


import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M, cheese;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cheese = 0;
        int time = 0;

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int in = Integer.parseInt(st.nextToken());
                map[i][j] = in;
                if(in == 1) cheese++;
            }
        }

        int cheeseCnt = 0;
        while(cheese != 0)
        {
            cheeseCnt = cheese;
            time++;
            visited = new boolean[N][M];
            bfs();
        }

        System.out.println(time);
        System.out.println(cheeseCnt);

    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        q.offer(0);
        visited[0][0] = true;

        while(!q.isEmpty()){
            int cr = q.poll();
            int cc = q.poll();

            for(int i = 0; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if(map[nr][nc] == 0){
                    q.offer(nr);
                    q.offer(nc);
                }else{
                    cheese--;
                    map[nr][nc] = 0;
                }
            }
        }

    }
}