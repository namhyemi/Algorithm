import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Deque<Integer> deq = new ArrayDeque<>();
		
		int n = Integer.parseInt(br.readLine());
		
		int i = 1;
		while(i <= n) {
			deq.addLast(i++);
		}
		
		while(deq.size() > 1) {
			deq.pop();

			deq.addLast(deq.peek());
			deq.pop();
		}
		
		System.out.println(deq.peek());
	}

}
