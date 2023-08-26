import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
1. summary
	방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하기
2. strategy : Dijkstra (최단 경로)
3. note
	1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000, 1 <= w <= 10
	서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의
 */
public class Main {

	static public class Node {
		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	static int V, E, startVertex;
	static List<Node> graph[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startVertex = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V+1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
		}

		int distance[] = new int[V+1]; // 거리체크 
		Arrays.fill(distance, Integer.MAX_VALUE);

		boolean visit[] = new boolean[V+1]; // 방문체크
		distance[startVertex] = 0; // 시작 정점
		
		for(int v = 0; v < V; v++) { // 노드 수만큼 반복
			int minWeight = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for(int i = 1; i <= V; i++) {
				if(!visit[i] && distance[i] < minWeight) { // 가장 작은 거리 체크
					minWeight = distance[i];
					minVertex = i;
				}
			}
						
			if(minVertex == -1) break;
			visit[minVertex] = true;
		
			for(int i = 0; i < graph[minVertex].size(); i++) {
				Node tmp = graph[minVertex].get(i);
				if(visit[tmp.vertex]) continue; // 이미 방문
				if(distance[tmp.vertex] < minWeight + tmp.weight) continue;
				
				distance[tmp.vertex] = minWeight + tmp.weight;
			}
		}
		
		for(int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(distance[i] + "\n");
		}
		
		System.out.println(sb);
	}
}