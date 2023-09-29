import java.util.*;
import java.io.*;
import java.awt.Point;

/*
1. summary : 맥주 마시면서 걸어가기
	편의점 방문 시 1000m 까지 이동 가능 
	편의점 간의 거리 체크하는 문제 (맨해튼 거리)
2. strategy : BFS
	시작 편의점부터 1000m 이하인 편의점 방문해서 페스티벌까지 도착할 수 있는지 체크
3. note :
	0 ≤ n (편의점 개수) ≤ 100 (100 * 100 * 100 = 1000000)
	모든 편의점 방문 X 
*/

public class Main {

	static int N; 
	static Point list[];
	static Point home = new Point();
	static Point festival = new Point();
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			// 데이터 입력 받기
			N = Integer.parseInt(br.readLine());
			list = new Point[N];
			
			st = new StringTokenizer(br.readLine());
			home.x = Integer.parseInt(st.nextToken()); 
			home.y = Integer.parseInt(st.nextToken()); 	
			for(int i = 0; i < N; i++) { // 집 + 편의점 + 페스티벌 (N+2)
				st = new StringTokenizer(br.readLine()); 
				int x = Integer.parseInt(st.nextToken()); 
				int y = Integer.parseInt(st.nextToken());
				
				list[i] = new Point(x, y); // 편의점 위치
			}
			st = new StringTokenizer(br.readLine());
			festival.x = Integer.parseInt(st.nextToken()); 
			festival.y = Integer.parseInt(st.nextToken()); 
			
			
			sb.append(BFS()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static String BFS() {

		boolean visit[] = new boolean[N];
		
		Queue<Point> q = new ArrayDeque<>();

		q.offer(home);
		while(!q.isEmpty()) {
			
			Point current = q.poll();
			
			int dist = Math.abs(current.x - festival.x) + Math.abs(current.y - festival.y);
			if(dist <= 1000) {
				return "happy";
			}
			for(int i = 0; i < N; i++){
				if(visit[i]) continue;
				
				dist = Math.abs(current.x - list[i].x) + Math.abs(current.y - list[i].y);
				if(dist <= 1000) {
					q.offer(new Point(list[i].x, list[i].y));
					visit[i] = true;
				}
			}
		}
		return "sad";
	}
}
