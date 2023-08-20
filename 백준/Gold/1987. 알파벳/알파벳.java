import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 방향 벡터(좌,하,우,상)
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
			
	static int R, C, board[][];
	static boolean useAlphabet[] = new boolean[26];
	
	static int maxLength = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 데이터 저장
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j) - 'A';
			}
		}
		
		// 시작 위치 (0,0)
		useAlphabet[board[0][0]] = true;

		findRoute(new Point(0,0), 0);
		
		System.out.println(maxLength + 1);
	}
	
	public static void findRoute(Point current, int length) {
		
		for(int i = 0; i < 4; i++) {
			int mx = current.x + dx[i];
			int my = current.y + dy[i];
						
			if(mx < 0 || mx >= R || my < 0 || my >= C) continue; // 공반 배열 넘어가면 패스
			if(useAlphabet[board[mx][my]]) continue; // 해당 알파벳 사용했으면 패스
			
			useAlphabet[board[mx][my]] = true;
			findRoute(new Point(mx, my), length + 1); 
			useAlphabet[board[mx][my]] = false;
		}
		maxLength = Math.max(length, maxLength);
	}
}
