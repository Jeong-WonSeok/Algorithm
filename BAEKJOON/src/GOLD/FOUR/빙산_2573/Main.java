package GOLD.FOUR.빙산_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 빙산은 1년에 주위에 빈칸이 있는 만큼 녹는다.
    // 빙산이 두 덩어리가 이상으로 분리되면 종료하고 그 시간을 구한다(다 녹을때까지 한 덩어리라면 0을 출력한다.)

    // 한번 반복할때마다 dfs를 사용하여 빙산이 두 덩어리 이상이 되는지 확인한다.
    // 녹을 때는 4방 탐색을 하여 녹을 양을 구한다.(녹는 양은 따로 배열로 저장한다.)

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

            //전체 맵을 탐색해 0이 아니거나 방문한적이 없으면 dfs를 돌리고 count를 높인다.
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(!visited[i][j] && map[i][j] != 0){
                        dfs(i,j);
                        count++;
                    }
                }
            }

            //count 개수가 0이거나 2이상이면 종료한다.
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

    // 녹을 때마다 방문 기록은 초기화시켜준다.
    private static void melt() {

        for(int i = 0; i< N; i++){
            for(int j = 0 ; j < M; j++){
                // melt에 있는 값만큼 map을 줄인다.
                map[i][j] -= melt[i][j];

                // 뺀 값이 0보다 작으면 0으로 만들어 준다.
                if(map[i][j] < 0)
                    map[i][j] = 0;

                // 방문기록, 녹을 양을 초기화한다.
                visited[i][j] = false;
                melt[i][j] = 0;
            }
        }
    }

    // dfs
    private static void dfs(int r, int c) {
        visited[r][c] = true;

        for(int i = 0 ; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < N && 0 <= nc && nc < M){
            // 상 하 좌 우를 확인할때 0인 값이 있으면 melt에 +1 을 한다.
                if(map[nr][nc] == 0)
                    melt[r][c]++;

                if(!visited[nr][nc] && map[nr][nc] != 0)
                    dfs(nr, nc);

            }
        }
    }

}
