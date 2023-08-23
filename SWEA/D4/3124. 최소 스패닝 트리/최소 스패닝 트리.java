import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static class Edge implements Comparable<Edge>{
		int from, to;
		long weight;
		
		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	public static void make() {
		parents = new int[V+1];
		rank = new int[V+1];
		for(int i = 1; i <= V; i++) {
			rank[i] = 1;
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		if(rank[aRoot] < rank[bRoot]) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
			
			if(rank[aRoot] == rank[bRoot])
				 rank[aRoot] += 1;
		}
		return true;
	}
	
	static int V,E;
	static int parents[], rank[];
	
	static Edge[] edgeList;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edgeList = new Edge[E];
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(from, to, weight); 
			}
			
			make();
			
			Arrays.sort(edgeList);
			
			int cnt = 0;
			long result = 0;
			for(int i = 0; i < edgeList.length; i++) {
				Edge edge = edgeList[i];
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					cnt++;
				}
				if(cnt == V-1) break;
			}
			
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}
}