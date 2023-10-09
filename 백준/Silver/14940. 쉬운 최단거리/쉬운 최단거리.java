import java.io.*;
import java.util.*;

public class Main {
	
	static int temp;
	static int n, m;
	static int board[][];
	
	static Deque<int[]> check = new ArrayDeque<>();
	
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n+2][m+2];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				temp = Integer.parseInt(st.nextToken());
				
				if(temp == 1) {
					board[i][j] = -1;
				}
				else if(temp == 2) {
					board[i][j] = 0;
					check.add(new int[] {i,j}); // push
				}
				else if(temp == 0) {
					board[i][j] = 0;
				}
			}
		}
		
		int mx, my = 0;
		while(!check.isEmpty()) {
			
			int[] a = check.getFirst(); 
			check.removeFirst(); // pop
						
			for(int i = 0; i < 4; i++) {
				mx = a[0] + dx[i];
				my = a[1] + dy[i];
				
				if(mx < 0 || my < 0 || mx >= n || my >= m) continue; // 범위
				if(board[mx][my] >= 0) continue; // 이미 방분
				
				check.addLast(new int[] {mx, my});
				board[mx][my] = board[a[0]][a[1]]+1;
			}	
		}
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				bw.write(board[i][j] + " ");
			}
			bw.newLine();
		}
		bw.flush();
	}
}