package GOLD.TWO.원판돌리기_17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2 {
    // N, M, fuel, 현재 택시 위치
    static int N, M, fuel, taxiR, taxiC;
    // map에 대한 정보
    static Location[][] map;
    // 승객에 대한 정보 리스트
    static Passenger[] passList;
    static int[] dr = {0, 1, -1, 0};
    static int[] dc = {-1, 0, 0, 1};

    // 승객이 탔는지에 대한 배열
    static boolean[] Checked;

    //승객의 출발위치와 도착위치를 알려주는 class
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
    // 현재위치를 출발지로 정한 승객과 도착지로 정한 승객, 택시가 지나갈수 있는 지 없는 지 알려주는 class
    static class Location {
        int startLoc, endLoc, num;

        public Location(int startLoc, int endLoc, int num) {
            this.startLoc = startLoc;
            this.endLoc = endLoc;
            this.num = num;
        }

        @Override
        public String toString() {
            return "{" + startLoc + endLoc + num +
                    '}';
        }
    }

    // 중간에 고려에 하야만한 경우
    // 1. 출발지와 도착지가 한 곳에 있을 경우(같은 승객)
    // 2. 출발지와 도착지가 한 곳에 있을 경우(다른 승객)
    // 3. 택시위치와 출발지가 같은 곳이 될 경우
    // 4. 같은 거리의 출발지가 여러개일 경우(행 -> 열 순으로 더 작은 출발지를 출발지로 선정)
    // 5. 중간에 갈 곳이 없어서(벽에 막혀서 ) bfs가 끝날 경우


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new Location[N][N];
        Checked = new boolean[M];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                map[i][j] = new Location(0,0,0);
                map[i][j].num = Integer.parseInt(temp[j]);
            }

        }

        st = new StringTokenizer(br.readLine());

        taxiR = Integer.parseInt(st.nextToken()) - 1;
        taxiC = Integer.parseInt(st.nextToken()) - 1;

        passList = new Passenger[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sR = Integer.parseInt(st.nextToken()) - 1;
            int sC = Integer.parseInt(st.nextToken()) - 1;
            int eR = Integer.parseInt(st.nextToken()) - 1;
            int eC = Integer.parseInt(st.nextToken()) - 1;
            map[sR][sC].startLoc = i+2;
            map[eR][eC].endLoc = i+2;
            passList[i] = new Passenger(sR, sC, eR, eC);
        }

        for(int i = 0 ; i < M; i++){

            // 현재 위치에서 출발지까지 bfs를 통하여 찾기
            // return 승객의 번호
            int passenger = SearchPassenger(taxiR, taxiC);

            // 만약 0보다 작으면 승객을 못찾거나 연료가 다 떨어진 것이므로 연료를 -1로 만든다.
            if(passenger < 0) {
                fuel = -1;
                break;
            }

            // 출발지에서 도착지로 bfs를 통하여 최단거리를 찾는다.
            int end = goEnd(taxiR, taxiC, passList[passenger].endR, passList[passenger].endC);

            // 만약 0보다 작으면 연료가 다 떨어진 것이므로 연료를 -1로 만든다.
            if(end == -1){
                fuel = -1;
                break;
            }
        }
        System.out.println(fuel);


    }

    // 승객을 찾는 알고리즘(bfs)
    private static int SearchPassenger(int r, int c){
        // 같은 거리의 출발지들을 저장할 Queue
        Queue<Integer> passengerList = new LinkedList<>();

        Queue<Integer> que = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        que.offer(r);
        que.offer(c);
        visited[r][c] = true;

        //택시가 전진하는데 필요한 연료 count
        int count = 0;

        //만약 현재 택시위치에 승객출발지가 있으면 bfs탐색전에 확정시키고 승객번호를 return 한다.
        //(승객번호에 체크가 안되어있어야한다.)
        for(int i = 0 ; i < passList.length; i++){

            if(r == passList[i].startR && c == passList[i].startC && !Checked[map[r][c].startLoc - 2]){
                //택시의 위치를 업데이트한다.
                taxiR = r;
                taxiC = c;
                //탑승한 승객을 체크한다.
                Checked[i] = true;
                return i;
            }
        }

        while(!que.isEmpty()){
            int size = que.size()/2;

            //탄 승객의 번호를 저장하기위한 변수
            int firstPassenger = 0;

            //한칸 전진 할 때 마다 연료를 증가시킨다.
            count++;

            for(int i = 0 ; i < size; i++){
                int cr = que.poll();
                int cc = que.poll();

                for(int d = 0; d < 4; d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc].num == 1 || visited[nr][nc]) continue;
                    //만약 현재까지 연료가 원래 있던 연료보다 많으면 -1을 return 시킨다.
                    if(count > fuel ) return -1;

                    // 현재 위치의 startLoc이 0이아니고 체크도 안되어 있다면 출발지 passengerList에 저장
                    if(map[nr][nc].startLoc != 0 && !Checked[map[nr][nc].startLoc - 2]){

                        passengerList.offer(map[nr][nc].startLoc - 2);
                        visited[nr][nc] = true;
                        que.offer(nr);
                        que.offer(nc);
                        continue;
                    }

                    visited[nr][nc] = true;
                    que.offer(nr);
                    que.offer(nc);
                }
            }

            // 출발지가 있다면 연료를 계산하고, 가장 적합한 출발지를 찾는다.
            if(!passengerList.isEmpty()) {

                fuel -= count;

                // 가장 적합한 (가장 작은 열, 행) 출발지 찾는 함수
                firstPassenger = NearPassenger(passengerList);

                //택시위치와 체크 배열 업데이트
                taxiR = passList[firstPassenger].startR;
                taxiC = passList[firstPassenger].startC;
                Checked[firstPassenger] = true;
                return firstPassenger;
            }

        }
        // 만약 전부 돌았는데도 출발지를 못찾으면 -1 return
        return -1;
    }

    private static int NearPassenger(Queue<Integer> passengerList) {
        // return 할 출발지의 번호
        int targetNum = passengerList.peek();
        // 출발지의 위치정보
        Passenger target = passList[passengerList.poll()];

        while(!passengerList.isEmpty()){
            // 비교할 출발지 번호
            int compareNum = passengerList.peek();
            // 비교할 출발지 정보
            Passenger compare = passList[passengerList.poll()];

            // 행과 열 순으로 더 작은 위치를 targer으로 바꿔줌
            if(compare.startR < target.startR){
                target = compare;
                targetNum = compareNum;
            }else if(compare.startR == target.startR && compare.startC < target.startC){
                target = compare;
                targetNum = compareNum;
            }
        }

        return targetNum;
    }

    // 도착지까지 가는 bfs탐색
    private static int goEnd(int startR, int startC, int endR, int endC){
        Queue<Integer> que = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        que.offer(startR);
        que.offer(startC);
        visited[startR][startC] = true;

        //출발지와 도착지가 같다면 return (연료 변경 X)
        if(startR == endR && startC == endC) return 1;

        //현재까지 연료 체크
        int count = 0;

        while(!que.isEmpty()){
            int size = que.size()/2;
            count++;

            for(int i = 0 ; i < size; i++){
                int cr = que.poll();
                int cc = que.poll();

                for(int d = 0; d < 4; d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc].num == 1 || visited[nr][nc]) continue;

                    // 만약 현재위치와 도착지가 같다면 연료와 위치를 업데이트 한다.
                    if(nr == endR && nc == endC) {
                        if(fuel >= count){
                            fuel = fuel - count + (count * 2);
                        }else{
                            return -1;
                        }
                        taxiR = endR;
                        taxiC = endC;
                        return 1;
                    }

                    que.offer(nr);
                    que.offer(nc);
                    visited[nr][nc] = true;
                }
            }


        }
        return -1;
    }
}
