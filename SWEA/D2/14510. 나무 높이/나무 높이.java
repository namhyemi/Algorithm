import java.util.*;
import java.io.*;

/*
1. summary : 모든 나무의 처음에 가장 컸던 나무와 같아지는 최소 날짜 구하기
	- N개의 나무
	- 물주기
		하루에 한번 준다.
		물을 주면 나무는 자란다. (짝수번째 날은 2 자라고, 홀수번째 날은 1자란다. 1 2 1 2 1 2 1 2 1 2 1 2)
		물을 주지 않을 수도 있다.
2. strategy
	- 짝수 날짜인 경우
	 	2증가 가능한 나무 있는 경우만 물 주기
	- 홀수 날짜인 경우 
		1증가 가능한 나무 있는 경우 물 주기
		1증가 가능한 나무가 없다면 2증가 가능한 나무가 마지막이 아닌 경우 물 주기 (2증가 나무가 하나인 경우는 기다렸다가 짝수날에 물주는게 더 최소) 
3. note
	2 ≤ N ≤ 100, 1 ≤ h (초기 나무 높이) ≤ 120
*/

public class Solution {

	static int N;
	static int board[];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for(int tc = 1; tc <= T; tc++) {
			
			// 데이터 입력받기
			N = Integer.parseInt(br.readLine());
			board = new int[N];
			
			int maxHeight = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				board[i] = Integer.parseInt(st.nextToken());
				
				maxHeight = Math.max(board[i], maxHeight);
			}
			
			int oddDay = 0;
			int evenDay = 0;
			for(int i = 0; i < N; i++) { 
				board[i] = maxHeight - board[i]; // 필요한 키 차이 

				evenDay += board[i] / 2;
				oddDay += board[i] % 2;
			}
			
			int day = 0;
			while(true) {
				if(evenDay == 0 && oddDay == 0) break; // 나무 모두 성장 종료
				
				day++; // 하루 지남
				
				if(day % 2 == 0) { // 짝수날 (2 증가)
					if(evenDay > 0) { // 2 증가 가능한 나무 있으면 물 주기 
						evenDay--;
					}
				}
				else { // 홀수날 (1증가)
					if(oddDay > 0) { // 1 증가 가능한 나무 있으면 물 주기
						oddDay--;
					}
					else { // 1 증가 가능한 나무 없다면
						if(evenDay > 1) { // 
							evenDay--;
							oddDay++;	
						}
					}
				}
			}
			
			int totalDay = day;
		
			sb.append("#").append(tc).append(" ").append(totalDay).append("\n");
		}
		
		System.out.println(sb);
	}
}