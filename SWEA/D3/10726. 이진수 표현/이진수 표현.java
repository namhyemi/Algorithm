import java.util.*;
import java.io.*;
/*
1. summary : 
	M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져 있는지 아닌지를 판별
2. strategy : 비트마스킹
3. note : 1 ≤ N(비트 자리수) ≤ 30 , 0 ≤ M ≤ 108
*/
public class Solution {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			boolean flag = true;
			for(int i = 0; i < N; i++) {
				int chk = (1 << i);
				
				if((M & chk) == 0) {
					flag = false;
				}
			}
			
			if(flag) sb.append("#" + tc + " " + "ON\n");
			else sb.append("#" + tc + " " + "OFF\n");
		}
		
		System.out.println(sb);
	}
}
