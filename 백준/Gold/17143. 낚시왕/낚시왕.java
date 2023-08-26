import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static class Shark{
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r; // 상어 행 (1~R)
			this.c = c; // 상어 열 (1~C)
			this.s = s; // 상어 속도
			this.d = d; // 상어 방향
			this.z = z; // 상어 크기
		}
	}
	
	// 방향 벡터  [1]:위, [2]:아래, [3]:오른쪽, [4]:왼쪽
	static int dir[][] = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
	
	static int R, C, M, board[][];
	static Shark sharkList[]; // 상어 배열
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		R = Integer.parseInt(st.nextToken()); // 수조관 행 
		C = Integer.parseInt(st.nextToken()); // 수조관 열
		M = Integer.parseInt(st.nextToken()); // 상어 수
		
		board = new int[R+1][C+1]; // 수조관은 (1~R, 1~C)
		sharkList = new Shark[M+1];
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); // 행
			int c = Integer.parseInt(st.nextToken()); // 열
			int s = Integer.parseInt(st.nextToken()); // 속도
			int d = Integer.parseInt(st.nextToken()); // 방향
			int z = Integer.parseInt(st.nextToken()); // 크기

			if(d == 1 || d == 2) {
				sharkList[i] = new Shark(r, c,  s % ((R-1)*2), d, z);
				// (R-1)*2 : 상어가 한바퀴 돌고나면 제자리로 오는 숫자
			}
			else {
				sharkList[i] = new Shark(r, c, s % ((C-1)*2), d, z); 
				// (C-1)*2 : 상어가 한바퀴 돌고나면 제자리로 오는 숫자
			}
			board[r][c] = i; // 초기 상어 위치 기록
		}
		
		int startFisiKing = 0; // 시작열
		int killShark = 0;
		for(int  move = 1; move <= C; move++) { // C번 이동
			
			// 1.낚시
			startFisiKing++; // 한칸 오른쪽 이동
			int startFish = 0;
			for(int i = 1; i <= R; i++) { // 열 체크
				startFish++; // (1~R)
				if(board[startFish][startFisiKing] != 0) { // 상어 발견
					int sharkNumber = board[startFish][startFisiKing]; // 상어 번호
					
					killShark += sharkList[sharkNumber].z; // 상어 무게 카운트
					board[startFish][startFisiKing] = 0; // 상어 포획
					sharkList[sharkNumber] = null; // 상어 삭제

					break; // 사냥 종료
				}
			}
			
			// 2. 상어 이동
			// 2-1. 상어 이동 위치 결정
			for(int i = 1; i <= M; i++) {
				if(sharkList[i] == null) continue; // 이미 포획 당한 상어
				
				Shark shark = sharkList[i];
				
				board[shark.r][shark.c] = 0; // 상어 출발 위치 흔적 지우기
				
				for(int j = 1; j <= shark.s; j++) { // 상어 속력만큼 이동
					// 방향 회전
					if(shark.r == 1 && shark.d == 1) shark.d = 2;
					else if(shark.r == R && shark.d == 2) shark.d = 1;
					else if(shark.c == 1 && shark.d == 4) shark.d = 3;
					else if(shark.c == C && shark.d == 3) shark.d = 4;
					
					shark.r += dir[shark.d][0]; shark.c += dir[shark.d][1]; // 한칸씩 이동
				}
			}
			
			// 2-2. 상어 이동 완료
			for(int i = 1; i <= M; i++) { // 상어 이동 후 위치 기록
				if(sharkList[i] == null) continue; // 이미 포획 당한 상어 
				
				Shark shark = sharkList[i];
				
				if(board[shark.r][shark.c] != 0) { // 도착 위치에 상어가 이미 있으면 잡아먹기
					if(sharkList[board[shark.r][shark.c]].z > shark.z) { // 기존에 있는 상어가 더 크면 
						sharkList[i] = null; // 현재 상어 삭제
					}
					else { // 현재 상어가 더 큰 경우
						sharkList[board[shark.r][shark.c]] = null; // 기존 상어 삭제
						board[shark.r][shark.c] = i; // 현재 위치에 상어 도착
					}
				}
				else { // 도착 위치에 상어가 없다면 도착
					board[shark.r][shark.c] = i; // 현재 위치에 상어 도착
				}	
			}
		}
		System.out.println(killShark);
	}
}
