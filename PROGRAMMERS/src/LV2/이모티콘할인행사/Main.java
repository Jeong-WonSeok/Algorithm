package LV2.이모티콘할인행사;

import java.util.*;
public class Main {
    public static void main(String[] args) {
//        int[][] users = {{40, 10000}, {25, 10000}};
//        int emoticons[] = {7000, 9000};
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int emoticons[] = {1300, 1500, 1600, 4900};
        int[] answer = solution(users, emoticons);

        System.out.println(Arrays.toString(answer));
    }
    static int cnt = 0;
    static public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        // index : 0 plus 가입자수
        // index : 1 가격
        max = new int[2];
        int[] input = new int[emoticons.length];
//        userSale(users, emoticons, new int[] {40, 40}, max);
        perRe(0, emoticons.length, input, new int[] {10, 20, 30, 40}, users, emoticons);
        System.out.println("\n" + Arrays.toString(max));
        return answer;
    }
    static int[] max;
    static private void perRe(int depth, int end, int[] input, int[] sales, int[][] users, int[] emoticons){
        if (depth == end){
//            System.out.println(Arrays.toString(input));
//            if(input[0] == 40 && input[1] == 40 && input[2] == 20 && input[3] == 40)
            userSale(users, emoticons, input, max);
            return;
        }

        for(int i = 0; i < 4; i++){
            input[depth] = sales[i];
            perRe(depth + 1,end, input, sales, users, emoticons);
        }
    }

    private static void userSale(int[][] users, int[] emoticons, int[] input, int[] max) {
        int subscriber = 0;
        int total_price = 0;
        loop : for(int[] user : users){
//            System.out.println("\n" + Arrays.toString(user));
            int price = 0;
            for(int i = 0; i <= input.length; i++){
                if(price >= user[1]) {
                    subscriber++;

//                    System.out.println("구독자 + 1 : " + subscriber);
                    continue loop;
                }
                if(i == input.length) {
//                    System.out.println(price);
                    break;
                }

                if(user[0] > input[i]) continue;
                price += (int)(emoticons[i] * (1 - ((double)input[i] / 100)));
            }

            // 구독자가 늘지 않았다는 뜻
            total_price += price;
        }
//        System.out.println(total_price);
        if(subscriber > max[0]){
            max[0] = subscriber;
            max[1] = total_price;
        }else if( subscriber == max[0] && total_price > max[1]){
            max[1] = total_price;
        }

    }
}
