import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. summary : 사람 네트워크
	모든 사람들과의 거리의 합이 가장 작은 사람 찾기
2. strategy : 플로이드워샬
3. note
	사람 네트워크 최대 사용자 수 1000이다.
	그룹 1 싸이클이 없고 N <= 10 인 경우
	그룹 2 싸이클이 없고 10 < N <= 100 인경우
	그룹 3 싸이클이 있고 N<= 10
	그룹 4 싸이클이 있고10 < N <= 100
	그룹 5 모든 경우가 존재하고 100 < N <= 1000 
*/
public class Solution {

	static final int INF = 10000; 
	
	static int N; // 사람 네트워크 사용자 수
	static int board[][];
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for(int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 사용자 수
			board = new int[N][N];
			
			// 관계 입력 받기 (k = 0)
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && board[i][j] == 0) { // 완전 그래프 X (경로 없는 경우)
						board[i][j] = INF;
					}
				}
			} 
			
			for(int k = 0; k < N; k++) { // 경로
				for(int i = 0; i < N; i++) { // 출발지
					if(i == k) continue; 
					
					for(int j = 0; j < N; j++) { // 도착지
						if(j == i || j == k) continue;
						
						board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
					}
				}
			}
			
			int minPerson = Integer.MAX_VALUE;
			int minSum = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) { // 사람 i 의
				int sum = 0;
				for(int j = 0; j < N; j++) { // 모든 관계 확인
					sum += board[i][j];
				}
				
				if(sum < minSum) {
					minSum = sum;
					minPerson = i;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(minSum).append("\n");
		}
		
		System.out.println(sb);
	}
}
