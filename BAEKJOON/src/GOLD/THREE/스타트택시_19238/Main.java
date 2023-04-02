package GOLD.THREE.스타트택시_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, fuel, taxiR, taxiC;
    static int[][] map;
    static Passenger[] passList;
    static int[] dr = {0, 1, -1, 0};
    static int[] dc = {-1, 0, 0, 1};

    static boolean[] Checked;

    static class Passenger{
        int startR, startC, endR, endC;

        public Passenger(int startR, int startC, int endR, int endC) {
            this.startR = startR;
            this.startC = startC;
            this.endR = endR;
            this.endC = endC;
        }


        @Override
        public String toString() {
            return "Passenger{" +
                    "startR=" + startR +
                    ", startC=" + startC +
                    ", endR=" + endR +
                    ", endC=" + endC +
                    '}';
        }
    }

    //문제 먼지 암 만약 도착과 출발이 같을 때 (내가 짠 코드의 경우는 도착 때 그것을 지운다 하지만 이러면
    // 출발 지점이 없어질수 있다
    // 따라서 해결책 Check배열을 만들어 해결한다.


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        Checked = new boolean[M];

        for(int i = 0 ; i < N; i++){
            String[] temp = br.readLine().split(" ");

            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }

        }

        st = new StringTokenizer(br.readLine());

        taxiR = Integer.parseInt(st.nextToken()) - 1;
        taxiC = Integer.parseInt(st.nextToken()) - 1;

        passList = new Passenger[M];

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int sR = Integer.parseInt(st.nextToken()) - 1;
            int sC = Integer.parseInt(st.nextToken()) - 1;
            int eR = Integer.parseInt(st.nextToken()) - 1;
            int eC = Integer.parseInt(st.nextToken()) - 1;
//            map[sR][sC] = i+2;
            passList[i] = new Passenger(sR, sC, eR, eC);

        }

//        for(int i= 0 ; i < N; i++){
//            System.out.println(Arrays.toString(map[i]));
//        }

        for(int i = 0 ; i < M; i++){

            int passenger = SearchPassenger(taxiR, taxiC);
            if(passenger < 0) {
                fuel = -1;
                break;
            }

            int r = passList[passenger].startR;
            int c = passList[passenger].startC;
            map[r][c] = 0;
            System.out.println("passenger : " + passenger);

            int end = goEnd(taxiR, taxiC, passList[passenger].endR, passList[passenger].endC);
            System.out.println("end : " + end);
            System.out.println("endR : " + taxiR + " endC : " + taxiC);
            if(end == -1){
                fuel = -1;
                break;
            }

            System.out.println("fuel " + fuel);
        }

        System.out.println(fuel);

    }

    // taxiR과 taxiC를 기준으로 승객을 찾는다.
    private static int SearchPassenger(int tR, int tC){
        Queue<Integer> que = new LinkedList<>();

        boolean visited[][] = new boolean[N][N];
        visited[tR][tC] = true;
        que.offer(tR);
        que.offer(tC);
        int cnt = 0;
        for(int i = 0 ; i < passList.length; i++){
            if(tR == passList[i].startR && tC == passList[i].startC){
                taxiR = tR;
                taxiC = tC;
                return i;
            }
        }
        while(!que.isEmpty()){

            cnt++;
            boolean passengerCheck = false;
            int minR = Integer.MAX_VALUE;
            int minC = Integer.MAX_VALUE;
            int firstPassenger = 0;
            int size = que.size()/2;

            for(int i = 0; i < size; i++){
                int r = que.poll();
                int c = que.poll();
                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;

                    if(map[nr][nc] != 0){
//                        System.out.println("asdasdsadsadadsaas : " + map[nr][nc]+ " " + cnt);
                        if(cnt > fuel) return -1;
                        passengerCheck = true;
//                        minR = nr;
//                        minC = nc;
//                        firstPassenger = map[nr][nc];
                        if(minR > nr){
                            minR = nr;
                            minC = nc;
                            firstPassenger = map[nr][nc];
                        }else if(minR == nr){
                            if(minC > nc) {
                                minR = nr;
                                minC = nc;
                                firstPassenger = map[nr][nc];
                            }
                        }
                        continue;
                    }
                    visited[nr][nc] = true;
                    que.offer(nr);
                    que.offer(nc);
                }
            }

            if(passengerCheck) {
//                System.out.println(fuel);
                fuel -= cnt;
//                System.out.println(fuel);
                taxiR = minR;
                taxiC = minC;
                return firstPassenger - 2;
            }

        }

        return -2;
    }


    //bfs로 체크?
    private static int goEnd(int startR, int startC, int endR, int endC){
        Queue<Integer> que = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        que.offer(startR);
        que.offer(startC);

        if(startR == endR && startC == endC) return 1;
        int cnt = 0;
        System.out.println("startR " + startR + " startC " + startC);
        System.out.println("endR " + endR + " endC " + endC);
        while(!que.isEmpty()){

            cnt++;
//            System.out.println("한칸 더 갔음");
            int size = que.size()/2;
            for(int i = 0 ; i < size; i++){
                int r = que.poll();
                int c = que.poll();
                for(int d = 0 ; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;

//                    System.out.println("nr : " + nr + " nc : " + nc);

                    if(nr == endR && nc == endC) {
                        System.out.println("out " + cnt);
                        if(fuel >= cnt){
                            fuel = fuel - cnt + (cnt * 2);
                        }else{
                            return -1;
                        }
                        taxiR = endR;
                        taxiC = endC;
                        return 1;
                    }

                    visited[nr][nc] = true;
                    que.offer(nr);
                    que.offer(nc);
                }
            }
        }

        return -1;
    }
}
