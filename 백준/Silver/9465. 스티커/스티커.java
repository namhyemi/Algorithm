import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < TC; tc++) {

            int N = Integer.parseInt(br.readLine());

            int[][] board = new int[2][N];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] DP = new int[2][N];
            int result = 0;

            if(N == 1){
                result = Integer.max(board[0][0], board[1][0]);
            }
            else{
                // 초기값
                DP[0][0] = board[0][0]; DP[0][1] = board[0][1] + board[1][0];
                DP[1][0] = board[1][0]; DP[1][1] = board[1][1] + board[0][0];

                for(int n = 2; n < N; n++){
                    for(int m = 0; m < 2; m++){
                        int maxNumber = Integer.max(DP[(m + 1) % 2][n-1], Integer.max(DP[0][n-2], DP[1][n-2]));
                        DP[m][n] = board[m][n] + maxNumber;
                    }
                }
                result = Integer.max(DP[0][N-1], DP[1][N-1]);
            }

            sb.append(result + "\n");
        }
        System.out.print(sb);
        br.close();
    }
}