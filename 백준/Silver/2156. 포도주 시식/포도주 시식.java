import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int wine[], choose[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 데이터 저장
		N = Integer.parseInt(br.readLine());
		wine = new int[N+1]; 
		for(int i = 1; i < N+1; i++) { // 와인 0~N-1
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		if(N >= 3) {
			// 와인 선택
			choose = new int[N+1]; 
			choose[0] = 0; // 첫번째 와인[0] 선택
			choose[1] = wine[1]; // 두번째 와인[1] 선택
			choose[2] = wine[2] + wine[1]; // 두번째 와인[1] 선택
			for(int i = 3; i <= N; i++) { // 세번째 와인[3]부터 마지막 와인[N-1]까지 선택해보기
				choose[i] = Math.max(choose[i-1], Math.max(choose[i-3] + wine[i-1] + wine[i], choose[i-2] + wine[i]));
			}
			System.out.println(choose[N]);
		}
		else if(N == 2) {
			System.out.println(wine[1] + wine[2]);
		}
		else if(N == 1) {
			System.out.println(wine[1]);
		}
	}
}