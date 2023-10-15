import java.io.*;
import java.util.*;

/*
1. summary : 사람 무리(네트워크) 개수 체크
2. strategy : union-find
3. note
	1 ≤ N ≤ 100, 0 ≤ M ≤ N(N-1)/2
*/

public class Solution {
	
	static int N,M;
	static int parent[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			make();
			
			for(int i = 0; i < M; i++) { // 관계
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				union(x, y);
			}
			
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(i == parent[i]) cnt++;
			}
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void make() { // 초기화
		parent = new int[N+1]; // 사람 (1~N번)
		for(int i = 1; i <= N; i++) {
			parent[i] = i; 
		}
	}
	
	public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	
	public static boolean union(int x, int y) {
		
		x = find(x);
		y = find(y);
		
		if(x == y) return false;
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
		
		return true;
	}
}
