import java.util.Scanner;

public class Main {

	static int n, m;
	static int result[];
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		result = new int[m];
		
		comb(0, 1);
	}
	
	public static void comb(int cnt, int start) {
		
		if(cnt == m) {
			for(int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i <= n; i++) {
			result[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
}
