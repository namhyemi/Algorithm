import java.util.*;
import java.io.*;
import java.awt.Point;

/*
1. summary
	공기청정기(-1)는 항상 1번열에 있고 두행을 차지한다.
	1. 미세먼지 확산
		- 인접한 네 방향으로 확산(공기청정기 있는 곳 X)
		- 확산되는 양 Ar,c/5 (소수점 X)
		- 남아있는 양 Ar,c - (Ar,c/5)×(확산된 방향의 개수)
	2. 공기청정기 작동
		- 위쪽은 반시계, 아래쪽은 시계
		- 미세먼지는 바람 방향으로 한칸씩 이동
		- 공기청정기에 들어간 먼지 정화
2. strategy
3. note
	6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000 (2500 * 1000 = 2500000)
*/

public class Main {

	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static int R, C, time;
	static int board[][];
	
	static List<Point> product = new ArrayList<>();
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 데이터 입력받기
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				
				if(board[r][c] == -1) { // 공기청정기 위치 기록
					product.add(new Point(r, c));
				}
			}
		}
		
		// 구현
		for(int t = 0; t < time; t++) {
			
			// 미세먼지 확산
			spread();
			
			// 공기청정기 작동
			move(product.get(0), new int[] {0, 3, 1, 2}); // 공기청정기 위 (위, 오른쪽, 아래, 왼쪽)
			move(product.get(1), new int[] {1, 3, 0, 2}); // 공기청정기 아래
		}
        
        // 결과 출력
		print();
	}
	
    public static void spread() { // 미세먼지 확산
		
		int tmpboard[][] = new int[R][C]; // 임시배열
				
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				tmpboard[r][c] += board[r][c];
				int cnt = 0;
				
				if(board[r][c] < 4) continue; // 미세먼지 양 체크 (4보다 작으면 패스) or 공기청정기
			
				int x = board[r][c]/5; // 이동할 양
				
				for(int d = 0; d < dir.length; d++) {
					int mr = r + dir[d][0];
					int mc = c + dir[d][1];
					
					if(mr < 0 || mr >= R || mc < 0 || mc >= C) continue; // 영역 벗어나면 패스
					if(board[mr][mc] == -1) continue; // 공기청정기 자리 패스
					
					tmpboard[mr][mc] += x; // 이동한 자리에 미세먼지 더해서 기록
					cnt++;
				}
				tmpboard[r][c] -= (x * cnt); // 현재 위치 미세먼지 빠진 값 계산 후 기록
			}
		}
	
		// 기록 덮어씌우기
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				board[r][c] = tmpboard[r][c]; 
			}
		}
	}
	
	public static void move(Point startPoint, int[] rotationDir) { // 미세먼지 작동

		int x = startPoint.x;
		int y = startPoint.y;
		for(int i = 0; i < 4; i++) {
			int d = rotationDir[i];
			
			while(true) {
				
				int mx = x + dir[d][0];
				int my = y + dir[d][1];
				
				if(board[x][y] == -1) {
					x = mx;
					y = my;
					continue;
				}
				
				if(mx < 0 || mx >= R || my < 0 || my >= C) break; // 영역 벗어나면 회전
				if(board[mx][my] == -1) {
					board[x][y] = 0;
					break; // 공기청정기
				}
				
				if(i == 2 && (x == startPoint.x)) break;

				board[x][y] = board[mx][my];
				x = mx;
				y = my;
			}
		}
	}
	
    // 결과 (미세먼지 양 계산)
	public static void print() {
		int result = 0;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(board[r][c] == -1) continue;
	
				result += board[r][c];
			}
		}
		System.out.println(result);
	}
}
