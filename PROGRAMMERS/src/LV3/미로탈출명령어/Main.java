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
    static char[] dir = {'r', 'd', 'u', 'l'};
    static int[] dr = {0, 1, -1, 0};
    static int[] dc = {1, 0, 0, -1};
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
        boolean[][] visited = new boolean[n+1][m+1];
        wordList = new ArrayList<>();

        dfs(x, y, r, c, 0, "", visited);
        Collections.sort(wordList);
        System.out.println(wordList.toString());

        if (wordList.size() == 0){
            answer = "impossible";
        }else {
            answer = wordList.get(0);
        }

        return answer;
    }

    private static void dfs(int x, int y, int r, int c, int cnt, String word, boolean[][] visited) {
        System.out.println(x + " " + y + " " + word + " " + cnt);
        if(cnt > K) {
            System.out.println("넘어감");
            return;
        }

        if (x == r && y == c) {
            System.out.println("check");
            if (K == cnt) {
                System.out.println("들어옴");
                wordList.add(word);
                return;
            }
        }

        for(int d = 0; d < 4; d++){
            int nx = x + dr[d];
            int ny = y + dc[d];
            if(nx <= 0 || ny <= 0 || nx > N || ny > M) continue;

            word += dir[d];
            visited[nx][ny] = true;
            dfs(nx, ny, r, c, ++cnt, word, visited);
            cnt--;
            visited[nx][ny] = false;
            word = word.substring(0, (word.length()-1));
        }
    }
}
