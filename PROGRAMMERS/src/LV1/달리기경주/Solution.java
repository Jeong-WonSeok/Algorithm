import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        HashMap<String, Integer> seqSN = new HashMap<>();
        HashMap<Integer, String> seqNS = new HashMap<>();
        int num = 1;
        for(String player : players ){
            seqSN.put(player, num);
            seqNS.put(num++, player);
        }
        for(String player : callings){
            int seq = seqSN.get(player);
            String temp = seqNS.get(seq-1);
            seqSN.put(player, seq-1);
            seqSN.put(temp, seq);
            seqNS.put(seq-1, player);
            seqNS.put(seq, temp);
        }
        
        for(String player : seqSN.keySet()){
            players[seqSN.get(player)-1] = player;
        }
        // System.out.println(Arrays.toString(players));
        return players;
    }
}