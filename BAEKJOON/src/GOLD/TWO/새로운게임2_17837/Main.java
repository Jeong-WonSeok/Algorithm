package GOLD.TWO.새로운게임2_17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean isEnd;
    static int[] dx = {0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    // 흰:0, 빨:1, 파:2
    static int[][] map;
    static chessPiece[] index;
    static Deque<chessPiece>[][] piece;
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
        piece = new Deque[N][N];
        index = new chessPiece[K];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                piece[i][j] = new ArrayDeque<>();
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            piece[y][x].offerFirst(new chessPiece(x,y,dir,i));
            index[i] = new chessPiece(x,y,dir,i);
        }
        boolean isEnd = false;
        int turn = 0;
        while(true){
            turn++;
            for(int i = 0; i < K; i++){
                isEnd = move(i);
            }
            for(int i = 0; i < K; i++){
                System.out.println("idx " + i + " x " + index[i].x + " y " +index[i].y);
            }
            System.out.println(isEnd);
            if(isEnd) {
                break;
            }
            if(turn >= 10) {
                turn = -1;
                break;
            }
        }

        System.out.println(turn);

    }

    static private boolean move(int idx){
        chessPiece cur = index[idx];
        int dir = cur.dir;
        int ny = cur.y + dy[dir];
        int nx = cur.x + dx[dir];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
            goBlue(cur, nx, ny, idx);
            return false;
        }

        if(map[ny][nx] == 0){
            return goWhite(cur, nx, ny, idx);
        }else if(map[ny][nx] == 1){
            return goRed(cur, nx, ny, idx);
        }else{
            goBlue(cur, nx, ny, idx);
        }
        return false;
    }

    // 이동하는 곳이 빨간색일 경우
    // 이동한 후 이동한 말과 그 위의 말의 순서를 반대로 바꾼다.
    static private boolean goRed(chessPiece cur, int nx, int ny, int idx){
        int cx = cur.x;
        int cy = cur.y;

        //현재 인덱스의 차례가 될 때까지만 진행
        int size = piece[cy][cx].size();
        loop : for(int i = 0; i < size; i++) {
            chessPiece temp = piece[cy][cx].pollLast();
            if(temp.idx == idx ) {
                piece[ny][nx].offer(new chessPiece(nx, ny, cur.dir, temp.idx));
                index[temp.idx] = new chessPiece(nx,ny, cur.dir, temp.idx);

                break loop;
            }
            piece[ny][nx].offer(new chessPiece(nx, ny, temp.dir, temp.idx));
            index[temp.idx] = new chessPiece(nx,ny, temp.dir, temp.idx);
        }

        if(piece[ny][nx].size() >= 4) {
            return true;
        }

        return false;
    }

    // 이동하는 곳이 흰색일 경우
    // 이동한 후 가장 위에 이동한 말을 놓는다.
    static private boolean goWhite(chessPiece cur, int nx, int ny, int idx){
        int cx = cur.x;
        int cy = cur.y;
        //현재 인덱스의 차례가 될 때까지 진행X
        boolean check = false;
        int size = piece[cy][cx].size();
        for(int i = 0; i < size; i++) {
            chessPiece temp = piece[cy][cx].pollFirst();
            if(temp.idx == idx ) {
                piece[ny][nx].offer(new chessPiece(nx, ny, cur.dir, temp.idx));
                index[temp.idx] = new chessPiece(nx,ny, cur.dir, temp.idx);
                check = true;
                continue;
            }
            if(!check) {
                piece[cy][cx].offer(temp);
                continue;
            }
            piece[ny][nx].offer(new chessPiece(nx, ny, temp.dir, temp.idx));
            index[temp.idx] = new chessPiece(nx,ny, temp.dir, temp.idx);
        }

        if(piece[ny][nx].size() >= 4) {
            return true;
        }

        return false;
    }

    // 이동하는 곳이 파란색, 경계 밖일 경우
    // 반대방향으로 한칸 이동한다.(만약 바꾸고도 파란색일 경우 이동 하지 않는다.)
    static private void goBlue(chessPiece cur, int nx, int ny, int idx){
        int curDir = 0;
        if(cur.dir == 1 || cur.dir == 3) curDir = cur.dir + 1;
        else curDir = cur.dir - 1;

        ny = cur.y + dy[curDir];
        nx = cur.x + dx[curDir];
        if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] == 2) return;

        if(map[ny][nx] == 0){
            goWhite(new chessPiece(cur.x, cur.y, curDir, idx), nx, ny, idx);
        }else {
            goRed(new chessPiece(cur.x, cur.y, curDir, idx), nx, ny, idx);
        }
    }


}
