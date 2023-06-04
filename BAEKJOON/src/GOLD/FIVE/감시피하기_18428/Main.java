package GOLD.FIVE.감시피하기_18428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, cnt;
    static String result;
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
        if(num == 3){
            watch();
            return;
        }

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
            int y = teacher.get(i);
            int x = teacher.get(i+1);
            for(int d = 0; d < 4; d++){
                int ny = y;
                int nx = x;
                while(true){
                    ny += dy[d];
                    nx += dx[d];
                    if(ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] == 'O') break;
                    if(map[ny][nx] == 'S') return;
                }
            }
        }

        // 전부 다 돌았단것은 S를 만나지 않았단것
        System.out.println("YES");
        System.exit(0);
    }

}
