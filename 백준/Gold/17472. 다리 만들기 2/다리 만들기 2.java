import java.util.*;
import java.io.*;
import java.awt.Point;

/*
1. summary : 모든 섬을 연결하는 다리의 최소값 구하기 (모든섬 연결 불가능시 -1 출력)
	- 지도 정보 (0: 바다, 1:땅)
	- 다리 조건
		다리 길이 2이상
		다리 방향은 바뀌면 안된다.
		다리는 교차가 가능하다.
2. strategy 
	- 섬 분리하기
	- 모든 섬들끼리 놓을 수 있는 최소 다리 구하기
	- 간접연결까지해서 모든 섬들이 연결되어있는지 확인하기
3. note :
	1 ≤ N, M ≤ 10
	3 ≤ N × M ≤ 100
	2 ≤ 섬의 개수 ≤ 6
*/
public class Main {

	static int dir[][] = {{-1,0}, {1,0}, {0, -1}, {0, 1}}; // 상하좌우
	
	static int N, M, board[][];
	static int dist[][];
	static List<Point> landList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 데이터 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j] == 1) {
					board[i][j] = -1;
					landList.add(new Point(i,j)); // 땅 부분 저장
				}
			}
		}
		
		// 섬 분리하기
		int landNum = 0;
		for(Point land : landList) {
			// 해당 섬 탐색 여부 체크
			if(board[land.x][land.y] != -1) continue; // 이미 탐색한 섬
			
			divideLand(land, ++landNum);
		}
		
		
		// 최단 거리 측정하기
		dist = new int[landNum+1][landNum+1]; // 거리 측정 배열
		for(int i = 0; i <= landNum; i++) {
			Arrays.fill(dist[i], 100);
		}
		findRoute();
		
		
		// 섬 연결 확인하기
		int result = checkRoute(landNum);
		System.out.println(result);
	}

	
	public static void divideLand(Point start, int landNum) { // BFS
		
		Queue<Point> queue = new ArrayDeque<>();
		
		queue.offer(start);
		board[start.x][start.y] = landNum;
		
		while(!queue.isEmpty()) {
			
			Point current = queue.poll();
			
			for(int d = 0; d < dir.length; d++) {
				int mx = current.x + dir[d][0];
				int my = current.y + dir[d][1];
				
				if(mx < 0 | mx >= N | my < 0 | my >= M) continue; // 영역 벗어나면 패스
				if(board[mx][my] != -1) continue; // 이미 방문한 적 있거나 바다(0)면 패스
				
				board[mx][my] = landNum; // 섬 번호 입력
				queue.offer(new Point(mx, my));
			}
		}
	}
	
	public static void findRoute() {
		for(Point land : landList) {
			for(int d = 0; d < dir.length; d++) {
				int dis = 0;
				int mx = land.x;
				int my = land.y;
				while(true) {
					dis++;
					mx += dir[d][0];
					my += dir[d][1];
					
					if(mx < 0 | mx >= N | my < 0 | my >= M) break; // 영역 벗어나면 해당 방향 탐색 종료
					if(board[mx][my] == board[land.x][land.y]) break; // 같은 섬이면 해당 방향 탐색 종료
					
					if(board[mx][my] != 0) { // 다른 섬 도달
						if(dis <= 2) break; // 거리가 2 이하면 종료 
						int currentLand = board[land.x][land.y];
						int otherLand = board[mx][my];
						if(dist[currentLand][otherLand] > dis-1) { // 두 섬 거리의 현재 경로가 더 작으면 업데이트
							dist[currentLand][otherLand] = dis-1;
							dist[otherLand][currentLand] = dis-1;
						}
						break;
					}
				}
			}
		}
		
	}
	
	public static int checkRoute(int landNum) { // 프림
		
		int result = 0;
		
		boolean visited[] = new boolean[landNum+1];
		int distance[] = new int[landNum+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[1] = 0; // 섬 번호 1~landNum
		
		int min = 0;
		int minLand = 0;
		for(int land = 1; land <= landNum; land++) { // 
			minLand = 0;
			min = Integer.MAX_VALUE;
			for(int i = 1; i <= landNum; i++) {
				if(!visited[i] && min > distance[i]) {
					min = distance[i];
					minLand = i; 
				}
			}
			
			if(min == Integer.MAX_VALUE) {
				result = -1;
				break;
			}
			
			visited[minLand] = true; // 방문처리
			result += min; // 신장 트리 비용 누적
			
			for(int i = 1; i <= landNum; i++) {
				if(visited[i]) continue;
				if(dist[minLand][i] != 100 && distance[i] > dist[minLand][i]) {
					distance[i] = dist[minLand][i];
				}
			}
		}
		
		return result;
	}
}
 