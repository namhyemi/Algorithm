import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	// 방향 벡터 (왼쪽, 앞, 오른쪽)
	static int dx[] = {0,-1,0};
	static int dy[] = {-1,0,1};
	
	static int N, M, D;
	static int board[][];
	static int[] bowmanList = new int[3]; // 궁수는 3명으로 고정
	static Point[] bowmanPosList = new Point[3]; // 궁수 3명의 좌표

	static Queue<Point> killEmemyList = new ArrayDeque<>(); // 한 턴에 죽을 적 최대 3명(활을 동시에 발사)
	static int maxKillEmemyCnt = 0, killEmemyCnt = 0;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 데이터 입력 받기
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 궁수 활 사정거리
		board = new int[N+1][M+1];
		
		// 공간벡터 정보 입력 받기 (0 : 빈공간, 1 : 적)
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j  < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		chooseBowman(0, 0);
		
		System.out.println(maxKillEmemyCnt);
	}
	
	public static void test() {
		System.out.println(M);
	}
	
	// 적 3명을 선택(순서 상관 X)
	public static void chooseBowman(int cnt, int start) {
		// 궁수 3명 선택
		if(cnt == 3) {
			for(int i = 0; i < 3; i++)
				bowmanPosList[i] = new Point(N, bowmanList[i]); // 성 위에 있기 때문에 N은 고정
		
			// 원본 공간 배열 복사 (초기 적 위치 정보)
			int tmpBoard[][] = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
			killEmemyCnt = 0;

			StartGame(tmpBoard);

			maxKillEmemyCnt = Math.max(killEmemyCnt, maxKillEmemyCnt);
			return;
		}
		
		for(int i = start; i < M; i++) { // 궁수는 성(길이 M)위에 있음
			bowmanList[cnt] = i;
			chooseBowman(cnt+1, i+1);
		}
		
	}
	
	// 선택된 궁수 3명으로 게임 진행
	public static void StartGame(int tmpBoard[][]) {
		for(int k = 0; k < N; k++) { // N-1턴 진행
			for(int i = 0; i < 3; i++) { // 궁수 3명 각각
				chooseEnemy(bowmanPosList[i], tmpBoard); // 죽일 적 선택
			}
			killEnemy(tmpBoard); // 선택된 적 죽이기
			moveEnemy(tmpBoard); // 남은 적 아래로 이동
		}
	}
	
	// 궁수가 죽일 적 고르기 (해당 함수에서 죽는 적은 killEmemyList에 저장만 하고 죽이진 않음)
	public static void chooseEnemy(Point bowman, int tmpBoard[][]) {
		
		int visit[][] = new int[N+1][M+1];
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(bowman);
		visit[bowman.x][bowman.y] = 1; // 궁수가 활 거리 방문 여부 (1부터 시작하기 때문에 마지막에 1을 빼줘야 한다.)
		
		while(!queue.isEmpty()) {
			Point tmp = queue.poll();
			
			for(int i = 0; i < 3; i++) { // 앞, 왼쪽, 오른쪽
				int mx = tmp.x + dx[i];
				int my = tmp.y + dy[i];
				
				if(mx < 0 || mx >= N || my < 0 || my >= M) continue; // 공간 배열 넘어가면 패스
				if(visit[mx][my] != 0) continue; // 이미 방문했으면 패스
				
				visit[mx][my] = visit[tmp.x][tmp.y] + 1; // 방문 배열 체크(거리 의미)
				queue.offer(new Point(mx, my));
				
				if(visit[mx][my] - 1 > D) break; // 활 사정거리 넘어가면 종료
				
				if(tmpBoard[mx][my] == 1) { // 만약 적이 있다면
					killEmemyList.offer(new Point(mx, my)); // 죽일 적 위치 저장
					queue.clear();
					break; // 해당 궁수 화살 추적 종료
				}			
			}
		}
	}
	
	// 적 죽이기
	public static void killEnemy(int tmpBoard[][]) {
		while(!killEmemyList.isEmpty()) {
			Point ememy = killEmemyList.poll();
			if(tmpBoard[ememy.x][ememy.y] == 1) { // 해당 위치에 적 살아있으면
				tmpBoard[ememy.x][ememy.y] = 0; // 보드에서 제외 시키기
				killEmemyCnt++;
			}
		}
	}
	
	// 적들을 한칸씩 아래로 이동시키는 함수
	public static void moveEnemy(int[][] tmpBoard) {
		for(int i = N-1; i > 0; i--) { // i-1행 적을 i행에 이동시킨다(열은 고정)
			for(int j = 0; j < M; j++) { 
				tmpBoard[i][j] = tmpBoard[i-1][j];
				tmpBoard[i-1][j] = 0;
			}
		}
	}
}