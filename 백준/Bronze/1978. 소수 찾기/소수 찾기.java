import java.io.*;
import java.util.*;

public class Main {
	
	static boolean per[] = new boolean[1005];;
	static int nums[];
	
	// 소수 찾기 함수 (per[i] 이 false 는 소수이다.)
	private static void perm() {
		
		per[0] = true;
		per[1] = true;
		
		for(int i = 2; i*i <= 1000; i++) {
			if(per[i]) continue;
			for(int j = i*i; j <= 1000 ; j += i) {
				per[j] = true;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if(n == 0) return;
		
		nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		perm();

		int result = 0;
		for(int i = 0; i < n; i++) {
			if(!per[nums[i]]) {
				result++;
			}
		}
		System.out.println(result);
	}
}