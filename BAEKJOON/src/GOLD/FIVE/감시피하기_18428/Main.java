package GOLD.FIVE.감시피하기_18428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, cnt;
    static char map[][];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> teacher;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        cnt = 0;
        teacher = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N;j++){
                char in = st.nextToken().charAt(0);
                map[i][j] = in;
                if(in == 'T') {
                    teacher.add(i);
                    teacher.add(j);
                }
            }
        }

        obstacleInstall(0);
        System.out.println("NO");
    }

    private static void obstacleInstall(int num) {
        // 장애물 개수가 3개가 되면 watch함수를 실행한다.
        if(num == 3){
            watch();
            return;
        }

        // 맵을 탐색하다가 X를 만나면 그 공간을 장애물로 만들고 재귀를 반복한다.
        for(int i =0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 'X'){
                    map[i][j] = 'O';
                    obstacleInstall(num + 1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    private static void watch() {
        for(int i = 0; i < teacher.size(); i = i + 2){
            // 선생님의 위치를 가져온다.
            int y = teacher.get(i);
            int x = teacher.get(i+1);
            // 4방향으로 감시를 진행한다.
            for(int d = 0; d < 4; d++){
                int ny = y;
                int nx = x;
                while(true){
                    ny += dy[d];
                    nx += dx[d];
                    // 경계를 벗어나거나 장애물을 만나면 반복문을 종료한다.
                    if(ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] == 'O') break;
                    // 학생을 만나면 안되므로 만난다면 return 시킨다.
                    if(map[ny][nx] == 'S') return;
                }
            }
        }

        // 전부 다 돌았단것은 S를 만나지 않았단 것
        System.out.println("YES");
        System.exit(0);
    }

}
