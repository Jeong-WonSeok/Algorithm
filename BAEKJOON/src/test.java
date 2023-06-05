public class test {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

// 작업 코드
        for(int i = 0; i < 9000000; i++){
            int a = 0;
        }

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("코드 실행 시간: %20dms", endTime - startTime));
    }
}
