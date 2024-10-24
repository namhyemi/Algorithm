import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for(int n = 0; n < N; n++) {
            String[] str = br.readLine().split(" ");

            arr[n][0] = Integer.parseInt(str[0]);
            arr[n][1] = Integer.parseInt(str[1]);
        }

        for(int i = 0; i < N; i++) {
            int cnt = 1;
            for(int j = 0; j < N; j++) {
                if(i == j) continue; // 자기 자신은 비교 X

                if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) { // 자신보다 덩치가 큰 사람
                    cnt++;
                }
            }
            answer.append(cnt).append(" ");
        }

        System.out.println(answer);
    }
}