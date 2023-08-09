import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				}
				else
					return Math.abs(o1) - Math.abs(o2);
			}
		});
		
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(bw.readLine());
		
		for(int i = 0; i < n; i++) {
			int k = Integer.parseInt(bw.readLine());
			
			if(k == 0) {
				if(pq.isEmpty()) sb.append(0 + "\n");
				else sb.append(pq.poll() + "\n");
			}
			else {
				pq.offer(k);
			}
		}

		System.out.println(sb);
	}
}