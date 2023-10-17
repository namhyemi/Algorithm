import java.util.*;
import java.io.*;

/*
1. summary : 필요한 고객 수를 늘리기 위해 필요한 최소금액
	여러 도시가 있다.
	각 도시에는 홍보할 수 있는 인원과 금액이 있다.
	각 금액의 배수만큼 배수의 인원을 구할 수 있다.
	적어도 C명을 늘리기 위해 필요한 최소 금액 구해보자.
2. strategy
	하나의 도시를 여러번 선택할 수 있다. -> 최고 효율 도시 여러번 선택
	
	C 명을 딱 떨어지게 구하는게 아니다.
3. note
	1 <= C <= 1000
	1 <= N <= 20
*/

public class Main {

	static int N, C;
	static int board[];
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); // 인원
		N = Integer.parseInt(st.nextToken()); // 도시 수
		
		board = new int[C + 100]; // 도시에서 얻을 수 있는 고객 수 100명 이하
		Arrays.fill(board, Integer.MAX_VALUE);
		
		board[0] = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken()); // 비용
			int people = Integer.parseInt(st.nextToken()); // 고객 수
			
			for(int j = people; j < C + 100; j++) {
				if(board[j - people] != Integer.MAX_VALUE) {
					board[j] = Math.min(board[j], cost + board[j - people]);
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i = C; i < C+100; i++) {
			answer = Math.min(answer, board[i]);
		}
		
		System.out.println(answer);
	}
}