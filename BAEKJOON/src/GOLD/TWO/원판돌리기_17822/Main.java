package GOLD.TWO.원판돌리기_17822;

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

        for(int t = 0 ; t < T; t++) {
            
            for (int i = xdk[t][0]-1; i < N; i = i + xdk[t][0]) {
                // d=1 이면 반시계방향으로 회전시킨다.
                if (xdk[t][1] == 1) {
                    //k만큼 회전 시킨다.
                    for (int j = 0; j < xdk[t][2]; j++) {
                        //가장 앞에있는 숫자를 뽑아 뒤로 넣어준다.
                        int num = stencil.get(i).pollFirst();
                        stencil.get(i).addLast(num);
                    }
                }
                // d=0 이면 시계방향으로 회전시킨다.
                else {
                    for (int j = 0; j < xdk[t][2]; j++) {
                        //가장 뒤에 있는 숫자를 앞으로 보낸다.
                        int num = stencil.get(i).pollLast();
                        stencil.get(i).addFirst(num);
                    }
                }
            }

            // 회전을 다 시키면 map을 업데이트 시킨다.
            for(int i = xdk[t][0]-1 ; i < N; i = i + xdk[t][0]){
                for(int j = 0; j < M; j++){
                    int  temp = stencil.get(i).pollFirst();
                    map[i][j] = temp;
                    stencil.get(i).addLast(temp);
                }
            }

            delete();


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

    // 삭제
    private static void delete() {
        int[][] clone = new int[N][M];
        // map을 복사하여 혹시 모를 변수를 차단한다.
        for(int i = 0; i < N; i++) {
            clone[i] = map[i].clone();
        }

        // 합과 카운트를 변수로 두고
        // 지워진적이 있음을 확인하기 위한 변수를 만든다.
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

                //4방 탐색
                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= N) continue;

                    // 만약 nc가 0보다 작아지면 M-1의 값으로 바꾸고
                    // nc가 M-1보다 커지면 0으로 값을 바꾼다.
                    if(nc < 0){
                        nc = M-1;
                    }else if( nc >= M){
                        nc = 0;
                    }

                    // 사방탐색중 같은 숫자가 있으면 그 위치를 0으로 바꿔준다.
                    if(num == map[nr][nc] && num != 0){
                        check = true;
                        clone[nr][nc] = 0;
                        clone[r][c] = 0;
                    }

                }
            }
        }

        // 만약 지워진 적이 없으면 평균을 구한다음
        // 평균보다 작으면 +1을 크면 -1을 해준다.
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
            map[i] = clone[i].clone();
        }
    }
}
