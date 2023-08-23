import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static final int T = 10;
	
	static int inputDatalength, startPerson;
	static int board[][];
	static int visit[];
	static int maxLength = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 10개의 테스트 케이스
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			inputDatalength = Integer.parseInt(st.nextToken());
			startPerson = Integer.parseInt(st.nextToken());
			
			board = new int[101][101]; 
			visit = new int[101];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i*2 < inputDatalength; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				board[from][to] = 1; // from에서 to로 가는 길이 존재함
			}
			
			bfs(startPerson);
			
//			for(int i = 1; i <= 100; i++) {
//				System.out.println(i + " : " + visit[i]);
//			}
			
//			System.out.println(maxLength);
			
			
			for(int i = 0; i < 100; i++) {
				if(visit[100 - i] == maxLength) {
					sb.append("#" + tc + " " + (100-i) + "\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
	
	private static void bfs(int startPerson) {
		
		Queue<Integer> call = new ArrayDeque<>();
		call.offer(startPerson);
		
		visit[startPerson] = 1;
		
		while(!call.isEmpty()) {
			int nextPerson = call.poll();
			
			for(int i = 1; i <= 100; i++) {
				if(board[nextPerson][i] == 0) continue; // 연락망이 없는 경우 패스
				if(visit[i] > 0) continue; // 이미 통화된 사람이면 패스
				
				call.offer(i);
				visit[i] = visit[nextPerson] + 1;
				
				maxLength = visit[i];
			}
		}
	}
}