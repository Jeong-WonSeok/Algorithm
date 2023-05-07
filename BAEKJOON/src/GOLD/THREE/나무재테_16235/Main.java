package GOLD.THREE.나무재테_16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //트리 위치와 나이를 저장하기 위한 클래스
    static class Tree implements Comparable<Tree>{
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree tree){
            return this.age - tree.age;
        }

        @Override
        public String toString() {
            return "[ " + x +", " + y +
                    ", " + age + ", " + nourishment[y][x] + " ]";
        }
    }
    static int N, M, K;
    static int[] dx  = {0 ,1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] nourishmentPlus, nourishment;
    static PriorityQueue<Tree> treeList;
    static Queue<Tree> deadTree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 겨울에 추가되는 양분 배열
        nourishmentPlus = new int[N][N];
        // 나무가 있는 곳과 나이알려주는 배열
        nourishment = new int[N][N];
        // 트리 정보
        treeList = new PriorityQueue<>();
        // 죽은 트리를 위한
        deadTree = new LinkedList<>();

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(nourishment[i], 5);
            for (int j = 0; j < N; j++) {
                nourishmentPlus[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            treeList.offer(new Tree(x-1, y-1, age));
        }

        for(int i = 0 ; i < K; i++){
//            System.out.println("Start " + treeList.toString() + "\n");
            Spring();
//            System.out.println("Spring " + treeList.toString() + "\n");
            Summer();
//            System.out.println("Summer " + treeList.toString() + "\n");
            Fall();
//            System.out.println("fall " + treeList.toString() + "\n");
            Winter();
//            System.out.println("winter "  + treeList.toString() + "\n");
//            System.out.println("##################################");
        }

        System.out.println(treeList.size());
    }

    private static void Spring() {
        Queue<Tree> temp = new LinkedList<>();
        while(!treeList.isEmpty()){
            temp.add(treeList.poll());
        }
        while(!temp.isEmpty()){
            Tree cTree = temp.poll();
            int x = cTree.x;
            int y = cTree.y;
            int age = cTree.age;
            // 나이가 양분보다 작거나 같으면 나이 + 1
            if (age <= nourishment[y][x]) {
                nourishment[y][x] -= age;
                age++;

                treeList.add(new Tree(x, y, age));
            }
            // 나이가 양분보다 크다면 삭제하고 죽은 트리큐에 추가한다.
            else if(age > nourishment[y][x]){
                deadTree.add(new Tree(x, y, age));
            }
        }
    }

    private static void Summer() {
        // 큐에 있다면 양분 += age/2를 해준다.
        while(!deadTree.isEmpty()){
            Tree t = deadTree.poll();
            nourishment[t.y][t.x] += t.age / 2;
        }
    }

    private static void Fall() {
        Queue<Tree> temp = new LinkedList<>();
        while(!treeList.isEmpty()){
            temp.add(treeList.poll());
        }
        while(!temp.isEmpty()){
            Tree t = temp.poll();

            //8방 탐색을 하여 나무를 추가한다.
            if(t.age % 5 == 0){
                for(int d = 0; d < 8; d++){
                    int nx = t.x + dx[d];
                    int ny = t.y + dy[d];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    treeList.offer(new Tree(nx, ny, 1));
                }
            }
            treeList.offer(t);
        }
    }

    private static void Winter() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                nourishment[i][j] += nourishmentPlus[i][j];
            }
        }
    }
}
