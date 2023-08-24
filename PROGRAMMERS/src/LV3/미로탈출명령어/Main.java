package LV3.미로탈출명령어;

import java.util.*;


public class Main {
    public static void main(String[] args) {
//        String result = solution(3, 4, 2, 3, 3, 1, 5);
//        String result = solution(2, 2, 1, 1, 2, 2, 2);
        String result = solution(3, 3, 1, 2, 3, 3, 4);
        System.out.println(result);
    }
    static int[][] map;
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static ArrayList<String> wordList;
    static int K, N, M;

    static public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        map = new int[n+1][m+1];
        // 출발지
        map[x][y] = 1;
        // 도착지
        map[r][c] = 2;
        K = k;
        N = n;
        M = m;
        wordList = new ArrayList<>();


        // Collections.sort(wordList);
        int distance = distance(x, y, r, c);
        if (distance > k || (k - distance) % 2 == 1) return "impossible";

        dfs(x, y, r, c, 0, "");

        if (wordList.size() == 0){
            answer = "impossible";
        }else {
            answer = wordList.get(0);
        }
        return answer;
    }

    private static int distance(int x, int y, int r, int c){
        return Math.abs(x - r) + Math.abs(y - c);
    }

    private static void dfs(int x, int y, int r, int c, int cnt, String word) {
        if(wordList.size() > 0) return;
        if(cnt + distance(x, y, r, c) > K) return;
        if (K == cnt) {
            if (x == r && y == c) {
                wordList.add(word);
            }
            return;
        }

        for(int d = 0; d < 4; d++){
            int nx = x + dr[d];
            int ny = y + dc[d];
            if(nx <= 0 || ny <= 0 || nx > N || ny > M) continue;

            word += dir[d];
            dfs(nx, ny, r, c, cnt + 1, word);
            word = word.substring(0, (word.length()-1));
        }
    }
}
