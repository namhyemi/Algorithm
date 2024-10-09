import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int answer = 0;
    static int board[];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            board[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board); // 정렬

        // 투 포인터
        for(int n = 0; n < N; n++) {
            int current = board[n];

            int left = 0;
            int right = N-1;

            while(left < right) {
                if(left == n) { left++; continue; } // 왼쪽 포인터가 자신일 때
                if(right == n) { right--; continue; } // 오른쪽 포인터가 자신일 때
                
                int sum = board[left] + board[right];
                
                if(sum == current) { // 좋은 수
                    answer++;
                    break;
                } 
                else if(sum > current) right--; // 합이 더 큰 경우 오른쪽 포인터 좌측 이동
                else left++; 
            }
        }
        
        System.out.println(answer);
    }
}