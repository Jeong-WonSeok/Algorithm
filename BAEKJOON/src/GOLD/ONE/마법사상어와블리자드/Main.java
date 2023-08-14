package GOLD.ONE.마법사상어와블리자드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dirIdx = {3, 2, 4, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            blizzard(d, s);
            while(true){
                if(!fourSame()) break;
                Boom();
            }
        }

        for(int i = 1 ; i <= n; i++){
            for(int k = 1; k <= n; k++){
                System.out.print(map[i][k]+" ");
            }
            System.out.println();
        }
        System.out.println();


    }

    private static void blizzard(int d, int s) {
        int x = (n / 2) +1;
        int y = (n / 2) +1;

        int rx = x;
        int ry = y;
        for(int i = 1 ; i <= s; i++){
            rx = rx + dx[d];
            ry = ry + dy[d];
            map[ry][rx] = 0;
        }
        for(int i = 1 ; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
        move(s);
    }

    private static void move(int s) {
        int x = n/2 + 1;
        int y = n/2 + 1;
        for(int j = 0 ; j < s;j++) {
            int cnt = 0;
            int cx = x;
            int cy = y;
            for (int i = 0; i < (n * 2) - 1; i++) {
                for (int t = 0; t < i / 2 + 1; t++) {
                    int nx = cx + dx[dirIdx[cnt]];
                    int ny = cy + dy[dirIdx[cnt]];
                    if (nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                    if (map[cy][cx] == 0 && !(cy == y && cx == x)) {
                        map[cy][cx] = map[ny][nx];
                        map[ny][nx] = 0;
                    }
                    cx = nx;
                    cy = ny;
                }
                cnt = (cnt + 1) % 4;
            }
            for(int i = 1 ; i <= n; i++){
                for(int k = 1; k <= n; k++){
                    System.out.print(map[i][k]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static boolean fourSame() {
        int valueCnt = 1;
        int cx = (n/2)+1;
        int cy = cx;
        int value = 0;
        int cnt = 0;
        for (int i = 0; i < (n * 2) - 1; i++) {
            for (int t = 0; t < i / 2 + 1; t++) {
                if(valueCnt >= 4 ) return true;
                if(value != map[cy][cx]){
                    valueCnt = 1;
                    value = map[cy][cx];
                }
                int nx = cx + dx[dirIdx[cnt]];
                int ny = cy + dy[dirIdx[cnt]];
                if (nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                cx = nx;
                cy = ny;
                valueCnt++;
            }
            cnt = (cnt + 1) % 4;
        }

        return false;
    }

    private static void Boom() {
        Queue<Integer> BoomList = new LinkedList<>();
        int valueCnt = 1;
        int cx = (n/2)+1;
        int cy = cx;
        int value = 0;
        int cnt = 0;
        int boomCnt=0;
        for (int i = 0; i < (n * 2) - 1; i++) {
            for (int t = 0; t < i / 2 + 1; t++) {
                if(value != map[cy][cx]){
                    if(valueCnt >= 4){
                        boomCnt += valueCnt;
                        while(!BoomList.isEmpty()){
                            int by = BoomList.poll();
                            int bx = BoomList.poll();
                            map[by][bx] = 0;
                        }
                    }
                    valueCnt = 1;
                    value = map[cy][cx];
                }
                int nx = cx + dx[dirIdx[cnt]];
                int ny = cy + dy[dirIdx[cnt]];
                if (nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
                BoomList.offer(cy);
                BoomList.offer(cx);
                cx = nx;
                cy = ny;
                valueCnt++;
            }
            cnt = (cnt + 1) % 4;
        }

        move(boomCnt);
    }
}
