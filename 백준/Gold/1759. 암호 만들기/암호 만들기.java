import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L, C, code[];
	static boolean check[] = new boolean[26];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			int num = st.nextToken().charAt(0) - 'a';
			check[num] = true; // 사용 가능한 알파벳
		}
	
		code = new int[L];
		Com(0, 0);

		System.out.println(sb);
		
		br.close();
	}

	public static void Com(int cnt, int start) {
		if(cnt == L) {
			int v = 0; // 모음
			int c = 0; // 자음
			String string = "";
			for(int i = 0; i < L; i++) {
				char alphabet = (char)(code[i] + 'a');
				if(alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') { v++; }
				else { c++; }
				
				string += alphabet;
			}
			
			if(v >= 1 && c >= 2) sb.append(string + "\n");
			
			return;
		}
		
		for(int i = start; i < check.length; i++) {
			if(!check[i]) continue; // 사용불가능한(입력받지 않은) 알파벳
			code[cnt] = i;
			Com(cnt + 1, i + 1);
		}
	}
}