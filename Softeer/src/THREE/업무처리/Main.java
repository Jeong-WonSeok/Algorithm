package THREE.업무처리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// *****미완*****
public class Main {

    static int H, K, R;
    static Queue<Integer>[][] workList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        workList = new Queue[(int)Math.pow(2, H+1)][3];

        for(int i = 1; i < workList.length; i++){
            workList[i][0] = new LinkedList<>(); // 왼쪽에서 오는 업무들
            workList[i][1] = new LinkedList<>(); // 말단(원래가지고 있는 업무들)
            workList[i][2] = new LinkedList<>(); // 오른쪽에서 오는 업무들
        }

        for(int i = (int)Math.pow(2, H) ; i < workList.length; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++)
                workList[i][1].offer(Integer.parseInt(st.nextToken()));
        }


        int sum = 0;
        // 날짜가 지나갈때마다 업무 올리기
        for(int i = 1; i <= R; i++){
            if(i % 2 == 0) {
                if(!workList[1][2].isEmpty())
                    sum += workList[1][2].poll();
                //오른쪽 부하 직원에서 올라옴
                for (int j = 3; j < (int)Math.pow(2, H); j++) {
                    if(!workList[j][2].isEmpty())
                        workList[j/2][2].offer(workList[j][2].poll());
                }
            }else{
                if(!workList[1][0].isEmpty())
                    sum += workList[1][0].poll();
                for(int j = 2; j < (int)Math.pow(2, H); j++){
                    if(!workList[j][0].isEmpty())
                        workList[j/2][0].offer(workList[j][0].poll());
                }
            }

            for(int j = (int)Math.pow(2,H); j <workList.length; j++ ){
                System.out.println(j + " " + workList[j][1].peek());
                if(j % 2 == 0) {
                    if(!workList[j][1].isEmpty())
                        workList[j / 2][0].offer(workList[j][1].poll());
                }else{
                    if(!workList[j][1].isEmpty())
                        workList[j / 2][2].offer(workList[j][1].poll());
                }
            }
        }

        System.out.println(sum);
    }
}
