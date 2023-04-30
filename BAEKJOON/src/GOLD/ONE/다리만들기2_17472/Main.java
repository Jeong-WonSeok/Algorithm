package GOLD.ONE.다리만들기2_17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static boolean[] linked; // 연결된 섬들을 체크
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static int[][] map;
    static boolean[][] isChecked;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] list;
    static int N, M, cnt, bridge;

    static class XY {
        int r, c;

        public XY(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Node implements Comparable<Node> {
        int to, weight;

        public Node(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node [ to=" + to + ", weight=" + weight + "]";
        }

    }

    public static void main(String[] args) throws IOException {

        // 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 또, 다리의 길이는 2 이상이어야 한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isChecked = new boolean[N][M];
        ArrayList<XY> island = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    island.add(new XY(i, j));
                    map[i][j] = -1;
                } else {
                    map[i][j] = num;
                }
            }
        }
        cnt = 0;
        for (XY pos : island) {
            if (map[pos.r][pos.c] == -1) {
                bfs(pos.r, pos.c);
            }

        }

        linked = new boolean[cnt + 1];
        list = new ArrayList[cnt + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        bridge = 0;

        for (XY pos : island) {
            link(pos);
        }


        list[0].add(new Node(1, 0));
        prim();

        boolean isLinked = true;
        for (int i = 1; i < linked.length; i++) {
            if (!linked[i])
                isLinked = false;
        }

        System.out.println(isLinked ? bridge : -1);

    }

    private static void prim() {
        pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (linked[cur.to])
                continue;
            linked[cur.to] = true;

            bridge += cur.weight;

            for (Node next : list[cur.to]) {
                if (!linked[next.to])
                    pq.offer(next);
            }
        }

    }

    private static void link(XY pos) {
        int r = pos.r;
        int c = pos.c;
        int from = map[r][c];

        for (int i = 0; i < 4; i++) {

            int distance = 0;
            int dirR = dr[i];
            int dirC = dc[i];
            int sr = r;
            int sc = c;

            while (true) {
                sr = sr + dirR;
                sc = sc + dirC;

                if (sr < 0 || sr >= N || sc < 0 || sc >= M || map[sr][sc] == from)
                    break;

                if (map[sr][sc] != 0 && map[sr][sc] != from && distance <= 1)
                    break;

                if (map[sr][sc] != 0 && map[sr][sc] != from && distance > 1) {
                    list[from].add(new Node(map[sr][sc], distance));
                    list[map[sr][sc]].add(new Node(from, distance));

                    break;
                }

                distance++;
            }

        }
    }

    private static void bfs(int r, int c) {
        cnt++;
//		System.out.println(cnt);
        map[r][c] = cnt;
        isChecked[r][c] = true;
        Queue<Integer> que = new LinkedList<>();
        que.offer(r);
        que.offer(c);

        while (!que.isEmpty()) {
            r = que.poll();
            c = que.poll();

            for (int i = 0; i < 4; i++) {
                int sr = r + dr[i];
                int sc = c + dc[i];

                if (sr < 0 || sr >= N || sc < 0 || sc >= M || isChecked[sr][sc] || map[sr][sc] == 0)
                    continue;

                que.offer(sr);
                que.offer(sc);
                map[sr][sc] = cnt;
                isChecked[sr][sc] = true;

            }
        }

    }

}
