import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> bigNumbers = new PriorityQueue<Integer>(); // 최소힙
		PriorityQueue<Integer> smallNumbers = new PriorityQueue<Integer>(Collections.reverseOrder()); // 최대힙

		smallNumbers.add(k);
		sb.append(k).append("\n");

		while(--n > 0){
			k = Integer.parseInt(br.readLine());

			if(smallNumbers.size() == bigNumbers.size()) { // 홀수인 경우 smallNumbers 에 하나 더 담음
				if (bigNumbers.peek() < k) {
					smallNumbers.add(bigNumbers.poll());
					bigNumbers.add(k);
				}
				else smallNumbers.add(k);
			}
			else if(smallNumbers.size() > bigNumbers.size()){ // 짝수인 경우 나눠서 담음
				if(smallNumbers.peek() > k){
					bigNumbers.add(smallNumbers.poll());
					smallNumbers.add(k);
				}
				else bigNumbers.add(k);
			}

			sb.append(smallNumbers.peek()).append("\n");
		}
		System.out.println(sb);
	}
}