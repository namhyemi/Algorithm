import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[] cables;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cables = new int[K];

        long end = 0;
        for(int n = 0; n < K; n++) {
            cables[n] = Integer.parseInt(br.readLine());

            end = Math.max(end, cables[n]);
        }
        
        long start = 1;

        while (start <= end) {
            long mid = (start + end) / 2; // 길이

            long sum = 0;
            for(int i = 0; i < K; i++) {
                sum += (cables[i] / mid);
            }
            
            if(sum >= N) start = mid + 1;
            else end = mid - 1;
        }

        System.out.println(end);
    }
}