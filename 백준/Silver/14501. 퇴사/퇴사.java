import java.io.*;
import java.util.*;

public class Main {

	static int board[][];
	static int result[];
	
	public static void main(String[] args) throws Exception  {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		board = new int[num+2][2];
		result = new int[num+2];
		
		for(int i = 1; i <= num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			board[i][0] = Integer.parseInt(st.nextToken()); // 상담 소요 날짜
			board[i][1] = Integer.parseInt(st.nextToken()); // 상담 금액
		}
		
		
		int max_result = 0;
		
		for(int i = num; i > 0; i--) { // 마지막 날 스케줄부터 체크 
			if(num - i + 1 >= board[i][0]) { // 상담 걸리는 시간이 마지막 날 넘어가는지 체크
				if(i + board[i][0] <= num) { 
					result[i] = board[i][1] + result[i + board[i][0]]; 
				}
				else {
					result[i] = board[i][1];
				}
			}
			
			 if(result[i] < result[i+1]) {
				 result[i] = result[i+1];
			 }

		}
			
		for(int i :  result) {
			if(max_result < i) max_result = i;
		}
	
		System.out.println(max_result);
	}
}