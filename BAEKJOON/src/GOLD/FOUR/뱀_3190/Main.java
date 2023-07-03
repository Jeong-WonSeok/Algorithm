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
            // turn 횟수를
            // 만약 더 이상 방향전환이 없다면 -1 입력
            int turnS = turn.isEmpty() ? -1 : Integer.parseInt(turn.peek());
            // 회전

            // 만약 cnt 와 방향 전환하는 턴이 같다면 방향 전환
            if(turnS == cnt){

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
        // 사과를 발견했을 때 꼬리를 추가(위에서 poll을 했기 때문에)
        // 머리를 추가(사과를 먹었기 때문에)
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
        // 위의 경우들이 아니면 그냥 한칸 진행
        // 꼬리를 없애고 머리를 추가
        else{
            snakeMap[taleY][taleX] = false;
            snake.offer(ny);
            snake.offer(nx);
            snakeMap[ny][nx] = true;
        }

        return true;
    }
}
