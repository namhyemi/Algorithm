import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	// 방향 벡터(아래, 오른쪽, 위, 왼쪽)
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	
	static int N;
	static char twoColor[][], threeColor[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		// 입력 저장하기
		N = Integer.parseInt(br.readLine());
		twoColor = new char[N][N];
		threeColor = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String string = br.readLine();
			for(int j = 0; j < N; j++) {
				// 적록색약이 아닌 사람 배열
				threeColor[i][j] = string.charAt(j); 
				// 적록색약인 사람 배열
				if(threeColor[i][j] == 'R' || threeColor[i][j] == 'G') twoColor[i][j] = 'R';
				else twoColor[i][j] = 'B';
			}
		}
	
		// 구현
		int threeResult = findSector(threeColor);
		int twoResult = findSector(twoColor);
		
		// 출력하기
		System.out.println(threeResult + " " + twoResult);
	}
	
	// 그림 영역 구하기 (BFS)
	public static int findSector(char board[][]) { 
		
		int result = 0;
		boolean visit[][] = new boolean[N][N]; // 방문 배열
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visit[i][j]) continue; // 이미 체크한 부분은 패스 
				
				Queue<Point> q = new ArrayDeque<>();
				q.offer(new Point(i, j));
				visit[i][j] = true;
				
				while(!q.isEmpty()) {
					Point current = q.poll(); 
					
					for(int k = 0; k < 4; k++) {
						int mx = current.x + dx[k];
						int my = current.y + dy[k];
						
						if(mx < 0 || mx >= N || my < 0 || my >= N) continue; // 영역 벗어남
						if(board[mx][my] != board[current.x][current.y]) continue; // 같은색인지 확인
						if(visit[mx][my]) continue; // 방문한적 있는지 확인
						
						visit[mx][my] = true;
						q.offer(new Point(mx, my));
					}
				}
				result++;
			}
		}
		return result;
	}
}