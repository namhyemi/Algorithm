import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. summary
	N * N 격자판, 각 칸은 빈칸이거나 벽이다.(벽이 있으면 파이프 설치 불가능하다.)
	파이프는 3가지 방향이 가능하다.(→, ↘, ↓ 방향)
	파이프는 항상 빈칸만 차지한다. 
	
	이전 파이프가 가로면 →, ↘, 세로면   ↓, ↘, 대각선이면 →, ↘, ↓
	처음 파이프는 (0,0)와 (1,1)를 차지하고 있다. => (1,1) 부터 시작한다. 
	
	파이프를 (N,N)까지 연결하는 방법의 개수를 구하자.
2. strategy
	파이프 도착라인부터 시작한다.
	파이프 라인에 도착할 수 있는 방법은 (N-1, N), (N-1,N-1), (N, N-1) 에서 오는 방법이 있다.
	이전에 어떻게 설치 되었는지에 따라서 다음 파이프 설치 방법이 달라진다.
	
	해당 위치에 벽이 있다면 그 방법은 피해서 온다.
	대각선의 경우 벽 여부는 두 곳을 체크한다.
3. note 
	N(3 ≤ N ≤ 16)
	방법의 수는 항상 1,000,000보다 작거나 같다.
	
	가로 파이프를 사용하면 파이프 왼쪽 라인 접근 불가능
*/
public class Main {
	
	static int N;
	static int board[][];
	static int pipe[][][];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		pipe = new int[3][N+1][N+1]; 
		// 해당 위치에 종료되는 파이프 개수 : 파이프 가로(0), 세로(1), 대각선(2) 설치 방법을 다 따로 기록한다.
		
		for(int i = 1; i <= N; i++) { // 행 : 1~N
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) { // 열 : 1~N
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기값 설정
		pipe[0][1][2] = 1;
		for(int i = 3; i <= N; i++) {
			// 1행 : 파이프 가로설치 가능
			if(board[1][i] == 1) continue;
			pipe[0][1][i] = pipe[0][1][i-1];
		}
		
		for(int i = 2; i <= N; i++) { // 행 : 1~N
			for(int j = 2; j <= N; j++) { // 열 : 2~N
				// 현재 위치가 벽이면 파이프 설치 패스
				if(board[i][j] == 1) continue; 
				
				// 가로 설치 방법 = 이전 파이프가 가로 + 대각선
				pipe[0][i][j] = pipe[0][i][j-1] + pipe[2][i][j-1];
				
				// 세로 설치 방법 = 이전 파이프가 세로 + 대각선
				pipe[1][i][j] = pipe[1][i-1][j] + pipe[2][i-1][j];

				if(board[i-1][j] == 1 || board[i][j-1] == 1) continue;
				// 대각선 설치 방법 = 이전 파이프가 가로 + 세로 + 대각선
				pipe[2][i][j] = pipe[0][i-1][j-1] + pipe[1][i-1][j-1] + pipe[2][i-1][j-1];
			}
		}
		System.out.println(pipe[0][N][N] + pipe[1][N][N] + pipe[2][N][N]);
	}
}