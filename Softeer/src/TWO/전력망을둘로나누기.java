package TWO;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/86971

public class 전력망을둘로나누기 {

	class Solution {
	    static boolean visited[];
	    public int solution(int n, int[][] wires) {
	        int answer = -1;
	        int min = 100;
	        boolean[][] connected = new boolean[n+1][n+1];
	        
	        //연결된 map을 만든다.
	        for(int i = 0; i < wires.length;i++){
	            connected[wires[i][0]][wires[i][1]] = true;
	            connected[wires[i][1]][wires[i][0]] = true;
	        }
	        
	        for(int i = 0 ; i < wires.length; i++){
	            visited = new boolean[n+1];
	            //전선을 끊을 두 송전 탑을 선택한다.
	            int start1 = wires[i][0];
	            int start2 = wires[i][1];
	            // 두 연결된 부분을 끊으면서 map에서도 삭제한다.
	            connected[start1][start2] = false;
	            connected[start1][start2] = false;
	            
	            min = Math.min(Math.abs ( bfs(start1,connected,1) - bfs(start2,connected,1)), min);
	            
	            // 다시 연결 시킨다.
	            connected[start1][start2] = true;
	            connected[start1][start2] = true;
	        }
	        
	        return min;
	    }
	    
	    // bfs를 통해 출발하는 송전탑에서 갈 수 있는 송전탑을 전부 검색한다.
	    public int bfs(int start, boolean[][] connected, int cnt){
	        Queue<Integer> que = new LinkedList<>();
	        
	        que.offer(start);
	        
	        visited[start] = true;
	        
	        while(!que.isEmpty()){
	            int cur = que.poll();
	            System.out.println(cur);
	            for(int i = 1 ; i < connected.length; i++){
	                
	                if(connected[cur][i] && !visited[i]){
	                    visited[i] = true;
	                    cnt++;
	                    que.offer(i);
	                }
	            }
	        }
	        
	        return cnt;
	    }
	    
	}
}
