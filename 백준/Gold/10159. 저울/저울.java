import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. summary :
	무게가 서로 다른 물건 N개 (1~N번)
	각 물건의 크기 비교값이 주어졌을 때, 각 물건당 크기를 비교할 수 없는 물건의 개수 구하기
2. strategy 
	
3. note
	5 ≤ N(물건 개수) ≤ 100
	0 ≤ M(측정) ≤ 2,000
*/

public class Main {

	static int N, M;
	static int inAdj[][], outAdj[][]; 
	
	static int Cnt = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		inAdj = new int[N+1][N+1]; // 진입차수 
		outAdj = new int[N+1][N+1]; // 진출차수
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			outAdj[a][b] = inAdj[b][a] = 1;
		}
		
	
		for(int i = 1; i <= N; i++) { // 각 물건마다 진행
			
			Cnt = 0; // 접근할 수 있는 물건 수
			
			boolean[] visited = new boolean[N+1];
			
			DFS(i, outAdj, visited); // 자신보다 가벼운 물건
			DFS(i, inAdj, visited); // 자신보다 무거운 물건
			
			// 접근 할 수 없는 물건 (비교 불가능) : (N-1) - Cnt
			sb.append(N - 1 - Cnt).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void DFS(int cur, int adj[][], boolean visited[]) {
		visited[cur] = true; // 방문처리
		
		for(int i = 1; i <= N; i++) {
			if(adj[cur][i] == 0 || visited[i]) continue;
			
			Cnt++;
			DFS(i, adj, visited);
		}
	}
}
