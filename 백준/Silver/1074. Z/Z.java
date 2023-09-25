import java.io.*;
import java.util.*;

public class Main {

	static int N, r, c;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		makeSpace(r, c, 1 << N); // 행, 렬, 길이
		
		System.out.println(result);
	}
	// 3 1 4
	
	public static void makeSpace(int x, int y, int size) {
		
		if(size == 2) { // 한변의 길이 2
			if(x == 0 && y == 0);
			else if(x == 0 && y == 1) result += 1;
			else if(x == 1 && y == 0) result += 2;
			else if(x == 1 && y == 1) result += 3;
			
			return;
		}
		
		if(size/2 > x) {
			if(size/2 > y) { // 1사분면
				makeSpace(x, y, size/2);
			}
			else { // 2사분면
				result += ((size/2) * (size/2) * 1);  
				makeSpace(x, y-size/2, size/2);
			}
		}
		else {  // 2 > 3
			if(size/2 > y) { // 3사분면  
				result += ((size/2) * (size/2) * 2);
				makeSpace(x-size/2, y, size/2);
			}
			else { // 4사분면
				result += ((size/2) * (size/2) * 3);
				makeSpace(x-size/2, y-size/2, size/2);
			}
		}
	}
}