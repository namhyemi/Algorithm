import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[] gates;
    static long answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gates = new int[N];
        for(int n = 0; n < N; n++) {
            gates[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(gates); 

        // 초를 기준으로 이분 탐색 (상한)
        answer = upperBound();

        System.out.println(answer);
    }

    private static long upperBound() {
        long start = gates[0]; 
        long end = (long) M * gates[0];

        while(start <= end) {
            long mid = (start + end) / 2;

            long sumCnt = 0;
            for(int gate : gates) {
                sumCnt += (mid / gate);
            }

            if(sumCnt >= M) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }
}
