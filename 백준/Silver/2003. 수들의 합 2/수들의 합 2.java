import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static String[] board;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = br.readLine().split(" ");

        int startIdx = 0;
        int endIdx = 0;
        int sum = Integer.parseInt(board[endIdx]);

        while(startIdx < N) {
            if(sum == M) {
                answer++; 
                sum -= Integer.parseInt(board[startIdx++]);
            } else if(sum < M) {
                if(endIdx == N - 1) break; // endIdx 가 더 이상 이동할 수 없으면 종료
                sum += Integer.parseInt(board[++endIdx]);
            } else {
                sum -= Integer.parseInt(board[startIdx++]);
            }
        }

        System.out.println(answer);
    }
}