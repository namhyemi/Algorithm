import java.util.Scanner;

public class Main {
	
	static int n, m;
	static int result[];
	static boolean check[] = new boolean[10];
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();

		result = new int[m];
		check = new boolean[n+1];
		
		per(0);
		
		
	}
	
	public static void per(int cnt) {
		if(cnt == m) {
			for(int i  = 0 ; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(check[i]) continue;

			check[i] = true;
			result[cnt] = i;
			per(cnt + 1);
			check[i] = false;
		}
	}

}