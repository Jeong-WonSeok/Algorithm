package LV3.표병합;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{45, 50, 74}, {89}};
        System.out.println(arr[1].length);

    }
    class table{
        String value;
        Queue<Integer> merge;
        table(String value){
            this.value = value;
            merge = new LinkedList<>();
        }
    }

    static table[][] map;

    public String[] solution(String[] commands) {
        String[] answer;
        ArrayList<String> answerTemp = new ArrayList<>();
        map = new table[51][51];
        for(int i = 1; i < 51; i++){
            for(int j = 1; j < 51; j++){
                map[i][j] = new table("");
            }
        }
        for(String command : commands){
            String[] detail = command.split(" ");
            boolean[][] visited = new boolean[51][51];
            if(detail[0].equals("UPDATE")){
                //value1 value2
                if(detail.length == 3){
                    for(int i = 1; i < 51; i++){
                        for( int j = 1; j < 51; j++){
                            if(detail[1].equals(map[i][j].value)){
                                map[i][j].value = detail[2];
                                visited[i][j] = true;
                                update(i,j, detail[2], visited);
                            }
                        }
                    }
                }
                // r c value
                else{
                    int r = Integer.parseInt(detail[1]);
                    int c = Integer.parseInt(detail[2]);
                    map[r][c].value = detail[3];
                    visited[r][c] = true;
                    // System.out.println("UPDATE " + r + " " + c + " " + map[r][c].merge.toString());
                    update(r,c, detail[3], visited);
                }
            }else if(detail[0].equals("MERGE")){
                int r1 = Integer.parseInt(detail[1]); int c1 = Integer.parseInt(detail[2]);
                int r2 = Integer.parseInt(detail[3]); int c2 = Integer.parseInt(detail[4]);
                if(r1 == r2 && c1 == c2) continue;
                map[r1][c1].merge.offer(r2); map[r1][c1].merge.offer(c2);
                map[r2][c2].merge.offer(r1); map[r2][c2].merge.offer(c1);
                // System.out.println(r1+ " " + c1 + " " + map[r1][c1].merge.toString());
                // System.out.println(r2+ " " + c2 + " " + map[r2][c2].merge.toString());
                if(map[r1][c1].value.equals("")){
                    map[r1][c1].value = map[r2][c2].value;
                }else{
                    map[r2][c2].value = map[r1][c1].value;
                }
            }else if(detail[0].equals("UNMERGE")){
                int r = Integer.parseInt(detail[1]); int c = Integer.parseInt(detail[2]);
                String temp = map[r][c].value;
                // System.out.println("UNMERGE " + r + " " + c);
                // System.out.println(map[r][c].merge.toString());
                unmerge(r,c);
                map[r][c].value = temp;
            }else{
                int r = Integer.parseInt(detail[1]); int c = Integer.parseInt(detail[2]);
                // System.out.println(map[r][c].value);
                answerTemp.add(map[r][c].value.equals("") ? "EMPTY" : map[r][c].value);
            }
        }
        answer = new String[answerTemp.size()];

        for(int i = 0; i < answer.length; i++){
            answer[i] = answerTemp.get(i);
        }
        return answer;
    }
    static private void update(int r, int c, String value, boolean[][] visited){
        int size = map[r][c].merge.size();
        for(int i = 0; i < size/2; i++){
            int cr = map[r][c].merge.poll();
            int cc = map[r][c].merge.poll();
            if(visited[cr][cc]){
                map[r][c].merge.offer(cr);
                map[r][c].merge.offer(cc);
                continue;
            }
            visited[cr][cc] = true;
            map[cr][cc].value = value;
            update(cr, cc, value, visited);

            map[r][c].merge.offer(cr);
            map[r][c].merge.offer(cc);
        }
    }
    static private void unmerge(int r, int c){
        while(!map[r][c].merge.isEmpty()){
            int cr = map[r][c].merge.poll();
            int cc = map[r][c].merge.poll();
            // System.out.println(cc + " " + cr);
            map[cr][cc].value = "";
            unmerge(cr, cc);
        }
    }
}
