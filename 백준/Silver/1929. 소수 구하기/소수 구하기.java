import java.io.*;
import java.util.*;

public class Main {

	static int MAX = 1000005;
	static boolean[] prime = new boolean[MAX];
	
	public static void prim() {
		
		prime[0] = true; 
		prime[1] = true; 
		
		for(int i=2; i < MAX; i++) {
			if(prime[i]) continue;
			for(int j = i+i; j < MAX; j+=i) {
				prime[j] = true;
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int startNum = Integer.parseInt(st.nextToken());
		int endNum = Integer.parseInt(st.nextToken());

		prim();
		

		for(int i = startNum; i <= endNum; i++) {
			if(prime[i]) continue;
			
			bw.write(i + "\n");
		}
		
		bw.flush();
	}
}
