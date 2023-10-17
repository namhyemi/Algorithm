import java.io.*;
import java.util.*;

/*
1. summary 
	N개의 도시로 이루어진 나라
	각 도시는 M개의 도로로 연결되어 있음 (각 도로 지나는 시간)
	도로의 개수를 최소로 했을 때, 모든 도로 시간의 합 구하기
	(불가능한 경우 -1 출력)
2. strategy
	주어진 정보는 플로이드 워샬이 이미 진행된 그래프(인접행렬)다.
	- 플로이드 워샬 진행 시 업데이트 된다면, 도로의 개수가 최소가 아니게 된다.
    - 플로이드 워샬 진행 시 갱신 값이랑 같은 경우에는 해당 경유 도로를 사용한 것음으로 계산에서 제외한다.
3. note
	1 ≤ N(도시 수) ≤ 20
	1 ≤ 시간 ≤ 2500
*/

public class Main {

	static int N;
	static int board[][], originalBoard[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력 데이터 저장
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		originalBoard = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				originalBoard[i][j] = Integer.parseInt(st.nextToken());
				
				board[i][j] = originalBoard[i][j];
			}
		}
		
		boolean flag = false;
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				if(i == k) continue;
				
				for(int j = 0; j < N; j++) {
					if(i == j || k == j) continue;
					
					if(board[i][j] > board[i][k] + board[k][j]) {
						flag = true;
					}
					else if(board[i][j] == board[i][k] + board[k][j]) {
						// 돌아오는 경우 (이미 있는 다리를 이용한 경우)
						originalBoard[i][j] = 0;
					}
					if(flag) break;
				}
				if(flag) break;
			}
			if(flag) break;
		}
		
		if(flag) System.out.println(-1);
		else {
			int sum = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = i; j < N; j++) {
					sum += originalBoard[i][j];
				}
			}
			System.out.println(sum);
		}
	}
}
