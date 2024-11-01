import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int arr[];
    static int totalTime = 0, maxTime = 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine());
        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());

        arr = new int[N];
        input = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(input.nextToken());
            totalTime += arr[n];

            maxTime = Math.max(maxTime, arr[n]);
        }

        int start = maxTime; 
        int end = totalTime;

        while(start < end) {
            int mid = (start + end) / 2;

            int cnt = 1;
            int sum = 0;
            for(int n = 0; n < N; n++) {
                if (sum + arr[n] > mid) {
                    sum = 0;
                    cnt++;
                }
                sum += arr[n];
            }

            if(cnt > M) start = mid + 1;
            else if(cnt <= M) end = mid;
        } 

        System.out.println(end);
    }
}