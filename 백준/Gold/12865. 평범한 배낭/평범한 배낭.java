import java.util.*;
import java.io.*;

/*
1. summary	
	N개의 물건 존재 (각 물건은 무게 W와 가치 V를 갖는다.)
	최대 K만큼의 무게만 담을 수 있을때 가질 수 있는 가치의 최대값 찾기
2. strategy : 0/1 knapsack
3. note
	1 ≤ N (물품 개수) ≤ 100, 1 ≤ K (버틸 수 있는 무게) ≤ 100,000
	1 ≤ W (각 물품 무게) ≤ 100,000, 0 ≤ V (각 물품 가치) ≤ 1,000
*/

public class Main {

	public static class Product {
		int weight;
		int value;
		
		public Product(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
	}

	static int N, K;
	static List<Product> list;
	
	static long board[]; 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 데이터 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			list.add(new Product(weight, value));
		}
		
		// 값 구하기
		board = new long[K+1]; // 담을 수 있는 무게 만큼의 배낭
		for(int n = 0; n < N; n++) { // 물건 1~N
			Product product = list.get(n);
			for(int weight = K; weight > 0; weight--) { // 일차원 배열이기 때문에 무거운 경우부터 갱신
				if(weight >= product.weight) {
					board[weight] = // 기존에 구한 값과 현재 물건을 추가했을 경우 중 더 큰 값 저장
							Math.max(board[weight - product.weight] + product.value, board[weight]);	
				}
			}
		}
		System.out.println(board[K]);
	}
}
