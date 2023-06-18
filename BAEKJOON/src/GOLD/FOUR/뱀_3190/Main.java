package GOLD.FOUR.뱀_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, k, l, dir;
    static int[][] map;
    static boolean[][] snakeMap;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static Deque<Integer> snake;
    static Queue<String> turn;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n][n];
        snakeMap = new boolean[n][n];
        snake = new LinkedList<>();

        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y-1][x-1] = 1;
        }

        l = Integer.parseInt(br.readLine());
        turn = new LinkedList<>();
        for(int i = 0 ; i < l; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            String dir = st.nextToken();
            turn.offer(num);
            turn.offer(dir);
        }


        int cnt = 0;
        // 첫번째 방향
        dir = 0;
        // 뱀의 첫번째 위치 0, 0
        int y = 0;
        int x = 0;

        snake.offer(y);
        snake.offer(x);
        snakeMap[0][0] = true;

        while(true){
            int turnS = turn.isEmpty() ? -1 : Integer.parseInt(turn.peek());
            // 회전
//            System.out.println("현재 뱀 머리 : " + y + " " + x);
//            System.out.println("cnt :" + cnt);
            if(turnS == cnt){
//                System.out.println("*******************");
                turn.poll();
                char cDir = turn.poll().charAt(0);
                if(cDir == 'D'){
                    dir = (dir == 0) ? 3 : (dir - 1);
                }else{
                    dir = (dir + 1) % 4;
                }
            }
            y = y + dy[dir];
            x = x + dx[dir];

            if(!move(y,x)) break;
//            for(int i = 0; i < n; i++){
//                System.out.println(Arrays.toString(snakeMap[i]));
//            }
            cnt++;
        }

        System.out.println(cnt+1);

    }

    private static boolean move(int ny, int nx) {
        if(ny < 0 || ny >= n || nx < 0 || nx >= n) return false;
        // 사과가 있을 경우 => 절대 게임이 끝나지 않음
        int taleY = snake.poll();
        int taleX = snake.poll();
//        System.out.println("head " + ny + " " + nx );
//        System.out.println("tale " + taleY + " " + taleY);
//        System.out.println(snake.toString());
        if(map[ny][nx] == 1){
            snake.offerFirst(taleX);
            snake.offerFirst(taleY);
            snake.offer(ny);
            snake.offer(nx);
            map[ny][nx] = 0;
            snakeMap[ny][nx] = true;
            return true;
        }
        // 자기 몸을 만난다면
        if(snakeMap[ny][nx]){
//            System.out.println("만났다");
            return false;
        }
        else{
            snakeMap[taleY][taleX] = false;
            snake.offer(ny);
            snake.offer(nx);
            snakeMap[ny][nx] = true;
        }

        //꼬리에 추가
        return true;
    }
}
