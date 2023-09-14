import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] board;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int switch_num = Integer.parseInt(br.readLine());
		
		board = new int[switch_num + 5];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= switch_num; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		

		int student_num = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= student_num; i++) {
			StringTokenizer student_info = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(student_info.nextToken());
			int pos = Integer.parseInt(student_info.nextToken());
			
			if(gender == 1) {
				for(int j = pos; j <= switch_num; j += pos) {
					if(board[j] == 0) board[j] = 1;
					else if(board[j] == 1) board[j] = 0;
				}
			}
			else if(gender == 2){
				int x = 0;
				while(true) {
					int right = pos + x;
					int left = pos - x;
					
					if(left < 1 || right > switch_num) break;
					if(board[left] != board[right]) break;
					
					if(board[left] == 0) {
						board[left] = 1;
						board[right] = 1;
					}
					else if(board[left] == 1) {
						board[left] = 0;
						board[right] = 0;
					}
					x++;
				}
			}
		}
		
		for(int i = 1; i <= switch_num; i++) {
			System.out.print(board[i] + " ");
			if(i % 20 == 0) System.out.println();
		}
	}
}