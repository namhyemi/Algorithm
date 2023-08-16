import java.io.*;
import java.util.*;

class Pair{
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int R, C, board[][];
	static int result;
	
	// 파이프 진행 방향 (오른쪽 위, 오른쪽, 오른쪽 아래)
	static int dx[] = {-1, 0, 1};
	static int dy[] = {1, 1, 1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력 데이터 저장
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C ; j++) {
				char c = s.charAt(j);				
				if(c == '.') board[i][j] = 0;  // 0 : 지나갈 수 있는 영역
				else if(c == 'x') board[i][j] = 1;  // 1 : 지나갈 수 없는 영역
			}
		}
        
		for(int i = 0; i < R; i++) {
			// 시작 위치
			nextCol(i, 0);
		}
		
		System.out.println(result);
	}
	
	public static boolean nextCol(int row, int col) {
		if(col == C-1) {
			result++; // 배관 추가 완료
			return true; // 해당 파이프 종료 위한 리턴값
		}
		
		for(int i = 0; i < 3; i++) { // 파이프 라인은 오른쪽 위, 오른쪽, 오른쪽 아래로 확장
			int mx = row + dx[i];
			int my = col + dy[i];
			
			if(mx < 0 || my < 0 || mx >= R || my > C) continue; // 범위 벗어나면 패스
			if(board[mx][my] == 1) continue; // 지나갈 수 없는 거리면 패스
			
			board[mx][my] = 1;
			if(nextCol(mx, my)) { // 파이프라인 확장
				return true; // 해당 배관 완성된거면 되돌아가기
			}
			// board[mx][my] = 0;
		}
		return false; // 해당 배관 완성실패 다음 확장 위치 확인 필요
	}
}