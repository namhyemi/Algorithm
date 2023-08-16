import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int board[][];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}

		makeSpace(0,0,N);
		
		System.out.println(sb);
	}
	
	public static void makeSpace(int r, int c, int size) {
		
		int sum = 0;
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				sum += board[i][j];
			}
		}
		
		if(sum == 0) {sb.append(0); return;}
		else if(sum == size*size) {sb.append(1); return;}
		
		
		if(size == 1) {
			sb.append(board[r][c]);
			return;
		}
		else {
			sb.append('(');
			int k = size/2;
			makeSpace(r,c,k);
			makeSpace(r,c+k,k);
			makeSpace(r+k,c,k);
			makeSpace(r+k,c+k,k);
			sb.append(')');
		}
	}
}