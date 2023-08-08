import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	static int board[][];
	static int n, m, cnt;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		
		board = new int[n+1][m+1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int changeCnt = cnt; // % (((n-1) + (m-1)) * 2);
		
		for(int i = 0; i < changeCnt; i++) rotate();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(board[i][j] + " ");
			} sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void rotate() {
		
		int depth = Math.min(n, m) / 2; // 문제 조건 : 작은 변은 무조건 2의 배수 

		for(int d = 0; d < depth; d++) {
			int tmp = board[d][d];
			
			int dir = 0;
			int startX = d, startY = d;
			while(dir < 4) {
				int mx = startX + dx[dir];
				int my = startY + dy[dir];
				
				if(mx >= d && my >= d && mx < n-d && my < m-d) {
					board[startX][startY] = board[mx][my];
					
					startX = mx;
					startY = my;		
				}
				else {
					dir++;
				}
			}
			
			board[d+1][d] = tmp;
		}
	}
}