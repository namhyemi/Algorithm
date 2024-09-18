import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] board;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board);

        pre(0);
        
        System.out.println(sb.toString());
    }

    public static void pre(int cnt) {
        if(cnt == M) {
            for(int i = 0; i < cnt; i++) {
                sb.append(board[result[i]]).append(" ");
            }
            sb.append("\n");
            return; 
        }

        for(int i = 0; i < N; i++) {
            result[cnt] = i;
            pre(cnt + 1);
        }
    }
}