import java.io.*;
import java.util.*;

public class Main {

	static int board[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int startHeight = Integer.parseInt(st.nextToken());
		
		board = new int[N];
	
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(board);

		int H = startHeight;
		for(int i = 0; i < N; i++) {
			if(board[i] <= H) H++;
			else break;
		}
		
		System.out.println(H);
	}

}