package THREE.출퇴근길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int m, n, S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] pointList = new ArrayList[n+1];
        // 역방향 간선 그래프 생성
        ArrayList<Integer>[] rPointList = new ArrayList[n+1];

        for(int i = 0; i < n+1; i++){
            pointList[i] = new ArrayList<>();
            rPointList[i] = new ArrayList<>();
        }
        for(int i = 0; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            pointList[from].add(to);
            rPointList[to].add(from);
        }


        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();

        // 정방향, 역방향 dfs로 거치는 곳들 확인
        dfs(S , T, pointList, s1, new boolean[n+1]);
        dfs(T , -1, rPointList, s2, new boolean[n+1]);

        // 교집합만 남김
        s1.retainAll(s2);

        Set<Integer> s3 = new HashSet<>();
        Set<Integer> s4 = new HashSet<>();

        dfs(T , S, pointList, s3, new boolean[n+1]);
        dfs(S , -1, rPointList, s4, new boolean[n+1]);

        s3.retainAll(s4);

        s1.retainAll(s3);

        int answer = s1.size();
        if(s1.contains(S)) answer--;
        if(s1.contains(T)) answer--;


        System.out.println(answer);

    }

    static private void dfs(int start, int end,ArrayList<Integer>[] pointList, Set<Integer> set, boolean[] visited){
        System.out.println(start + " " + end);
        if(end != -1 && start == end){
            return;
        }

        for(int next : pointList[start]){
            if(visited[next]) continue;
            visited[next] = true;
            set.add(next);
            dfs(next, end, pointList, set, visited);
        }

        return;
    }
}