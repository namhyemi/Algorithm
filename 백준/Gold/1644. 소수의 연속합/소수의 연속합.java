import java.io.*;
import java.util.*;

public class Main {

	
	static int MAX = 4000005;
	static boolean prime[] = new boolean[MAX];

	static List<Integer> check = new ArrayList<>();
	
	public static void prim() {

		prime[0] = true;
		prime[1] = true;
		
		int tmp = 0;
		for(int i = 2; i < MAX; i++) {
			if(prime[i]) continue;
			
			check.add(i);
			
			for(int j = i+i; j < MAX; j += i) {
				prime[j] = true;
			}
		}
		
		check.add(MAX);
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		prim();
		
		int sum = check.get(0); 
		int start = 0, end = 1;
		
		int answer = 0;
		while(true) {
			if(sum == n) answer++;
			
			if(end <= start) break;
			
			if(sum < n) {
				sum += check.get(end++);
			}
			else sum -= check.get(start++);
		}
		
		System.out.println(answer);
	}
}
