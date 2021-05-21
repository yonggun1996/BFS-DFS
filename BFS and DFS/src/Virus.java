import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Virus {
	
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	static boolean[] visit;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int computer = in.nextInt();
		int network = in.nextInt();
		visit = new boolean[computer + 1];
		
		for(int i = 1; i <= computer; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			map.put(i, list);
		}
		
		for(int i = 0; i < network; i++) {
			int num1 = in.nextInt();
			int num2 = in.nextInt();
			
			ArrayList<Integer> list1 = map.get(num1);
			list1.add(num2);
			map.put(num1, list1);
			
			ArrayList<Integer> list2 = map.get(num2);
			list2.add(num1);
			map.put(num2, list2);
		}
		
		queue.offer(1);
		ArrayList<Integer> answer_list = new ArrayList<Integer>();
		
		while(!queue.isEmpty()) {
			int key = queue.poll();
			if(!visit[key]) {
				answer_list.add(key);
				visit[key] = true;
				
				ArrayList<Integer> list = map.get(key);
				for(int i = 0; i < list.size(); i++) {
					int num = list.get(i);
					if(!visit[num]) {
						queue.offer(num);
					}
				}
			}
		}
		
		System.out.println(answer_list.size() - 1);
		
	}

}
