import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String startText = br.readLine(); // 텍스트 입력
		int cnt = Integer.parseInt(br.readLine()); // 횟수 입력
		
		LinkedList<Character> edit = new LinkedList<>(); 
		
		for(int i = 0; i < startText.length(); i++) {
			edit.add(startText.charAt(i));
		}
	
		ListIterator<Character> iter = edit.listIterator();
		
		while(iter.hasNext()) { // 커서를 맨뒤로 이동시키기
			iter.next();
		}
		
		for(int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			
			if(c == 'P') {
				char addText = st.nextToken().charAt(0);
				
				iter.add(addText);
			}
			else if(c == 'L'){ 
				if(iter.hasPrevious()) iter.previous();

			}
			else if(c == 'D') {
				if(iter.hasNext()) iter.next();

			}
			else if(c == 'B') {
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
			}
		}
		
		for(Character c : edit) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}