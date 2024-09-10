import java.util.*;
import java.io.*;

public class Main {
    
    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int answer = 0;

    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++) {
                board[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        for(int n = 0; n < N; n++) {
            for(int m = 0; m < M; m++) {
                visited[n][m] = true;
                DFS(n, m, 1, board[n][m]); 
                visited[n][m] = false;

                comb(n, m, 0, 0, board[n][m]); // x, y 위치, 테트로미노 개수, 조합시작인덱스, 점수계산
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int x, int y, int cnt, int sum) { // 행, 열, 선택개수, 총합
        if(cnt == 4) { // 4개 선택
            answer = Math.max(sum, answer);
            return;
        }

        for(int[] dir : direction) {
            int mx = x + dir[0];
            int my = y + dir[1];

            if(mx < 0 || mx >= N || my < 0 || my >= M) continue; // 범위 벗어난 경우 패스
            if(visited[mx][my]) continue; // 이미 방문한 경우 패스
            
            visited[mx][my] = true;
            DFS(mx, my, cnt+1, sum + board[mx][my]);
            visited[mx][my] = false;
        }
    }

    public static void comb(int x, int y, int start, int cnt, int sum) { // 'ㅏ', 'ㅗ', 'ㅓ', 'ㅜ' (중심으로 부터 인접 4개 정사각형 중 3개 선택) 경우의 수 4
        if(cnt == 3) {
            answer = Math.max(sum, answer);
            return;
        }

        for(int dir = start; dir < direction.length; dir++) {
            int mx = x + direction[dir][0];
            int my = y + direction[dir][1];
            
            if(mx < 0 || mx >= N || my < 0 || my >= M) continue; // 범위 벗어나면
            
            comb(x, y, dir + 1, cnt + 1, sum + board[mx][my]);
        }
    }
}

