
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] LIS = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int maxLen = 0;
			for(int i = 0; i < N; i++) {
				
				LIS[i] = 1;
				for(int j = 0; j < i; j++) {
					if(arr[j] < arr[i]) {
						LIS[i] = Math.max(LIS[i], LIS[j] + 1);
					}
				}
				maxLen = Math.max(maxLen, LIS[i]);
			}
			
			sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
		}
		
		System.out.println(sb);
	}
}
