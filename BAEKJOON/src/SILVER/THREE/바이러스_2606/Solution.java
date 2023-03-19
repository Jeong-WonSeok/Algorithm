package SILVER.THREE.바이러스_2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    // https://www.acmicpc.net/problem/2606
    public static int[][] map;
    public static boolean[] visited;
    public static int sum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int v = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        visited = new boolean[n+1];


        for(int i = 0 ; i < v; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            map[f][s] = 1;
            map[s][f] = 1;
        }
        sum = 0 ;
        bfs(1);
        System.out.println(sum);
    }

    public static void dfs(int start){
        visited[start] = true;

        for(int i = 1 ; i < map.length; i++){
            if(visited[i] == false && map[start][i] == 1){
                sum++;
                dfs(i);
            }
        }
    }
    public static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;

        while(!que.isEmpty()){
            int cur = que.poll();
            for(int i = 1 ; i < map.length; i++){
                if(visited[i] == false && map[cur][i] == 1){
                    que.add(i);
                    visited[i] = true;
                    sum++;
                }
            }
        }
    }
}
