package SILVER.TWO.DFS와BFS_1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1_Matrix {

    public static int[][] map;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // https://www.acmicpc.net/problem/1260
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int V = Integer.parseInt(st.nextToken()); // 간선의 개수
        int s = Integer.parseInt(st.nextToken()); // 탐색 시작 할 정점 번호

        map = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i = 0 ; i < V; i++){
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());

            map[fir][sec] = 1;
            map[sec][fir] = 1;
        }

        dfs(s);
        System.out.println();

        visited = new boolean[N+1];
        bfs(s);


    }

    private static void bfs(int num) {
        Queue<Integer> q = new LinkedList<Integer>();

        q.add(num);
        visited[num] = true;
        System.out.print(num + " ");

        while (!q.isEmpty()) {
            int temp = q.poll();
            for(int i = 1; i < map.length;i++){
                if (map[temp][i] == 1 && visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                    System.out.print(i + " ");

                }
            }
        }

    }

    private static void dfs(int num){
        visited[num] = true;
        System.out.print(num + " ");

        if(num == map.length)
            return;

        for(int j = 1; j < map.length; j++){
            if(map[num][j] == 1 && visited[j] == false)
                dfs(j);
        }
    }
}
