package GOLD.FOUR.미세먼지안녕_17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int top, bot;
    static int[][] map;
    static Queue<Integer> dustLoc;
    static int[] dr = {0 , 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dustLoc = new LinkedList<>();

        for(int i = 0 ; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    if(top == 0){
                        top = i;
                    }else{
                        bot = i;
                    }
                }
            }
        }

        for(int i = 0; i < T; i++){
            dustSpread();

            rotate();
        }

        int result = 0;
        for(int i = 0 ; i < R; i++){
            for(int j = 0; j < C; j++){
                result += map[i][j];
            }
        }

        System.out.println(result + 2);

    }

    private static void dustSpread() {
        // 큐에 미세먼지 위치 넣기
        for(int i = 0 ; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == -1 || map[i][j] == 0) continue;
                dustLoc.offer(i);
                dustLoc.offer(j);
            }
        }


        int tempMap[][] = new int[R][C];
        tempMap[top][0] = -1;
        tempMap[bot][0] = -1;

        while(!dustLoc.isEmpty()){
            int cr = dustLoc.poll();
            int cc = dustLoc.poll();
            int dust = map[cr][cc];
            int cnt = 0;
            for(int i = 0 ; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1) continue;

                tempMap[nr][nc] += dust/5;
                cnt++;
            }
            tempMap[cr][cc] += (dust - ((dust/5)*cnt));
        }

        for(int i = 0; i < R; i++){
            map[i] = tempMap[i].clone();
        }
    }

    private static void rotate() {
        // 윗지역 순환
        // 아래로 이동
        for(int i = top - 1; i > 0; i--) map[i][0] = map[i-1][0];
        // 왼쪽으로 이동
        for(int i = 0; i < C -1; i++) map[0][i] = map[0][i+1];
        // 위으로 이동
        for(int i = 0; i < top; i++) map[i][C - 1] = map[i+1][C-1];
        // 오른쪽으로 이동
        for(int i = C-1; i > 1; i--) map[top][i] = map[top][i-1];
        map[top][1] = 0;
        // 아랫지역 순환
        // 위로 이동
        for(int i = bot + 1; i < R-1; i++ ) map[i][0] = map[i+1][0];
        // 왼쪽으로 이동
        for(int i = 0; i < C-1; i++) map[R-1][i] = map[R-1][i+1];
        // 아래로 이동
        for(int i = R-1; i > bot; i--) map[i][C-1] = map[i-1][C-1];
        // 오른쪽으로 이동
        for(int i = C-1; i > 1; i--) map[bot][i] = map[bot][i-1];
        map[bot][1] = 0;

    }

}
