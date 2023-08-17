import java.io.*;
import java.util.*;

public class Solution {

	static int N, board[][];
	static boolean visit[];
	
	static int minResult;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 입력 데이터 저장하기
			N = Integer.parseInt(br.readLine());
			board = new int[N+2][2]; // 회사 + 집  + 방문할 집(N)
			visit = new boolean[N+2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N + 2; i++) {
				board[i][0] = Integer.parseInt(st.nextToken()); // x좌표
				board[i][1] = Integer.parseInt(st.nextToken()); // y좌표
			}

			minResult = Integer.MAX_VALUE;
			
			dfs(0, 0, 0);
			
			sb.append("#" + tc + " " + minResult + "\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	public static void dfs(int cnt, int curHouse, int result) {
		if(cnt == N) {
			// 집 도착
			int distance = (Math.abs(board[curHouse][0] - board[1][0])
					+ Math.abs(board[curHouse][1] - board[1][1]));
			
			minResult = Math.min(result + distance, minResult);
			return;
		}
		
		for(int nextHouse = 2; nextHouse < N+2; nextHouse++) {  
			if(visit[nextHouse]) continue; // 이미 방문한 집이면 패스
			
			int distance = (Math.abs(board[curHouse][0] - board[nextHouse][0])
					+ Math.abs(board[curHouse][1] - board[nextHouse][1]));
			if(result + distance > minResult) continue; // 이전에 구해둔 최소 경로를 넘어서면 패스
			
			visit[nextHouse] = true;
			dfs(cnt + 1, nextHouse, result + distance);
			visit[nextHouse] = false;
		}
	}
}