package GOLD.THREE.원판돌리기_17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, T;
    static int[][] map;
    static int[][] xdk;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Map<Integer, Deque<Integer>> stencil = new HashMap<>();

        for(int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            stencil.put(i, new ArrayDeque<>());
            for( int j = 0; j < M; j++){
                int temp = Integer.parseInt(st.nextToken());
                map[i][j] = temp;
                stencil.get(i).add(temp);
            }
        }

        xdk = new int[T][3];
        for(int i = 0 ; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            xdk[i][0] = Integer.parseInt(st.nextToken());
            xdk[i][1] = Integer.parseInt(st.nextToken());
            xdk[i][2] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i < N;i++){
            System.out.println(stencil.get(i).toString());
        }
        System.out.println();

        for(int t = 0 ; t < T; t++) {
            
            for (int i = xdk[t][0]-1; i < N; i = i + xdk[t][0]) {
                System.out.println("x: " + i);
                // d=0 이면 시계방향으로 회전시킨다.
                if (xdk[t][1] == 1) {
                    //k만큼 회전 시킨다.
                    for (int j = 0; j < xdk[t][2]; j++) {
                        //가장 앞에있는 숫자를 뽑아 뒤로 넣어준다.
                        int num = stencil.get(i).pollFirst();
                        stencil.get(i).addLast(num);
                    }
                }
                // d=1 이면 반시계방향으로 회전시킨다.
                else {
                    System.out.println(stencil.get(i).toString());
                    for (int j = 0; j < xdk[t][2]; j++) {
                        System.out.println(j);
                        //가장 뒤에 있는 숫자를 앞으로 보낸다.
                        int num = stencil.get(i).pollLast();
                        System.out.println("넘어옴");
                        stencil.get(i).addFirst(num);
                        System.out.println(stencil.get(i).toString());
                    }
                }
                System.out.println("11111111111111");
            }

            // 회전을 다 시키면 map을 업데이트 시킨다.
            for(int i = xdk[t][0]-1 ; i < N; i = i + xdk[t][0]){
                for(int j = 0; j < M; j++){
                    int  temp = stencil.get(i).pollFirst();
                    map[i][j] = temp;
                    stencil.get(i).addLast(temp);
                }
            }

            System.out.println(t+"/////////////////");
            System.out.println("deque");
            for(int i = 0 ; i < N; i++){
                System.out.println(stencil.get(i).toString());
            }
            System.out.println();
            for(int i = 0 ; i < N; i++){
                System.out.println(Arrays.toString(map[i]));
            }
            delete();

            System.out.println();

            for(int i = 0 ; i < N; i++){
                stencil.get(i).clear();
                for(int j = 0 ; j < M; j++){
                    stencil.get(i).add(map[i][j]);
                }
            }

        }
        int sum = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                sum += map[i][j];
            }
        }

        System.out.println(sum);

    }

    private static void delete() {
        int[][] clone = new int[N][M];
        for(int i = 0; i < N; i++) {
            clone[i] = map[i].clone();
        }

        boolean check = false;
        float sum = 0;
        float cnt = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                int r = i;
                int c = j;
                int num = map[r][c];
                sum += map[r][c];
                if( map[r][c] != 0) cnt++;

                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= N) continue;

                    if(nc < 0){
                        nc = M-1;
                    }else if( nc >= M){
                        nc = 0;
                    }

                    if(num == map[nr][nc] && num != 0){
                        System.out.println(r + " " + c + " " + map[r][c]);
                        System.out.println("next " + nr + " " + nc + " " + map[nr][nc]);
                        check = true;
                        clone[nr][nc] = 0;
                        clone[r][c] = 0;
                    }

                }
            }
        }

        if(!check){
            float avg = sum / cnt;
            for(int i = 0 ; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(clone[i][j] != 0) {
                        if (clone[i][j] < avg) clone[i][j]++;
                        else if (clone[i][j] > avg) clone[i][j]--;
                    }
                }
            }
        }

        for(int i = 0 ; i < N; i++){
            System.out.println(Arrays.toString(clone[i]));
            map[i] = clone[i].clone();
        }
    }
}
