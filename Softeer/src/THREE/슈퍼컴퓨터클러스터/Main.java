package THREE.슈퍼컴퓨터클러스터;
import java.io.*;
import java.util.*;
public class Main {

    static int N, B;
    static ArrayList<Integer> comList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        comList = new ArrayList<>();
        comList.add(-1);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            comList.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(comList);
        System.out.println(comList.toString());
        int cost = 0;
        int idx = 1;
        while(true){
            System.out.println("IDX : " + idx);
            System.out.println("cost : "+ cost);
            int dif = 0;
            if(idx < N) {
                int curCom = comList.get(idx);
                int nextCom = comList.get(idx + 1);
                dif = nextCom - curCom;
            }else if(idx == N){
                int curCom = comList.get(idx);
                dif = B - curCom;
            }else{
                break;
            }
            boolean check = false;
            for(int i = dif; i >= 0;i--){
                if(cost + (Math.pow(i, 2) * idx) <= B){
                    cost += (int)Math.pow(i,2) * idx;
                    comList.set(1, comList.get(1)+i);
                    check = true;
                    System.out.println("lastDif : " + i);
                    break;
                }
            }

            if(check == false) break;
            idx++;
            System.out.println("first IDX value : " + comList.get(1));
            System.out.println();
        }

        System.out.println(comList.get(1));

    }
}
