import java.util.*;
import java.io.*;

public class Main {

	static int N; // 삼각형 크기
	static List<Integer> list = new ArrayList<>(); // 일차원 배열 사용
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		list.add(0); // 1번 인덱스부터 유효값 저장
		for(int i = 1; i <= N ; i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= i; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int index = list.size(); // 마지막 인덱스 + 1
		
		for(int i = N; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				index--;
				if(i != N) {
					int updateNum = list.get(index) + Math.max(list.get(index + i), list.get(index + i + 1));
					list.set(index, updateNum);
	
				}
			}
		}
        
		System.out.println(list.get(1));
	}
}
