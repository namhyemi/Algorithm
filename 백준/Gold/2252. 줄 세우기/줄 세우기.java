import java.io.*;
import java.util.*;

public class Main {

	static int N, M;  

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 키재기 횟수
		
		// 그래프 표현
		List<Integer> compare[] = new ArrayList[N+1];// 정점번호 1부터 사용
		for (int i = 0; i < compare.length; i++) { 
			compare[i] = new ArrayList<>();
		}
		
		// 각 정점 별 진입 차수 저장 배열
		int[] indegree = new int[N+1];
		
		// 각 정점별 인접 정점 표시
		for (int i = 0; i < M; i++) { // 간선의 수만큼
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			compare[x].add(y); // x -> y 로의 간선 존재
			indegree[y]++; // 진입차수 카운팅
		}
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i < indegree.length; i++) { 
			if(indegree[i] == 0) // 진입차수가 0인 정점들 => 선행점점이 없는 시작 가능한 정점들
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int nodeNo = q.poll();
			sb.append(nodeNo + " "); // 작업
			
			// nodeNo 와 인접해 있는 정점들 검색
			List<Integer> list = compare[nodeNo];
			
			for(int i = 0; i< list.size(); i++) { // 인접한 정점 수만큼
				indegree[list.get(i)]--; // 인접 정점의 진입 차수 감소
				
				if(indegree[list.get(i)] == 0)
					q.offer(list.get(i));
			}
		}
		System.out.println(sb);
	}
}
