import java.util.*;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		
		int T = 10;
		for(int tc = 1; tc <= T; tc++) {
			List<Integer> codeList = new LinkedList<>();
			
			int N = Integer.parseInt(br.readLine()); // 암호 개수
			st = new StringTokenizer(br.readLine()); // 암호 뭉치
			for(int i = 0; i < N; i++) { 
				int code = Integer.parseInt(st.nextToken());
				codeList.add(code);
			}
			
			int M = Integer.parseInt(br.readLine()); // 변경 횟수
			st = new StringTokenizer(br.readLine()); // 변경 내역
			for(int i = 0; i < M; i++) { 
				String command = st.nextToken();
				
				if(command.equals("I")) { // 삽입
					int X = Integer.parseInt(st.nextToken());
					int Y = Integer.parseInt(st.nextToken());
					for(int y = 0; y < Y; y++) {
						int code = Integer.parseInt(st.nextToken());
						codeList.add(X+y, code);
					}
				}
				else if(command.equals("D")){ // 삭제
					int X = Integer.parseInt(st.nextToken());
					int Y = Integer.parseInt(st.nextToken());
					for(int y = 0; y < Y; y++) {
						codeList.remove(X);
					}
				}
				else if(command.equals("A")){ // 추가
					int Y = Integer.parseInt(st.nextToken()); 
					for(int y = 0; y < Y; y++) {
						int code = Integer.parseInt(st.nextToken());
						codeList.add(code);
					}
				}
				else;
			}
			
			sb.append("#" + tc + " ");
			for(int i = 0; i < 10; i++) {
				sb.append(codeList.get(i) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}