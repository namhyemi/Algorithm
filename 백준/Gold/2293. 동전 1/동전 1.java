import java.util.*;
import java.io.*;

/*
1. summary : 동전1
	각기 다른 n가지 종류의 동전을 가지고 k원을 만들 때, 경우의 수를 구하자 (동전개수의 제한은 없다.) 
2. strategy : DP
	순서는 고려하지 않는다. (조합)
	각 동전마다 경우의 수를 구한다. 이전 동전의 경우의 수를 합산한다.
3. note :
	시간 제한이 0.5초이므로 완전탐색말고 다른 방법 구상
	1 ≤ n(동전 개수) ≤ 100, 1 ≤ k(동전 가치) ≤ 10,000
	경우의 수는 2^31보다 작다.
	
*/
public class Main {

	static long money[][]; // 동전 경우의 수
	static int coin[]; // 동전 종류
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 동전 종류
		int K = Integer.parseInt(st.nextToken()); // 최종 금액
		
		coin = new int[N+1]; 
		for(int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		money = new long[N+1][K+1]; 
		for(int i = 1; i <= N; i++) { // coin[i] 를 사용해서 얻을 수 있는 경우의 수  
			for(int j = 1; j <= K; j++) { // 금액 (1원~K원)
				money[i][j] = money[i-1][j];
				
				if(j-coin[i] == 0) {
					money[i][j] += 1;
				}
				else if(j-coin[i] > 0) { 
					// coin[i]를 가지고 j원을 만들 수 있는 경우의 수는
					// coin[i]를 포함해서 만드는 방법 + coin[i]를 포함시키지 않고 만드는 방법
					money[i][j] += money[i][j-coin[i]];	
				}
			}
		}
		
		System.out.println(money[N][K]);
	}
}
