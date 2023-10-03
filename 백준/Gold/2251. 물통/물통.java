import java.util.*;
import java.io.*;

/*
1. summary
2. strategy
3. note 
	1 ≤ A, B, C ≤200
*/

public class Main {

	static int list[];
	static boolean check[][]; // 물 최대 200
	static Set<Integer> answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		list = new int[3];
		for(int i = 0; i < 3; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		check = new boolean[201][201]; // 물 최대 200
				
		answer = new TreeSet<>();
		dfs(0, 0, list[2]);
		
		for(int num : answer) {
			sb.append(num + " ");
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int a, int b, int c) {
		
		if(check[a][b]) return;
		
		if(a == 0) {
			answer.add(c);
		}
		check[a][b] = true;
		
		// a -> b
		if(a + b > list[1]) dfs((a+b) - list[1], list[1], c); // 넘치는 경우
		else dfs(0, a+b, c); // 전부 이동
		
		// b -> a
		if(a + b > list[0]) dfs(list[0], (a+b) - list[0], c);
		else dfs(a+b, 0, c);
		
		// c -> a
		if(a + c > list[0]) dfs(list[0], b, (a+c) - list[0]);
		else dfs(a+c, b, 0);
		
		// c -> b
		if(b + c > list[1]) dfs(a, b, (b+c)-list[1]);
		else dfs(a, b+c, 0);
		
	
		// 1 -> 2
		dfs(a, 0, b+c);// c는 넘치지 않는다. (c를 나눠가졌기 때문에)
		// 0 -> 2
		dfs(0, b, a+c); // c는 넘치지 않는다. (c를 나눠가졌기 때문에)
	}
}
