package GOLD.TWO.주사위윷놀이_17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 시작 말 4개
// 게임은 화살표의 방향대로만 이동 가능(파란색 칸에서 이동 시작하면 파란색 화살표)
// 10개의 턴 (5면체 주사위)
// 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다.( 도착 칸이라면 고를 수 있음)
// 칸에 적혀있는 수가 추가 됨

// 10개의 주사위 값을 알고 있을 때(얻을 수 있는 최댓값)
public class Main {

    static final int[] map = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0,
/* 22 ~ 30 */                 10, 13, 16, 19, 25, 30, 35, 40, 0,
/* 31 ~ 38 */                 20, 22, 24, 25, 30, 35, 40, 0,
/* 39 ~ 47 */                 30, 28, 27, 26, 25, 30, 35, 40, 0};
    static int[] dice, tokenSeq;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = 0;
        dice = new int[10];
        tokenSeq = new int[10];
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        per(0);
        System.out.println(result);

    }
    // 중복 순열을 통하여 말의 순서를 정한다.
    private static void per(int cnt) {
        if (cnt == 10) {
            Game();
            return;
        }

        for (int i = 0; i < 4; i++) {
            tokenSeq[cnt] = i;
            per(cnt + 1);
        }
    }

    private static void Game() {
        int[] loc = new int[4];
        int score = 0;

        for (int i = 0; i < 10; i++) {
            // 이동시킬 말
            int curToken = tokenSeq[i];
            if( loc[curToken] == -1) {
                return;
            }

            // 현재 위치가 지름길로 갈 수 있는 위치 판단
            int curLoc = shortcutCheck(loc[curToken]);
            int nextLoc = dice[i] + curLoc;

            // 만약 true 라면 도착지 도착했다는 뜻
            if (outCheck(curLoc, nextLoc)) {
                loc[curToken] = -1;
                continue;
            }

            for (int j = 0; j < 4; j++) {
                if (j == curToken) continue;
                // 만약 위치가 중복된다면 그 경우의 수는 삭제
                if (loc[j] == nextLoc || SameLoc(loc[j], nextLoc)){
                    return;
                }
            }

            loc[curToken] = nextLoc;
            score += map[nextLoc];
        }

        result = Math.max(score, result);


    }

    private static boolean SameLoc(int curLoc, int nextLoc) {

        if(curLoc == 20 || curLoc == 29 || curLoc == 37 || curLoc == 46) {
            if(nextLoc == 20 || nextLoc == 29 || nextLoc == 37 || nextLoc == 46) return true;
        }
        else if(curLoc == 26 || curLoc == 34 || curLoc == 43){
            if(nextLoc == 26 || nextLoc == 34 || nextLoc == 43) return true;
        }
        else if(curLoc == 27 || curLoc == 35 || curLoc == 44){
            if(nextLoc == 27 || nextLoc == 35 || nextLoc == 44) return true;
        }
        else if(curLoc == 28 || curLoc == 36 || curLoc == 45) {
            if (nextLoc == 28 || nextLoc == 36 || nextLoc == 45) return true;
        }

        return false;
    }


    // 도착했는지 체크
    private static boolean outCheck(int curLoc, int nextLoc) {
        // 지름길 없이 도착
        if(curLoc < 21) {
            if (nextLoc >= 21) return true;
        }
            // 지름길 5번 루트 이용
        else if(curLoc < 30) {
            if (nextLoc >= 30)
                return true;
        }
            // 지름길 10번 루트 이용
        else if(curLoc < 38){
            if(nextLoc >= 38)
                return true;
        }
            //지름길 15번 루트 이용
        else if(curLoc < 47){
            if(nextLoc >= 47)
                return true;
        }

        return false;

    }


    private static int shortcutCheck(int curLoc) {
        if(curLoc == 5){
            return 22;
        }else if(curLoc == 10){
            return 31;
        }else if(curLoc == 15){
            return 39;
        }else {
            return curLoc;
        }
    }

}
