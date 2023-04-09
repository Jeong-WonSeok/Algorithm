package GOLD.TWO.불켜기_11967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static boolean[][] switched, visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static ArrayList<Room>[][] graph;

    static class Room{
        int x;
        int y;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 시작점은 무조건 불이 켜져있음 시작 +1
    // 방문 못하더라도 불을 키면 방 count 가능
    // 각 방에서 킬 수 있는 방은 거리에 상관없는 방도 가능합니다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        switched = new boolean[n][n]; // true : 불킴 / false : 불끔
        visited = new boolean[n][n]; // 이미 방을 방문해 불을 켰는지 안켰는지 체크

        //2차원 ArrayList 배열 사용
        graph = new ArrayList[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                graph[i][j] = new ArrayList<Room>();
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken()) - 1;
            int y = Integer.valueOf(st.nextToken()) - 1;
            int a = Integer.valueOf(st.nextToken()) - 1;
            int b = Integer.valueOf(st.nextToken()) - 1;


            Room endPoint = new Room(a, b);
            graph[y][x].add(endPoint);
        }

        // 시작에서 +1을 하고 싶음
        int count = bfs() + 1;

        System.out.println(count);
    }

    // 0,0 부터 bfs
    private static int bfs() {
        int count = 0;
        Queue<Room> que = new LinkedList<>();
        que.offer(new Room(0,0));

        // 방문한 기록을 초기화
        for(int i = 0 ; i < n; i++){
            Arrays.fill(visited[i], false);
        }

        switched[0][0] = true;
        visited[0][0] = true;

        boolean Check = false;

        //
        // 불킨적 없을 때 까지 BFS 반복
        while(!que.isEmpty()){
            Room loc = que.poll();
            for(Room nextRoom : graph[loc.y][loc.x]){
                if(!switched[nextRoom.y][nextRoom.x]){
                    switched[nextRoom.y][nextRoom.x] = true;
                    count++;
                    Check = true;
                }
            }

            //상하좌우 이동 체크
            for(int d = 0; d < 4; d++){
                int nX = loc.x + dx[d];
                int nY = loc.y + dy[d];
                if(nX < 0 || nX >= n || nY < 0 || nY >= n || visited[nY][nX]) continue;

                if(!switched[nY][nX] || visited[nY][nX]) continue;
                que.offer(new Room(nX, nY));
                visited[nY][nX] = true;
            }
        }

        if(Check){
            count += bfs();
        }

        return count;
    }

}
