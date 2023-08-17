import java.io.*;
import java.util.*;

public class Solution {

	// 방향 벡터 (bfs 사용)
	static int dx[] = {0,-1,0,1,0};
	static int dy[] = {0,0,1,0,-1};
	
	static int time, chargeCnt;
	static int moveA[][], moveB[][];
	static int board[][][], visit[][];

	static int chooseBC[] = new int[2];
	static boolean isSelected[];
	
	static int maxSum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken()); // 이동 시간
			chargeCnt = Integer.parseInt(st.nextToken()); // 충전기 개수

			moveA = new int[time+1][2]; moveB = new int[time+1][2]; // A, B 이동 위치(x,y) 담는 배열
			
			int Ax = 0, Ay = 0;
			int Bx = 9, By = 9;
			
			moveA[0][0] = Ax; moveA[0][1] = Ay;
			moveB[0][0] = Bx; moveB[0][1] = By;
			
			StringTokenizer stOne = new StringTokenizer(br.readLine());
			StringTokenizer stTwo = new StringTokenizer(br.readLine());
			for(int t = 1; t <= time; t++) {
			
				// 방향 벡터 받아오기
				int dirA = Integer.parseInt(stOne.nextToken());
				int dirB = Integer.parseInt(stTwo.nextToken());
				
				// 방향 벡터 계산
				Ax += dx[dirA]; Ay += dy[dirA];
				Bx += dx[dirB]; By += dy[dirB];
				
				// 현재 시간 위치 저장
				moveA[t][0] = Ax; moveA[t][1] = Ay;
				moveB[t][0] = Bx; moveB[t][1] = By;
			}
			
//			for(int t = 0; t <= time; t++) {
//				System.out.println(moveB[t][0] + " " + moveB[t][1]);
//			}
			
			
			// 배터리 정보 저장
			board = new int[8][10][10];
			for(int i = 0; i < chargeCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken())-1; // 배터리 위치 x
				int x = Integer.parseInt(st.nextToken())-1; // 배터리 위치 y
				int chargeNum = Integer.parseInt(st.nextToken()); // 배터리 범위 -> BFS()
				int power = Integer.parseInt(st.nextToken()); // 배터리 위력
				
				bfs(i, x, y, chargeNum, power);
				
//				for(int a = 0; a < 10; a++) {
//					for(int b = 0; b < 10; b++) {
//						System.out.print(board[i][a][b] + "\t");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
			
			// 사람 이동
			int result = 0;
			for(int t = 0; t <= time; t++) {

				Ax = moveA[t][0]; Ay = moveA[t][1];
				Bx = moveB[t][0]; By = moveB[t][1];
				
				isSelected = new boolean[8];
				maxSum = 0;
				
				Perm(0, Ax, Ay, Bx, By);
				
				result += maxSum;
			}
			
			sb.append("#" + tc + " " + result + "\n");
			
		}
		System.out.println(sb);
		
		br.close();
	}
	
	
	// bfs 충전기 범위에 파워 저장
	public static void bfs(int i, int x, int y, int chargeNum, int power) {
		
		visit = new int[10][10]; // 방문배열
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		visit[x][y] = 1;
		board[i][x][y] = power;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
		
			for(int k = 0; k < 5; k++) {
				int mx = pos[0] + dx[k];
				int my = pos[1] + dy[k];
				
				if(mx < 0 || mx >= 10 || my < 0 || my >= 10) continue; // 지도 벗어나면 패스
				if(visit[mx][my] != 0) continue; // 이미 방문했으면 패스
			
				visit[mx][my] = visit[pos[0]][pos[1]] + 1;
				
				if(visit[mx][my] > chargeNum + 1) continue; // 충전기 범위 벗어나면 패스

				q.offer(new int[] {mx, my});
				board[i][mx][my] = power;
			}
		}
	}
	
	public static void Perm(int cnt, int Ax, int Ay, int Bx, int By) {
		
		if(cnt == 2) { // 건전지 두개 선택 (순열)
			
			int A = chooseBC[0]; // A 가 선택한 건전지
			int B = chooseBC[1]; // B 가 선택한 건전지
			
			int sum = board[A][Ax][Ay] + board[B][Bx][By];
			
			maxSum = Math.max(sum, maxSum);
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			chooseBC[cnt] = i;
			Perm(cnt+1, Ax, Ay, Bx, By);
			isSelected[i] = false;
		}
	}
}