import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. summary 
	1 번~N번 집이 있다. 각 집에 색을 칠한다. (빨강, 초록, 파랑)
	다만, 양 옆과 같은 색으로 칠하면 안된다.
	각 집은 칠하는 색깔마다 비용이 다르다 
	모든 집을 칠하는 비용의 최소값을 구하자
2. strategy : DP
	현재 세가지 색깔을 칠할때, 이전 집의 겹치지 않는 두가지 색중에 더 저렴한 색깔을 선택해서 저장
3. note 
	2 ≤ N ≤ 1,000
*/
public class Main {

	static int N;
	static int house[][], houseMax[][];
	
	static int Sum = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		house = new int[N][3]; // (집 : 0 ~ N-1)
		
		// 데이터 입력받기
		int check[] = new int[3];
		for(int i = 0; i < N; i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			house[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			house[i][1] = Integer.parseInt(st.nextToken()); // 초록
			house[i][2] = Integer.parseInt(st.nextToken()); // 파랑

		}
	
		houseMax = new int[N][3];
		for(int i = 1; i < N; i++) {
			// house[i][0] : i번째 집에 빨강색
			if(house[i-1][1] > house[i-1][2]) { // 이전 집 값 비교 : 초록보다 파랑색이 더 작음
				house[i][0] += house[i-1][2];
			} else { // 이전 집 값 비교 : 파랑보다 초록색이 더 작음
				house[i][0] += house[i-1][1];
			}
			
			// house[i][1] : i번째 집에 초록색
			if(house[i-1][0] > house[i-1][2]) {  // 이전 집 값 비교 : 빨강보다 파랑색이 더 작음
				house[i][1] += house[i-1][2];
			} else { // 이전 집 값 비교 : 파랑보다 빨강색이 더 작음
				house[i][1] += house[i-1][0];
			}
			
			// house[i][2] : i번째 집에 파랑색
			if(house[i-1][0] > house[i-1][1]) { // 이전 집 값 비교 : 빨강보다 초록색이 더 작음
				house[i][2] += house[i-1][1];
			} else {  // 이전 집 값 비교 : 초록보다 빨강색이 더 작음
				house[i][2] += house[i-1][0];
			}
		}
		
		Arrays.sort(house[N-1]);

		System.out.println(house[N-1][0]);
	}
}