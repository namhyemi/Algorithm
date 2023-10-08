import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int board[][];
	static int n,m;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			board = new int[n+2][n+2];
			
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken()) + board[i][j-1] + board[i-1][j] - board[i-1][j-1];
				}
			}
			
			int max = 0;
			
			for(int i = 0; i < n-m+1; i++) {
				for(int j = 0; j < n-m+1; j++) {
					if(max < board[i+m][j+m] - board[i][j+m] - board[i+m][j] + board[i][j]) {
						max = board[i+m][j+m] - board[i][j+m] - board[i+m][j] + board[i][j];
					}
				}
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}
}