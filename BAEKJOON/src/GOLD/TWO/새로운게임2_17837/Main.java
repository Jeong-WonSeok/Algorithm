package GOLD.TWO.새로운게임2_17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static boolean isEnd;
    static int[] dx = {0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    // 흰:0, 빨:1, 파:2
    static int[][] map,count;
    static ArrayList<chessPiece> index;
    static chessPiece[][] piece;
    static class chessPiece{
        int x, y;
        int dir, idx;

        public chessPiece(int x, int y, int dir, int idx) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        isEnd = false;
        map = new int[N][N];
        count = new int[N][N];
        index = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            count[y][x]++;
            index.add(new chessPiece(x, y, dir, count[y][x]));
        }

        int turn = 0;
        while(true){
            turn++;
            for(int i = 0; i < K; i++){
                chessPiece cur = index.get(i);
                move(cur.x, cur.y, cur.dir, cur.idx);
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] >= 4){
                        System.out.println(turn);
                        System.exit(0);
                    }
                }
            }
            if(turn > 1000) {
                turn = -1;
                break;
            }
        }

    }
    
    
    // x, y 현재 위치
    // dir 방향
    // idx 이동하는 첫번째
    private static void move(int x, int y, int dir, int idx) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] == 2){
            Blue(x, y, dir, idx);
        }else if(map[ny][nx] == 1){
            Red(x, y, dir, idx);
        }else {
            White(x, y, dir, idx);
        }
    }

    private static void White(int x, int y, int dir, int idx) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int cnt = 0;
        for(int i = 0 ; i < K; i++){
            int curIdx = index.get(i).idx;
            int cx = index.get(i).x;
            int cy = index.get(i).y;
            int cDir = index.get(i).dir;
            // 만약 검색한 인덱스가 현재 인덱스보다 크거나 같으면 이동할 차례
            if(x == cx && y == cy && curIdx >= idx){
                // 이동할 곳에 +되는 인덱스
                int tempIdx = curIdx - idx + 1;
                cnt++;
                index.set(i, new chessPiece(nx, ny, cDir, map[ny][nx] + tempIdx));
                map[ny][nx]++;
            }
        }
        //현재 위치 개수 초기화
        map[y][x] -= cnt;
    }

    private static void Red(int x, int y, int dir, int idx) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int cnt = 0;
        Queue<chessPiece> que = new LinkedList<>();
        Queue<Integer> number = new LinkedList<>();
        for(int i = 0; i < K; i++){
            int curIdx = index.get(i).idx;
            int cx = index.get(i).x;
            int cy = index.get(i).y;
            int cDir = index.get(i).dir;

            if(x == cx && y == cy && curIdx >= idx){
                que.offer(new chessPiece(cx, cy, cDir, curIdx));
                number.offer(i);
                cnt++;
            }
        }
        while(!que.isEmpty()){
            chessPiece temp = que.poll();
            int tempNum = number.poll();
            int tempIdx = cnt - temp.idx;
            index.set(tempNum, new chessPiece(nx, ny, temp.dir, map[ny][nx] + tempIdx));
        }
        map[y][x] -= cnt;
        map[ny][nx] += cnt;

    }

    private static void Blue(int x, int y, int dir, int idx) {
        if(dir == 1 || dir == 3){
            dir = dir + 1;
        }else if(dir == 2 || dir == 4){
            dir = dir - 1;
        }
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] == 2){
            Blue(x, y, dir, idx);
        }else if(map[ny][nx] == 1){
            Red(x, y, dir, idx);
        }else {
            White(x, y, dir, idx);
        }
    }


}
