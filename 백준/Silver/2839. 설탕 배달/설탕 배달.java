import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		int kg_3 = 0;
		int kg_5 = 0;


		while(T - (kg_3 * 3) >= 0) {
			int remain = T - (kg_3 * 3);
			if(remain % 5 == 0) {
				kg_5 = remain / 5;
				System.out.println(kg_5 + kg_3);
				return;
			}
			else kg_3++;
		}
		
		System.out.println(-1);
	}
}