import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. summary
	사이트는 서쪽 N개, 동쪽 M개 존재한다.
	사이트는 중복 사용 불가능하다.
	서쪽의 N개를 동쪽에 모두 연결하려고 한다.
	다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하자
2. strategy : DP
	서쪽 사이트 N개, 동쪽 사이트 M개를 연결하는 경우의 수는 N-1 과 M-1 연결하는 방법 + N개와 M-1개 연결하는 방법과 같다.
3. note 
	N ≤ M
	0 < N ≤ M < 30
*/
public class Main{

	static long board[][];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		board = new long[35][35];

		// 초기값
		for(int i = 1; i <= 30; i++) { // 서쪽 사이트 1개일 때, 동쪽 사이트 i개일때 경우의 수
			board[1][i] = i;
		}
		
		for(int i = 2; i <= 30; i++) { // 서쪽 사이트 2~30
			for(int j = i; j <= 30; j++) { // 동쪽 사이트 2~30
				if(i == j) board[i][j] = 1;
				else {
					board[i][j] = board[i-1][j-1] + board[i][j-1];
				}
			}
		}
 		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 서쪽 사이트
			int M = Integer.parseInt(st.nextToken()); // 동쪽 사이트
			
			sb.append(board[N][M] + "\n");
		}
		
		System.out.println(sb);
	}
}
