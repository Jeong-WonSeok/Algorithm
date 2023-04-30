package GOLD.THREE.학교탐방하기_13418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.compare;

public class Main {
    // 정점, 간선
    static int V, E;
    static int[][] graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()) + 1;
        E = Integer.parseInt(st.nextToken()) + 1;
        int upRoad = 0;

        graph = new int[E][3];

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }
        parent = new int[V];

        //오름차순으로 정렬 (오르막길 먼저 구현)
        Arrays.sort(graph, (o1, o2) -> compare(o1[2], o2[2]));


        // makeSet(초기화)
        for(int i = 0 ; i < V; i++){
            parent[i] = i;
        }

        // 피로도가 최대인 루트 찾기
        for(int i = 0 ; i < E; i++){

            if(find(graph[i][0]) != find(graph[i][1])){

                union(graph[i][0], graph[i][1]);

                if(graph[i][2] == 0){
                    upRoad++;
                }

            }
        }

        for(int i = 0 ; i < V; i++){
            parent[i] = i;
        }

        // 배열을 내림차순으로 정렬(내리막길인 간선 먼저 정렬)
        Arrays.sort(graph, (o1, o2) -> compare(o2[2], o1[2]));
        int downRoad = 0;

        // 피로도가 최소인 루트 찾기
        for(int i = 0 ; i < E; i++){
            if(find(graph[i][0]) != find(graph[i][1])){

                union(graph[i][0], graph[i][1]);

                if(graph[i][2] == 0){
                    downRoad++;
                }
            }
        }
        System.out.println((int)(Math.pow(upRoad,2) - Math.pow(downRoad,2)));


    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if( a > b) {
            parent[a] = b;
        } else{
            parent[b] = a;
        }
    }

    private static int find(int x){
        if(parent[x] == x)
            return x;
        else
            return find(parent[x]);
    }

}
